package com.codingelab.validation.errors;
/*
 * @author Abdulrahman Abdulhamid Alsaedi
 * @version 1.0.1
 */
public class NumbersOutRange implements Error<String>{
	private String range;
	public NumbersOutRange(String range){
		this.range=range;
	}
	@Override public int getErrorNumber() {return 263;}
	@Override public String get() {return this.range;}
}
