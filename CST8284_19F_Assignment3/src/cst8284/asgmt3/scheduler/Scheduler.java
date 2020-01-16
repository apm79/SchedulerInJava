/* Course Name: CST8284_310 Object Oriented Programming (Java)
*Student Name: Armando Perez Menendez
*Class name: Scheduler
*Date: November 26, 2019
*/
package cst8284.asgmt3.scheduler;

import java.util.*;
import cst8284.asgmt3.employee.*;
import java.io.*;
import java.text.*;

/**
 * The class Scheduler handles all of the input and output of the application; instantiates
 * Appointment objects and stores them in an appointment ArrayList; displays the menu, and executes 
 * the option that the user selects. 
 * 
 * @author Armando Perez Menendez 
 * @version 3.0
 */
public class Scheduler {

	/**
	 * scan is a class field that gets assigned a new Scanner object, used for user input
	 */
	private static Scanner scan = new Scanner(System.in);
	
	/**
	 * appointments is an ArrayList that stores objects of type  Appointment class.
	 */
	private ArrayList <Appointment> appointments = new ArrayList <>();
	private Employee employee;	
	
	/**
	 *  7 final class fields of type int are declared to control the options of the case loop loaded by excecuteMenuItem method.
	 *  {@value} SAVE_APPOINTMENT stores a value of 1
	 *	{@value} DELETE_APPOINTMENT stores a value of 2
	 *	{@value} CHANGE_APPOINTMENT stores a value of 3
	 *	{@value} DISPLAY_APPOINTMENT stores a value of 4
	 *	{@value} DISPLAY_SCHEDULE stores a value of 5
	 *	{@value} SAVE_APPOINTMENTS_TO_FILE stores a value of 6,
	 *	{@value} LOAD_APPOINTMENTS_FROM_FILE stores a value of 7,
	 *	{@value} EXIT stores a value of 0
	 */
	private static final int SAVE_APPOINTMENT = 1,
							 DELETE_APPOINTMENT = 2,
							 CHANGE_APPOINTMENT = 3,
							 DISPLAY_APPOINTMENT = 4, 
							 DISPLAY_SCHEDULE = 5,
							 SAVE_APPOINTMENTS_TO_FILE = 6,
							 LOAD_APPOINTMENTS_FROM_FILE = 7,
							 EXIT = 0;
	
	/**
	 * 	Constructs a Scheduler with the given employee object as its user.
	 * @param emp the given employee object.
	 */
	public Scheduler(Employee emp) {
		this.setEmployee(emp);
		
	}
	
	/**
	 * Registers the given Employee object that will be using the application. 
	 * @param emp contains the Employee object that will be stored in the employee field.
	 */
	private void setEmployee(Employee emp) {
		employee = emp;
	}
	
	/**
	 * Get the current Employee object that is using the application.
	 * @return the Employee object with the name of the employee using the application.
	 */
	private Employee getEmployee() {
		return employee;
	}

	/**
	 * Calls the loadAppointmentsFromFile method, which loads the Appointment objects stored
	 * in the CurrentAppointments.apts file; then prints a personalized message with the current user's name.
	 * <p>
	 * Finally, it calls the displayMenu and executeMenuItem in order to display the available options to the user, as long as
	 * the menu choice is not EXIT.
	 */
	public void launch() {
		/**
		 * menuChoice stores the integer value returned by the displayMenu method.
		 */
		int menuChoice;
		this.loadAppointmentsFromFile("CurrentAppointments.apts", getAppointments());
		System.out.println("Scheduling appointments for " + this.getEmployee().getName());
		do {			
			menuChoice = this.displayMenu();
			this.executeMenuItem(menuChoice);
		} while (menuChoice != EXIT);
	}

