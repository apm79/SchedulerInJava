/* Course Name: CST8284_310 Object Oriented Programming (Java)
Student Name: Armando Perez Menendez
Class name: Employee
Date: November 26, 2019
*/ 
package cst8284.asgmt3.employee;

import java.util.Scanner;

/**
 *  Employee is an abstract class that provides the abstract method for getting the type of activity of the
 *  instantiated Employee. 
 *  
 * @author Armando Perez Menendez
 * @version 1.0
 *
 */
public abstract class Employee {
	
	/**
	 * fullName is a class field that is assigned a reference value of the Employee's full name.
	 */
	private String fullName;
	
	/**
	 * Constructs an Employee with the the "unknown" value as full name.
	 */
	protected Employee() {this("unknown");}
	
	/**
	 * Constructs an Employee with the the the string passed as a parameter as full name.
	 */
	protected Employee(String fullName) {setName(fullName);}
	protected static Scanner scan = new Scanner(System.in);
	
	/**
	 * This method assigns the string passed as parameter to the full name class field.
	 * @param fullName is a String that contains the full name of the user.
	 */
	public void setName(String fullName) {this.fullName = fullName;}
	
	/**
	 * This method returns a string with the full name of the user.
	 * @return a string with the full name of the user.
	 */
	public String getName() {return fullName;}
	
	/**
	 * This abstract method will allow the subclasses that extend Employee to get the activity type. Must be overridden by
	 * the subclass.
	 * @return  a String value with the activity type. 
	 */
	public abstract String getActivityType();
	
	/**
	 * Overridden method that returns a String with the full name of the user.
	 */
	@Override
	public String toString() {return getName();}
	
}