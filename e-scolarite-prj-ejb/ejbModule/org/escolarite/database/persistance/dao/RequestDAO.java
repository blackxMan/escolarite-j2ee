package org.escolarite.database.persistance.dao;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.escolarite.database.persistance.entities.Request;
import org.escolarite.database.persistance.entities.RequestType;

public class RequestDAO extends AbstractDAO<Request> {

	
	public RequestDAO(Class<Request> refClass) {
		
		super(refClass);
	}

	private static final long serialVersionUID = -4329258049805105016L;

	@SuppressWarnings("unchecked")
	@Override
	public List<Request> getAllResultFrom(String namedQuery,Map<String, Object> parameters) {
		
		Query query = this.prepareNamedQuery(namedQuery, parameters);
		
		return query.getResultList();
	}

	@Override
	public Request getOneResultFrom(String namedQuery,Map<String, Object> parameters) {
		
		Query query = this.prepareNamedQuery(namedQuery, parameters);
		return (Request)query.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Request> findRequestsFor(String code){
		Map<String,Object> parameters = new Hashtable<String,Object>();
		parameters.put("code", code);
		
		Query query = this.prepareNamedQuery("Request.findRequestsFor", parameters);
		
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Request> findRequestsByType(RequestType requestType){
		Map<String,Object> parameters = new Hashtable<String,Object>();
		parameters.put("requestType", requestType);
		
		Query query = this.prepareNamedQuery("Request.findRequestsByType", parameters);
		
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Request> findRequestsBy(String code,RequestType requestType){
		Map<String,Object> parameters = new Hashtable<String,Object>();
		parameters.put("requestType", requestType);
		parameters.put("code", code);
		
		Query query = this.prepareNamedQuery("Request.findRequestsBy", parameters);
		
		return query.getResultList();
	}
	
	public void remove(int id){
		this.beginTransaction();
		this.delete(id);
		this.commit();
	}
	
	public void updateOne(Request request){
		this.beginTransaction();
		this.update(request);
		this.commit();
	}
	
	public void save(Request request){
		this.beginTransaction();
		this.insert(request);
		this.commit();
	}
	

}

