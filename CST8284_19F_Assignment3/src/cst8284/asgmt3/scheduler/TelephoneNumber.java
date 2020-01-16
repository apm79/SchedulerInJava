/* Course Name: CST8284_310 Object Oriented Programming (Java)
Student Name: Armando Perez Menendez
Class name: TelephoneNumber
Date: November 26, 2019
*/ 



package cst8284.asgmt3.scheduler;

import java.io.Serializable;

/**
 * This class allows the manipulation of a string containing digits of the 
 * patient's phone number. A TelephoneNumber object encapsulates the parsed phone number
 * string's values into area code, prefix, and line number respectively. 
 *   
 * @author Armando Perez Menendez
 * @version 3.0
 * 
 */

public class TelephoneNumber implements Serializable {
	
	/**
	 * serialVersionUID is a long type class field that contains the serializable class version.
	 * areaCode is an int type class field that is assigned value of the area code of the phone number.
	 * prefix  is an int type class field that is assigned value of the prefix of the phone number.
	 * lineNumber  is an int type class field that is assigned value of the line number of the phone number.
	 */
	public static final long serialVersionUID = 1L;		
	private int areaCode;
	private int prefix;
	private int lineNumber;
	
/**
 * Initialize a TelephoneNumber object. Takes a String parameter
 * and parses it and uses the respective setters to encapsulate the integer values of the area code,
 * prefix and line number
 * 
 * <p>
 * 
 * Use of parseInt method as instructed in Oracle documentation Oracle (2018)
 * Class Integer [WebPage]. Retrieved from
 * https://docs.oracle.com/javase/8/docs/api/java/lang/Integer.html#parseInt-
 * java.lang.String-		
 * 
 * @param phoneNumber the phone number in a AAAPPPNNNN format.
 * @throws BadAppointmentDataException if an empty value was entered, if the string contains an invalid character
 *         if the string has in incorrect format.
 */
	public TelephoneNumber(String phoneNumber) {
		
		/**
		 * dash1Idx is an int local variable that contains the index number of the 1st dash character.
		 * dash2Idx is an int local variable that contains the index number of the 1st dash character.
		 */
		int dash1Idx=3;
		int dash2Idx=7;
		phoneNumber = phoneNumber.trim();
		
		if(phoneNumber.isEmpty())
			throw new BadAppointmentDataException("Must enter a value. ", "Empty or null value entered");
		
		if(phoneNumber.matches("(.*)[^0-9&&[^-]](.*)"))
			throw new BadAppointmentDataException("Telephone numbers can only contain numbers or the character '-'. ", 
												  "Bad character(s) in input string.");
		
		if(phoneNumber.length() < 12 || phoneNumber.charAt(dash1Idx) != '-' || phoneNumber.charAt(dash2Idx) !='-')
			throw new BadAppointmentDataException("Missing digit(s); correct format is AAA-PPP-NNNN, "
					+ "where AAA is the area code and PPP-NNNN is the local number. ", "Incorrect format.");
		
		if(phoneNumber.charAt(0) == '0' || phoneNumber.charAt(0) == '1')
			throw new BadAppointmentDataException("Area code can't start with a '0' or a '1'. ", "Invalid number.");
		
		
		/**
		 * splitPhone is a local variable that contains the assigned value of the splitted phone number.
		 * aCode is an int type local variable that contains the value of the area code of the phone number.
		 * pFix  is an int type local variable that contains the value of the prefix of the phone number.
		 * lNumber is an int type local variable that contains the value of the line number of the phone number.
		 */
		
		String[] splitPhone= phoneNumber.split("-");
		int aCode = Integer.parseInt(splitPhone[0].trim());	
		int pFix = Integer.parseInt(splitPhone[1].trim());
		int lNumber = Integer.parseInt(splitPhone[2].trim());
		this.setAreaCode(aCode);
		this.setPrefix(pFix);
		this.setLineNumber(lNumber);		
	}
	
	/**
	 * Returns the area code's value of the current telephoneNumber instance
	 * 
	 * @return the integer value of the phone's number area code
	 */
	public int getAreaCode() { return areaCode;}
	
	/**
	 * Registers the area code's value of the current telephoneNumber instance
	 * 
	 * @param areaCode the integer value of area code element of the telephone Number
	 */	
	public void setAreaCode(int areaCode) {this.areaCode = areaCode;}
	
	/**
	 * Returns the prefix's value of the current telephoneNumber instance
	 * 
	 * @return the integer value of the phone's number prefix. 
	 */	
	public int getPrefix() { return prefix;}
	
	/**
	 * Registers the prefix's value of the current telephoneNumber instance
	 * 
	 * @param prefix the integer value of the prefix element of the telephone Number
	 */	
	public void setPrefix(int prefix) {	this.prefix = prefix;}
	
	/**
	 * Returns the line number's value of the current telephoneNumber instance
	 * 
	 * @return the integer value of the phone number's prefix.
	 */
	public int getLineNumber() {return lineNumber;}
	
	/**
	 * Registers the line number's value of the current telephoneNumber instance
	 * @param lineNumber contains the line Number value of the phone number.
	 */
	public void setLineNumber(int lineNumber) {	this.lineNumber = lineNumber;}
	
	
	/**
	 *  This method returns a String containing the instance properties
	 *  of the current telephoneNumber instance, formatted as: (AAA)PPP-NNNN.
	 *  
	 *  @return the formatted string of the phone number's instance properties.
	 */
	@Override
	public String toString() {
		return("(" + getAreaCode( ) +") " + getPrefix() + "-" + getLineNumber());		
	}
}
