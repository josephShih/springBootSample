package com.springBoot.sample.demo.exception;

public class BussinessException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String className;
	private String message;
	
	public BussinessException(String message,String className) {
		super(message);
		this.className = className;
	}
	
	public String getClassName() {
		return className;
	}
	
	
}
