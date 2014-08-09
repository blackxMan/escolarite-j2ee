package org.escolarite.session.frontend;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.escolarite.database.persistance.entities.Reclamation;
import org.escolarite.database.persistance.entities.ReclamationType;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.framework.EntityHome;
import org.jboss.seam.log.Log;

@Name("reclamationHome")
public class ReclamationHome extends EntityHome<Reclamation> {
	@Logger
	private Log log;
	private boolean allowed = true;

	public void setReclamationId(Long id) {
		setId(id);	
	}
	
	public Long getReclamationId() {		
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
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public Reclamation getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	@SuppressWarnings("unchecked")
	@Factory(value="reclamationT", scope=ScopeType.EVENT)
	public List<ReclamationType> getCountriesList() {
		
		return (this.getEntityManager().createQuery("from ReclamationType")).getResultList();
	}
	
	public String ajouterReclamation(){				
		if(verifier())return persist();
		FacesMessages.instance().add("Vous avez envoyé le nombre maximum permis dans ce type");
		return null;
	}
	
	public String modifierReclamation(){
		if(verifier())return update();
		FacesMessages.instance().add("Vous avez envoyé le nombre maximum permis dans ce type");
		return null;
	}
	public boolean verifier(){
		Calendar debutSaison = Calendar.getInstance();
		int thisMonth = debutSaison.get(Calendar.MONTH);
		
		debutSaison.set(Calendar.MONTH, 8);
		debutSaison.set(Calendar.DAY_OF_MONTH, 1);
		if(thisMonth <= 6 ){
			debutSaison.add(Calendar.YEAR, -1);
		}
		Calendar finSaison = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		finSaison.set(debutSaison.get(Calendar.YEAR)+1, 6, 1);
		Query req = getEntityManager().createQuery("from Reclamation r" +
				" where r.etudiant_id=1 and r.reclamationType = :recType and r.created_at between :date1 and :date2");
		 
		req.setParameter("date1", debutSaison.getTime());
		req.setParameter("date2", finSaison.getTime());
		req.setParameter("recType", instance.getReclamationType());
		List<Reclamation> reclamations = req.getResultList();		
		instance.setEtudiant_id(1);
		
		return instance.getReclamationType().getMax_authorized() > reclamations.size();
		
	}
	

}
