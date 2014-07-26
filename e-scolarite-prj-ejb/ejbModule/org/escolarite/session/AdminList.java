package org.escolarite.session;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import org.escolarite.database.persistance.entities.Admin;

@Name("adminList")
public class AdminList extends EntityQuery<Admin>
{
    public AdminList()
    {
        setEjbql("select admin from Admin admin");
    }
}
