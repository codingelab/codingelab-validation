package com.codingelab.validation.languages;

import com.codingelab.validation.Validation;
import com.codingelab.validation.errors.Error;
/** 
 * This class responsible for translating the errors into readable language by delegating the task of translation
 * to the Language interface
 * @author Abdulrahman Abdulhamid Alsaedi
 * @since 1.0.1
 * @see Language
 */
public final class Translator{
	public Language defaultLang;
	public Language override;
	public Translator(){
		this.defaultLang=LanguageFactory.get(Lang.ENGLISH);
		this.override=null;
	}
	/**
	 * This method knows how to translate the error into readable language
	 * @param variable allows the method to change the language variable
	 * @param error which needed to be translated into readable language
	 * @return String which is the translation of the error
	 * @see Language
	 */
	public String translate(String variable,Error error){
		if(error==null)return "";
		if(error.getErrorNumber()==202352425)return Validation.versionNo();
		String translate="";
		if(isOverrode()){
			translate=override.translate(error);
			if((translate!=null || !translate.isEmpty()) && variable!=null){
				translate=translate.replaceAll("\\b"+this.override.getVariable()+"\\b",variable);
			}
		}
		if(translate==null || translate.isEmpty()){
			// the defaultLang should not return null value 
			translate=defaultLang.translate(error);
			if(variable!=null && translate!=null){
				translate=translate.replaceAll("\\b"+this.defaultLang.getVariable()+"\\b",variable);
			}
		}
		// to avoid any mistakes
		if(translate==null)translate="";
		return translate;	
	}
	/**
	 * A setter method</br>To change the default language (English) for error notification
	 * @param language which will override the default language (English)
	 */
	public void setLanguage(Language language){this.override=language;}
	/**
	 * To see if the default language has been overridden or not 
	 * @return true if a language has been set via {@link Translator#setLanguage(Language)} method else it will return false
	 */
	public boolean isOverrode(){return override!=null;}
	/**
	 * Get the language variable by delegating the task to the language interface
	 * @return the variable language
	 */
	public String getLanguageVariable(){
		return this.override==null?this.defaultLang.getVariable():this.override.getVariable();
	}
}
