package org.escolarite.session.backend;

import org.escolarite.database.persistance.entities.RequestType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("requestTypeList")
public class RequestTypeList extends EntityQuery<RequestType> {

	private static final String EJBQL = "select requestType from RequestType requestType";

	private static final String[] RESTRICTIONS = {
			"lower(requestType.code) like lower(concat(#{requestTypeList.requestType.code},'%'))",
			"lower(requestType.title) like lower(concat(#{requestTypeList.requestType.title},'%'))", };

	private RequestType requestType = new RequestType();

	public RequestTypeList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public RequestType getRequestType() {
		return requestType;
	}
}
