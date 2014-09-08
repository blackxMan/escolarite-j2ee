package org.escolarite.session.backend;

import org.escolarite.database.persistance.entities.Admin;
import org.escolarite.database.persistance.entities.RequestState;
import java.util.ArrayList;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityHome;

@Name("adminHome")
@Scope(ScopeType.EVENT)
public class AdminHome extends EntityHome<Admin> {

	public void setAdminId(Long id) {
		setId(id);
	}

	public Long getAdminId() {
		return (Long) getId();
	}

	@Override
	protected Admin createInstance() {
		Admin admin = new Admin();
		return admin;
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

	public Admin getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<RequestState> getRequeststates() {
		return getInstance() == null ? null : new ArrayList<RequestState>(
				getInstance().getRequestStates());
	}
	

}
