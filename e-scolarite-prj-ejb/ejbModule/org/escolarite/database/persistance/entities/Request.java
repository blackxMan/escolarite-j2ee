package org.escolarite.database.persistance.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


// class de mapping de la demande 
@Table(name="requests")
@Entity
@NamedQueries({
	@NamedQuery(name="Request.findRequestsByType",query="Select r from Request r where r.requestType = :requestType"),
	@NamedQuery(name="Request.findRequestsFor",query="Select r from Request r where r.code = :code"),
	@NamedQuery(name="Request.findRequestsBy",query="Select r from Request r where r.code = :code and r.requestType = :requestType"),
	@NamedQuery(name="Request.findAll",query="Select r from Request r")
})
public class Request implements Serializable
{
	/**
	 * for serial id
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * notification status
	 */
	public static final short SHOW_ADMIN_NOTIFICATION = 0;
	public static final short HIDE_ADMIN_NOTIFICATION = 1;
	public static final short SHOW_STUDENT_NOTIFICATION = 2;
	public static final short HIDE_STUDENT_NOTIFICATION = 3;
	
	/**
	 * status de la demande
	 */
	public static final short IN_PROGRESS = 0;
	public static final short ACCEPTED = 1;
	public static final short REJECTED = 2;
	
	
	
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;// date of creation

	
	@Temporal(TemporalType.TIMESTAMP) 
	private Date updatedAt;// date of update
	
	@Temporal(TemporalType.TIMESTAMP) 
	private Date meetingDate;// la date du rendu vous
	
	
	private String notice;// la remarque
	
	private short notified = 0;// aide la gestion du notification 
	
	private short status = 0;// aide a determi
	
	@ManyToOne(targetEntity=RequestType.class)
	@JoinColumn(name="requesttype_id",referencedColumnName="id")
	private RequestType requestType;
	
	
	@OneToMany(targetEntity=RequestState.class,mappedBy="request",cascade={CascadeType.PERSIST,CascadeType.REMOVE})
	private List<RequestState> requestStates;
	
	
	@ElementCollection
	private Collection<String> datas = new ArrayList<String>();
	
	private String code;// le code appoge
	
	
	public Request(){
		this.requestStates = new ArrayList<RequestState>();
		this.createdAt = new Date();				
	}
	
	public void setId(Long id){
		this.id = id;
	}
	
	public Long getId(){
		return this.id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	public Date getUpdatedAt() {
		return updatedAt;
	}


	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public Date getMeetingDate() {
		return meetingDate;
	}


	public void setMeetingDate(Date meetingDate) {
		this.meetingDate = meetingDate;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public short getNotified() {
		return notified;
	}

	public void setNotified(short notified) {
		this.notified = notified;
	}

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public Collection<String> getDatas() {
		return this.datas;		
	}

	public void setDatas(Collection<String> datas) {
		this.datas = datas;
	}

	public String getAppogeRef() {
		return code;
	}

	public void setAppogeRef(String code) {
		this.code = code;
	}
	
	public RequestType getRequestType() {
		return requestType;
	}

	public void setRequestType(RequestType requestType) {
		this.requestType = requestType;
	}

	public List<RequestState> getRequestStates() {
		return requestStates;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void addRequestState(RequestState requestState) {
		this.requestStates.add(requestState);
		if(requestState.getRequest() != this)
			requestState.setRequest(this);
	}

}
