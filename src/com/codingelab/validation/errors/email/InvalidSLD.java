package com.codingelab.validation.errors.email;

import com.codingelab.validation.errors.Error;
/*
 * @author Abdulrahman Abdulhamid Alsaedi
 * @version 1.0.1
 */
public class InvalidSLD  implements Error<Void>{

	@Override
	public int getErrorNumber() {
		return 312;
	}

	@Override
	public Void get() {
		return null; //"a-zA-Z";
	}

}
