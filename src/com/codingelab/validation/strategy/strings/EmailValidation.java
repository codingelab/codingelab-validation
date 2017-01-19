package com.codingelab.validation.strategy.strings;

import com.codingelab.validation.Valid;
import com.codingelab.validation.valid.strategy.Email;
/*
 * @author Abdulrahman Abdulhamid Alsaedi
 * @version 1.0.1
 */
public class EmailValidation extends StringValidation{

	public EmailValidation() {
		super(new Email());
	}
	
	@Override public void setLength(Valid<String> length) {
		super.setLengthValue(length);
	}
	@Override public void setValid(Valid<String> valid) {}

}
