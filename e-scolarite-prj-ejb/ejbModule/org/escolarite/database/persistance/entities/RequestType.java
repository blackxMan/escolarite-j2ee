package org.escolarite.database.persistance.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


// class de mapping de type de demande
@Entity
@Table(name="request_types")
@NamedQueries({
	@NamedQuery(name="RequestType.findAll",query="Select rt From RequestType rt")
	})
public class RequestType implements Serializable
{
	private static final long serialVersionUID = 5671984051316304571L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	
	@NotNull
	private String code;// le code de la demande
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date created_at;
	
	@NotNull
	private String title;// le libelle du type de demande
	
	@NotNull
	@Digits(fraction = 0, integer = 1)
	@Min(value = 1)
	private short max_request;// la max autorise pour un type de demande
	
	@OneToMany(targetEntity=Request.class,mappedBy="requestType",cascade={CascadeType.PERSIST,CascadeType.REMOVE})
	private List<Request> requests;
	
	public RequestType(){
		requests =  new ArrayList<Request>();
		this.created_at =  new Date();
	}

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public short getMaxRequest() {
		return max_request;
	}

	public void setMaxRequest(short max_request) {
		this.max_request = max_request;
	}
	
	public List<Request> getRequests() {
		return this.requests;
	}

	public void addRequest(Request request) {
		this.requests.add(request);
		if(request.getRequestType() != this)
			request.setRequestType(this);
	}


	public Date getCreatedAt() {
		return created_at;
	}


	public void setCreatedAt(Date created_at) {
		this.created_at = created_at;
	}

}
