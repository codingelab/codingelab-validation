package com.codingelab.validation.valid.strategy.decorator.regex;

import java.util.ArrayList;
import java.util.List;

import com.codingelab.validation.Regex;
import com.codingelab.validation.Validator;
import com.codingelab.validation.errors.Error;
import com.codingelab.validation.errors.NoError;
import com.codingelab.validation.errors.WhiteSpaceOutRange;
import com.codingelab.validation.exceptions.ValidationException;
/*
 * 
 * @author Abdulrahman Abdulhamid Alsaedi
 * @since 23 September 2016
 * @version 1.0.1
 *
 */
public final class Spaces extends _RegexDecorator {
	//private final String range;
	private final String spacesOutRange;
	public Spaces(_Regex compoment)
		//	throws NullPointerException
	{
		super(compoment,0);
		//if(range.equals(null))throw new NullPointerException("Range cannot be empty");
		super.setRegex("[ ]");
		//super.setRequired(0);
		this.spacesOutRange="[\\s&&[^ ]]";
		super.setRequiredError(new NoError());
		//this.range=range;
	}
	// additional error detection
	@Override protected boolean isInvalid(String input){
		return super.findError(input,this.spacesOutRange);
	}
	@Override public String repair(String input) {
		//input=input.replaceAll(this.spacesOutRange,"");
		//input=input.replaceAll("[ ]{2,}"," ");
		// Validator.decorate(Regex.spaces(),Regex.specialCharacters());
		// why the below syntax has a problem with the above ??
		input=super.repairInput(input, this.spacesOutRange);
		input=input.replaceAll("[ ]{2,}"," ");
		input=super.repair(input);
		return input;
	}
	@Override protected List<Error> getRangeError(String input){
		/*List<Error>errors=new ArrayList<>();
		int times=super.count(input, "[\\s&&[^ ]]");
		Error error=new Error();
		if(times>0){
			error.setTimes(times);
			error.setErrorType(ErrorType.WHITE_SPACES_OUT_RANGE);
			error.setRange("Space");
			errors.add(error);
		}*/
		List<Error>errors=new ArrayList<>();
		if(this.isInvalid(input)) errors.add(new WhiteSpaceOutRange());
		return errors;
	}

	@Override protected String typeToString() {
		return RegexType.WHITE_SPACES.toString().toLowerCase()
				+super.getComponent().typeToString();
	}
}
