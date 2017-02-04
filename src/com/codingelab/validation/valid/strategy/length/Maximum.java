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
import com.codingelab.validation.errors.LengthAboveMaxLimit;
/**
 * @author Abdulrahman Abdulhamid Alsaedi
 * @since 1.0.1
 */
public final class Maximum implements Valid<String>{
	private int max;
	public Maximum(int max) {
		this.max=max;
	}
	@Override public boolean isValid(String input) {
		return input.length()<=this.max;
	}

	@Override public List<Error> getErrors(String input){
		List<Error>errors=new ArrayList<>();
		if(!this.isValid(input))
			errors.add(new LengthAboveMaxLimit(this.max));
		return errors;
	}
	@Override public String repair(String input){
		if(input.length()>this.max)
			return input.substring(0,this.max) ;
		return input;
	}
}
