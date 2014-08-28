package org.escolarite.database.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Connexion {

	private static Connection connection = null;

	private Connexion() {

	}

	public static Connection getConnection() throws ClassNotFoundException,
			SQLException {
		if (connection == null) {
			String driverName = "oracle.jdbc.driver.OracleDriver";

			Class.forName(driverName);

			String url = "jdbc:oracle:thin:@196.200.177.7:1521:apotest";

			String username = "zakaria";
			String password = "admin";

			connection = DriverManager.getConnection(url, username, password);
			return connection;

		}

		return connection;

	}

	public static ResultSet createQuery(String req) throws SQLException, ClassNotFoundException {
		Statement st = getConnection().createStatement();
		ResultSet rs = st.executeQuery(req);
		//connection.close();		
		return rs;
	}
	
}
