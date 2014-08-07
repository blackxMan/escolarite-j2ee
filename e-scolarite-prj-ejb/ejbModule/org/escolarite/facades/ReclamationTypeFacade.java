package org.escolarite.facades;

import java.util.List;

import org.escolarite.database.persistance.dao.ReclamationTypeDAO;
import org.escolarite.database.persistance.entities.ReclamationType;
import org.jboss.seam.annotations.In;

public class ReclamationTypeFacade implements EscolariteFacadeInterface{
	@In
	private ReclamationTypeDAO reclamationTypeDAO = null;
	
	public void remove(int id){
		reclamationTypeDAO.remove(id);
	}
	
	public void update(ReclamationType reclamationType){
		reclamationTypeDAO.updateOne(reclamationType);
	}
	
	public void save(ReclamationType reclamationType){
		
		reclamationTypeDAO.save(reclamationType);
	}
	
	
	
	public List<ReclamationType> findAll(){
		return reclamationTypeDAO.findAll();
	}

	@Override
	public ReclamationTypeDAO getEntityDAO() {
		
		return reclamationTypeDAO;
	}

	@Override
	public Class<?> getEntityClass() {
		
		return ReclamationType.class;
	}

}
