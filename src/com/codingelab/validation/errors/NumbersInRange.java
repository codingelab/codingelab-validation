package com.codingelab.validation.errors;
/*
 * @author Abdulrahman Abdulhamid Alsaedi
 * @version 1.0.1
 */
public class NumbersInRange implements Error<String>{
	private String range;
	public NumbersInRange(String range){
		this.range=range;
	}
	@Override public int getErrorNumber() {return 262;}
	@Override public String get() {return this.range;}
}
