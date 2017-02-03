package com.codingelab.validation;

import com.codingelab.validation.exceptions.EmptyArrayException;
import com.codingelab.validation.exceptions.EmptyRange;
import com.codingelab.validation.exceptions.InvalidRange;
import com.codingelab.validation.exceptions.NullRange;
import com.codingelab.validation.languages.Lang;
import com.codingelab.validation.languages.Language;
import com.codingelab.validation.languages.LanguageFactory;
import com.codingelab.validation.strategy.strings.ClientValidation;
import com.codingelab.validation.strategy.strings.DecorateValidation;
import com.codingelab.validation.strategy.strings.EmailValidation;
import com.codingelab.validation.strategy.strings.StringValidation;
import com.codingelab.validation.valid.strategy.decorator.regex.Letters;
import com.codingelab.validation.valid.strategy.decorator.regex.LettersInRange;
import com.codingelab.validation.valid.strategy.decorator.regex.LettersOutRange;
import com.codingelab.validation.valid.strategy.decorator.regex.Numbers;
import com.codingelab.validation.valid.strategy.decorator.regex.NumbersInRange;
import com.codingelab.validation.valid.strategy.decorator.regex.NumbersOutRange;
import com.codingelab.validation.valid.strategy.decorator.regex.RegexBase;
import com.codingelab.validation.valid.strategy.decorator.regex.Spaces;
import com.codingelab.validation.valid.strategy.decorator.regex.SpecialCharacters;
import com.codingelab.validation.valid.strategy.decorator.regex.SpecialCharactersInRange;
import com.codingelab.validation.valid.strategy.decorator.regex.SpecialCharactersOutRange;
import com.codingelab.validation.valid.strategy.decorator.regex.WhiteSpaces;
import com.codingelab.validation.valid.strategy.decorator.regex._Regex;

/**
 * Validator is a factory that knows how to instantiate that knows how to validat the data entries.
 * All of the objects which will be instantiated via this factory will display the error notifications 
 * in English language since it is the default language of the API library. However, to change the default 
 * language of all the object which will be instantiate in the future via this factory use one of these methods: 
 * <br/>{@link Validator#selectLanguage(Lang lang)} or
 * <br/>{@link Validator#setLanguage(Lang lang)}
 * @author Abdulrahman Abdulhamid Alsaedi
 * @since 1.0.1
 */
