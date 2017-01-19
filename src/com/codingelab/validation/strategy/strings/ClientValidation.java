package com.codingelab.validation.strategy.strings;

import com.codingelab.validation.Valid;
/*
 * @author Abdulrahman Abdulhamid Alsaedi
 * @version 1.0.1
 */
public final class ClientValidation extends StringValidation {

	public ClientValidation() {
		// the client has to provide it valid instance via setValid method
		// to use this object 
		super(null);
	}
	@Override
	public void setValid(Valid<String> valid) {
		super.setValidValue(valid);	
	}
	
	@Override
	public void setLength(Valid<String> length) {
		super.setLengthValue(length);
	}

	

}
