package com.codingelab.validation.languages;
/*
 * @author Abdulrahman Abdulhamid Alsaedi
 * @version 1.0.1
 */
public final class LanguageFactory {
	private LanguageFactory(){}
	public static Language get(Lang lang){
		if(lang==null)return new English();
		switch (lang) {
			case ENGLISH:return new English();
			case ARABIC:return new Arabic();
			case GERMAN: return new German();
			case GREEK:return new Greek();
			default:return new English();
		}
	}
}