	/**
	 * This method will call getResponseTo method to display the options available to the user, concatenating
	 * the final value of the selection's fields and a message describing each selection.
	 * <p>	
	 * It returns an integer value corresponding to the operation that user wants to execute.
	 * 
	 * @return option The integer value of the operation selected by the user.
	 */
	private int displayMenu() {
		System.out.println();	
		
		/**
		 * option stores the parsed integer value returned by getResponseTo method, that indicates the selection chosen
		 * by the user.
		 */
		int option = Integer.parseInt(Scheduler.getResponseTo("Enter a selection from the following menu: \n" + 
							(SAVE_APPOINTMENT) + ". Save appointment \n" +
							(DELETE_APPOINTMENT) + ". Remove appointment \n" +
							(CHANGE_APPOINTMENT) + ". Change appointment \n" +
							(DISPLAY_APPOINTMENT) + ". Get appointment \n"+
							(DISPLAY_SCHEDULE) + ". Display schedule \n" +
							(SAVE_APPOINTMENTS_TO_FILE) + ". Backup appointments to file \n" + 
							(LOAD_APPOINTMENTS_FROM_FILE) + ". Load appointments from file \n" + 
							(EXIT) + ". Exit program \n"));		
		return (option);
	}

	/**
	 * This method will call the method of the operation selected by the user, indicated by the passed parameter.
	 * <p>
	 * If choice is 1, it will call saveAppointment method; if choice is 2, it will call deleteAppointment method;
	 * if choice 3, it will call changeAppointment method; if choice is 4, it will call displayAppointment method;
	 * if choice is 5, it will call displayDaySchedule method; choice 6 will  call saveAppointmentsToFile method; choice 7
	 * will call loadAppointmentsToFile method; choice 0 will exit the application.
	 * @param choice
	 * @throws BadAppointmentDataException if saveAppointment method members contain invalid data
	 * @throws Exception if there's a general exception of source unknown.
	 */
	private void executeMenuItem(int choice) {
		switch (choice) {
		case SAVE_APPOINTMENT:			
			System.out.println();
			try {
			this.saveAppointment(this.makeAppointmentFromUserInput());
			} catch(BadAppointmentDataException ex) {
				System.out.println(ex.getMessage() + ex.getDescription());
			} 
			catch (Exception ex) {
				System.out.println("General exception thrown; source unknown");
			}			
			break;
		case DELETE_APPOINTMENT:
			System.out.println();
			this.deleteAppointment(Scheduler.makeCalendarFromUserInput(false));
			break;
		case CHANGE_APPOINTMENT:
			System.out.println();
			this.changeAppointment(Scheduler.makeCalendarFromUserInput(false));
			break;
		case DISPLAY_APPOINTMENT:
			System.out.println();
			this.displayAppointment(Scheduler.makeCalendarFromUserInput(false));
			break;
		case DISPLAY_SCHEDULE:
			System.out.println();
			this.displayDaySchedule(Scheduler.makeCalendarFromUserInput(true));
			break;
		case SAVE_APPOINTMENTS_TO_FILE:
			System.out.println();
			this.saveAppointmentsToFile(getAppointments(), "CurrentAppointments.apts");
			break;
		case LOAD_APPOINTMENTS_FROM_FILE:
			System.out.println();
			this.loadAppointmentsFromFile("CurrentAppointments.apts", getAppointments());
			break;
		case EXIT:
			this.saveAppointmentsToFile(getAppointments(), "CurrentAppointments.apts");
			System.out.println("Exiting Scheduler");			
			break;
		default:
			System.out.println("Bad input choice");
			break;
		}// end of switch
	}//end of executeMenuItem
	
	
	/**
	 * This method will return a new Appointment object. It will prompt the user to input the client's full name,
	 * phone number, day and hour of the appointment, and the activity.
	 * 
	 * @return Appointment returns an Appointment object containing the calendar object, Full name, phone number, and activity
	 * 			object.
	 * @throws BadAppointmentData Exception if the patient's name value is empty or null.
	 */	
	private Appointment makeAppointmentFromUserInput() {
		
		/**
		 * fullName is a local variable of type String that stores the First and Last name of the patient,
		 * 			returned by the getResponseTo method.
		 */
		String fullName = Scheduler.getResponseTo("Enter Client Name (as FirstName LastName): ");
		if (fullName.isEmpty())
			throw new BadAppointmentDataException("Must enter a value.", " Empty or null value entered.");
		
		/**
		 * phone is a local variable of type TelephoneNumber that stores the phone number of the patient,
		 * 			returned by the getResponseTo method.
		 */
		TelephoneNumber phone = new TelephoneNumber(Scheduler.getResponseTo("Phone Number (e.g. 613-555-1212): "));	
		
		/**
		 * cal is a local variable of type Calendar that stores the day and time of the patient's appointment,
		 * 			returned by the makeCalendarFromUserInput method.
		 */
		Calendar cal = Scheduler.makeCalendarFromUserInput(false);
		
		/**
		 * activity is a local variable of type String that stores the First and Last name of the patient,
		 * 			returned by the getResponseTo method.
		 */
		Activity activity = new Activity(Scheduler.getResponseTo("Enter Activity: "),this.getEmployee().getActivityType());

		return new Appointment(cal, fullName, phone, activity);
	}// end of makeAppointmentFromUserInput	
	
