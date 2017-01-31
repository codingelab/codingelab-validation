package com.codingelab.validation.languages;

import com.codingelab.validation.errors.Error;
/*
 * @author Abdulrahman Abdulhamid Alsaedi
 * @version 1.0.1
 */
public interface Language {
	public String getVariable();
	public String translate(Error error);
}
