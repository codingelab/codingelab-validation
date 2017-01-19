package com.codingelab.validation.exceptions;
/*
 * @author Abdulrahman Abdulhamid Alsaedi
 * @version 1.0.1
 */
public class EmptyRange extends RegexException{
	public EmptyRange() {
		this("The value of range argument is empty.");
	}
	public EmptyRange(String msg) {
		super(msg);
	}

}
