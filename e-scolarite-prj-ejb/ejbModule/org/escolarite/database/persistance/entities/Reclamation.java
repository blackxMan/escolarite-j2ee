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
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name="reclamations")
@NamedQueries({@NamedQuery(name="Reclamation.getAttachedDocuments",query="Select d From Document d where d.reclamation = :reclamation")})
public class Reclamation implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4752550903229929595L;

	
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
	public static final short ACCEPTED = 0;
	public static final short REJECTED = 1;
	public static final short IN_PROGRESS = 2;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	
	
	@NotNull(message="Veuillez remplir l'objet de la reclamation")
	@Length(max=60,message="l'objet de la reclamation ne doit pas depasser 60 caractere")
	private String subject;
	
	@NotNull(message="Veuillez remplir la description de la reclamation")
	private String description;
	
	private String responce;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date created_at;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date consulted_at;
	
	private short notified;
	
	private short status;
	
	@ManyToOne(targetEntity=ReclamationType.class)
	@JoinColumns(value = { @JoinColumn(name="reclamationtype_id",referencedColumnName="id") })
	private ReclamationType reclamationType;
	
	@OneToMany(targetEntity=Document.class,mappedBy="reclamation",cascade={CascadeType.PERSIST,CascadeType.REMOVE})
	private List<Document> documents;

	public Reclamation(){
		this.created_at = new Date();
		this.documents =  new ArrayList<Document>();
	}
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getResponce() {
		return responce;
	}

	public void setResponce(String responce) {
		this.responce = responce;
	}

	public Date getCreatedAt() {
		return created_at;
	}

	public void setCreatedAt(Date created_at) {
		this.created_at = created_at;
	}

	public Date getConsultedAt() {
		return consulted_at;
	}

	public void setConsultedAt(Date consulted_at) {
		this.consulted_at = consulted_at;
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
	
	public ReclamationType getReclamationType() {
		return reclamationType;
	}

	public void setReclamationType(ReclamationType reclamationType) {
		this.reclamationType = reclamationType;
	}
	
	public List<Document> getDocument() {
		return documents;
	}

	public void addDocument(Document document) {
		this.documents.add(document);
		if(document.getReclamation() != this)
			document.setReclamation(this);
	}

}
