package org.escolarite.session.backend;

import javax.faces.application.FacesMessage;
import static org.jboss.seam.annotations.Install.FRAMEWORK;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Install;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Startup;
import org.jboss.seam.annotations.intercept.BypassInterceptors;
import org.jboss.seam.annotations.intercept.Interceptor;
import org.jboss.seam.annotations.intercept.InterceptorType;
import org.jboss.seam.core.Events;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.navigation.Pages;
import org.jboss.seam.security.Identity;

@Scope(ScopeType.APPLICATION)  
@BypassInterceptors  
@Name("org.jboss.seam.navigation.pages")  
@Startup
@Install(precedence=FRAMEWORK, classDependencies="javax.faces.context.FacesContext")  
public class TestPage extends Pages{

    /**
     * Overridden to prevent "Please log in first" faces message
     */
	
	
	@Logger
	Log log;
	
    protected void notLoggedIn() {  
		log.info("not logiiiiiiiiiiinnnnnnnnnnnnn");
        //Events.instance().raiseEvent("org.jboss.seam.security.notLoggedIn");
        Events.instance().raiseEvent("org.jboss.seam.notLoggedIn");
        //Events.instance().raiseEvent(Identity.EVENT_LOGIN_SUCCESSFUL);
        
        
    }
} 
