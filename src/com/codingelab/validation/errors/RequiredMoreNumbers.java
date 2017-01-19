package com.codingelab.validation.errors;
/*
 * @author Abdulrahman Abdulhamid Alsaedi
 * @version 1.0.1
 */
public class RequiredMoreNumbers implements Error<Integer>{
	private int required;
	public RequiredMoreNumbers(int required) {
		this.required=required;
	}
	@Override public int getErrorNumber() {return 264;}
	@Override public Integer get() {return this.required;}
}