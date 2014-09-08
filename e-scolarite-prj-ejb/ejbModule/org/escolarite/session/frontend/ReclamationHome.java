package org.escolarite.session.frontend;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.jboss.seam.security.AuthorizationException;
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
		log.info("wire : ");
		getInstance();
	}

	public boolean isWired() {
		log.info("isWired : ");
		return true;
	}

	public Reclamation getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	@SuppressWarnings("unchecked")
	@Factory(value = "reclamationT", scope = ScopeType.EVENT)
	public List<ReclamationType> getReclamationTypeList() {

		return (this.getEntityManager().createQuery("from ReclamationType"))
				.getResultList();
	}

	public String ajouterReclamation() {
		if (verifier())
			return persist();
		FacesMessages.instance().add(
				"Vous avez envoyé le nombre maximum permis dans ce type");
		return null;
	}

	public String modifierReclamation() {
		if (verifier())
			return update();
		FacesMessages.instance().add(
				"Vous avez envoyé le nombre maximum permis dans ce type");
		return null;
	}

	public boolean verifier() {
		Calendar debutSaison = Calendar.getInstance();
		int thisMonth = debutSaison.get(Calendar.MONTH);

		debutSaison.set(Calendar.MONTH, 8);
		debutSaison.set(Calendar.DAY_OF_MONTH, 1);
		if (thisMonth <= 6) {
			debutSaison.add(Calendar.YEAR, -1);
		}
		Calendar finSaison = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		finSaison.set(debutSaison.get(Calendar.YEAR) + 1, 6, 1);
		Query req = getEntityManager()
				.createQuery(
						"from Reclamation r"
								+ " where r.code='1' and r.reclamationType = :recType and r.created_at between :date1 and :date2");

		req.setParameter("date1", debutSaison.getTime());
		req.setParameter("date2", finSaison.getTime());
		req.setParameter("recType", instance.getReclamationType());
		List<Reclamation> reclamations = req.getResultList();
		instance.setCode("1");

		return instance.getReclamationType().getMax_authorized() > reclamations
				.size();

	}

	public String repondre() {
		if (instance.getResponce() == null) {
			FacesMessages.instance().add("Veuillez remplir le champ réponse");
			return null;
		}
		instance.setNotified(Reclamation.SHOW_STUDENT_NOTIFICATION);
		instance.setStatus(Reclamation.ACCEPTED);
		return this.update();
	}

	public void vueAdmin() {
		
			log.info("set notified to 1 cad vue par admin");
			instance.setNotified(Reclamation.HIDE_ADMIN_NOTIFICATION);
			instance.setConsultedAt(new Date());
			this.update();
			FacesMessages.instance().clear();
			
		
	}
	public void vueEtudiant() {
		
			log.info("set notified to 3 cad vue par etudiant");
			instance.setNotified(Reclamation.HIDE_STUDENT_NOTIFICATION);			
			this.update();
			FacesMessages.instance().clear();
			
		
	}
	
	public void isItsReclamation() throws AuthorizationException{		
		Reclamation rec = (Reclamation) this.getEntityManager().createQuery("from Reclamation where id = :id and code = '1'").setParameter("id", instance.getId()).getSingleResult();
		if(rec == null)
		throw new AuthorizationException("erreur");
		
	}
	
	public String supprimer(){
		this.wire();
		FacesMessages.instance().clear();
		if(this.instance.getStatus()!=0)
			FacesMessages.instance().add("Reclamation deja validée");
		else this.remove();
		return "removed";
	}
	

}
