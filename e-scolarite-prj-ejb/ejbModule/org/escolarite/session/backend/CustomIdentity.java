package org.escolarite.session.backend;

import org.escolarite.session.frontend.Etudiant;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Install;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Observer;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Startup;
import org.jboss.seam.annotations.intercept.BypassInterceptors;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Identity;

@Name("org.jboss.seam.security.identity")
@Scope(ScopeType.SESSION)
@Install(precedence = Install.APPLICATION)
@BypassInterceptors
@Startup
public class CustomIdentity extends Identity {
	private static final long serialVersionUID = -9154737979944336061L;
	
	@Logger
	Log log;
	
	private Etudiant etudiant;

	public Etudiant getEtudiant() {
		if (etudiant == null)this.etudiant = new Etudiant();
		return etudiant;
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}

	@Override
	public String login() {
			
		String retVal = "";
	       try {
	           // clear out the login message in case it was triggered
	           // by an authenticate occurring outside the login
	           loginErrorMessage = null;          
	           retVal = super.login();          
	       } finally {          
	           // check if any error messages were registered from
	           // this logging., if they are write them out          
	           //if (StringUtils.isNotBlank(loginErrorMessage)) {
	    	   FacesMessages.instance().clear();
	    	   if(this.loginErrorMessage != null){
	    		   FacesMessages.instance().add(loginErrorMessage);
	    	   }
	           //}
	       }      
	       return retVal;
	}

	private String loginErrorMessage;

	/**
	 * This is used to save off an error message in case of a login.
	 * 
	 * @param message
	 */
	@Observer("invalidLogin")
	public void writeInvalidLogin(String message) {
		loginErrorMessage = message;
	}
}