/* Course Name: CST8284_310 Object Oriented Programming (Java)
Student Name: Armando Perez Menendez
Class name: Appointment
Date: November 26, 2019
*/ 
package cst8284.asgmt3.scheduler;

import java.util.Calendar;
import java.io.Serializable;
import java.text.SimpleDateFormat;;

/**
 * This class allows  the instantiation of an Appointment object, 
 * which manipulate the user's input and encapsulates 5 properties:
 * a Calendar class instance to store the patient's date and time of the appointment,
 * first and last name, phone number, and activity to be performed.
 * 
 * @author Armando Perez Menendez
 * @version 3.0
 *
 */
public class Appointment implements Serializable{
	
	/**
	 * serialVersionUID is a long type class field that contains the serializable class version
	 * aptDate is a Calendar type class field that is assigned the reference value of the description of the appontment's date.
	 * firstName  is a String type class field that is assigned the reference value of the patient's first name.
	 * lastName  is a String type class field that is assigned the reference value of the patient's last name.
	 * Activity  is an Activity type class field that is assigned the reference value of the activity to be performed.
	 */
	public static final long serialVersionUID = 1L;
	private Calendar aptDate;
	private String firstName;
	private String lastName;
	private TelephoneNumber phone;
	private Activity activity;
	
	
	/**
	 *  This 4 argument constructor parses the patient full name into two strings with the first and
	 *  last name of the patient respectively, then it chains and passes its processed values to 
	 *  the 5 argument constructor.
	 *   
	 * @param cal the Calendar object containing the date and time of the appointment.
	 * @param fullName the string with the patient's full name, to be parsed into first and last name.
	 * @param phone the TelephoneNumber object with the patient's phone number
	 * @param activity the Activity object containing the text of the activity to be performed.
	 */
	public Appointment (Calendar cal, String fullName, TelephoneNumber phone, Activity activity) {
		this(cal, fullName.trim().substring(0,fullName.indexOf(" ")), 
			 fullName.trim().substring(fullName.indexOf(" "),fullName.length()), phone,activity);		
	}
	
	/**
	 * This constructor initialize an Appointment object. Takes 5 parameters and uses the class'
	 * setters to encapsulate the processed values passed by the 4-arg constructor. 
	 * @param cal cal the Calendar object containing the date and time of the appointment.
	 * @param firstName the string with the patient's first name.
	 * @param lastName the string with the patient's last name.
	 * @param phone the TelephoneNumber object with the patient's phone number
	 * @param activity the Activity object containing the text of the activity to be performed.
	 */
	public Appointment (Calendar cal, String firstName, String lastName, TelephoneNumber phone, Activity activity) {
		this.setAptDate(cal);			
		this.setFirstName(firstName.trim());		
		this.setLastName(lastName.trim());
		this.setPhone(phone);
		this.setActivity(activity);
	}
	
	/**
	 * Returns the date and time's value of the current Appointment instance
	 * 
	 * @return the Calendar object with the date and time of the appointment.
	 */
	public Calendar getAptDate() {return aptDate;}
	
	/**
	 * Registers the date and time's value of the current Appointment instance
	 * 
	 * @param cal the Calendar object with the date and time of the appointment.
	 */
	public void setAptDate(Calendar cal) {this.aptDate = cal;}
	
	/**
	 * Returns the First Name value of the current Appointment instance
	 * 
	 * @return the string with the first name of the patient.
	 */
	public String getFirstName() {return firstName;}
	
	/**
	 * Registers the patient's first name value of the current Apppointment instance
	 * 
	 * @param firstName the string with the first name value.
	 * @throws BadAppointmentDataException if there is an illegal character, or the name exceeds maximum length.
	 */
	public void setFirstName(String firstName) {
		
		if (!hasValidChars(firstName)) 
			throw new BadAppointmentDataException("Name cannot include characters other than alphabetic characters, the "
	                 + "dash (-), the period (.), and the apostrophe (').", " Illegal characters in name");
		if (!hasValidLength(firstName)) 
			throw new BadAppointmentDataException("Name cannot exceed 30 characters", "Name exceeds maximum length");
		this.firstName = firstName;
	}

	
	/**
	 * Returns the Last Name value of the current Appointment instance
	 * 
	 * @return the string with the last name of the patient.
	 */
	public String getLastName() {return lastName;}
	
