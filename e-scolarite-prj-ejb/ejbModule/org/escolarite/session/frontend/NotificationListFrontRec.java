package org.escolarite.session.frontend;

import org.escolarite.database.persistance.entities.Reclamation;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;
import java.util.List;

@Name("notificationListFrontRec")
@Scope(ScopeType.EVENT)
public class NotificationListFrontRec extends EntityQuery<Reclamation> {

	private static final String EJBQL = "select reclamation from Reclamation reclamation where reclamation.notified = 2 and reclamation.code like '1'";

	private static final String[] RESTRICTIONS = {
		"lower(reclamation.description) like lower(concat(#{reclamationListFront.reclamation.description},'%'))",
		"lower(reclamation.responce) like lower(concat(#{reclamationListFront.reclamation.responce},'%'))",
		"lower(reclamation.subject) like lower(concat(#{reclamationListFront.reclamation.subject},'%'))", };

	private Reclamation reclamation = new Reclamation();	

	public NotificationListFrontRec() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public Reclamation getReclamation() {
		return reclamation;
	}
}
