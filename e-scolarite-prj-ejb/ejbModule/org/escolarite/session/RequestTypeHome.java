package org.escolarite.session;

import org.escolarite.database.persistance.entities.RequestType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("requestTypeHome")
public class RequestTypeHome extends EntityHome<RequestType> {

	public void setRequestTypeId(Long id) {
		setId(id);
	}

	public Long getRequestTypeId() {
		return (Long) getId();
	}

	@Override
	protected RequestType createInstance() {
		RequestType requestType = new RequestType();
		return requestType;
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

	public RequestType getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
