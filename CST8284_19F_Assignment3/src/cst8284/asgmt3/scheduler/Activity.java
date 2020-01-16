/* Course Name: CST8284_310 Object Oriented Programming (Java)
Student Name: Armando Perez Menendez
Class name: Activity
Date: November 26, 2019
*/ 
package cst8284.asgmt3.scheduler;

import java.io.Serializable;

/**
 * This class allows the instantiation of a new Activity object,
 * encapsulating the description and category of the activity to be performed
 * to the patient.
 * 
 * @author Armando Perez Menendez
 * @version 3.0
 */
public class Activity implements Serializable{
	
	/**
	 * serialVersionUID is a long type class field that contains the serializable class version
	 * descriptionOfWork is a String type class field that is assigned the reference value of the description of Work.
	 * category  is a String type class field that is assigned the reference value of the Activity's category.
	 */
	public static final long serialVersionUID = 1L;
	private String descriptionOfWork;
	private String category;
	 
/**
 * Constructs a new Activity with the specified description and category strings.
 * 
 * @param description A brief description of the procedure scheduled for the patient.
 * @param category The type of procedure that will be scheduled for the patient.
 */
	public Activity(String description, String category) {
		setDescription(description);
		setCategory(category);
	
	}
	
	/** 
	 * Returns the description of the procedure scheduled for the patient.
	 * 
	 * @return the description of the work that will be performed to the patient.
	 */
	public String getDescription() {return descriptionOfWork;}
	
	/**
	 * Register the description of the procedure scheduled for the patient.
	 * @param s contains the brief description that will be stored in the descriptionOfWork field.
	 */
	public void setDescription(String s) {this.descriptionOfWork = s;}
	
	/** 
	 * Returns the category  of the procedure scheduled for the patient.
	 * 
	 * @return the type of category of the procedure that will be performed to the patient.
	 */
	public String getCategory() {return category;}
	
	/**
	 * Register the category of the procedure scheduled for the patient.
	 * @param s contains type of category that will be stored in the category field.
	 */
	public void setCategory(String s) {this.category = s;}
	
	/**
	 * Returns a string with the category and description of the procedure to be performed to the patient.
	 * 
	 * @return Returns the concatenation of the category and description of the procedure. 
	 */
	@Override
	public String toString() {		
		return getCategory() + "\n" + getDescription()  ;
	}

}
