/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.codingelab.validation;
import java.util.List;

import com.codingelab.validation.errors.Error;
import com.codingelab.validation.languages.Translator;
/**
 * This interface has the logic of how to validate the data entry, find out the errors in the given input,
 * and it also responsible for fixing input.
 * @author Abdulrahman Abdulhamid Alsaedi
 * @since 1.0.1
 * @see
 *  <ul>
 * 		<li><a href='https://github.com/codingelab/validation'>Documentation</a></li>
 * 		<li><a href='https://www.youtube.com/watch?v=yph8ga-Ciec&list=PLGPi5XFpVjY8iSuKXsA7lLQvwSEvvyFfj'>English Tutorial</a></li>
 * 		<li><a href='https://www.youtube.com/watch?v=cWVJsD0xd5g&index=1&list=PLvZcoGWLT5r3I6NMmm8GQV1vk-oxPPEQA'>Arabic Tutorial</a></li>
 * </ul>
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
