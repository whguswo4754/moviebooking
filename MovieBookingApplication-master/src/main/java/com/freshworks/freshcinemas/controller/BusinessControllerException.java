package com.freshworks.freshcinemas.controller;

public class BusinessControllerException extends Exception{
	public BusinessControllerException(String s) {
		super(s);
	}
	
	public BusinessControllerException(Throwable s) {
		super(s);
	}
}
