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
package com.codingelab.validation.valid.strategy.decorator.regex;

import com.codingelab.validation.errors.Error;
import com.codingelab.validation.errors.RequiredMoreNumbers;
/**
 * @author Abdulrahman Abdulhamid Alsaedi
 * @since 1.0.1
 *
 */
public final class Numbers extends _RegexDecorator {

	public Numbers(_Regex compoment, int required) {
		super(compoment,required);
		super.setRegex("[\\d]");
		//super.setRequired(required);
		Error error=new RequiredMoreNumbers(required);
		super.setRequiredRegex("[\\d]");
		super.setRequiredError(error);
	}
/*	@Override
	public boolean isValid(String input){
		// general validation
		if(!super.isValid(input))return false;
		// has requirement
		if(!super.containsRequirments(input))return false;
		// delegate the task to the next node to validate the input
		return super.getComponent().isValid(input);
	}
	*/
/*	@Override
	public List<Error> getErrors(String input) {
		List<Error>errors=new ArrayList<>();
		errors.addAll(super.getErrors(input));
		errors.addAll(super.getRequirmentError(input));
		errors.addAll(super.getComponent().getErrors(input));
		return errors;
	}*/

	@Override protected String typeToString() {
		return RegexType.NUMBERS.toString().toLowerCase()
				+super.getComponent().typeToString();
	}

}
