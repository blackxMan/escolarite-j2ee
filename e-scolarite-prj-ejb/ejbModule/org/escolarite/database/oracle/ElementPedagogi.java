package org.escolarite.database.oracle;

public class ElementPedagogi {
	
	private String code;
	private String libelle;	
	
	public ElementPedagogi(String code, String libelle) {
		super();
		this.code = code;
		this.libelle = libelle;
		
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
		
	}
	
	

}
