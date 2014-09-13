package org.escolarite.database.oracle;

import java.util.ArrayList;
import java.util.List;
 
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.log.Log;

import sun.util.logging.resources.logging;
 
/**
 * @author Ilya Shaikovsky
 */
@Name("selectsBean")
public class User {
	@Logger
	Log log;
    private static final String[] FRUITS = { "", "Banana", "Cranberry", "Blueberry", "Orange" };
    private static final String[] VEGETABLES = { "", "Potatoes", "Broccoli", "Garlic", "Carrot" };
    private String currentItem = "";
    private String currentType = "";
    private List<SelectItem> firstList = new ArrayList<SelectItem>();
    private List<SelectItem> secondList = new ArrayList<SelectItem>();
 
    public User() {
        SelectItem item = new SelectItem("", "");
 
        firstList.add(item);
        item = new SelectItem("fruits", "Fruits");
        firstList.add(item);
        item = new SelectItem("vegetables", "Vegetables");
        firstList.add(item);
 
        for (int i = 0; i < FRUITS.length; i++) {
            item = new SelectItem(FRUITS[i]);
        }
    }
 
    public List<SelectItem> getFirstList() {
        return firstList;
    }
 
    public List<SelectItem> getSecondList() {
        return secondList;
    }
 
    public static String[] getFRUITS() {
        return FRUITS;
    }
 
    public static String[] getVEGETABLES() {
        return VEGETABLES;
    }
 
    public void valueChanged(ValueChangeEvent event) {
    	log.info("yesssssssssssssssssssssssssssssssssss");
        secondList.clear();
        if (null != event.getNewValue()) {
            String[] currentItems;
 
            if (((String) event.getNewValue()).equals("fruits")) {
                currentItems = FRUITS;
            } else {
                currentItems = VEGETABLES;
            }
 
            for (int i = 0; i < currentItems.length; i++) {
                SelectItem item = new SelectItem(currentItems[i]);
 
                secondList.add(item);
            }
        }
    }
 
    public String getCurrentType() {
        return currentType;
    }
 
    public void setCurrentType(String currentType) {
        this.currentType = currentType;
    }
 
    public String getCurrentItem() {
        return currentItem;
    }
 
    public void setCurrentItem(String currentItem) {
        this.currentItem = currentItem;
    }
}