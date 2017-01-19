package com.codingelab.validation.valid.strategy.decorator.regex;

import com.codingelab.validation.errors.Error;
import com.codingelab.validation.errors.RequiredMoreNumbers;
/*
 * 
 * @author Abdulrahman Abdulhamid Alsaedi
 * @since 23 September 2016
 * @version 1.0.1
 *
 */
public final class Numbers extends _RegexDecorator {

	public Numbers(_Regex compoment, int required) {
		super(compoment,required);
		super.setRegex("[\\d]");
		//super.setRequired(required);
		Error error=new RequiredMoreNumbers(required);
		super.setRequiredRegex("[\\d]");
		super.setRequiredError(error);
	}
/*	@Override
	public boolean isValid(String input){
		// general validation
		if(!super.isValid(input))return false;
		// has requirement
		if(!super.containsRequirments(input))return false;
		// delegate the task to the next node to validate the input
		return super.getComponent().isValid(input);
	}
	*/
/*	@Override
	public List<Error> getErrors(String input) {
		List<Error>errors=new ArrayList<>();
		errors.addAll(super.getErrors(input));
		errors.addAll(super.getRequirmentError(input));
		errors.addAll(super.getComponent().getErrors(input));
		return errors;
	}*/

	@Override protected String typeToString() {
		return RegexType.NUMBERS.toString().toLowerCase()
				+super.getComponent().typeToString();
	}

}
