package com.codingelab.validation.valid.strategy.length;

import java.util.ArrayList;
import java.util.List;

import com.codingelab.validation.Valid;
import com.codingelab.validation.errors.Error;
import com.codingelab.validation.errors.LengthBelowMinLimit;
/*
 * @author Abdulrahman Abdulhamid Alsaedi
 * @version 1.0.1
 */
public final class Minimum implements Valid<String>{
	private int min;
	public Minimum(int min) {
		this.min=min;
	}
	@Override
	public boolean isValid(String input) {
		return input.length()>=this.min;
	}

	@Override
	public List<Error> getErrors(String input) {
		List<Error>errors=new ArrayList<>();
		if(!this.isValid(input))
			errors.add(new LengthBelowMinLimit(this.min));
		return errors;
	}
	@Override
	public String repair(String input){
		return input;
	}
}
