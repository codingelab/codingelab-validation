package com.codingelab.validation.valid.strategy.decorator.regex;

import java.util.ArrayList;
import java.util.List;

import com.codingelab.validation.errors.Error;
/*
 * 
 * @author Abdulrahman Abdulhamid Alsaedi
 * @since 23 September 2016
 * @version 1.0.1
 *
 */
public final class RegexBase extends _Regex {

	@Override
	public boolean isValid(String input) {
		return true;
	}

	@Override
	public List<Error> getErrors(String input) {
		return new ArrayList<>();
	}

	@Override
	public String getRegex() {
		return "";
	}

	@Override
	public String typeToString() {
		return "";
	}
	public String repair(String input){
		return input;
	}
	

}
