package com.codingelab.validation.valid.strategy.decorator.regex;

import com.codingelab.validation.errors.Error;
import com.codingelab.validation.errors.RequiredMoreLetters;
/*
 * 
 * @author Abdulrahman Abdulhamid Alsaedi
 * @since 23 September 2016
 * @version 1.0.1
 *
 */
public final class Letters extends _RegexDecorator {
	public Letters(_Regex compoment,int required){
		super(compoment,required);// passing the base component
		super.setRegex("[\\w&&[^\\d_]]"); // passing the Regex
		//super.setRequired(required); // passing how many letters are needed from the above Regex
		// if there is an error in the requirement this will be the error type
		Error error=new RequiredMoreLetters(required);
		super.setRequiredRegex("[\\w&&[^\\d_]]");
		super.setRequiredError(error);
		
	}
	@Override
	public String typeToString() {
		return RegexType.LETTERS.toString().toLowerCase()
				+super.getComponent().typeToString();
		
	}

}
