package com.codingelab.validation.errors;
/*
 * @author Abdulrahman Abdulhamid Alsaedi
 * @version 1.0.1
 */
public class HasNumbers implements Error<Void>{
	@Override public int getErrorNumber() {return 261;}
	@Override public Void get() {return null;}
}
