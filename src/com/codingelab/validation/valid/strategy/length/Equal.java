package com.codingelab.validation.valid.strategy.length;

import java.util.ArrayList;
import java.util.List;

import com.codingelab.validation.Valid;
import com.codingelab.validation.errors.Error;
import com.codingelab.validation.errors.LengthNotEqual;
/*
 * @author Abdulrahman Abdulhamid Alsaedi
 * @version 1.0.1
 */
public final class Equal implements Valid<String>{
	private int size;
	public Equal(int size){this.size=size;}
	@Override public boolean isValid(String input) {return input.length()==size;}
	@Override public List<Error> getErrors(String input) {
		List<Error>errors=new ArrayList<>();
		if(!this.isValid(input))
			if(input.length()!=this.size)
				errors.add(new LengthNotEqual(this.size));
		return errors;
	}
	@Override
	public String repair(String input){
		if(input.length()>this.size)
			return input.substring(0,this.size) ;
		return input;
	}
}
