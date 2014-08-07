package org.escolarite.database.persistance.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

public abstract class AbstractDAO<T> implements Serializable {
	
	@PersistenceContext(unitName="escolarite")
	private EntityManager entityManager;
	
	private static final long serialVersionUID = 1L;
	
	private Class<T> refClass;
	
	public AbstractDAO(Class<T> refClass){
		this.refClass = refClass;
		//EntityManagerFactory factory = Persistence.createEntityManagerFactory("e-scolarite-prj");
		//entityManager = factory.createEntityManager();
	}
	
	
	
	public void beginTransaction(){
		
		this.entityManager.getTransaction().begin();
		
	}
	
	public void rollback(){
		this.entityManager.getTransaction().rollback();
	}
	
	public void commit(){
		this.entityManager.getTransaction().commit();
	}
	
	
	public void joinTransaction(){
		this.entityManager.joinTransaction();
	}
	
	public void flush(){
		this.entityManager.flush();
	}
	
	public T findEntityReference(int id){
		return this.entityManager.getReference(refClass, id);
	}
	
	public T findOne(int id){
		return this.entityManager.find(refClass, id);
	}
	
	public void insert(T entity){
		this.entityManager.persist(entity);
	}
	
	public void update(T entity){
		this.entityManager.merge(entity);
	}
	
	public void delete(int id){
		T entityRef = this.findEntityReference(id);
		this.entityManager.remove(entityRef);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<T> findAll(){
		CriteriaQuery query = this.entityManager.getCriteriaBuilder().createQuery();
		query.select(query.from(refClass));
		return this.entityManager.createQuery(query).getResultList();
	}
	
	public Query prepareNamedQuery(String namedQuery,Map<String,Object> parameters){
		Query query = this.entityManager.createNamedQuery(namedQuery);
		this.fillParameters(query, parameters);
		return query;
		
	}
	
	private void fillParameters(Query query,Map<String,Object> parameters){
		
		if(parameters != null && !parameters.isEmpty()){
			for(Entry<String,Object> param : parameters.entrySet()){
				query.setParameter(param.getKey(), param.getValue());
			}
		}
	}
	
	public abstract List<T> getAllResultFrom(String namedQuery,Map<String,Object> parameters);
	
	public abstract T getOneResultFrom(String namedQuery,Map<String,Object> parameters);
}

