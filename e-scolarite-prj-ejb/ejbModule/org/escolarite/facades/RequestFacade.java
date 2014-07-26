package org.escolarite.facades;

import java.util.List;

import org.escolarite.database.persistance.dao.RequestDAO;
import org.escolarite.database.persistance.entities.Request;
import org.escolarite.database.persistance.entities.RequestType;
import org.jboss.seam.annotations.In;


// this class is used like a repository for the request Table/Object
public class RequestFacade implements EscolariteFacadeInterface{
	
	@In
	private RequestDAO requestDAO = null;
	
	public void remove(int id){
		requestDAO.remove(id);
	}
	
	public void update(Request request){
		requestDAO.updateOne(request);
	}
	
	public void save(Request request){
		
		requestDAO.save(request);
	}
	
	public List<Request> findRequestsFor(String code_apogee){
		return requestDAO.findRequestsFor(code_apogee);
	}
	
	public List<Request> findRequestsByType(RequestType requestType){
		return requestDAO.findRequestsByType(requestType);
	}
	
	public List<Request> findRequestsBy(String code_apogee,RequestType requestType){
		return requestDAO.findRequestsBy(code_apogee,requestType);
	}
	
	public List<Request> findAll(){
		return requestDAO.findAll();
	}

	@Override
	public RequestDAO getEntityDAO() {
		
		return requestDAO;
	}

	@Override
	public Class<?> getEntityClass() {
		
		return Request.class;
	}

}
