package com.codingelab.validation.errors;
/*
 * @author Abdulrahman Abdulhamid Alsaedi
 * @version 1.0.1
 */
public class RequiredMoreSpecialCharacters implements Error<Integer>{
	private int required;
	public RequiredMoreSpecialCharacters(int required) {
		this.required=required;
	}
	@Override public int getErrorNumber() {return 268;}
	@Override public Integer get() {return this.required;}
}
