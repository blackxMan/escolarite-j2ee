package org.escolarite.database.persistance.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

@Table(name="admin")
@Entity
@NamedQueries({@NamedQuery(name="Admin.findAllMyRequests",query="Select r From Request r join r.requestStates rs join rs.admin a where a = :admin ")})
public class Admin implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 848116932671642016L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotNull(message="Veuillez saisir le nom")
    private String firstname;
    
    @NotNull(message="Veuillez saisir le prenom")
    private String lastname;
    
    @NotNull
    @Email(message="Veuillez entrer un email valide")
    private String email;
    
    private Date created_at;
    
    private boolean expired;
    
    @NotNull
    @Length(min=6,max=12,message="la longueur du mot de passe est entre {min} et {max}")
    private String password;
    
    @OneToMany(targetEntity=RequestState.class,mappedBy="admin",cascade={CascadeType.PERSIST,CascadeType.REMOVE})
    private List<RequestState> requestStates;
    
    
    public Admin(){
    	this.requestStates = new ArrayList<RequestState>();
    	this.created_at = new Date();
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreatedAt() {
		return created_at;
	}

	public void setCreatedAt(Date created_at) {
		this.created_at = created_at;
	}

	public boolean isExpired() {
		return expired;
	}

	public void setExpired(boolean expired) {
		this.expired = expired;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<RequestState> getRequestStates() {
		return requestStates;
	}

	public void addRequestState(RequestState requestState) {
		this.requestStates.add(requestState);
		if(requestState.getAdmin() != this)
			requestState.setAdmin(this);
	}

}
