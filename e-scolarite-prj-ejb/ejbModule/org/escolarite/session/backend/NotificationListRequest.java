package org.escolarite.session.backend;

import java.util.Arrays;

import org.escolarite.database.persistance.entities.Request;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;
import org.jboss.seam.log.Log;

@Name("notificationListRequest")
@Scope(ScopeType.EVENT)
public class NotificationListRequest extends EntityQuery<Request> {
	
	@Logger
	Log log;

	private static final String EJBQL = "select request from Request request where request.notified = 0";

	private static final String[] RESTRICTIONS = {
		"lower(request.code) like lower(concat(#{notificationListRequest.request.code},'%'))",
		"lower(request.notice) like lower(concat(#{notificationListRequest.request.notice},'%'))", };

	private Request request = new Request();

	public NotificationListRequest() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public Request getRequest() {
		return request;
	}
}
