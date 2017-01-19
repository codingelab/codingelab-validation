package com.codingelab.validation.valid.strategy.decorator.regex;

import java.util.ArrayList;
import java.util.List;

import com.codingelab.validation.errors.Error;
import com.codingelab.validation.errors.LettersInRange;
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
public final class LettersOutRange extends _RegexDecorator{
	private final String range;
	private Error errorRange;
	private final String lettersInRange;
	public LettersOutRange(_Regex compoment,String range,int required)
			throws ValidationException
	{
		super(compoment,required);
		//if(range.equals(null))throw new NullPointerException("Range cannot be empty");
		if(range==null)throw new NullRange();
		if(range.isEmpty())throw new EmptyRange();
		/*
		 * if any character found that is not letter in any language then I will go inside the if
		 * condition
		 */
		if(super.findErrorWithException(range,"_")) throw new InvalidRange("LettersOutRange can only contains letters or valid range of letters.");
		if(super.findErrorWithException(range,"[[\\W][\\d]]")){
			/*
			 * I will re-check again maybe that character is - which means range like a-z 
			 * if it is range I am not going to throw exp unless if the range was like this
			 * a-b-c then is is useless range, it should be a-c only.
			 */
			if(!super.matches(range,"([[\\w&&[\\D]]]([-]?[[\\w&&[\\D]]]{1})?)+")){
				throw new InvalidRange("LettersOutRange can only contains letters or valid range of letters.");
			}
				
		}
		super.setRegex("[[\\w&&[^\\d_]]&&[^"+range+"]]");
		//super.setRequired(required);
		Error error=new RequiredMoreLetters(required);
		super.setRequiredRegex("[\\w&&[^\\d_]]");
		super.setRequiredError(error);
		this.range=range;
		this.lettersInRange="["+range+"&&[\\w&&[^\\d_]]]";
		// to Opposite Range exception... 
		super.findErrorWithException("",this.lettersInRange);
		this.errorRange=new LettersInRange(range);
	}

	// additional error detection
	@Override protected boolean isInvalid(String input){
		return super.findError(input,this.lettersInRange);
	}
	@Override public String repair(String input) {
		try{
			input=super.repairInput(input, this.lettersInRange);
			input=super.repair(input);
		}catch(Exception exception){
			exception=new Exception("Invalid syntax ... check Regexs arguments. Exception may occur because you have inserted an opposite range");
			exception.printStackTrace();
		}
		return input;
	}
	@Override protected List<Error> getRangeError(String input){
		/*List<Error>errors=new ArrayList<>();
		int times=super.count(input,"["+range+"&&[\\w&&[^\\d]]]");
		Error error=new Error();
		if(times>0){
			error.setTimes(times);
			error.setErrorType(ErrorType.LETTERS_IN_RANGE);
			error.setRange(this.range);
			errors.add(error);
		}*/
		List<Error>errors=new ArrayList<>();
		if(this.isInvalid(input)) errors.add(errorRange);
		return errors;
	}

	@Override protected String typeToString() {
		return RegexType.LETTERS_OUT_RANGE.toString().toLowerCase()
				+super.getComponent().typeToString();
	}
}
