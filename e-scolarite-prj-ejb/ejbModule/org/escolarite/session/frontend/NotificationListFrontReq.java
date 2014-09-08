package org.escolarite.session.frontend;

import org.escolarite.database.persistance.entities.Request;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;
import java.util.List;

@Name("notificationListFrontReq")
@Scope(ScopeType.EVENT)
public class NotificationListFrontReq extends EntityQuery<Request> {

	private static final String EJBQL = "select request from Request request where request.notified = 2 and request.code like '1'";

	private static final String[] RESTRICTIONS = {
		"lower(request.code) like lower(concat(#{notificationListRequest.request.code},'%'))",
		"lower(request.notice) like lower(concat(#{notificationListRequest.request.notice},'%'))", };

	private Request request = new Request();	

	public NotificationListFrontReq() {
		setEjbql(EJBQL);
		//setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public Request getRequest() {
		return request;
	}
}
