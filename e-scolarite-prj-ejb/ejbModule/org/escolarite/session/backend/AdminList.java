package org.escolarite.session.backend;

import org.escolarite.database.persistance.entities.Admin;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("adminList")
public class AdminList extends EntityQuery<Admin> {

	private static final String EJBQL = "select admin from Admin admin";

	private static final String[] RESTRICTIONS = {
			"lower(admin.email) like lower(concat(#{adminList.admin.email},'%'))",
			"lower(admin.firstname) like lower(concat(#{adminList.admin.firstname},'%'))",
			"lower(admin.lastname) like lower(concat(#{adminList.admin.lastname},'%'))",
			"lower(admin.password) like lower(concat(#{adminList.admin.password},'%'))", };

	private Admin admin = new Admin();

	public AdminList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public Admin getAdmin() {
		return admin;
	}
}
