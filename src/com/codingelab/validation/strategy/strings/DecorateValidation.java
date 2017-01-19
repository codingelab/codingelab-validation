package com.codingelab.validation.strategy.strings;

import com.codingelab.validation.Valid;
import com.codingelab.validation.valid.strategy.decorator.regex._Regex;
/*
 * @author Abdulrahman Abdulhamid Alsaedi
 * @version 1.0.1
 */
public final class DecorateValidation extends StringValidation{
	private _Regex regex;
	public DecorateValidation(_Regex regex) throws Exception{
		super(regex);
		this.regex=regex;
	}
	@Override public void setValid(Valid<String> valid) {}
	@Override public void setLength(Valid<String> length) {
		super.setLengthValue(length);
	}
	public String removeInvalidChar(){
		super.setInput(regex.repair(super.getInput()));
		return super.getInput();
	}	 
}
