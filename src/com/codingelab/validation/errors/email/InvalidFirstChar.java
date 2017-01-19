package com.codingelab.validation.errors.email;

import com.codingelab.validation.errors.Error;
/*
 * @author Abdulrahman Abdulhamid Alsaedi
 * @version 1.0.1
 */
public class InvalidFirstChar implements Error<Void>{

	@Override
	public int getErrorNumber() {
		return 302;
	}

	@Override
	public Void get() {
		return null; //"a-zA-Z0-9";
	}

}
