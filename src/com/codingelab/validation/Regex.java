package com.codingelab.validation;

import com.codingelab.validation.valid.strategy.decorator.regex.RegexType;
/*
 * 
 * @author Abdulrahman Abdulhamid Alsaedi
 * @version 1.0.1
 */
public class Regex {
	private RegexType type;
	private int requirement;
	private String range;
	private boolean enableHyphen;
	
	private Regex(RegexType type,int requirement){
		this(type,requirement,"");
	}
	private Regex(RegexType type,int requirement,String range){
		this(type,requirement,range,false);
	}
	private Regex(RegexType type,int requirement,String range,boolean enableHyphen){
		this.type=type;
		this.setRequirement(requirement);
		this.range=range;
		this.enableHyphen=enableHyphen;
	}
	
	
	private void setRequirement(int requirement){
		if(requirement>0){
			this.requirement=requirement;
		}else this.requirement=0;
	}
	/**
	 * getType() method will return the type of the Regex, so you will know whether that Object of regex is letters, 
	 * numbers, white spaces, or special characters.
	 * @return Type that determine whether the object of Regex is letters, numbers, etc.
	 */
	RegexType getType(){return this.type;}
	/**
	 * getRequirement() tells how many characters at least are required in the input(Entry) to be valid.</br>
	 * Regex regex=Regex.letters();</br>
	 * regex.setRequirement(2);</br>
	 * Validation name=Validator.getValidation(regex);</br>
	 * name.setInput("o");</br>
	 * system.out.println(name.isValid());</br>
	 * The result is false, because at less 2 letters are required to be in the input(Entry).
	 * @return int the least required number of characters that must be in the input(Entry).
	 */
	public int getRequirement(){return this.requirement;}
	public String getRange(){return this.range;}
	public boolean isHyphenEnabled(){return this.enableHyphen;}
	// instance of letters
	/**
	 * Return an object of Regext which is representing the superset of all the letters in all languages.
	 * @return Regex to be composed with an object of type Validation.
	 */
	public static Regex letters(){
		return new Regex(RegexType.LETTERS, 0);
	}
	public static Regex letters(int required){
		return new Regex(RegexType.LETTERS, required);
	}
	/**
	 * Return an object of Regex which present a group of letters in a certain range or scope.
	 * @param range is a group of letters that intersect with the superset letters.
	 * @return Regex to be composed with an object of type Validation.
	 */
	public static Regex lettersInRange(String range){
		return new Regex(RegexType.LETTERS_IN_RANGE, 0,range);
	}
	public static Regex lettersInRange(String range,int required){
		return new Regex(RegexType.LETTERS_IN_RANGE,required,range);
	}
	/**
	 * Return an object of Regex which present a group of letters outside of a certain range or scope.
	 * @param range is a group of letters that should be outside of the superset letters.
	 * @return Regex to be composed with an object of type Validation.
	 */
	public static Regex lettersOutRange(String range){
		return new Regex(RegexType.LETTERS_OUT_RANGE, 0,range);
	}
	public static Regex lettersOutRange(String range,int required){
		return new Regex(RegexType.LETTERS_OUT_RANGE,required,range);
	}
	/**
	 * Return an object of Regext which is representing the superset of the numbers.
	 * @return Regex to be composed with an object of type Validation.
	 */
	public static Regex numbers(){
		return new Regex(RegexType.NUMBERS, 0);
	}
	public static Regex numbers(int required){
		return new Regex(RegexType.NUMBERS,required);
	}
	/**
	 * Return an object of Regex which present a group of numbers in a certain range or scope.
	 * @param range is a group of numbers that intersect with the superset numbers.
	 * @return Regex to be composed with an object of type Validation.
	 */
	public static Regex numbersInRange(String range){
		return new Regex(RegexType.NUMBERS_IN_RANGE,0,range);
	}
	public static Regex numbersInRange(String range,int required){
		return new Regex(RegexType.NUMBERS_IN_RANGE,required,range);
	}
	/**
	 * Return an object of Regex which present a group of numbers outside of a certain range or scope.
	 * @param range is a group of numbers that should be outside of the superset numbers.
	 * @return Regex to be composed with an object of type Validation.
	 */
	public static Regex numbersOutRange(String range){
		return new Regex(RegexType.NUMBERS_OUT_RANGE,0,range);
	}
	public static Regex numbersOutRange(String range,int required){
		return new Regex(RegexType.NUMBERS_OUT_RANGE,required,range);
	}
	/**
	 * Return an object of Regext which is representing the superset of the special characters.
	 * @return Regex to be composed with an object of type Validation.
	 */
	public static Regex specialCharacters(){
		return new Regex(RegexType.SPECIAL_CHARACTERS, 0);
	}
	public static Regex specialCharacters(int required){
		return new Regex(RegexType.SPECIAL_CHARACTERS, required);
	}
	/**
	 * Return an object of Regex which present a group of special characters in a certain range or scope.
	 * @param range is a group of special characters that intersect with the superset special characters.
	 * @return Regex to be composed with an object of type Validation.
	 */
	public static Regex specialCharactersInRange(String range){
		return new Regex(RegexType.SPECIAL_CHARACTERS_IN_RANGE, 0,range);
	}
	public static Regex specialCharactersInRange(String range,int required){
		return new Regex(RegexType.SPECIAL_CHARACTERS_IN_RANGE,required,range);
	}
	/**
	 * Return an object of Regex which present a group of special characters outside of a certain range or scope.
	 * @param range is a group of special characters that should be outside of the superset special characters.
	 * @return Regex to be composed with an object of type Validation.
	 */
	public static Regex specialCharactersOutRange(String range){
		return new Regex(RegexType.SPECIAL_CHARACTERS_OUT_RANGE, 0,range);
	}
	public static Regex specialCharactersOutRange(String range,int required){
		return new Regex(RegexType.SPECIAL_CHARACTERS_OUT_RANGE,required,range);
	}
	/**
	 * Return an object of Regext which is representing a space.
	 * @return Regex to be composed with an object of type Validation.
	 */
	public static Regex spaces(){
		return new Regex(RegexType.SPACES, 0);
	}

	/**
	 * Return an object of Regext which is representing all the white spaces(tab, enter, and etc.) except a space.
	 * @return Regex to be composed with an object of type Validation.
	 */
	public static Regex whiteSpaces(){
		return new Regex(RegexType.WHITE_SPACES,0);
	}
}
