package com.codingelab.validation.valid.strategy.length;

import java.util.ArrayList;
import java.util.List;

import com.codingelab.validation.Valid;
import com.codingelab.validation.errors.Error;
import com.codingelab.validation.errors.LengthAboveMaxLimit;
/*
 * @author Abdulrahman Abdulhamid Alsaedi
 * @version 1.0.1
 */
public final class Maximum implements Valid<String>{
	private int max;
	public Maximum(int max) {
		this.max=max;
	}
	@Override public boolean isValid(String input) {
		return input.length()<=this.max;
	}

	@Override public List<Error> getErrors(String input){
		List<Error>errors=new ArrayList<>();
		if(!this.isValid(input))
			errors.add(new LengthAboveMaxLimit(this.max));
		return errors;
	}
	@Override public String repair(String input){
		if(input.length()>this.max)
			return input.substring(0,this.max) ;
		return input;
	}
}
