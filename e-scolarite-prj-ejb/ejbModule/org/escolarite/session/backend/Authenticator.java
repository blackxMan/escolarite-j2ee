package org.escolarite.session.backend;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.escolarite.database.persistance.entities.Admin;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Credentials;
import org.jboss.seam.security.Identity;

@Name("authenticator")
public class Authenticator {
	@Logger
	private Log log;
	
	private String username = "admin";
	private String password = "admin";

	@In
	Identity identity;
	@In
	Credentials credentials;
	@In
	EntityManager entityManager;

	public boolean authenticate() {

		try {
			
			if(credentials.getUsername().equals(this.username) && credentials.getPassword().equals(this.password)){
				identity.addRole("superAdmin");
				log.info("okkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
				return true;
			}
			Admin admin = (Admin) entityManager
					.createQuery(
							"from Admin where email = :username and password = :password")
					.setParameter("username", credentials.getUsername())
					.setParameter("password", credentials.getPassword())
					.getSingleResult();
			if(admin != null){
				if(!admin.isExpired()){
					FacesMessages.instance().add("ce compte est verouillé");
					return false;
				}
			}
			identity.addRole("admin");

			return true;
		} catch (NoResultException ex) {
			return false;
		}
	}

}
