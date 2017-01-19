package com.codingelab.validation.valid.strategy.decorator.regex;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import com.codingelab.validation.errors.Error;
import com.codingelab.validation.errors.HasLetters;
import com.codingelab.validation.errors.HasNumbers;
import com.codingelab.validation.errors.HasSpecialCharacters;
import com.codingelab.validation.errors.HasWhiteSpaces;
import com.codingelab.validation.exceptions.InvalidRange;
import com.codingelab.validation.exceptions.RegexException;
import com.codingelab.validation.exceptions.ValidationException;
/*
 * RegexDecorator applied the Decorator design pattern. 
 * This Abstract class is responsible<br>
 * for determining if a client/user input has an error or not based on Regex rules<br>
 * In addition to that, RegexDecorator will return a list of errors if errors exist<br>
 * @author Abdulrahman Abdulhamid Alsaedi
 * @since 23 September 2016
 * @version 1.0.1
 *
 */
public abstract class _RegexDecorator extends _Regex{
							/*Properties*/
	/*
	 *  component property are needed to point out or to reference to the next 
	 *  component in the decorator design pattern
	 */
	private _Regex compoment;
	/*
	 * regex property are needed to hold the Regex which come from sup-type
	 */
	private String regex;
	private String requiredRegex;
	/*
	 * required property are needed to determine how many characters at least must be 
	 * in the input. For examples:
	 * Example 1: new Letters(5); this is means the input must has at least 5 letters
	 * 			  if the input contains 6 or more letters it is valid
	 * Example 2: new Numbers(2); this is means the input must has at least 2 numbers
	 * 			  if the input contains 1 or less number it is invalid
	 * hint: if the required property set to be 0 or less, that means the requirement
	 * 		 are not active.
	 */
	private int required;
	// examples: has REQUIRED_MORE_LETTERS or REQUIRED_MORE_NUMBERS
	// helpful in case the required property has been set
	private Error requiredError;
	private final String letters;
	private final String numbers;
	private final String specialCharacter;
	private final String whiteSpaces;
	
							/*Constructor(s)*/
	/*
	 * Forcing the sub-type to pass the base component
	 */
	public _RegexDecorator(_Regex compoment,int required){
		this.compoment=compoment;
		// do to activating the required checking unless the sub-type asked for
		//this.required=0;
		this.setRequired(required);
		// making this instance on the top(for the general checking)
		super.setTop(true); 
		/*
		 * making the component reference in the bottom
		 * (to do not check the general checking twice)
		 */
		compoment.setTop(false);
		this.letters="[\\w&&[^\\d_]]";
		this.numbers="[\\d]";
		this.specialCharacter="(_|[^\\s\\w])";
		this.whiteSpaces="[\\s]";
	}
	
	
	
							/*Getter method(s)*/
	protected _Regex getComponent() {return this.compoment;}
	protected String getRegex() {return this.regex+this.compoment.getRegex();}
	protected int getRequired() {return this.required;}
	protected Error getRequiredErrorType(){return this.requiredError;}
	protected String getRequiredRegex(){return this.requiredRegex;}


							/*setter method(s)*/
	protected void setComponent(_Regex component) {this.compoment=component;}
	protected void setRegex(String regex) {this.regex=regex+"*";}
	protected void setRequired(int required) {this.required=required>0?required:0;}
	protected void setRequiredError(Error requiredError){
		this.requiredError=requiredError;
	}
	protected final void setRequiredRegex(String regex){this.requiredRegex=regex;}

	
	
							/*
							 * Implemented method(s) &
							 * The logic of this class
							 */
	/*
	 *  isValid(input) is a framework on how the sub-type should validate the input.
	 *  In this framework there is a method called isInvalid(input);
	 *  By overriding this method the sub-types have ability 
	 *  to add their own validation to the framework
	 */
	@Override public final boolean isValid(String input){
		// if this object in the first/top object then check the general validation
		if(super.isTop()){
			String types=this.typeToString();
			if(!types.contains("letters")){
				boolean hasError=this.findError(input,this.letters);
				if(hasError)return false;
			}
			if(!types.contains("numbers")){
				boolean hasError=this.findError(input,this.numbers);
				if(hasError)return false;
			}
			if(!types.contains("special_characters")){	
				boolean hasError=this.findError(input,this.specialCharacter);
				if(hasError)return false;
			}
			if(!types.contains("white_spaces")){	
				boolean hasError=this.findError(input,this.whiteSpaces);
				if(hasError)return false;
			}
		}
		if(this.isInvalid(input))return false;
		// if the required properties is 0 or below this part of validation will be skipped 
		if(!this.containsRequirments(input))return false;
		// delegate the task to the next node to validate the input
		return this.getComponent().isValid(input);
	}
	
