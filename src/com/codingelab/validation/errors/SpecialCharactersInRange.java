package com.codingelab.validation.errors;
/*
 * @author Abdulrahman Abdulhamid Alsaedi
 * @version 1.0.1
 */
public class SpecialCharactersInRange implements Error<String>{
	private String range;
	public SpecialCharactersInRange(String range){
		this.range=range;
	}
	@Override public int getErrorNumber() {return 266;}
	@Override public String get() {
		return this.range;
	}
}
