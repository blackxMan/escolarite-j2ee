package org.escolarite.database.oracle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.enterprise.context.SessionScoped;

import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.log.Log;

@Name("user")
public class User {
	
	
	
	public String[] getTest() {
		return test;
	}

	public void setTest(String[] test) {
		this.test = test;
	}
	
	//private String []test1 = new S;
	
	public String[] getTest1() {
		
		String[] test1 = {"test1","test2"};
		return test1;
	}

	

	

	public void setElements(ArrayList<ElementPedagogi> elements) {
		this.elements = elements;
	}

	@Logger
	Log log;
	
	private String[] test;
	private ArrayList<ElementPedagogi> elements = new ArrayList<ElementPedagogi>();
	private String[] elementstab = {"element0"};
	
	public String[] getElementstab() {
		return elementstab;
	}

	public void setElementstab(String[] elementstab) {
		this.elementstab = elementstab;
	}

	public User() {
		
	}
	private double i = Math.random();
	
	private double j = Math.random();
	
	
	public double getJ() {
		return j;
	}

	public void setJ(double j) {
		this.j = j;
	}

	public double getI() {
		return i;
	}

	public void setI(double i) {
		this.i = i;
	}

	/*public void verifier(){
		//this.elementstab[0]="element1";
		//this.elementstab[1]="element2";
		elements.add(new ElementPedagogi("1", "element1"));
		elements.add(new ElementPedagogi("2", "element2"));
	}*/
	public void valider(){
		log.info(" nombre de element "+j);
		
	}
}
