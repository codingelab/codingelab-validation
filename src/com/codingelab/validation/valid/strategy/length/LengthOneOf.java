package com.codingelab.validation.valid.strategy.length;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.codingelab.validation.Valid;
import com.codingelab.validation.errors.Error;
import com.codingelab.validation.errors.LengthNotOneOf;
import com.codingelab.validation.exceptions.EmptyArrayException;
/*
 * @author Abdulrahman Abdulhamid Alsaedi
 * @version 1.0.1
 */
public final class LengthOneOf implements Valid<String>{// TODO throw exption here
	private int[]lengths;
	public LengthOneOf(int ... lengths)throws Exception{
		if(lengths==null)throw new NullPointerException("Validation.LengthOneOf(null) is invalid argument."
				+ " LengthOneOf(int ... lengths) method must contains one length at least."
				+ " For example, Validation.LengthOneOf(5,7,9);");
		if(lengths.length==0)throw new EmptyArrayException("LengthOneOf(int ... lengths) method must contains one length at least."
				+ " For example, Validation.LengthOneOf(5,7,9);");
		this.lengths=lengths;
		Arrays.sort(this.lengths);
	}
	@Override
	public boolean isValid(String input) {
		for(int i=0;i<lengths.length;i++){
			if(this.lengths[i]==input.length())return true;
		}
		return false;
	}
	
	@Override public List<Error> getErrors(String input) {
		List<Error>errors=new ArrayList<>();
		if(!this.isValid(input))
			errors.add(new LengthNotOneOf(this.lengths));
		return errors;
	}
	@Override
	public String repair(String input){
		if(input.length()>this.lengths[this.lengths.length-1])
			return input.substring(0,this.lengths[this.lengths.length-1]) ;
		return input;
	}
}
