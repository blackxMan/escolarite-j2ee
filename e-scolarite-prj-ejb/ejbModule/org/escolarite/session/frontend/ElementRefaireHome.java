package org.escolarite.session.frontend;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.escolarite.database.oracle.Connexion;
import org.escolarite.database.oracle.ElementPedagogi;
import org.escolarite.database.persistance.entities.Reclamation;
import org.escolarite.database.persistance.entities.Request;
import org.escolarite.database.persistance.entities.RequestType;
import org.escolarite.session.backend.CustomIdentity;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.framework.EntityHome;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.AuthorizationException;
import org.jboss.seam.security.Identity;

@Name("ElementRefaire")
@Scope(ScopeType.EVENT)
public class ElementRefaireHome extends EntityHome<Request>{

	@In
	Connexion connexion;
	
	@Logger
	Log log;	
	
	@In
	CustomIdentity identity;
	
	
	
	private Collection<String> libElemDemande;
		
	private List<ElementPedagogi> elementsArefaire = null;
	
	private List<ElementPedagogi> elementsDemandes = null;
	
	private String[] elements;
	
	public void wire() {
		log.info("wire : ");
		getInstance();
	}
	
	 public List<ElementPedagogi> getElementsArefaire() throws SQLException, ClassNotFoundException{
		 if(this.elementsArefaire!=null)return this.elementsArefaire;
		 //try {
				
				this.elementsArefaire = new ArrayList<ElementPedagogi>();
				this.elementsDemandes = new ArrayList<ElementPedagogi>();
				String req = "select re.COD_ELP from resultat_elp re, element_pedagogi ep where cod_ind = 1104905 and re.cod_elp = ep.cod_elp and cod_tre like 'NV' and cod_nel like 'MOD'";
				
				ResultSet rs = connexion.createQuery(req);			
				while (rs.next()) {
					if (!verify(rs.getString("COD_ELP"), 1)) {
						continue;
					}
					
					String codeElement = replace(rs.getString("COD_ELP"));					
					req = "select * from element_pedagogi ep,resultat_elp re  where cod_ind = 1104905 and re.cod_elp = ep.cod_elp and cod_nel like 'ELM' and not_elp is not null and ep.cod_elp like '"
							+ codeElement + "'";
					ResultSet rs1 = connexion.createQuery(req);
					while (rs1.next()) {
						if (rs1.getFloat("NOT_ELP") < 10) {
							List l = this.getEntityManager().createQuery("from Request r where :codElm in elements(r.datas) and r.code like '1'").setParameter("codElm", rs1.getString("cod_elp")).getResultList();
							//entityManager.cr
							 boolean demande= (l.size() == 0)?false:true;
							 ElementPedagogi ep = new ElementPedagogi(rs1.getString("cod_elp"), rs1.getString("lib_elp"));
							//log.info("okkkkkkkkkkkkkkkkkkkkkkkkkkk"+l.size());
							demande = (demande)?this.elementsDemandes.add(ep):this.elementsArefaire.add(ep);
							//log.info("okkkkkkkkkk "+elementsArefaire.size());
							
						}
					}
					rs1.close();

				}
				rs.close();								
			
		return elementsArefaire;
	}	
	 
	public List<ElementPedagogi> getElementsDemandes() throws SQLException, ClassNotFoundException{
		if(this.elementsDemandes==null)this.getElementsArefaire();
		return elementsDemandes;
	}

	public void setRequestId(Long id) {	
		log.info("nooooooooooooooooooooooooooooooooooo");
		setId(id);
	}

	public Long getRequestId() {		
		return (Long) getId();
	}

	@Override
	protected Request createInstance() {
		Request request = new Request();
		return request;
	}
	
	public Collection<String> getLibElemDemande() {
		return libElemDemande;
	}	

	public void setElementsDemandes(List<ElementPedagogi> elementsDemandes) {
		this.elementsDemandes = elementsDemandes;
	}

	public String[] getElements(){
		return elements;
	}

	public void setElements(String[] elements) {
		this.elements = elements;
	}		

	public String envoyer() throws ClassNotFoundException, SQLException{
		System.out.println("le nombre des elmentssssssssssssssssssssss  ");
		Request r = new Request();
		Map<String,String> c = new HashMap<String,String>();		
		ResultSet rs = null;
		for (String element : elements) {
			String req = "select lib_elp from element_pedagogi l where l.cod_elp = '"+element+"'";
			rs = this.connexion.createQuery(req);
			rs.next();
			//libElemDemande.add(rs.getString("lib_elp"));
			c.put(element, rs.getString("lib_elp"));
		}	
		rs.close();
		r.setDatas(c);
		r.setStatus(Request.IN_PROGRESS);
		r.setCode(identity.getEtudiant().getCode());
		r.setCreatedAt(new Date());
		r.setNotified(Request.SHOW_ADMIN_NOTIFICATION);
		RequestType rt = (RequestType)getEntityManager().createQuery("from RequestType r where r.code = 'ER'").getSingleResult();
		r.setRequestType(rt);
		getEntityManager().persist(r);
		getEntityManager().flush();
		return "envoyee";
	}
	
	public String replace(String s) {
		int j = 0;
		String codeElement = "";
		char c = 0;
		//s = "DFS1M200";
		for (int i = 0; i < s.length(); i++) {
			try {
				c = s.charAt(i);

				Integer.parseInt(c + "");
				j++;
				if (j == 1) {

				}
				if (j == 3) {
					codeElement += "_";
					j++;
					continue;
				}
				codeElement += "" + c;

			} catch (Exception e) {
				codeElement += "" + c;
			}
		}
		return codeElement;
	}

	public boolean verify(String codeModule, int session) {

		char c = 0;
		int indiceSession = (session == 1) ? 1 : 0;

		for (int i = 0; i < codeModule.length(); i++) {
			
			try {
				
				c = codeModule.charAt(i);

				int j = Integer.parseInt(c + "");
				if (j % 2 == indiceSession) {
					
					return true;
				} else {
					
					return false;
				}

			} catch (Exception e) {
				
			}
		}
		
		return false;

	}
	
	public void isItsDemande() throws AuthorizationException{
		
		Request req = (Request) this.getEntityManager().createQuery("from Request where id = :id and code = '1'").setParameter("id", this.instance.getId()).getSingleResult();
		if(req == null)
		throw new AuthorizationException("erreur");
		
	}
	
	public void vueEtudiant() throws ClassNotFoundException, SQLException {
		
		log.info("set notified to 3 cad vue par etudiant");				
		this.instance.setNotified(Request.HIDE_STUDENT_NOTIFICATION);
		this.update();				
//		this.libElemDemande = new ArrayList<String>();
//		ResultSet rs = null;
//		for (String s : this.instance.getDatas()) {
//			String req = "select lib_elp from element_pedagogi l where l.cod_elp = '"+s+"'";
//			rs = this.connexion.createQuery(req);
//			rs.next();
//			libElemDemande.add(rs.getString("lib_elp"));
//		}	
//		rs.close();
		FacesMessages.instance().clear();
		
	
}

}
