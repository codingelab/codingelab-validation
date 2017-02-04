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

import java.util.ArrayList;
import java.util.List;

import com.codingelab.validation.errors.Error;
import com.codingelab.validation.errors.NumbersOutRange;
import com.codingelab.validation.errors.RequiredMoreNumbers;
import com.codingelab.validation.exceptions.EmptyRange;
import com.codingelab.validation.exceptions.InvalidRange;
import com.codingelab.validation.exceptions.NullRange;
import com.codingelab.validation.exceptions.ValidationException;
/**
 * @author Abdulrahman Abdulhamid Alsaedi
 * @since 1.0.1
 */
public final class NumbersInRange extends _RegexDecorator{
	private final String range;
	private Error errorRange;
	private final String numbersOutRange;
	public NumbersInRange(_Regex compoment,String range,int required)
			throws ValidationException
	{
		super(compoment,required);
		//if(range.equals(null))throw new NullPointerException("Range cannot be empty");
		if(range==null)throw new NullRange();
		if(range.isEmpty())throw new EmptyRange();
		/*
		 * if any character found that is not letter in any language then I will go inside the if
		 * condition
		 */
		if(super.findErrorWithException(range,"[\\D]")){
			/*
			 * I will re-check again maybe that character is - which means range like 1-9 
			 * if it is range I am not going to throw exp unless if the range was like this
			 * 1-2-3 then is is useless range, it should be 1-3 only.
			 */
			if(!super.matches(range,"([\\d]([-]?[\\d]{1})?)+")){
				throw new InvalidRange("NumbersInRange can only contains numbers or valid range of numbers.");
			}
				
		}
		super.setRegex("[[\\d]&&["+range+"]]");
		//super.setRequired(required);
		Error error=new RequiredMoreNumbers(required);
		super.setRequiredError(error);
		super.setRequiredRegex("[\\d]");
		this.range=range;
		this.numbersOutRange="[[\\d]&&[^"+this.range+"]]";
		// to Opposite Range exception... 
		super.findErrorWithException("",this.numbersOutRange);
		this.errorRange=new NumbersOutRange(range);
	}

	// additional error detection
	@Override protected boolean isInvalid(String input){
		return super.findError(input,this.numbersOutRange );
	}
	@Override public String repair(String input) {
		try{
			input=super.repairInput(input, this.numbersOutRange);
			input=super.repair(input);
		}catch(Exception exception){
			exception=new Exception("Invalid syntax ... check Regexs arguments. Exception may occur because you have inserted an opposite range");
			exception.printStackTrace();
		}
		return input;
	}
	@Override protected List<Error> getRangeError(String input){
		/*List<Error>errors=new ArrayList<>();
		int times=super.count(input, "[[\\d]&&[^"+range+"]]");
		Error error=new Error();
		if(times>0){
			error.setTimes(times);
			error.setErrorType(ErrorType.NUMBERS_OUT_RANGE);
			error.setRange(this.range);
			errors.add(error);
		}*/
		List<Error>errors=new ArrayList<>();
		if(this.isInvalid(input)) errors.add(errorRange);
		return errors;
	}

	@Override protected String typeToString() {
		return RegexType.NUMBERS_IN_RANGE.toString().toLowerCase()
				+super.getComponent().typeToString();
	}

}
