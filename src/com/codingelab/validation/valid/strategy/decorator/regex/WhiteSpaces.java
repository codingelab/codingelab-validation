package com.codingelab.validation.valid.strategy.decorator.regex;

import com.codingelab.validation.errors.ErrorType;
import com.codingelab.validation.errors.NoError;
/*
 * 
 * @author Abdulrahman Abdulhamid Alsaedi
 * @since 23 September 2016
 * @version 1.0.1
 *
 */
public final class WhiteSpaces extends _RegexDecorator{
	public WhiteSpaces(_Regex compoment) {
		super(compoment,0);
		super.setRegex("[\\s]");
		//super.setRequired(0);
		super.setRequiredError(new NoError());
	}

	@Override protected String typeToString() {
		return RegexType.WHITE_SPACES.toString().toLowerCase()
				+super.getComponent().typeToString();
	}
}
