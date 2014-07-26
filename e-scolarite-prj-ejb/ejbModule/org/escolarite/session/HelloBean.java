package org.escolarite.session;

import javax.ejb.Stateless;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.log.Log;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.international.StatusMessages;

@Stateless
@Name("Hello")
public class HelloBean implements Hello
{
    @Logger private Log log;

    @In StatusMessages statusMessages;
    @In FacesMessages facesMessages;

    public String hello()
    {
        // implement your business logic here
        log.info("Hello.hello() action called");
        statusMessages.add("hello");
        return "Hello world";
    }

    // add additional action methods

}
