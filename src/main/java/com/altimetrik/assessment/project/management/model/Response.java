package com.altimetrik.assessment.project.management.model;

import java.util.List;
/**
 * 
 * @author Ashish Rawat
 *
 * @param <T>
 */
public class Response<T> {
	List<T> result;
	T record;
	String message;
	int code;
	
	public Response(T record , int code, String message) {
		this.record = record;
		this.code=code;
		this.message= message;
	}
	
	public Response(List<T> result,int code, String message) {
		this.result = result;
		this.code=code;
		this.message= message;
		
	}
	public Response(int code, String message) {
		this.code=code;
		this.message= message;
		
	}
	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

	public T getRecord() {
		return record;
	}

	public void setRecord(T record) {
		this.record = record;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
