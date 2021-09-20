package com.altimetrik.assessment.project.management.exception;
/**
 * 
 * @author Ashish Rawat
 *
 */
public class PMEException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int errorCode;
	
	public PMEException() {
		
	}
	
	public PMEException(String message) {
		super(message);
	}
	
	public PMEException(int errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}
	
	public PMEException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public int getErrorCode() {
		return this.errorCode;
	}
}
