/* Course Name: CST8284_310 Object Oriented Programming (Java)
*Student Name: Armando Perez Menendez
*Class name: SchedulerLauncher
*Date: November 26, 2019
*/ 
package cst8284.asgmt3.scheduler;

import cst8284.asgmt3.employee.*;

/**
 * This class contains the main method that instantiates a new 
 * Scheduler object, launching the application.
 * 
 * @author Armando Perez Menendez 
 * @version 3.0
 */
public class SchedulerLauncher {	
	
/**
 * Instantiates a new Scheduler object with a new Dentist object as a parameter,
 * then executes the launch method of the new Scheduler instance.
 * @param args unused
 */
	public static void main(String[] args) {			
		Scheduler schedule = new Scheduler(new Dentist("Dr. Andrews"));
		schedule.launch();		
	}
}
