package com.codingelab.validation.errors;
/*
 * @author Abdulrahman Abdulhamid Alsaedi
 * @version 1.0.1
 */
public class LengthNotEqual implements Error<Integer>{
	private int equal;
	public LengthNotEqual(int equal){this.equal=equal;}
	@Override public int getErrorNumber() {return 1;}
	@Override public Integer get() {return this.equal;}
}
