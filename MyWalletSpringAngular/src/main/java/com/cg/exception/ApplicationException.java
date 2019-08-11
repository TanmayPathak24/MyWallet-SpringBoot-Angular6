package com.cg.exception;

/**
 * @author Tanmay Pathak
 * */
public class ApplicationException extends RuntimeException{
	public ApplicationException(String message) {
		super(message);
	}
}
