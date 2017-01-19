package com.codingelab.validation.errors;
/*
 * @author Abdulrahman Abdulhamid Alsaedi
 * @version 1.0.1
 */
public class WhiteSpaceOutRange implements Error<Void>{
	@Override public int getErrorNumber(){return 270;}
	@Override public Void get(){return null;}
}
