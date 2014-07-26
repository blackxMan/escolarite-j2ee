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
import javax.validation.constraints.NotNull;

@Entity
@Table(name="reclamation_types")
@NamedQueries({@NamedQuery(name="ReclamationType.getAttachedReclamations",query="Select r From Reclamation r where r.reclamationType = :reclamationType")})
public class ReclamationType implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3484101550849856144L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
	@NotNull(message="le code est obligatoire.")
	private String code;
	
	@NotNull(message="Veuillez saisir le libelle.")
	private String title;
	
	@NotNull(message="Veuillez saisir le max autorisee")
	private short max_authorized;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date created_at;
	
	
	@OneToMany(targetEntity=Reclamation.class,mappedBy="reclamationType",cascade={CascadeType.PERSIST,CascadeType.REMOVE})
	private List<Reclamation> reclamations;
	
	public ReclamationType(){
		this.created_at = new Date();
		this.reclamations =  new ArrayList<Reclamation>();
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

	public short getMax_authorized() {
		return max_authorized;
	}

	public void setMax_authorized(short max_authorized) {
		this.max_authorized = max_authorized;
	}

	public Date getCreatedAt() {
		return created_at;
	}

	public void setCreatedAt(Date created_at) {
		this.created_at = created_at;
	}

	public List<Reclamation> getReclamations() {
		return reclamations;
	}

	public void addReclamation(Reclamation reclamation) {
		this.reclamations.add(reclamation);
		if(reclamation.getReclamationType() != this)
			reclamation.setReclamationType(this);
	}
    
    

}