	/**
	 * This method stores the Appointment object passed as parameter into an ArrayList. It calls findAppointment
	 * method passing the Appointment object's Calendar property as a parameter; if findAppointment returns null, it will add
	 * the appointment object into the ArrayList, otherwise, it will display a message informing that there's already an appointment
	 * at that time.
	 * @param apt Is the Appointment object that will be add to the Appointments ArrayList if findAppointment returns null.
	 * @return Returns true if the appointment is saved into the ArrayList; returns false if a previous Appointment object
	 * 			already exists at that time.
	 */
	private boolean saveAppointment(Appointment apt) {		
		if(this.findAppointment(apt.getAptDate())!=null) {
			System.out.println("Cannot save; an appointment at that time already exists"); 
			return false;	
		}
		getAppointments().add(apt);			
		System.out.println("Appointment saved.");
		Collections.sort(getAppointments(),new SortAppointmentByCalendar());
		return true;
	}//end of saveAppointmentToArray	
	
	/**
	 * This method will look into the appointments ArrayList if the Calendar object passed as a parameter exists by 
	 * calling the sort method of Collections class, passing as parameterts the ArrayList and a new instance of 
	 * SortAppointmentByCalendar class, then calling Collections class binarySearch method, which returns an integer 
	 * value that is stored in a local variable called idx.
	 * 
	 *  
	 *  
	 * @param cal the time of the appointment to search in the ArrayList.
	 * @return the Appointment object stored in the ArrayList at the index returned by the binarySearch method, or
	 * 			return null if the index returned is less than 0.
	 */
	private Appointment findAppointment(Calendar cal) {		
		Collections.sort(getAppointments(), new SortAppointmentByCalendar());	
		/**
		 * idx is a local variable that stores the returned value of binarySearch method.
		 */
		int idx = Collections.binarySearch(getAppointments(),new Appointment(cal,"foo foo",null,null),new SortAppointmentByCalendar());
	//	for (Appointment apt: getAppointments()) {
			//if (apt.getAptDate().equals(cal))
				if (idx >= 0)
					return getAppointments().get(idx);
				//return apt;
		//}
		return null;			
	}// end of findAppointment
	
	/**
	 * this method will pass its Calendar parameter to the findAppointment method, which returns an Appointment object that
	 * is stored in a local variable; if the returned value is null, this method will display a message informing the user
	 * that there is no appointment scheduled at the hour contained in the Calendar parameter; otherwise, it will display the
	 * Appointment object returned by the findAppointment method.
	 * @param cal is the Calendar type parameter passed to the method that will be searched in the appointments ArrayList.
	 */
	private void displayAppointment(Calendar cal) {
		
		/**
		 * aptFound is a local variable of type Appointment that stores the returned value of findAppointment method. 
		 */
		Appointment aptFound = this.findAppointment(cal);
		if(aptFound!=null) {
			System.out.println();
			System.out.println(aptFound.toString());
			System.out.println();
		}
		else {
			System.out.println("No appointment scheduled between " + cal.get(Calendar.HOUR_OF_DAY) + ":00" + " and "
								+ (cal.get(Calendar.HOUR_OF_DAY) + 1) + ":00");
		}
	}// end of displayAppointment

