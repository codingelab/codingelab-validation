package com.codingelab.validation.exceptions;
/*
 * @author Abdulrahman Abdulhamid Alsaedi
 * @version 1.0.1
 */
public class NullRange extends RegexException{
	public NullRange() {
		this("The value of the range argument is null.");
	}
	public NullRange(String msg) {
		super(msg);
	}
}