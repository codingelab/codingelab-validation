package com.codingelab.validation;

import com.codingelab.validation.valid.strategy.decorator.regex.RegexType;
/**
 * Regex helps the {@link Validator} class to create an object which knows how to validate the data entries
 * by passing the Regex object(s) to the argument of {@link Validator#decorate(Regex...)} method. 
 * @author Abdulrahman Abdulhamid Alsaedi
 * @since 1.0.1
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
	 * A getter method to return the type of Regex
	 * @return Type that determine whether the object of Regex is letters, numbers, etc.
	 */
	RegexType getType(){return this.type;}
	/**
	 * A getter method which tells how many characters at least are required to be in the input 
	 * so the input can be valid.</br>
	 * @return int which tells how many characters are required to be in the input of the given Regex.
	 */
	public int getRequirement(){return this.requirement;}
	// TODO make the basic component return empty String (empty range)
	/**
	 * A getter method </br> 
	 * returns empty String if the Regex object from the basic components which are:</br
	 * <ul>
	 * 		<li> Regex.letters()</li>
	 * 		<li> Regex.numbers() </li>
	 * 		<li> Regex.specialCharacters()</li>
	 * 		<li> Regex.whiteSpace()</li>
	 * 		<li> Regex.spaces()</li>
	 * </ul>
	 * and return "the given range" if the Regex object from the range components which are:</br>
	 * <ul>
	 * 		<li> Regex.lettersInRange("range") </li>
	 * 		<li> Regex.numbersInRange("range") </li>
	 * 		<li> Regex.specialCharactersInRange("Range")</li> 
	 *  	<li> Regex.lettersOutRange("range")</li>
	 *  	<li> Regex.numbersOutRange("range")</li>
	 *  	<li> Regex.specialCharactersOutRange("Range")</li>
	 * </ul>
	 * @return String which represents a range of characters that input must or must not use this range
	 */
	public String getRange(){return this.range;}
	@Deprecated
	public boolean isHyphenEnabled(){return this.enableHyphen;}
	// instance of letters
	/**
	 * Regex which represents all the letters in all languages. 
	 * @return object of Regex which has meaning only for the {@link Validator#decorate(Regex...)} method
	 */
	public static Regex letters(){
		return new Regex(RegexType.LETTERS, 0);
	}
	/**
	 * Regex which represents all the letters in all languages. 
	 * @param required exists to force to client to insert a certain number of letters
	 * @return object of Regex which has meaning only for the {@link Validator#decorate(Regex...)} method
	 */
	public static Regex letters(int required){
		return new Regex(RegexType.LETTERS, required);
	}
	/**
	 * Regex which represent the intersection of all the letters in all languages with the given range. 
	 * @param range is a group of letters that intersect with all letters in all languages.
	 * @return object of Regex which has meaning only for the {@link Validator#decorate(Regex...)} method
	 */
	public static Regex lettersInRange(String range){
		return new Regex(RegexType.LETTERS_IN_RANGE, 0,range);
	}
	/**
	 * Regex which represent the intersection of all the letters in all languages with the given range. 
	 * @param range is the only valid group of letters (the input cannot not contain any letters out of this range)
	 * @param required exists to force to client to insert a certain number of letters
	 * @return object of Regex which has meaning only for the {@link Validator#decorate(Regex...)} method
	 */
	public static Regex lettersInRange(String range,int required){
		return new Regex(RegexType.LETTERS_IN_RANGE,required,range);
	}
	/**
	 * Regex which represent all letters in all languages except the given range
	 * @param range is invalid group of letters (the input cannot not contain these letters)
	 * @return object of Regex which has meaning only for the {@link Validator#decorate(Regex...)} method
	 */
	public static Regex lettersOutRange(String range){
		return new Regex(RegexType.LETTERS_OUT_RANGE, 0,range);
	}
	/**
	 * Regex which represent all letters outside of the given range.
	 * @param range is a group that cannot be part of the input.
	 * @param required exists to force to client to insert a certain number of letters
	 * @return object of Regex which has meaning only for the {@link Validator#decorate(Regex...)} method
	 */
	public static Regex lettersOutRange(String range,int required){
		return new Regex(RegexType.LETTERS_OUT_RANGE,required,range);
	}
	/**
	 * Regex which represents the decimal numbers (from 0 to 9). 
	 * @return object of Regex which has meaning only for the {@link Validator#decorate(Regex...)} method
	 */
	public static Regex numbers(){
		return new Regex(RegexType.NUMBERS, 0);
	}
	/**
	 * Regex which represents all decimal numbers from 0 to 9.
	 * @param required exists to force to client to insert a certain amount of decimal number
	 * @return object of Regex which has meaning only for the {@link Validator#decorate(Regex...)} method
	 */
	public static Regex numbers(int required){
		return new Regex(RegexType.NUMBERS,required);
	}
	/**
	 * Regex which represent the intersection of the decimal numbers with the given range 
	 * @param range is a group of numbers that intersect with the decimal numbers
	 * @return object of Regex which has meaning only for the {@link Validator#decorate(Regex...)} method
	 */
	public static Regex numbersInRange(String range){
		return new Regex(RegexType.NUMBERS_IN_RANGE,0,range);
	}
	/**
	 * Regex which represent the intersection of the decimal numbers with the given range 
	 * @param range is a group of numbers that intersect with the decimal numbers
	 * @param required exists to force to client to insert a certain amount of decimal number
	 * @return object of Regex which has meaning only for the {@link Validator#decorate(Regex...)} method
	 */
	public static Regex numbersInRange(String range,int required){
		return new Regex(RegexType.NUMBERS_IN_RANGE,required,range);
	}
	/**
	 * Regex which represent all of the decimal numbers out of the given range
	 * @param range is invalid group of numbers
	 * 		(the input cannot not contain these numbers)
	 * @return object of Regex which has meaning only for the {@link Validator#decorate(Regex...)} method
	 */
	public static Regex numbersOutRange(String range){
		return new Regex(RegexType.NUMBERS_OUT_RANGE,0,range);
	}
	/**
	 * Regex which represent all of the decimal numbers out of the given range
     * @param range is invalid group of numbers
	 * 		(the input cannot not contain these numbers)
	 * @param required exists to force to client to insert a certain amount of decimal numbers
	 * @return object of Regex which has meaning only for the {@link Validator#decorate(Regex...)} method
	 */
	public static Regex numbersOutRange(String range,int required){
		return new Regex(RegexType.NUMBERS_OUT_RANGE,required,range);
	}
	/**
	 * Regex which represents all of the special characters
	 * @return object of Regex which has meaning only for the {@link Validator#decorate(Regex...)} method
	 */
	public static Regex specialCharacters(){
		return new Regex(RegexType.SPECIAL_CHARACTERS, 0);
	}
	/**
	 * Regex which represents all of the special characters
	 * @param required exists to force to client to insert a certain number of special characters
	 * @return object of Regex which has meaning only for the {@link Validator#decorate(Regex...)} method
	 */
	public static Regex specialCharacters(int required){
		return new Regex(RegexType.SPECIAL_CHARACTERS, required);
	}
	/**
	 * Regex which represent the intersection of all special characters with the given range 
	 * @param range is a group of special characters that intersect with the all special characters.
	 * @return object of Regex which has meaning only for the {@link Validator#decorate(Regex...)} method
	 */
	public static Regex specialCharactersInRange(String range){
		return new Regex(RegexType.SPECIAL_CHARACTERS_IN_RANGE, 0,range);
	}
	/**
	 * Regex which represent the intersection of all special characters with the given range 
	 * @param range is a group of special characters that intersect with the all special characters.
	 * @param required exists to force to client to insert a certain number of special characters
	 * @return object of Regex which has meaning only for the {@link Validator#decorate(Regex...)} method
	 */
	public static Regex specialCharactersInRange(String range,int required){
		return new Regex(RegexType.SPECIAL_CHARACTERS_IN_RANGE,required,range);
	}
	/**
	 * Regex which represent every single special character out of the given range
	 * @param range is invalid group of special characters
	 * 		(the input cannot not contain these of special characters)
	 * @return object of Regex which has meaning only for the {@link Validator#decorate(Regex...)} method
	 */
	public static Regex specialCharactersOutRange(String range){
		return new Regex(RegexType.SPECIAL_CHARACTERS_OUT_RANGE, 0,range);
	}
	/**
	 * Regex which represent every single special character out of the given range
	 * @param range is invalid group of special characters
	 * 		(the input cannot not contain these of special characters)
	 * @param required exists to force to client to insert a certain number of special characters
	 * @return object of Regex which has meaning only for the {@link Validator#decorate(Regex...)} method
	 */
	public static Regex specialCharactersOutRange(String range,int required){
		return new Regex(RegexType.SPECIAL_CHARACTERS_OUT_RANGE,required,range);
	}
	/**
	 * Object of Regex which represents a space from the white spaces range
	 * @return object of Regex which has meaning only for the {@link Validator#decorate(Regex...)} method
	 */
	public static Regex spaces(){
		return new Regex(RegexType.SPACES, 0);
	}

	/**
	 * Object of Regex which represents white spaces
	 * @return object of Regex which has meaning only for the {@link Validator#decorate(Regex...)} method
	 */
	public static Regex whiteSpaces(){
		return new Regex(RegexType.WHITE_SPACES,0);
	}
}