	/**
	 * This method will display the schedule of the day value stored in the Calendar object passed as a parameter from 8:00 hrs.
	 * to 16:00 hrs.
	 * @param cal
	 */
	private void displayDaySchedule(Calendar cal) {		
		for (int i = 8; i <= 16; i++) {
			cal.set(Calendar.HOUR_OF_DAY, i);
			this.displayAppointment(cal);
		}
	}// end of displayDaySchedule
	
	/**
	 *  This method calls findAppointment method to look for the Calendar object passed as its parameter into the Appointments
	 *  ArrayList; if found, it calls displayAppointment method and prompts the user to confirm the deletion of the
	 *  matching appointment object stored in the ArrayList; if user enters yes, the appointment is removed from the 
	 *  ArrayList and returns true, otherwise returns false.
	 * @param cal Calendar type parameter that contains the appointment date that the user wants to remove.
	 * @return true if the appointment is deleted; false if it's not deleted, or the appointment is not found in the ArrayList.
	 */
	private boolean deleteAppointment(Calendar cal) {	
		
		/**
		 * apt is a local variable of type Appointment that stores the returned value of findAppointment method.
		 */
		Appointment apt = findAppointment(cal);
		
				if (apt.getAptDate().equals(cal)){
					this.displayAppointment(cal);
					System.out.println();
					String resp = Scheduler.getResponseTo("Enter 'Yes' to delete this appointment ");
					if(resp.charAt(0) == 'Y' || resp.charAt(0) == 'y') {
						getAppointments().remove(apt);
						System.out.println("Appointment deleted");						
						return true;						
					}				
				}				
		return false;
	}// end of deleteAppointment
	
	
	/**
	 *  This method calls findAppointment method to look for the Calendar object passed as its parameter into the Appointments
	 *  ArrayList; if found, it calls displayAppointment method and prompts the user to confirm the update of the
	 *  matching appointment object stored in the ArrayList; if user enters yes, the appointment is updated from the 
	 *  ArrayList and returns true, otherwise returns false.
	 * @param cal Calendar type parameter that contains the appointment date that the user wants to update.
	 * @return true if the appointment is updated; false if it's not updated, or the appointment is not found in the ArrayList.
	 */
	private boolean changeAppointment(Calendar cal) {
		/**
		 * apt is a local variable of type Appointment that stores the returned value of findAppointment method.
		 */
		Appointment apt = findAppointment(cal);
		this.displayAppointment(apt.getAptDate());					
				String resp = Scheduler.getResponseTo("Enter 'Yes' to change the date and time of this appointment ");
				if (resp.charAt(0) == 'Y' || resp.charAt(0) == 'y') {
					System.out.println("Enter new date and time: \n");
					/**
					 * newApt is a local variable of type Calendar that stores the returned value of makeCalendarFromUserInput
					 * method
					 */
					Calendar newApt = Scheduler.makeCalendarFromUserInput(false);
					/**
					 * idx is a local variable of type int that will store the index of the appointment object to be updated.
					 */
					int idx = getAppointments().indexOf(apt);
					apt.setAptDate(newApt);
					getAppointments().set(idx, apt);
					System.out.println("Appointment re-booked");					
				}				
		return true;
	}// end of changeAppointment

