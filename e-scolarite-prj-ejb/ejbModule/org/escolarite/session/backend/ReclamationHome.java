package org.escolarite.session.backend;

import java.util.Date;

import org.escolarite.database.persistance.entities.Reclamation;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.framework.EntityHome;
import org.jboss.seam.log.Log;

@Name("reclamationHomeBackEnd")
public class ReclamationHome extends EntityHome<Reclamation> {
	@Logger
	private Log log;
	

	public void setReclamationId(Long id) {
		log.info("setReclamationId : "+ id);
		setId(id);	
	}
	
	public Long getReclamationId() {
		log.info("getReclamationId : ");
		return (Long) getId();
	}

	@Override
	protected Reclamation createInstance() {
		Reclamation reclamation = new Reclamation();
		return reclamation;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		log.info("getReclamationId : ");
		getInstance();
	}
	
	public void vueAdmin(){
		instance.setNotified(Reclamation.HIDE_ADMIN_NOTIFICATION);
	}

	public boolean isWired() {
		return true;
	}

	public Reclamation getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}
	
	public String repondre(){
		if(instance.getResponce() == null){ FacesMessages.instance().add("Veuillez remplir le champ réponse"); return null;}
		instance.setConsultedAt(new Date());
		instance.setStatus(Reclamation.ACCEPTED);
		return this.update();		
	}
	
	
	

}
