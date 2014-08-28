package org.escolarite.session.backend;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import org.apache.poi.hssf.record.formula.functions.Char;
import org.escolarite.database.oracle.Connexion;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

@Name("ElementRefaire")
public class ElementRefaireHome {

	@In 
	private Connexion con;
	
	private boolean enable;
	
	public String replace(String s){
		int j = 0;
		String codeElement = "";
		char c = 0;
		s = "DFS1M200";
		for (int i = 0; i < s.length(); i++) {
			try {
				c = s.charAt(i);
				if (j == 2) {
					codeElement += "_";
					j++;
					continue;
				}
				Integer.parseInt(c + "");
				j++;
				codeElement += "" + c;

			} catch (Exception e) {
				codeElement += "" + c;
			}
		}
		return codeElement;
	}
	
	public void verifier() throws SQLException, ClassNotFoundException{
		String req = "select * from resultat_elp re, element_pedagogi ep where cod_ind = 1104905 and re.cod_elp = ep.cod_elp and cod_tre like 'NV' and cod_nel like 'MOD'";
		ResultSet rs = Connexion.createQuery(req);
		while(rs.next()){
			String codeElement = replace(rs.getString("COD_ELP"));
			req = "select * from resultat_elp re, element_pedagogi ep where cod_ind = 1104905 and re.cod_elp = ep.cod_elp and cod_nel like 'ELM' and cod_elp like '"+codeElement+"'";
		}
		
	}
	
}