	/**
	 * This method will store in a file the elements of the current ArrayList of objects of type Appointment.
	 * It instantiates a new FileOutputStream that takes the String parameter of the method as a parameter itself,  which in 
	 * turn is passed as a parameter to instantiate a new ObjectOutputStream object.
	 * <p>
	 * The method runs a loop through the ArrayList, passing each appointment object stored in the ArrayList as a parameter
	 * to the writeObject method of the ObjectOutputStream object, displaying a message when all objects are saved in the file.
	 *  
	 * @param apts The ArrayList with the appointment objects that are saved into the file.
	 * @param saveFile The name of the file where the appointments are saved.
	 * @return true if the appointments are successfully saved.
	 * @throws FileNotFoundException if the file is not found.
	 * @throws IOException if there is an input/output exception.
	 */
	private boolean saveAppointmentsToFile(ArrayList <Appointment> apts, String saveFile) {		
		try(
				/**
				 * osStream is a local variable that is assigned a reference value to a new FileOutputStream instance.
				 * oos is a local variable that is assigned a reference value to a new ObjectOutputStream.				 * 
				 */
			FileOutputStream oStream = new FileOutputStream(saveFile);					
			ObjectOutputStream oos = new ObjectOutputStream(oStream);				
		){
			for(Appointment thisApt : apts) {					
				oos.writeObject(thisApt);					
			}				
		}
		catch (FileNotFoundException ex) {
			System.out.println("File not found");				
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}	
		System.out.println("Appointment data saved to " + saveFile);
		return true;
	}// end of saveAppointmentsToFile
	
	/**
	 * This method will retrieve from a file the elements of an ArrayList of objects of type Appointment previously stored in it.
	 * It instantiates a new FileInputStream that takes the String parameter of the method as a parameter itself,  which in 
	 * turn is passed as a parameter to instantiate a new ObjectInputStream object.
	 * <p>
	 * The method runs a loop  that calls the readObject method of the ObjectInputStream object,
	 *  casting the element that is read during each iteration into an Appointment type, then its reference value is 
	 *  assigned to a local variable, which is passed as a parameter to the add method of the ArrayList of Appointment objects
	 *  that was passed as a parameter to the loadAppointmentsFromFile method.
	 *  
	 * @param apts The ArrayList of Appointment objects where the elements from the source file are saved.
	 * @param saveFile The name of the file that contains the appointment elements to be saved.
	 * @return true if the appointments are successfully loaded.
	 * @throws EOFException when the appointments are successfully loaded.
	 * @throws FileNotFoundException if the file does not exists.
	 * @throws IOException if there is an input/output exception.
	 * @throws ClassNotFoundException if the file is not found
	 */
	private boolean loadAppointmentsFromFile(String sourceFile, ArrayList <Appointment> apts) {
		Appointment thisApt;
	
		try(
				/**
				 * iStream is a local variable that is assigned a reference value to a new FileInputStream instance.
				 * ois is a local variable that is assigned a reference value to a new ObjectIntputStream.				 * 
				 */
			FileInputStream iStream = new FileInputStream(sourceFile);
			ObjectInputStream ois = new ObjectInputStream(iStream);
		){
		/* Implementation of while loop based on example by Krishna Srinivasan, JavaBeat (2016)
		 *  EOFException Example in Java [WebPage]. Retrieved from https://javabeat.net/eofexception/ */
			apts.clear();
			while(true) {			
				thisApt = (Appointment)(ois.readObject());				
				apts.add(thisApt);
			}			
		}		
		catch (EOFException ex) {
			System.out.println("Appointments succesfully loaded from " + sourceFile);
			System.out.println();
		}		
		catch (FileNotFoundException ex1) {
			// if file does not exists, creates it
			try(
					/**
					 * osStream is a local variable that is assigned a reference value to a new FileOutputStream instance.
					 * oos is a local variable that is assigned a reference value to a new ObjectOutputStream.				 * 
					 */
					FileOutputStream oStream = new FileOutputStream(sourceFile);					
					ObjectOutputStream oos = new ObjectOutputStream(oStream);				
				){}					
				catch (IOException ex) {
					ex.printStackTrace();
				}	
		}
		catch (ClassNotFoundException ex2) {
			System.out.println("File not found");
		}		
		catch (IOException ex3) {
			System.out.println("General IO Exception");
		}
		return true;	
	}
	
	
	/**
	 * This method instantiate a new Calendar object from the user input generated when getResponseTo method is called.
	 * It takes a boolean parameter that if false prompts the user to input the hour of the appointment. The method 
	 * instantiate a new SimpleDateFormat object in order to validate the user's input
	 * <p>
	 *  This solution is implemented using documentation about Calendar class'
	 * methods, Oracle (2018) Class Scanner [WebPage]. Retrieved from
	 * https://docs.oracle.com/javase/8/docs/api/java/util/Calendar.html, and
	 * examples from GeeksforGeeks, Calendar Class in Java with examples
	 * [WebPage].Retrieved from
	 * https://www.geeksforgeeks.org/calendar-class-in-java-with-examples/
	 * 
	 * @param suppressHour is a boolean parameter that if false prompts the user to input the hour of the appointment.
	 * @return a new Calendar object.
	 */
	private static Calendar makeCalendarFromUserInput(boolean suppressHour) {		
	
		/**
		 * cal is a local variable of type Calendar that is assigned a reference value to a new Calendar instance.		 * 
		 */
			Calendar cal = Calendar.getInstance();
			cal.clear();
			
			/* Date validation implementation based on lab 7 and Oracle docs */
		
			/**
			 * dateFormat is a local variable of type DateFormat that is assigned a reference value to a new SimpleDateFormat
			 * instance.
			 */
			DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
			dateFormat.setLenient(false);			
		
			try {
				/**
				 * date is a local variable of type String that stores a reference value of the returned String of the 
				 * getResponseTo method.
				 */					
				String date = Scheduler.getResponseTo("Appointment Date (entered as DDMMYYYY): ");
				if(date.isEmpty())
					throw new BadAppointmentDataException("Must enter a value. ", "Empty or null value entered");
				else {
					/**
					 * parsedDate is a local variable of type Date that is assigned a reference value
					 * to the Date returned by the parse method of the dateFormat variable.
					 */
					Date parsedDate = dateFormat.parse(date);		
					cal.setTime(parsedDate);
				}
			}
			catch (ParseException ex1) {
				throw new BadAppointmentDataException("Bad calendar date entered; format is DDMMYYYY. ", 
													  "Bad Calendar format");
			}		

		// if suppressHour is true, hour's default will be 0, otherwise, time is
		// prompted from user.
			/**
			 * hour is a local variable of type int that stores a 0 if suppressHour is true, and the appointment time
			 * if is false.
			 */
		int hour = (suppressHour ? 0 : Scheduler.processTimeString(getResponseTo("Appointment Time: ")));		
		
		cal.add(Calendar.HOUR_OF_DAY, hour);		
		return cal;	
	}

