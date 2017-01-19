package com.codingelab.validation;
/*
 * This Interface provides two methods to be implemented.<br>
 * The first method Checks if there is an error in the input or not.<br>
 * The second method returns the input error if there is.
 * @author Abdulrahman Abdulhamid Alsaedi
 * @since 23 September 2016
 * @version 1.0.1
 *
 */
import java.util.List;
/*
 * @author Abdulrahman Abdulhamid Alsaedi
 * @version 1.0.1
 */
import com.codingelab.validation.errors.Error;
import com.codingelab.validation.exceptions.ValidationException;
public interface Valid<T>{
	boolean isValid(T input);
	T repair(T input);
	List<Error> getErrors(T input);
}
