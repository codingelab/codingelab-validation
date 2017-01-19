package com.codingelab.validation.translator;
/*
 * @author Abdulrahman Abdulhamid Alsaedi
 * @version 1.0.1
 */
public final class LanguageFactory {
	private LanguageFactory(){}
	public static Language get(Lang lang){
		switch (lang) {
			case ENGLISH:return new English();
			case ARABIC:return new Arabic();
			case GERMAN: return new German();
			case GREEK:return new Greek();
			default:return new English();
		}
	}
}
