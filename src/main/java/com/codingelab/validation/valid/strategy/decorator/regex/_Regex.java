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

import com.codingelab.validation.Valid;
/**
 * Each Decorator Design pattern has a common interface for both of the baseComponent
 * and the Decorator component and this interface is this abstract class
 * @author Abdulrahman Abdulhamid Alsaedi
 * @since 1.0.1
 * 
 */
public abstract class _Regex implements Valid<String>{
							/*Properties*/
	/*
	 *  isTop is to determine which decorator is in the top
	 *  The need of this property is because the top decorator has to check 
	 *  the general validation only once while the below decorators will not 
	 *  check the general validation since the top did check it.
	 */
	private boolean isTop;
	
	
	
							/*Getter method(s)*/
	protected boolean isTop(){
		return this.isTop;
	}
	
	
	
							/*Setter method(s)*/
	protected void setTop(boolean isTop){this.isTop=isTop;}
	
	
	
	
							/*Abstract method(s)*/
		
	/*
	* This method will return the entire Regex as one unit. For example:
	* Regex base=new RegexBase();
	* Regex letter=new Letter(base);
	* Regex number=new Number(letter);
	* String regex=number.getRegex();
	* regex output: [\\w&&[\\D]]*[\\d]*
	* This [\\w&&[\\D]]* came from Letters instance
	* this [\\d]* came from Numbers instance
	*/
	protected abstract String getRegex();
	/*
	* This method will return the entire Type as one unit. For example:
	* Regex base=new RegexBase();
	* Regex letter=new Letter(base);
	* Regex number=new Number(letter);
	* String type=number.typeToString();
	* type output: numbersletters
	* This part: letters came from Letters instance
	* this part: numbers came from Numbers instance
	* this method will just help us in the general checking, weather an input can 
	* contains letters, numbers, special characters, and etc.
	* 
	*/
	protected abstract String typeToString();
	public abstract String repair(String input);
	
}
