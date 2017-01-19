package com.codingelab.validation.errors;
/*
 * @author Abdulrahman Abdulhamid Alsaedi
 * @version 1.0.1
 */
public class LengthAboveMaxLimit implements Error<Integer>{
	private int max;
	public LengthAboveMaxLimit(int max){this.max=max;}
	@Override public int getErrorNumber() {return 3;}
	@Override public Integer get() {return this.max;}
}
