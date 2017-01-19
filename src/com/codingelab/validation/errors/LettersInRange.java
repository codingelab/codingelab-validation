package com.codingelab.validation.errors;
/*
 * @author Abdulrahman Abdulhamid Alsaedi
 * @version 1.0.1
 */
public class LettersInRange implements Error<String>{
	private String range;
	public LettersInRange(String range){
		this.range=range;
	}
	@Override public int getErrorNumber() {return 258;}
	@Override public String get() {return this.range;}
}
