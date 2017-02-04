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
import com.codingelab.validation.errors.RequiredMoreSpecialCharacters;
import com.codingelab.validation.errors.SpecialCharactersOutRange;
import com.codingelab.validation.exceptions.EmptyRange;
import com.codingelab.validation.exceptions.InvalidRange;
import com.codingelab.validation.exceptions.NullRange;
import com.codingelab.validation.exceptions.ValidationException;
/**
 * @author Abdulrahman Abdulhamid Alsaedi
 * @since 1.0.1
 */
public final class SpecialCharactersInRange extends _RegexDecorator {
	private final String range;
	private Error errorRange;
	private boolean enableHyphen;
	private boolean enableBackslash;
	public SpecialCharactersInRange(_Regex compoment,String range,int required) 
		throws ValidationException
	{
		super(compoment,required);
		if(range==null)throw new NullRange();
		if(range.isEmpty())throw new EmptyRange();
		//this.enableHyphen=enableHyphen;
		//if(super.findError(range,"[-]")) throw new InvalidRange("Remove - from the range... If you need hyphen(-) then use Regex.specialCharactersInRange(String range,boolean enableHyphen) method");
		//if any character found that is not special character then I will throw exception
		this.enableHyphen=false;
		this.enableBackslash=false; 
		String addition="";
		if(range.contains("-")){
			this.enableHyphen=true;
			if(range.length()>1){
				range=range.replace("-","");
				addition="-";
			}
		}
		if(range.contains("\\")){
			this.enableBackslash=true;
			range=range.replace("\\","\\\\");
		}
		//[\\w&&[^_]][\\s]
		if(super.findErrorWithException(range,"[\\s\\w&&[^_]]")) throw new InvalidRange("SpecialCharactersInRange can only contains special characters.");
		String newRange=range.replace("\\","");
		if(enableBackslash)newRange+="\\";
		this.errorRange=new SpecialCharactersOutRange(newRange+addition);	
		super.setRegex("[^\\w\\s_&&["+range+"]]");
		//super.setRequired(required);
		Error error=new RequiredMoreSpecialCharacters(required);
		super.setRequiredRegex("(_|[^\\s\\w])");
		super.setRequiredError(error);
		this.range=range;
		// to Opposite Range exception... 
		super.findErrorWithException("",this.SPOutRange());
	}
	private final String SPOutRange(){
		String hypenPosition1="";
		String hypenPosition2="";
		if(this.enableHyphen){
			hypenPosition1="-";
		}else{
			hypenPosition2="[-]";
		}
		// backslash issue
		String slashPosition1="";
		String slashPosition2="";
		if(this.enableBackslash){
			slashPosition1="\\\\";
		}else{
			slashPosition2="[\\\\]";
		}
		return "[^"+range+"&&[[^\\w\\s"+hypenPosition1+slashPosition1+"][_]"+hypenPosition2+slashPosition2+"]]";
	}
	// additional error detection
	@Override protected boolean isInvalid(String input){
		return super.findError(input,this.SPOutRange());
	}
	@Override public String repair(String input) {
		try{
			input=super.repairInput(input, this.SPOutRange());
			input=super.repair(input);
		}catch(Exception exception){
			exception=new Exception("Invalid syntax ... check Regexs arguments. Exception may occur because you have inserted an opposite range");
			exception.printStackTrace();
		}
		return input;
	}
	@Override protected List<Error> getRangeError(String input){
		/*List<Error>errors=new ArrayList<>();
		int times=super.count(input, "[^\\w\\s"+range+"]");
		Error error=new Error();
		if(times>0){
			error.setTimes(times);
			error.setErrorType(ErrorType.SPECIAL_CHARACTERS_OUT_RANGE);
			error.setRange(this.range);
			errors.add(error);
		}*/
		List<Error>errors=new ArrayList<>();
		if(this.isInvalid(input)) errors.add(errorRange);
		return errors;
	}

	@Override protected String typeToString() {
		return RegexType.SPECIAL_CHARACTERS_IN_RANGE.toString().toLowerCase()
				+super.getComponent().typeToString();
	}

}
