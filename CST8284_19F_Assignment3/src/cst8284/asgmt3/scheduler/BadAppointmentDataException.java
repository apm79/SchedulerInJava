/* Course Name: CST8284_310 Object Oriented Programming (Java)
*Student Name: Armando Perez Menendez
*Class name: BadAppointmentDataException
*Date: November 26, 2019
*/ 
package cst8284.asgmt3.scheduler;

/**
 *  This class exception extends from RuntimeException; is thrown when 
 *  bad input has been entered, for example, a phone number string that contains invalid characters.
 *   @author Armando Perez Menendez
 *   @version 1.0
 */
public class BadAppointmentDataException extends RuntimeException {
	
	/**
	 * serialVersionUID is a long type class field that contains the serializable class version.
	 * description is a String type class field that is assigned the reference value of the description of the exception.
	 */
	private static final long serialVersionUID = 1L;
	private String description;
	
	/**
	 * Constructs a BadAppointmentDataException with a default message. It is chained to 
	 * a 2-arg constructor.
	 */
	public BadAppointmentDataException() {
		this("Please try again", "Bad data entered");

	}

	/**
	 * Constructs a BadAppointmentDataException with the specified output and description message.
	 * @param ExMsg1 The output message passed to the superclass RuntimeException.
	 * @param ExMsg2 The description message
	 */
	public BadAppointmentDataException(String ExMsg1, String ExMsg2) {		
		super(ExMsg1);		
		description = ExMsg2;
	}
	
	/**
	 * Returns the description part of the exception message
	 * @return The detailed description of this throwable.
	 */
	public String getDescription() {		
		return description;
	}
	
	/**
	 * Register the description message into the field called description.
	 * @param description string containing the message to be stored in the description field.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}// end of class
