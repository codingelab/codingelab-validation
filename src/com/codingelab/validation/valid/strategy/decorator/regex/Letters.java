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
import com.codingelab.validation.errors.RequiredMoreLetters;
/**
 * @author Abdulrahman Abdulhamid Alsaedi
 * @since 1.0.1
 *
 */
public final class Letters extends _RegexDecorator {
	public Letters(_Regex compoment,int required){
		super(compoment,required);// passing the base component
		super.setRegex("[\\w&&[^\\d_]]"); // passing the Regex
		//super.setRequired(required); // passing how many letters are needed from the above Regex
		// if there is an error in the requirement this will be the error type
		Error error=new RequiredMoreLetters(required);
		super.setRequiredRegex("[\\w&&[^\\d_]]");
		super.setRequiredError(error);
		
	}
	@Override
	public String typeToString() {
		return RegexType.LETTERS.toString().toLowerCase()
				+super.getComponent().typeToString();
		
	}

}
