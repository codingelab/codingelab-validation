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
package com.codingelab.validation.valid.strategy.length;

import java.util.ArrayList;
import java.util.List;

import com.codingelab.validation.Valid;
import com.codingelab.validation.errors.Error;
import com.codingelab.validation.errors.LengthNotBetween;
/**
 * @author Abdulrahman Abdulhamid Alsaedi
 * @since 1.0.1
 */
public final class Between implements Valid<String> {
	private int [] between;
	public Between(int start,int end) {
		this.between=new int[2];
		if(start<=end){
			this.between[0]=start;
			this.between[1]=end;
			
		}else{
			this.between[0]=end;
			this.between[1]=start;
		}
	}
	@Override public boolean isValid(String input) {
		return (input.length()>=this.between[0] && input.length()<=this.between[1]);
	}
	@Override
	public List<Error> getErrors(String input) {
		List<Error>errors=new ArrayList<>();
		if(!this.isValid(input)){
			errors.add(new LengthNotBetween(this.between));
		}
		return errors;
	}
	@Override
	public String repair(String input){
		if(input.length()>between[1])
			return input.substring(0,between[1]) ;
		return input;
	}
}