public class Validator {
	private static Language language;
	/**
	 * This method gives you the ability to add your language if the API library does not support it. 
	 * By setting a new language using this method, any object which will be instantiated by the Validator class
	 * will use the language which you have set for the error notifications.<br/>
	 * To add a new language implements {@link com.codingelab.validation.languages.Language}
	 * @param language set a new language for error notifications, if null value was passed to the argument then 
	 * the English language will be selected.
	 */
	public static void setLanguage(Language language){
		if(language==null){
			Validator.language=LanguageFactory.get(Lang.ENGLISH);
		}
		else Validator.language=language;
	}
	/**
	 * This method gives you the ability to select one of the languages that exist in this API library.
	 * By selecting a language using this method, any object which will be instantiated by the Validator class
	 * will use the language which you have selected for the error notifications.
	 * @param lang select one of the built-in language in the API library for error notification,if null value 
	 * was passed to the argument then the English language will be selected.
	 */
	public static void selectLanguage(Lang lang){
		Language language=LanguageFactory.get(lang);
		Validator.language=language;
	}
	/**
	 * Any object which will be instantiated by the Validator class will have the English language for 
	 * the error notifications. This method can be used after changing the default language of the Validator class
	 * by using one of these methods:
	 * <br/>{@link Validator#selectLanguage(Lang lang)}
	 * <br/>{@link Validator#setLanguage(Language language)}<br/>
	 * This method more or less is equal to <b>Validator.selectLanguage(Lang.English);</b>
	 */
	public static void selectDefaultLanguage(){
		Language language=LanguageFactory.get(Lang.ENGLISH);
		Validator.language=language;
	}
	/**
	 * This method decorates the given regexes with each other and returns an object that knows how
	 * to validate the input
	 * @param regexs an array of type Regex that represents how the valid input should look like.
	 * @return StringValidation which knows how to validate the input based on the given regexes
	 * @throws NullPointerException if a null value was passed to the argument like Validator.decorate(null) or <br/>
	 * in case the array of type Regex has a null value like:<br/>
	 * Regex [] regexes={Regex.letters(),null,Regex.numbers()};<br/>
	 * Validator.decorate(regexes);
	 * @throws EmptyArrayException if nothing was passed to the argument like:<br/>
	 * <ul>
	 * 		<li>Validator.decorate();</li>
	 * 		<li>
	 * 		Passing an empty array like:
	 * <pre>
	 *   Regex [] regexs={};
	 *   Validator.decorate(regexs);
	 * </pre>
	 * 		</li>
	 * </ul>
	 * 
	 * @throws InvalidRange if you pass invalid <b>character</b> or <b>range</b> in one of the components 
	 * which accept range of characters<br>
	 * For example: <br>
	 * <ul>
	 * 		<li>Validator.decorate(Regex.lettersInRange("<b>2</b>ae")); lettersInRange cannot contain number.</li>
	 * 		<li>Validator.decorate(Regex.numbersOutRange("<b>#</b>12")); numberOutRange cannot contain special characters.</li>
	 * 		<li>Validator.decorate(Regex.lettersOutRange("<b>a-b-c</b>")); it should be Regex.lettersOutRange("<b>a-c</b>");</li>
	 * 		<li>Validator.decorate(Regex.numbersInRange("<b>0-1-2</b>")); it should be Regex.numberInRange("<b>0-2</b>");</li>
	 * </ul>
	 * @throws EmptyRange if the given range was empty. For example, Validator.decorate(Regex.lettersInRange(<b>""</b>));
	 * @throws NullRange if the given range was null. For example, Validator.decorate(Regex.numbersInRange(<b>null</b>));
	 * 
	 */
	public static StringValidation decorate(Regex...regexs) // TODO remove this NllPointerExcption
			throws Exception{
		if(regexs==null)throw new NullPointerException("Validator.decorate(null) is invalid argument."
				+ " decorate(Regex ... regex) method must contains one regex at least."
				+ " For example, Validator.decorate(Regex.letters());");
		if(regexs.length==0)throw new EmptyArrayException("decorate(Regex ... regex) method must"
				+ " contains one element of regex at least. For example:"
				+ " Validator.decorate(Regex.letters());");
		if(regexs.length>0){
			_Regex regex=new RegexBase();
			for(int i=0;i<regexs.length;i++){
				Regex validate=regexs[i];
				if(validate==null)throw new NullPointerException("An element of the array has null value...");
				switch(validate.getType()){
					case LETTERS:regex=new Letters(regex,validate.getRequirement());break; // TODO remove the 0
					case NUMBERS:regex=new Numbers(regex,validate.getRequirement());break;
					case SPECIAL_CHARACTERS:regex=new SpecialCharacters(regex,validate.getRequirement());
						break;
					case SPACES:regex=new Spaces(regex);break;
					case WHITE_SPACES:regex=new WhiteSpaces(regex);break;
					case LETTERS_IN_RANGE:regex=new LettersInRange
							(regex,validate.getRange(),validate.getRequirement());
						break;
					case LETTERS_OUT_RANGE:regex=new LettersOutRange
							(regex,validate.getRange(),validate.getRequirement());
						break;
					case NUMBERS_IN_RANGE:regex=new NumbersInRange
							(regex,validate.getRange(), validate.getRequirement());
					break;
					case NUMBERS_OUT_RANGE:regex=new NumbersOutRange
							(regex,validate.getRange(), validate.getRequirement());
					break;
					case SPECIAL_CHARACTERS_IN_RANGE:regex=new SpecialCharactersInRange
							(regex,validate.getRange(),validate.getRequirement());
						break;
					case SPECIAL_CHARACTERS_OUT_RANGE:regex=new SpecialCharactersOutRange
							(regex,validate.getRange(),validate.getRequirement());
						break;
					
				}
			}
			StringValidation validation=new DecorateValidation(regex);
			if(Validator.language!=null)
				validation.setLanguage(Validator.language);
			return validation;
		}
		return null;
	}
	/*public static Validation getEqualToOneOf(String input,boolean enableEmptyValue,boolean ignoreCase, String...strings)
	throws NullPointerException,EmptyArrayException{
		if(input==null)throw new NullPointerException("Input cannot be null");
		if(strings==null)throw new NullPointerException("Regex cannot be null");
		if(strings.length==0)throw new EmptyArrayException();
		Valid valid=new EqualToOneOf(enableEmptyValue,ignoreCase, strings);
		Validation v=new Validation(input,enableEmptyValue, valid);
		return v;
	}*/
	/**
	 * getEmail() is a predefined component<br/>
	 * This method returns an object of type Validation which knows how to validate the email address.</br>
	 * The rules of valid email is:</br>
	 * <ul>
	 * 		<li>Email name consist of letters, numbers, dot, underscore, and hyphen.</li>
	 * 		<li>Email name start with letter or number like <b>2</b>omar or <b>o</b>mar2.</li>
	 * 		<li>Email name cannot end with dot. Example: <b>omar.</b> is wrong while <b>omar.e</b> is correct.</li>
	 * 		<li>Each email contains only one @ Like <b>name@</b></li>
	 * 		<li>Domain name consist only of letters and number like omar@<b>gmail</b></li>
	 * 		<li>Each email contains top level domain (TLD) like omar@gmail<b>.com</b></li>
	 * 		<li>Each email may or may not contains sub-level domain like omar@gmail.com<b>.sa</b></li>
	 * </ul>
	 * @return Validation object that knows to validate an input(entry) that is representing an email.
	 */
	public static StringValidation getEmail(){
		return new EmailValidation();
	}
	/**
	 * getInstance() method returns an of object of type Validation that always will return false whenever you call 
	 * isValid() method, and will return empty String whenever you call getError() method.</br>
	 * The need of this object is to give you the ability to provide your implementation of type Valid and pass it
	 * to this object using setValid(Valid valid) method.
	 * @return StringValidation which knows how to validate the data entry based on your implementation
	 */
	public static StringValidation getInstance(){
		StringValidation validation=new ClientValidation();
		if(Validator.language!=null)
			validation.setLanguage(Validator.language);
		return validation;
	}
	/**
	 * getName() is a predefined component<br/>
	 * This method knows how to validate name. The rule of valid name are:<br>
	 * <ul>
	 * 		<li>Has at least two letters.</li>
	 * 		<li>Name can have spaces (To support the concept of the first and the last name).</li>
	 * </ul>
	 * This method is equal to: 
	 * <pre>
	 * try {
	 *     StringValidation validation=Validator.decorate(Regex.letters(2),Regex.spaces());
	 * }catch (Exception e) {}
	 * </pre>
	 * To remove the extra spaces from name see {@link Validation#repair()}
	 * @return StringValidation which knows how to validate names
	 */
	public static StringValidation getName(){
		try {return Validator.decorate(Regex.letters(2),Regex.spaces());}catch (Exception e) {}
		return null;
	}
	/**
	 * getName() is a predefined component<br/>
	 * This method knows how to validate username. The rule of valid username are:<br>
	 * <ul>
	 * 		<li>Username only consists of English letters and numbers.</li>
	 * 		<li>Username has to be between 4 to 16 characters.</li>
	 * 		<li>Username must have 1 letter at least.</li>
	 * </ul>
	 * This method is equal to:<br/>
	 *<pre>
	 *try{
	 *    StringValidation validation=Validator.decorate(Regex.letters(),Regex.numbers());
	 *    validation.setLengthBetween(4, 16);
	 *}catch (Exception e) {}
	 *</pre>
	 * @return StringValidation which knows how to validate the username
	 */
	public static StringValidation getUsername(){
		try{
			Regex [] regex={Regex.lettersInRange("a-zA-Z",1),Regex.numbers()};
			StringValidation validation=Validator.decorate(regex);
			validation.setLengthBetween(4, 16);
			return validation;
		}catch (Exception e) {}
		return null;
	}
	/**
	 * getPassword() is a predefined component<br/>
	 * This method knows how to validate password. The rule of valid password are:<br>
	 * <ul>
	 * 		<li>Password must have at least 2 letters.</li>
	 * 		<li>Password must have at least 2 numbers.</li>
	 * 		<li>Password must have at least 2 special characters.</li>
	 * 		<li>Password consist of 8 characters at least.</li>
	 * 		<li>Password cannot have tabs, enters, and vertical spaces.</li>
	 * </ul>
	 * This method is equal to:<br/>
	 *<pre>
	 *try{
	 *    Regex [] regex={Regex.letters(2),Regex.numbers(2),Regex.specialCharacters(2),Regex.spaces()}
	 *    StringValidation validation=Validator.decorate(regex);
	 *    validation.setMinimumLength(8);
	 *}catch (Exception e) {}
	 *</pre>
	 * @return StringValidation which knows how to validate the password
	 */
	public static StringValidation getPassword(){
		try{
			StringValidation validation=Validator.decorate(Regex.letters(2),Regex.numbers(2),
					Regex.specialCharacters(2),Regex.spaces());
			validation.setMinimumLength(8);
			return validation;
		}catch (Exception e) {}
		return null;
	}
	/**
	 * getMix() is a predefined component<br/>
	 * This method accepts letters, numbers, special characters and only space from the entire white space range.<br/>
	 * This method is useful in case programmers care about the input length yet do not care about the type of characters
	 * which the input may or may not contain.<br/>
	 * This method is equal to:<br/>
	 * <pre>
	 *try{
	 *    StringValidation validation=Validator.decorate(Regex.letters(),
	 *                                                   Regex.numbers(),
	 *                                                   Regex.specialCharacters(),
	 *                                                   Regex.spaces());
	 *}catch (Exception e) {}
	 * <pre>
	 * @return StringValidation which accepts all characters except tab, enters and vertical spaces
	 */
	public static StringValidation getMix(){
		try{
			return Validator.decorate(Regex.letters(),Regex.numbers(),
					Regex.specialCharacters(),Regex.spaces());
		}catch (Exception e) {}
		return null;
	}
	/**
	 * getMixAll() is a predefined component<br/>
	 * This method accepts characters as valid characters. 
	 * It is useful in case programmers care about the input length yet do not care about the type of characters
	 * which the input may or may not contain.<br/>
	 * This method is equal to:<br/>
	 * <pre>
	 *try{
	 *    StringValidation validation=Validator.decorate(Regex.letters(),
	 *                                                   Regex.numbers(),
	 *                                                   Regex.specialCharacters(),
	 *                                                   Regex.whiteSpaces());
	 *}catch (Exception e) {}
	 * <pre>
	 * @return StringValidation which accepts all characters (You may want to change the length behaviour).
	 */
	public static StringValidation getMixAll(){
		try{
			return Validator.decorate(Regex.letters(),Regex.numbers(),
					Regex.specialCharacters(),Regex.whiteSpaces());
		}catch (Exception e) {}
		return null;
	}
	/*public static FileValidation getFile() throws Exception{
		FileValidation validation=new FileValidation();
		return validation;
	}*/
	
}