	/*
	 *  getErrors(input) is a framework on how the sub-type should collect errors
	 *  from an input.
	 *  In this framework there is a method called getRangeError(input);
	 *  By overriding this method the sub-types have ability 
	 *  to add their own extra error collector to the framework
	 *  hint: this method should be overridden in case the sub-type deal with 
	 *  a specific range. For example range from a-f from the range of all letters.
	 *  This give us the ability to check weather the input goes out of that range 
	 *  or not.
	 */
	@Override public final List<Error> getErrors(String input){
		List<Error>errors=new ArrayList<>();
		// only the top decorator can enter this condition for once.
		if(super.isTop()){
			String types=this.typeToString();
			if(!types.contains("letters")){
				//int counter=count(input,"[\\w&&[\\D]]");
				/*if(counter>0){
					Error error=new Error();
					error.setErrorType(ErrorType.HAS_LETTERS);
					error.setTimes(counter);
					errors.add(error);
				}*/
				boolean hasError=this.findError(input,this.letters);
				if(hasError){
					Error error=new HasLetters();
					errors.add(error);
				}
			}
			if(!types.contains("numbers")){
				/*int counter=count(input,"[\\d]");
				if(counter>0){
					Error error=new Error();
					error.setErrorType(ErrorType.HAS_NUMBERS);
					error.setTimes(counter);
					errors.add(error);
				}*/
				boolean hasError=this.findError(input,this.numbers);
				if(hasError){
					Error error=new HasNumbers();
					errors.add(error);
				}
			}
			if(!types.contains("special_characters")){	
				/*int counter=count(input,"[^\\w\\s]");
				if(counter>0){
					Error error=new Error();
					error.setErrorType(ErrorType.HAS_SPECIAL_CHARACTERS);
					error.setTimes(counter);
					errors.add(error);
				}*/
				
				boolean hasError=this.findError(input,this.specialCharacter);
				//boolean hasError=this.findError(input,"[^\\w\\s]");
				if(hasError){
					Error error=new HasSpecialCharacters();
					errors.add(error);
				}
			}
			if(!types.contains("white_spaces")){	
				/*int counter=count(input,"[\\s]");
				if(counter>0){
					Error error=new Error();
					error.setErrorType(ErrorType.HAS_WHITE_SPACES);
					error.setTimes(counter);
					errors.add(error);
				}*/
				boolean hasError=this.findError(input,this.whiteSpaces);
				if(hasError){
					Error error=new HasWhiteSpaces();
					errors.add(error);
				}
			}
		}
		// see this.getRangeError(input)
		errors.addAll(getRangeError(input));
		// see this.getRequirmentError(input)
		errors.addAll(this.getRequirmentError(input));
		/*
		 * delegate the task to the next component to check its 
		 * error range and requirement.
		 */
		errors.addAll(this.getComponent().getErrors(input));
		return errors;
	}
	
	
	
							/*
							 *extra needed method(s)
							 *{for implemented/logic method(s)}
							 */
	/*
	 * If the required properties is 0 or below this method always will return true.
	 * Else this method will skim ever character in the input, and start counting
	 * the characters that match the Regex of this instance, and 
	 * if the counter is bigger or equal to the required property, true will be returned
	 * else it will return false.
	 */
	protected boolean containsRequirments(String input){
		if(this.required>0){
			String regex=this.regex.substring(0,this.regex.length()-1);
			//System.out.println(regex);
			//Pattern pattern=Pattern.compile(regex,Pattern.UNICODE_CHARACTER_CLASS);
			//Matcher matcher=pattern.matcher(input);
			int counter=this.count(input, regex);//0
			//while(matcher.find())counter++;
			//System.out.println(counter+" VS "+required);
			if(counter<this.required)return false;
		}
		return true;
	}
	
