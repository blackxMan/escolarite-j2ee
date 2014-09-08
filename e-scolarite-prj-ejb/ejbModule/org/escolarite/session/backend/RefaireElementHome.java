package org.escolarite.session.backend;

import java.util.Date;

import org.escolarite.database.persistance.entities.Admin;
import org.escolarite.database.persistance.entities.Request;
import org.escolarite.database.persistance.entities.RequestState;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.framework.EntityHome;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Credentials;

@Name("refaireElementHome")
public class RefaireElementHome extends EntityHome<Request> {
	
	@In
	Credentials credentials;
	
	@Logger
	Log log;

	public void setRequestId(Long id) {
		setId(id);
	}

	public Long getRequestId() {
		return (Long) getId();
	}

	@Override
	protected Request createInstance() {
		Request requests = new Request();
		return requests;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public Request getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}
	
	public String accepter(){
		resultat(Request.ACCEPTED);
		return "result";
	}
	public String refuser(){
		resultat(Request.REJECTED);
		return "result";
	}
	public void vueParAdmin(){
		instance.setNotified(Request.HIDE_ADMIN_NOTIFICATION);
		this.persist();
		FacesMessages.instance().clear();
	}
	public void resultat(short i){			
		instance.setUpdatedAt(new Date());
		instance.setStatus(i);
		if(instance.getNotice().equals(""))instance.setNotice(null);
		instance.setNotified(Request.SHOW_STUDENT_NOTIFICATION);
		RequestState rs = new RequestState();
		Admin a = (Admin) this.getEntityManager().createQuery("from Admin a where a.email = :e").setParameter("e", this.credentials.getUsername()).getSingleResult();
		rs.setAdmin(a);
		rs.setCreatedAt(new Date());
		rs.setRequest(instance);
		this.persist();
		this.getEntityManager().persist(rs);
		this.getEntityManager().flush();
		FacesMessages.instance().clear();
		
	}

}
