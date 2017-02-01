package com.codingelab.validation.errors;

import com.codingelab.validation.languages.Language;

/** 
 * This interface apply the strategy design pattern to create many types of errors and each one of these errors 
 * represent a strategy that hold a unique number to help the {@link Language} interface to translate the error
 * into readable language.</br>
 * By using this concept know we no longer need to memorize the error number we just need to instantiate the right
 * object and that object hold the error number. We also can send a message to the Language {@link Language}
 * if needed by the help of {@link #get()} method
 * @author Abdulrahman Abdulhamid Alsaedi
 * @version 1.0.1
 */
public interface Error <R> {
	/**
	 * Hold a unique number that identify the error
	 * @return a number which helps the Language interface to translate the error into readable language
	 */
	int getErrorNumber();
	/**
	 * Hold a message that can be used by the language interface
	 * @return a message based on the type variable (Error&ltR&gt).
	 */
	R get();
}
