package org.escolarite.facades;

import org.escolarite.database.persistance.dao.AbstractDAO;

public interface EscolariteFacadeInterface {
	
	public AbstractDAO<?> getEntityDAO();
	
	public Class<?> getEntityClass();
	
}
