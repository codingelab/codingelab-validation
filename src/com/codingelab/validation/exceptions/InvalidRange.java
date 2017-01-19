package com.codingelab.validation.exceptions;
/*
 * @author Abdulrahman Abdulhamid Alsaedi
 * @version 1.0.1
 */
public class InvalidRange extends RegexException{
	public InvalidRange() {
		this("Regex is invalid");
	}
	public InvalidRange(String msg) {
		super(msg);
	}

}
