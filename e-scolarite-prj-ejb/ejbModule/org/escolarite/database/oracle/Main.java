package org.escolarite.database.oracle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {

	/**
	 * @param args
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */

	public static void main(String[] args) {
		ElementPedagogi e1 = new ElementPedagogi("1", "element");
		ElementPedagogi e2 = new ElementPedagogi("1", "element11");
		if(e1.equals(e2))System.out.println("heloooooooooooooooooooooooooo");
	}

}
