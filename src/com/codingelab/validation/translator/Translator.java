package com.codingelab.validation.translator;

import com.codingelab.validation.Validation;
import com.codingelab.validation.errors.Error;
/*
 * @author Abdulrahman Abdulhamid Alsaedi
 * @version 1.0.1
 */
public final class Translator{
	public Language defaultLang;
	public Language override;
	public Translator(){
		this.defaultLang=LanguageFactory.get(Lang.ENGLISH);
		this.override=null;
	}
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
	public void setLanguage(Language language){this.override=language;}
	public boolean isOverrode(){return override!=null;}
	public String getLanguageVariable(){
		return this.override==null?this.defaultLang.getVariable():this.override.getVariable();
	}
}
