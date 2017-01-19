package com.codingelab.validation.errors;
/*
 * @author Abdulrahman Abdulhamid Alsaedi
 * @version 1.0.1
 */
public class LengthNotBetween implements Error<int[]>{
	private int [] between;
	public LengthNotBetween(int [] between){
		this.between=between;
	}
	@Override public int getErrorNumber(){return 4;}
	@Override public int[] get() {return between;}
}
