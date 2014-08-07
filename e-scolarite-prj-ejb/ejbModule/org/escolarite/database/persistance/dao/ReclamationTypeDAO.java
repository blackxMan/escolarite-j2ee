package org.escolarite.database.persistance.dao;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.escolarite.database.persistance.entities.ReclamationType;


public class ReclamationTypeDAO extends AbstractDAO<ReclamationType> {

	
	public ReclamationTypeDAO(Class<ReclamationType> refClass) {
		
		super(refClass);
	}

	private static final long serialVersionUID = -4329258049805105016L;

	@SuppressWarnings("unchecked")
	@Override
	public List<ReclamationType> getAllResultFrom(String namedQuery,Map<String, Object> parameters) {
		
		Query query = this.prepareNamedQuery(namedQuery, parameters);
		
		return query.getResultList();
	}

	@Override
	public ReclamationType getOneResultFrom(String namedQuery,Map<String, Object> parameters) {
		
		Query query = this.prepareNamedQuery(namedQuery, parameters);
		return (ReclamationType)query.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<ReclamationType> findReclamationTypeFor(String code){
		Map<String,Object> parameters = new Hashtable<String,Object>();
		parameters.put("code", code);
		
		Query query = this.prepareNamedQuery("ReclamationType.findReclamationTypeFor", parameters);
		
		return query.getResultList();
	}
	
	
	
	
	
	public void remove(int id){
		this.beginTransaction();
		this.delete(id);
		this.commit();
	}
	
	public void updateOne(ReclamationType reclamationType){
		this.beginTransaction();
		this.update(reclamationType);
		this.commit();
	}
	
	public void save(ReclamationType reclamationType){
		this.beginTransaction();
		this.insert(reclamationType);
		this.commit();
	}
	

}

