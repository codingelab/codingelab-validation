package com.codingelab.validation.errors;
/*
 * @author Abdulrahman Abdulhamid Alsaedi
 * @version 1.0.1
 */
public class LettersOutRange implements Error<String>{
	private String range;
	public LettersOutRange(String range){
		this.range=range;
	}
	@Override public int getErrorNumber() {return 259;}
	@Override public String get() {return this.range;}
}
