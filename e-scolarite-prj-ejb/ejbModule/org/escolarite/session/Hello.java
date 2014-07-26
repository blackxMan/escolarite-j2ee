package org.escolarite.session;

import javax.ejb.Local;

@Local
public interface Hello
{
    // seam-gen method
    public String hello();

    // add additional interface methods here

}
