package com.codingelab.validation.valid.strategy.decorator.regex;

import java.util.ArrayList;
import java.util.List;

import com.codingelab.validation.errors.Error;
import com.codingelab.validation.errors.LettersOutRange;
import com.codingelab.validation.errors.RequiredMoreLetters;
import com.codingelab.validation.exceptions.EmptyRange;
import com.codingelab.validation.exceptions.InvalidRange;
import com.codingelab.validation.exceptions.NullRange;
import com.codingelab.validation.exceptions.RegexException;
import com.codingelab.validation.exceptions.ValidationException;
/*
 * 
 * @author Abdulrahman Abdulhamid Alsaedi
 * @since 23 September 2016
 * @version 1.0.1
 *
 */
public final class LettersInRange extends _RegexDecorator{
	private final String range;
	private Error errorRange;
	private final String lettersOutRange;
	public LettersInRange(_Regex compoment,String range,int required)
		throws ValidationException
	{
		super(compoment,required);
		if(range==null)throw new NullRange();
		if(range.isEmpty())throw new EmptyRange();
		/*
		 * if any character found that is not letter in any language then I will go inside the if
		 * condition
		 */
		if(super.findErrorWithException(range,"_")) throw new InvalidRange("LettersInRange can only contains letters or valid range of letters.");
		if(super.findErrorWithException(range,"[[\\W][\\d]]")){
			/*
			 * I will re-check again maybe that character is - which means range like a-z 
			 * if it is range I am not going to throw exp unless if the range was like this
			 *  a-b-c then is is useless range, it should be a-c only.
			 */
			if(!super.matches(range,"([[\\w&&[\\D]]]([-]?[[\\w&&[\\D]]]{1})?)+")){
				throw new InvalidRange("LettersInRange can only contains letters or valid range of letters. ");
			}
				
		}	
		super.setRegex("[[\\w&&[^\\d_]]&&["+range+"]]");
		//super.setRequired(required);
		Error error=new RequiredMoreLetters(required);
		super.setRequiredRegex("[\\w&&[^\\d_]]");
		super.setRequiredError(error);
		this.range=range;
		this.lettersOutRange="[[\\w&&[^\\d_]]&&[^"+this.range+"]]";
		// to Opposite Range exception... 
		super.findErrorWithException("",this.lettersOutRange);
		this.errorRange=new LettersOutRange(range);

	}
	// additional error detection
	@Override protected boolean isInvalid(String input){
		return super.findError(input,this.lettersOutRange );
	}
	@Override public String repair(String input) {
		try{
			input=super.repairInput(input, this.lettersOutRange);
			input=super.repair(input);
		}catch(Exception exception){
			exception=new Exception("Invalid syntax ... check Regexs arguments. Exception may occur because you have inserted an opposite range");
			exception.printStackTrace();
		}
		return input;
	}
	@Override protected List<Error> getRangeError(String input){
		/*List<Error>errors=new ArrayList<>();
		int times=super.count(input, "[[\\w&&[\\D]]&&[^"+this.range+"]]");
		Error error=new Error();
		if(times>0){*/
		List<Error>errors=new ArrayList<>();
		if(this.isInvalid(input)){
			/*error.setTimes(times);
			error.setErrorType(ErrorType.LETTERS_OUT_RANGE);
			error.setRange(this.range);
			errors.add(error);*/
			errors.add(errorRange);
		}
		return errors;
	}

	@Override protected String typeToString() {
		return RegexType.LETTERS_IN_RANGE.toString().toLowerCase()
				+super.getComponent().typeToString();
	}

}
