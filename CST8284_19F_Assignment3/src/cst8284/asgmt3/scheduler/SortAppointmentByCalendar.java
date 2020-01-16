/* Course Name: CST8284_310 Object Oriented Programming (Java)
*Student Name: Armando Perez Menendez
*Class name: SortAppointmentByCalendar
*Date: November 26, 2019
*/ 
package cst8284.asgmt3.scheduler;

import java.util.Comparator;

/** 
 * This class implements Comparator interface and override its
 * compare method. It is used in the Scheduler class to sort the elements of 
 * an ArrayList of appointment objects
 * 
 * @author Armando Perez Menendez
 * @version 3.0
 *
 */
public class SortAppointmentByCalendar implements Comparator<Appointment>{

/**
 * This overridden method from the Comparator interface receives 2 Appointment objects as parameters
 * and compares its Calendar values, returning a 0 if both have the same value, a positive integer if 
 * the value of the 1st parameter is bigger than the 2nd parameter, and a negative integer if the 2nd parameter 
 * bigger than the 1st parameter.
 * <p>
 * compareTo() method implemented using Oracle documentation from
 * https://docs.oracle.com/javase/8/docs/api/java/util/Calendar.html
 * 
 *  @param apt1 Calendar object with the 1st date to compare.
 *  @param apt2 Calendar object with the 2nd date to compare.
 *  
 *  @return will return 0 if apt1 equals to apt2, a positive number if apt1 is bigger than apt2, and
 *  		a negative number if apt1 is smaller than apt2.
 */
	@Override
	public int compare(Appointment apt1, Appointment apt2) {		
		
		return (apt1.getAptDate().compareTo(apt2.getAptDate()));
	}	
}// end of class
			
			


		


			