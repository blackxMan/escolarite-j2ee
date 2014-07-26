package org.escolarite.database.persistance.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumns;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Document implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5427708560025587223L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	
	private String name;
	
	private String path;
	
	private String description;
	
	@ManyToOne(targetEntity=Reclamation.class)
	@JoinColumns(value={@JoinColumn(name="reclamation_id",referencedColumnName="id")})
	private Reclamation reclamation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Reclamation getReclamation() {
		return reclamation;
	}

	public void setReclamation(Reclamation reclamation) {
		this.reclamation = reclamation;
	}
}
