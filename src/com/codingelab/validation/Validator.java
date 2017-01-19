package com.codingelab.validation;

import com.codingelab.validation.exceptions.EmptyArrayException;
import com.codingelab.validation.strategy.files.FileValidation;
import com.codingelab.validation.strategy.strings.ClientValidation;
import com.codingelab.validation.strategy.strings.DecorateValidation;
import com.codingelab.validation.strategy.strings.EmailValidation;
import com.codingelab.validation.strategy.strings.StringValidation;
import com.codingelab.validation.translator.Lang;
import com.codingelab.validation.translator.Language;
import com.codingelab.validation.translator.LanguageFactory;
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
 * Validator is a factory that knows how to instantiate the right object properly. All of the objects which will
 * be instantiated via this factor will display the error notification in English language since it is the default language
 * of the API library. However, to change the default language of all the object which will be instantiate in the 
 * future via this factor use one of these methods: 
 * <br/>{@link Validator#selectLanguage(Lang lang)} or
 * <br/>{@link Validator#setLanguage(Lang lang)}
 * @author Abdulrahman Abdulhamid Alsaedi
 * @version 1.0.1
 */
public class Validator {
	private static Language language;
	
	public static void setLanguage(Language language)throws Exception{
		if(language==null)throw new NullPointerException("Language cannot be null");
		Validator.language=language;
	}
	public static void selectLanguage(Lang lang){
		Language language=LanguageFactory.get(lang);
		Validator.language=language;
	}
	public static void selectDefaultLanguage(){
		Language language=LanguageFactory.get(Lang.ENGLISH);
		Validator.language=language;
	}
	
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
	 * getEmail() method return an object of validation which knows how to validate an email.</br>
	 * The rule of valid email is:</br>
	 * 1- Email name consist of letters, numbers, dot, underscore, and hyphen.</br>
	 * 2- Email name start with letter or number. Like <b>2</b>omar, <b>o</b>mar2.</br>
	 * 3- Email name cannot end with dot. Example: <b>omar.</b> is wrong while <b>omar.e</b> is correct.</br> 
	 * 4- Each email contains only one @ . Like, <b>name@</b></br>
	 * 5- Domain name consist only of letters and number. like, omar@<b>gmail</b></br>
	 * 6- Each email contains top level domain (TLD). like, omar@gmail<b>.com</b></br>
	 * 7- Each email may or may not contains sub-level domain. Like, omar@gmail.com<b>.sa</b>
	 * @return Validation object that knows to validate an input(entry) that is representing an email.
	 */
	public static StringValidation getEmail(){
		return new EmailValidation();
	}
	/**
	 * getInstance() method return an of object of type Validation that always will return false whenever you call 
	 * isValid() method, and will return empty String whenever you call getError() method.</br>
	 * The need of this object is to provide your own implementation of type Valid and pass it to this object
	 * using setValid(Valid valid) method.
	 * @return
	 */
	public static StringValidation getInstance(){
		StringValidation validation=new ClientValidation();
		if(Validator.language!=null)
			validation.setLanguage(Validator.language);
		return validation;
	}
	public static StringValidation getName(){
		try {
			return Validator.decorate(Regex.letters(2),Regex.spaces());
		} catch (Exception e) {}
		return null;
	}
	public static StringValidation getUsername(){
		try{
			StringValidation validation=Validator.decorate(Regex.letters(),Regex.numbers());
			validation.setLengthBetween(4, 16);
			return validation;
		}catch (Exception e) {}
		return null;
	}
	public static StringValidation getPassword(){
		try{
			StringValidation validation=Validator.decorate(Regex.letters(2),Regex.numbers(2),
					Regex.specialCharacters(2),Regex.spaces());
			validation.setMinimumLength(8);
			return validation;
		}catch (Exception e) {}
		return null;
	}
	public static StringValidation getMix(){
		try{
			return Validator.decorate(Regex.letters(),Regex.numbers(),
					Regex.specialCharacters(),Regex.spaces());
		}catch (Exception e) {}
		return null;
	}
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
