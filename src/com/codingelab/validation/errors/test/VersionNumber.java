package com.codingelab.validation.errors.test;

import com.codingelab.validation.errors.Error;

public class VersionNumber implements Error<Void>{

	@Override
	public int getErrorNumber() {
		return 202352425;
	}

	@Override
	public Void get() {
		return null;
	}

}
