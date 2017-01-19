package com.codingelab.validation.errors;
/*
 * @author Abdulrahman Abdulhamid Alsaedi
 * @version 1.0.1
 */
public class HasWhiteSpaces implements Error<Void>{
	@Override public int getErrorNumber() {return 269;}
	@Override public Void get() {return null;}
}
