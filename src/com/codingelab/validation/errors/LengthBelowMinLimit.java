package com.codingelab.validation.errors;
/*
 * @author Abdulrahman Abdulhamid Alsaedi
 * @version 1.0.1
 */
public class LengthBelowMinLimit implements Error<Integer>{
	private int min;
	public LengthBelowMinLimit(int min){this.min=min;}
	@Override public int getErrorNumber() {return 2;}
	@Override public Integer get() {return this.min;}
}
