package org.escolarite.session;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.web.RequestParameter;
import org.jboss.seam.framework.EntityHome;

import org.escolarite.database.persistance.entities.Document;

@Name("documentHome")
public class DocumentHome extends EntityHome<Document>
{
    @RequestParameter Long documentId;

    @Override
    public Object getId()
    {
        if (documentId == null)
        {
            return super.getId();
        }
        else
        {
            return documentId;
        }
    }

    @Override @Begin
    public void create() {
        super.create();
    }

}