	/*
	 * if the required property is above than 0 then will check if the input meet
	 * the required number of characters or not
	 * in case the input meet the required number empty list will be return
	 * else it will return a list contains one error.
	 */
	protected List<Error> getRequirmentError(String input){
		List<Error>errors=new ArrayList<>();
		if(this.required>0){
			// remove the last char which is *
			//String regex=this.regex.substring(0,this.regex.length()-1);
			int counter=count(input, /*regex*/this.requiredRegex);
			if(counter<this.required){
				errors.add(this.requiredError);
			}
		}
		return errors;
	}
	/*
	 *  counting number of characters that exist in an input based on a specific Regex.
	 *  Example 1: 	you could pass regex that says count any character that 
	 *  		 	is representing a number but it has to be out of this range (159).
	 *  		   	To be more clear: count("a46159",[\\d&&[^159]])
	 * 			   	the result is 2 since 4 and 6 is out of the range('a' is not a number)
	 * Example 1: 	You could also say count all letters in this range (aeoiu)
	 * 				count("omar",["aeoiu"])
	 *  			the result is 1 since the only letter in that range is 'a' 
	 */
	
	protected int count(String input,String regex){
		Pattern pattern=Pattern.compile(regex,Pattern.UNICODE_CHARACTER_CLASS);
		Matcher matcher=pattern.matcher(input);
		int counter=0;
		while(matcher.find())counter++;
		return counter;
	}
	/*
	 * will method will return false as soon as the matcher object find
	 * letter out of a given range this range came from the regex param
	 */
	protected boolean findErrorWithException(String input,String regex)throws RegexException{
		try{
			//[^\\&&[[^\w\s\][_][-]]]
			//System.out.println(regex);
			Pattern pattern=Pattern.compile(regex,Pattern.UNICODE_CHARACTER_CLASS);
			Matcher matcher=pattern.matcher(input);
			return matcher.find();
		}catch(PatternSyntaxException e){
			throw new InvalidRange("Opposite Range... ");
		}catch (Exception e) {
			throw e;
		}
		
		
	}
	
	protected boolean findError(String input,String regex){
		try{
			Pattern pattern=Pattern.compile(regex,Pattern.UNICODE_CHARACTER_CLASS);
			Matcher matcher=pattern.matcher(input);
			return matcher.find();
		}catch(Exception exception){
			exception=new Exception("Invalid syntax ... check Regexs arguments. Exception may occur because you have inserted an opposite range");
			exception.printStackTrace();
		}
		return false;
	}
	
	
	
	
	
	// see this.isValid(input) it will make sense. (hook in the framework)
	protected boolean isInvalid(String input){return false;}
	
	/*
	 * collect errors from sub-type that overrode this method in case if there 
	 * is an error based on the Regex sub-type,
	 * else it will return empty list
	 */
	protected List<Error> getRangeError(String input){
		return new ArrayList<>();
	}
	/*
	 *  after building the entire regex and just surround the regex with [regex]*
	 *  and this is needed for validate the entire regex as one unit as start
	 *  if it is valid then each object will validate its part of the input
	 */
	public String buildRegex(){return "["+this.getRegex()+"]*";}
	
	/*
	 * to match all regex as one unit
	 */
	public boolean matches(String input,String regex)throws RegexException{
		try{
			Pattern pattern=Pattern.compile(regex,Pattern.UNICODE_CHARACTER_CLASS);
			Matcher matcher=pattern.matcher(input);
			return matcher.matches();
		}catch(PatternSyntaxException e){
			throw new InvalidRange("Opposite Range... ");
		}catch (Exception e) {
			throw e;
		}
	}
	public String repair(String input){
		// only the top decorator can enter this condition for once.
		if(super.isTop()){
			String types=this.typeToString();
			if(!types.contains("letters")){
				input=repairInput(input, this.letters);
			}
			if(!types.contains("numbers"))input=repairInput(input, this.numbers);
			if(!types.contains("special_characters"))input=repairInput(input, this.specialCharacter);
			if(!types.contains("white_spaces"))input=repairInput(input, this.whiteSpaces);
		}
		input=this.getComponent().repair(input);
		return input;
	}
	protected String repairInput(String input,String regex){
		if(input==null)return null;
		Pattern pattern=Pattern.compile(regex,Pattern.UNICODE_CHARACTER_CLASS);
		Matcher matcher=pattern.matcher(input);
		return matcher.replaceAll("");
	}
}
