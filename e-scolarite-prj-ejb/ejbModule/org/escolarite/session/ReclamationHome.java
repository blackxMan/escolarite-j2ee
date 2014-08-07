package org.escolarite.session;

import org.escolarite.database.persistance.entities.Reclamation;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("reclamationHome")
public class ReclamationHome extends EntityHome<Reclamation> {

	public void setReclamationId(Long id) {
		setId(id);
	}

	public Long getReclamationId() {
		return (Long) getId();
	}

	@Override
	protected Reclamation createInstance() {
		Reclamation reclamation = new Reclamation();
		return reclamation;
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

	public Reclamation getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
