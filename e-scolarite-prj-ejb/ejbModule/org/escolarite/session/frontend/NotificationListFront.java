package org.escolarite.session.frontend;

import org.escolarite.database.persistance.entities.Reclamation;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("notificationListFront")
public class NotificationListFront extends EntityQuery<Reclamation> {

	private static final String EJBQL = "select reclamation from Reclamation reclamation where reclamation.notified = 2 and reclamation.code = '1'";

	private static final String[] RESTRICTIONS = {
		"lower(reclamation.description) like lower(concat(#{reclamationList.reclamation.description},'%'))",
		"lower(reclamation.responce) like lower(concat(#{reclamationList.reclamation.responce},'%'))",
		"lower(reclamation.subject) like lower(concat(#{reclamationList.reclamation.subject},'%'))", };

	private Reclamation reclamation = new Reclamation();

	public NotificationListFront() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public Reclamation getReclamation() {
		return reclamation;
	}
}
