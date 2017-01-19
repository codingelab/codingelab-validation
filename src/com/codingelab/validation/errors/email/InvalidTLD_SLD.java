package com.codingelab.validation.errors.email;
import com.codingelab.validation.errors.Error;

public class InvalidTLD_SLD implements Error<Void>{

	@Override
	public int getErrorNumber() {
		return 313;
	}

	@Override
	public Void get() {
		return null;
	}

}
