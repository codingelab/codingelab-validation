package com.codingelab.validation;
import java.util.List;

import com.codingelab.validation.errors.Error;
import com.codingelab.validation.languages.Translator;
/**
 * This interface has the logic of how to validate the data entry, find out the errors in the given input,
 * and it also responsible for fixing input.
 * @author Abdulrahman Abdulhamid Alsaedi
 * @since 31 January 2017
 * @version 1.0.1
 */
public interface Valid<T>{
	/**
	 * This method has the logic of how to validate the data entry
	 * @param input which needed to be validated 
	 * @return true if the input is valid, else it will return false
	 */
	boolean isValid(T input);
	/**
	 * This method has to the logic of how to fix the data entry 
	 * @param input which needed to be fixed/repaired
	 * @return return the fixed/repaired input
	 */
	T repair(T input);
	/**
	 * This method has the logic to find the mistakes in the given input
	 * @param input which may has mistakes
	 * @return list of errors which the {@link Translator} class know how to translate them into readable language
	 */
	List<Error> getErrors(T input);
}
