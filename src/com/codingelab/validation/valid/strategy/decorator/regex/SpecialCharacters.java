package com.codingelab.validation.valid.strategy.decorator.regex;

import com.codingelab.validation.errors.Error;
import com.codingelab.validation.errors.RequiredMoreSpecialCharacters;
/*
 * 
 * @author Abdulrahman Abdulhamid Alsaedi
 * @since 23 September 2016
 * @version 1.0.1
 *
 */
public final class SpecialCharacters extends _RegexDecorator{

	public SpecialCharacters(_Regex compoment,int required) {
		super(compoment,required);
		//super.setRegex("[^\\w\\s]"); // passing the Regex 
		super.setRegex("(_|[^\\s\\w])");
		//super.setRequired(required); // passing how many letters are needed from the above Regex
		// if there is an error in the requirement this will be the error type
		Error error=new RequiredMoreSpecialCharacters(required);
		super.setRequiredRegex("(_|[^\\s\\w])");
		super.setRequiredError(error);
	}

	@Override protected String typeToString() {
		return RegexType.SPECIAL_CHARACTERS.toString().toLowerCase()
				+super.getComponent().typeToString();
	}
}
