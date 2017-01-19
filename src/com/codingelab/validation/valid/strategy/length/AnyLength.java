package com.codingelab.validation.valid.strategy.length;

import java.util.ArrayList;
import java.util.List;

import com.codingelab.validation.Valid;
import com.codingelab.validation.errors.Error;
/*
 * @author Abdulrahman Abdulhamid Alsaedi
 * @version 1.0.1
 */
public final class AnyLength implements Valid<String> {
	@Override public boolean isValid(String input) {return true;}
	@Override public List<Error> getErrors(String input) {
		return new ArrayList();
	}
	@Override
	public String repair(String input){
		return input;
	}
}
