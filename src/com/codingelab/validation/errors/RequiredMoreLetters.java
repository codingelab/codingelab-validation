package com.codingelab.validation.errors;
/*
 * @author Abdulrahman Abdulhamid Alsaedi
 * @version 1.0.1
 */
public class RequiredMoreLetters implements Error<Integer>{
	private int required;
	public RequiredMoreLetters(int required) {
		this.required=required;
	}
	@Override public int getErrorNumber() {return 260;}
	@Override public Integer get() {return this.required;}
}
