package org.escolarite.session;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.web.RequestParameter;
import org.jboss.seam.framework.EntityHome;

import org.escolarite.database.persistance.entities.Admin;

@Name("adminHome")
public class AdminHome extends EntityHome<Admin>
{
    @RequestParameter Long adminId;

    @Override
    public Object getId()
    {
        if (adminId == null)
        {
            return super.getId();
        }
        else
        {
            return adminId;
        }
    }

    @Override @Begin
    public void create() {
        super.create();
    }

}
