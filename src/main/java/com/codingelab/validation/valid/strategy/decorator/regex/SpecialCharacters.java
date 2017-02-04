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
import com.codingelab.validation.errors.RequiredMoreSpecialCharacters;
/**
 * @author Abdulrahman Abdulhamid Alsaedi
 * @since 1.0.1
 */
public final class SpecialCharacters extends _RegexDecorator{

	public SpecialCharacters(_Regex compoment,int required) {
		super(compoment,required);
		//super.setRegex("[^\\w\\s]"); // passing the Regex 
		super.setRegex("(_|[^\\s\\w])");
		//super.setRequired(required); // passing how many letters are needed from the above Regex
		// if there is an error in the requirement this will be the error type
		Error error=new RequiredMoreSpecialCharacters(required);
		super.setRequiredRegex("(_|[^\\s\\w])");
		super.setRequiredError(error);
	}

	@Override protected String typeToString() {
		return RegexType.SPECIAL_CHARACTERS.toString().toLowerCase()
				+super.getComponent().typeToString();
	}
}
