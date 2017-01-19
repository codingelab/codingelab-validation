package com.codingelab.validation.exceptions;
/*
 * @author Abdulrahman Abdulhamid Alsaedi
 * @version 1.0.1
 */
public class EmptyArrayException extends ValidationException{
	public EmptyArrayException(){
		this("Array must contains one element at least.");
	}
	public EmptyArrayException(String msg){
		super(msg);
	}

}
