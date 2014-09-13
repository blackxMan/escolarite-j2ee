package org.escolarite.session.backend;

import java.sql.ResultSet;

import javax.persistence.EntityManager;

import org.escolarite.database.oracle.Connexion;
import org.escolarite.database.persistance.entities.Admin;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.core.Events;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.faces.Redirect;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Credentials;

@Name("authenticator")
public class Authenticator {
	@Logger
	private Log log;

	@In
	Redirect redirect;

	@In
	Connexion connexion;

	private String username = "admin";
	private String password = "admin";

	@In
	CustomIdentity identity;
	@In
	Credentials credentials;
	@In
	EntityManager entityManager;

	public boolean authenticate() {

		String user = credentials.getUsername();
		String pass = credentials.getPassword();

		try {

			if (user.equals(this.username) && pass.equals(this.password)) {
				identity.addRole("superAdmin");

				return true;
			}
			if (user.contains("@")) {
				Admin admin = (Admin) entityManager
						.createQuery(
								"from Admin where email = :username and password = :password")
						.setParameter("username", user)
						.setParameter("password", pass).getSingleResult();
				if (admin != null) {
					if (admin.isExpired()) {
						Events.instance().raiseEvent("invalidLogin",
								"ce compte est verouillé");
						return false;
					}
				}
				identity.addRole("admin");

				return true;
			}

			String req = "select * from individu where cod_ind = " + user
					+ " and cod_nne_ind = '" + pass + "'";

			ResultSet rs = connexion.createQuery(req);

			if (rs.next()) {

				identity.addRole("etudiant");
				identity.getEtudiant().setCne(rs.getString("cod_nne_ind"));
				identity.getEtudiant().setCode(rs.getString("cod_ind"));
				identity.getEtudiant().setNom(rs.getString("LIB_NOM_PAT_IND"));
				identity.getEtudiant().setPrenom(rs.getString("LIB_PR1_IND"));
				// identity.setUsername(rs.getString("LIB_PR1_IND")+" "+rs.getString("LIB_NOM_PAT_IND"));

				rs.close();
				FacesMessages.instance().clear();
				return true;

			} else {
				rs.close();
				Events.instance().raiseEvent("invalidLogin",
						"login ou mot de passe est incorrect");
				return false;
			}

		} catch (Exception ex) {
			Events.instance().raiseEvent("invalidLogin",
					"login ou mot de passe est incorrect");
			return false;
		}
	}

	public String isFrontLog() {
		if (redirect.getViewId() != null
				&& redirect.getViewId().contains("front"))
			return "front";
		// if(redirect.getViewId().contains("/backend/login")){redirect.setViewId(null);}
		 log.info(redirect.getViewId()+" reeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
		return null;

	}

}
