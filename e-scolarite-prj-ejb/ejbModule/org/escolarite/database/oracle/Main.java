package org.escolarite.database.oracle;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

	/**
	 * @param args
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	
	public static void main(String[] args) {
		try {
			String req = "select re.COD_ELP from resultat_elp re, element_pedagogi ep where cod_ind = 1104905 and re.cod_elp = ep.cod_elp and cod_tre like 'NV' and cod_nel like 'MOD'";
			//String req = "select * from resultat_elp re";
			ResultSet rs = Connexion.createQuery(req);
			//System.out.println(rs.getString("COD_ELP"));
			while(rs.next()) {
				String codeElement = replace(rs.getString("COD_ELP"));
				req = "select * from resultat_elp re, element_pedagogi ep where cod_ind = 1104905 and re.cod_elp = ep.cod_elp and cod_nel like 'ELM' and cod_elp like '"
						+ codeElement + "'";
				rs = Connexion.createQuery(req);
				System.out.print(rs.getString("COD_ELP"));
			}
		} catch (ClassNotFoundException e) {
			System.out.println("notfound");
			e.printStackTrace();

		} catch (SQLException e) {
			System.out.println("sqlex");
			e.printStackTrace();
		}
	}

	public static String replace(String s) {
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

}
