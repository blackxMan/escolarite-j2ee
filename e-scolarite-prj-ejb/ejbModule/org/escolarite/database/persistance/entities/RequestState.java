package org.escolarite.database.persistance.entities;

import java.io.Serializable;
import java.util.Date;


import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class RequestState implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = -5704251598683614993L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    private String justification;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;
    
    @ManyToOne(targetEntity=Admin.class)
	@JoinColumn(name="admin_id",referencedColumnName="id")
	private Admin admin;
    
    @ManyToOne(targetEntity=Request.class)
	@JoinColumn(name="request_id",referencedColumnName="id")
	private Request request;
    
    public RequestState(){
    	this.created_at =  new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}
	
	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	
	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public Date getCreatedAt() {
		return created_at;
	}

	public void setCreatedAt(Date created_at) {
		this.created_at = created_at;
	}

    
}
