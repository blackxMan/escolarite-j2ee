package org.escolarite.session.backend;

import java.util.Arrays;
import java.util.List;

import org.escolarite.database.persistance.entities.Reclamation;
import org.escolarite.database.persistance.entities.Request;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;
import org.jboss.seam.log.Log;

@Name("notificationListReclamation")
@Scope(ScopeType.EVENT)
public class NotificationListReclamation extends EntityQuery<Reclamation> {
	
	@Logger
	Log log;

	private static final String EJBQL = "select reclamation from Reclamation reclamation where reclamation.notified = 0";
	
	

	public List getRequests() {
		log.info("selec * from requestssssssssssssssssssssssss");
		List requests;
		requests = this.getEntityManager().createQuery("from Request r where r.notified = 0").getResultList();
		return requests;
	}


	private static final String[] RESTRICTIONS = {
			"lower(reclamation.description) like lower(concat(#{reclamationList.reclamation.description},'%'))",
			"lower(reclamation.responce) like lower(concat(#{reclamationList.reclamation.responce},'%'))",
			"lower(reclamation.subject) like lower(concat(#{reclamationList.reclamation.subject},'%'))", };

	private Reclamation reclamation = new Reclamation();

	public NotificationListReclamation() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public Reclamation getReclamation() {
		return reclamation;
	}
}