	/** 
	 * This method processes the string parameter that is passed into it formats it and parses it into an integer
	 * value that represents the hour in a 24-hour format.
	 * <p>
	 * Implementation of Lookahead regular expression constructs adapted from code
	 * provided in Lab 3, following documentation
	 * from RexEgg Mastering Lookahead and Lookbehind [WebPage] Retrieved from
	 * https://www.rexegg.com/regex-lookarounds.html, and Oracle Summary of
	 * regular-expressions constructs [WebPage]. Retrieved from
	 * https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html#sum
	 * 
	 * @param t the string containing the time to be processed.
	 * @return an integer value of the hour in a 24-hour format.
	 */
	private static int processTimeString(String t) {		
		int intHour;
		
		if (t.contains("p.m.")) {
			String[] StrHour = t.split("(?= )|(?=:)");
			return Integer.parseInt(StrHour[0]) + 12; // Process hour into 24-hour format.
		} else if (t.contains(" a.m.") || t.contains(":00 a.m.")) {
			String[] hour = t.split("(?= )|(?=:)");
			return Integer.parseInt(hour[0]);
		} else if (t.contains(":00")) {
			String[] hour = t.split("(?=:)");
			return Integer.parseInt(hour[0]);
			}
		intHour = Integer.parseInt(t);
		return (intHour <= 4 ? intHour + 12 : intHour); // Process hour into 24-hour format if user input is
														// between 1 and 4 with no a.m./p.m.
	}

	/**
	 * Returns the ArrayList of type Appointment objects.
	 * @return an ArrayList object with the current Appointment type objects.
	 */
	private ArrayList <Appointment> getAppointments() {
		return this.appointments;
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
	}// end of getResponseTo

}// end of class
