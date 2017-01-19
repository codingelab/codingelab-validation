package com.codingelab.validation.errors;
/*
 * @author Abdulrahman Abdulhamid Alsaedi
 * @version 1.0.1
 */
public class LengthNotOneOf implements Error<int[]>{
	private int [] lengths;
	public LengthNotOneOf(int[] lengths){this.lengths=lengths;}
	@Override public int getErrorNumber(){return 6;}
	@Override public int[] get() {return lengths;}

}