	/**
	 * Registers the patient's last name value of the current Appointment instance
	 * 
	 * @param lastName the string with the first name value.
	 * @throws BadAppointmentDataException if there is an illegal character, or the name exceeds maximum length.
	 */
	public void setLastName(String lastName) {
		
		if (!hasValidChars(lastName)) 
			throw new BadAppointmentDataException("Name cannot include characters other than alphabetic characters, the "
	                 + "dash (-), the period (.), and the apostrophe (').", " Illegal characters in name");			
		if (!hasValidLength(lastName)) 
			throw new BadAppointmentDataException("Name cannot exceed 30 characters", "Name exceeds maximum length");
		this.lastName = lastName;
	}

	/**
	 * Returns the phone number value of the current Appointment instance
	 * 
	 * @return the telephoneNumber object with the patient's phone number.
	 */
	public TelephoneNumber getPhone() {return phone;}
	
	/**
	 * Registers the patient's phone number value of the current Appointment instance
	 * 
	 * @param phone the TelephoneNumber object with the patient's phone number 
	 */	
	public void setPhone(TelephoneNumber phone) {this.phone = phone;}

	
	/**
	 * Returns the activity value of the current Appointment instance.
	 * 
	 * @return the Activity object with the information of the activity to be performed.
	 */			
	public Activity getActivity() {return activity;}
	
	/**
	 * Registers the value of the activity to be performed of the current Appointment instance
	 * 
	 * @param activity the Activity object with the information of the activity to be performed.
	 */
	public void setActivity(Activity activity) {this.activity = activity;}	
	
	/**
	 * validates if the string parameter passed into it contains characters others than
	 * letters (upper and lower case), "-", ".", or " ' ".
	 * @param name the string to be validated
	 * @return false is the string contains invalid characters, and true if it has only valid characters.
	 */
	private static boolean hasValidChars(String name) {
		if (name.matches("(.*)[^a-zA-Z&&[^-]&&[^.]&&[^']](.*)"))
			return false;
		return true;			
	}
	
	/**
	 * validates if the string parameter passed into it is longer than 30 characters.
	 * @param name the string to be validated
	 * @return false if the string is longer than 30 characters, and true if its length is less than 30 chars.
	 */
	private static boolean hasValidLength(String name) {
		
		if (name.length() > 30)
			return false;
		return true;
	}
	
	
	/**
	 *  This method returns a formated String containing the instance properties
	 *  of the current Appointment instance.
	 *  The use of SimpleDateFormat is implemented as suggested in Assignment 1 feedback
	 *  following documentation from Oracle (2018) Class SimpleDateFormat [WebPage]. Retrieved from
	 *  https://docs.oracle.com/javase/8/docs/api/java/text/SimpleDateFormat.html
	 *  and an example retrieved from Jenkov Aps (2019) 
	 *  http://tutorials.jenkov.com/java-internationalization/simpledateformat.html	 *
	 */
	@Override	
	public String toString() {		 

		/**
		 * pattern  is a String type local variable that is assigned the reference value of the date's pattern.
		 * simpleDate  is a SimpleDateFormat type local variable that is assigned the reference value to a new SimpleDateFormat
		 * 				instance.
		 */
		String pattern = "EEE MMM dd yyyy HH:mm"; 
		SimpleDateFormat simpleDate = new SimpleDateFormat(pattern); 

		return (simpleDate.format(getAptDate().getTime()) + "\n" + this.getFirstName() + " " + this.getLastName() + "\n"
				+ phone.toString() + "\n" + activity.toString());

	}
}
