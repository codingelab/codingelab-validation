package com.codingelab.validation.valid.strategy;

import java.util.ArrayList;
import java.util.List;

import com.codingelab.validation.Valid;
import com.codingelab.validation.errors.Error;
/*
 * @author Abdulrahman Abdulhamid Alsaedi
 * @version 1.0.1
 */
public class NoError<T> implements Valid<T>{

	@Override
	public boolean isValid(T input) {
		return true;
	}

	@Override
	public List<Error> getErrors(T input) {
		return new ArrayList<>();
	}

	@Override
	public T repair(T input){
		return input;
	}

}
