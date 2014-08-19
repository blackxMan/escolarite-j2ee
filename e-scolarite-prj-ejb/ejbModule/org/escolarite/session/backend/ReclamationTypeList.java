package org.escolarite.session.backend;

import java.util.Arrays;

import org.escolarite.database.persistance.entities.ReclamationType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;

@Name("reclamationTypeList")
public class ReclamationTypeList extends EntityQuery<ReclamationType> {

	private static final String EJBQL = "select reclamationType from ReclamationType reclamationType";
	
	private static final String[] RESTRICTIONS = {
			"lower(reclamationType.code) like lower(concat(#{reclamationTypeList.reclamationType.code},'%'))",
			"lower(reclamationType.title) like lower(concat(#{reclamationTypeList.reclamationType.title},'%'))", };

	private ReclamationType reclamationType = new ReclamationType();
	
	public ReclamationTypeList() {	
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public ReclamationType getReclamationType() {
		
		return reclamationType;
	}
	
}
