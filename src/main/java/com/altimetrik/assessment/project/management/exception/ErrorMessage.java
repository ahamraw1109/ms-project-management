package com.altimetrik.assessment.project.management.exception;
/**
 * 
 * @author Ashish Rawat
 *
 */
public enum ErrorMessage {
	NO_RECORD_FOUND("No record found"), NO_PROJECT_FOUND("Project not found"),
	NO_DEPARTMENT_FOUND("Department not found"), NO_EMPLOYEE_FOUND("Employee not found"),
	EMPLOYEE_ASSOCIATION_FAILED("Employee is not associated with the selected project");

	String message;

	ErrorMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
