package com.codingelab.validation.errors;
/*
 * @author Abdulrahman Abdulhamid Alsaedi
 * @version 1.0.1
 */
public class LengthBetween implements Error<int[]>{
	private int [] between;
	public LengthBetween(int [] between){
		this.between=between;
	}
	@Override public int getErrorNumber(){return 5;}
	@Override public int[] get() {return between;}

}
