package com.codingelab.validation.exceptions;
/*
 * @author Abdulrahman Abdulhamid Alsaedi
 * @version 1.0.1
 */
public class InvalidLength extends RegexException{
	public InvalidLength() {
		this("Length must be unsigned (opsitive number)");
	}
	public InvalidLength(String msg) {
		super(msg);
	}
}

