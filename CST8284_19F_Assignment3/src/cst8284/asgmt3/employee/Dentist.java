/* Course Name: CST8284_310 Object Oriented Programming (Java)
Student Name: Armando Perez Menendez
Class name: Dentist
Date: November 26, 2019
*/ 
package cst8284.asgmt3.employee;

/**
 * This class extends from Employee abstract class. It allows the instantiation of a new Dentist object; it
 * encapsulates the Dentist full Name and overrides Employee getActivityType abstract method.
 * @author Armando Perez Menendez
 * @version 3.0
 */
public class Dentist extends Employee {
	
	/**
	 *  4 final class fields of type int are declared to control the options of the case loop loaded by the excecuteItem method.
	 *  {@value} ASSESSMENT stores a value of 1
		{@value} FILLING stores a value of 2
		{@value} CROWN stores a value of 3
		{@value} COSMETIC stores a value of 4
	 */
	private static final int ASSESSMENT = 1,
							 FILLING = 2,
							 CROWN = 3,
							 COSMETIC = 4;
	
	/**	
	 *  This constructor chains to the Employee constructor, passing the specified fullName string as a parameter,
	 *  in order to instantiate a new Dentist.  	 
	 * @param fullName contains the string with the full name of the user.
	 */
	public Dentist(String fullName) {
		super(fullName);		
	}

	/**
	 * Overridden method that returns the String with the activity selected by the user, according to the
	 * numeric option passed as a parameter.
	 */
	@Override
	public String getActivityType() {
		
		/**
		 * cat is a local variable of type int that stores the value returned by the displayMenu method.
		 */
		int cat = displayMenu();
		return executeItem(cat);		
	}
	
	/**
	 * This method displays a menu with the category options for the user to choose.
	 * 
	 * @return an integer value that represents the option of the category chosen by the user.
	 */
	private int displayMenu() {
		System.out.println();
		
		/** 
		 * opt is a local variable of type int that stores the numeric value of the category chosen by the user.
		 */
		int opt = Integer.parseInt(getResponseTo("Enter a category from the following menu: \n" +
												ASSESSMENT + ".Assessment \n" +
												FILLING + ".Filling \n" +
												CROWN + ".Crown \n" +
												COSMETIC + ".Cosmetic Repair \n"));
		return (opt);
	}
	
	/**
	 * This method returns a string corresponding to the integer value of the category chosen by the user, which is passed 
	 * as a parameter to the method.
	 * 
	 * @param choice contains the integer value that represents the user's choice of category.
	 * @return
	 */
	private String executeItem(int choice) {
		switch (choice) {
			case ASSESSMENT:
				return ("Assessment");
			case FILLING:
				return ("Filling");
			case CROWN:
				return ("Crown");
			case COSMETIC:
				return ("Cosmetic Repair");
			default:
				System.out.println("Bad input choice");
				return null;			
			}
		}	
	
	/**
	 * This method prints out the String that receives as a parameter, scans and returns the user's response.
	 * 
	 * @param s String parameter that contains the message to be displayed on the screen.
	 * @return the scanned user's response.
	 */
	private static String getResponseTo(String s) {
		System.out.printf(s);
		return (scan.nextLine());
	}

}
