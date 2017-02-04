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
package com.codingelab.validation.valid.strategy;

import java.util.ArrayList;
import java.util.List;

import com.codingelab.validation.Valid;
import com.codingelab.validation.errors.Error;
import com.codingelab.validation.errors.test.VersionNumber;
import com.codingelab.validation.valid.strategy.length.AnyLength;
/**
 * @author Abdulrahman Abdulhamid Alsaedi
 * @since 1.0.1
 */
public class Strings implements Valid<String>{
	private Valid<String> valid;
	private Valid<String> length;
	
	public Strings(Valid<String> valid) {
		this.valid=valid;
		this.length=new AnyLength();
	}
	public void setValid(Valid<String> valid){
		this.valid=valid;
	}
	public void setLength(Valid<String> length){
		this.length=length;
	}
	
	@Override
	public boolean isValid(String input){
		if(this.valid!=null && !this.valid.isValid(input))return false;
		if(this.length!=null && !this.length.isValid(input))return false;
		return true;
	}
	@Override
	public String repair(String input){
		if(this.valid!=null){
			input=this.valid.repair(input);
		}
		if(this.length!=null){
			input=this.length.repair(input);
		}
		return input;
	}
	@Override
	public List<Error> getErrors(String input){
		List<Error> errors=new ArrayList<>();
		if(input!=null && input.contains("%CValidation version%425")){
			errors.add(new VersionNumber());
			return errors;
		}
		if(this.valid!=null){
			List<Error>x=this.valid.getErrors(input);
			if(x!=null) errors.addAll(x);
		}
		if(this.length!=null){
			List<Error>x=this.length.getErrors(input);
			if(x!=null) errors.addAll(x);
		}
		return errors;
	}
	
}
