package com.codingelab.validation.errors;
/*
 * @author Abdulrahman Abdulhamid Alsaedi
 * @version 1.0.1
 */
public class SpecialCharactersOutRange implements Error<String>{
	private String range;
	public SpecialCharactersOutRange(String range){
		this.range=range;
	}
	@Override public int getErrorNumber() {return 267;}
	@Override public String get() {
		return this.range;
	}
}
