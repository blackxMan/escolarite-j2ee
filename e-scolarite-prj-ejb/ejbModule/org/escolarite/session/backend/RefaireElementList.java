package org.escolarite.session.backend;

import org.escolarite.database.persistance.entities.Request;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("refaireElementList")
public class RefaireElementList extends EntityQuery<Request> {

	private static final String EJBQL = "select request from Request request";

	private static final String[] RESTRICTIONS = {
			"lower(request.code) like lower(concat(#{requestList.request.code},'%'))",
			"lower(request.notice) like lower(concat(#{requetsList.request.notice},'%'))", };

	private Request request = new Request();

	public RefaireElementList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public Request getRequests() {
		return request;
	}
}
