package org.escolarite.session;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import org.escolarite.database.persistance.entities.Document;

@Name("documentList")
public class DocumentList extends EntityQuery<Document>
{
    public DocumentList()
    {
        setEjbql("select document from Document document");
    }
}
