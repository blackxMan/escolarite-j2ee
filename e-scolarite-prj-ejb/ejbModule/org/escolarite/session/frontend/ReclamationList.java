package org.escolarite.session.frontend;

import org.escolarite.database.persistance.entities.Reclamation;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import org.jboss.seam.log.Log;

import java.util.Arrays;
import java.util.List;

@Name("reclamationList")
public class ReclamationList extends EntityQuery<Reclamation> {
	
	@Logger
	Log log;
	
	private List<Reclamation> reclamations;

	private static final String EJBQL = "select reclamation from Reclamation reclamation";

	private static final String[] RESTRICTIONS = {
			"lower(reclamation.description) like lower(concat(#{reclamationList.reclamation.description},'%'))",
			"lower(reclamation.responce) like lower(concat(#{reclamationList.reclamation.responce},'%'))",
			"lower(reclamation.subject) like lower(concat(#{reclamationList.reclamation.subject},'%'))",
			"reclamation.code like #{etudiant.code}",
			};

	private Reclamation reclamation = new Reclamation();

	public ReclamationList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
		
	}

	public Reclamation getReclamation() {
		
		return reclamation;
	}
	
//	public List<Reclamation> getReclamations(){
//		String req = "select reclamation from Reclamation reclamation where reclamation.code = :code";
//		this.setEjbql(req);
//		this.createQuery().setParameter("code", etudiant.getCode());
//		this.reclamations = this.getResultList();
//		return this.reclamations;
//	}
}
