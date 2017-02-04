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
package com.codingelab.validation.strategy.strings;

import com.codingelab.validation.Valid;
import com.codingelab.validation.valid.strategy.decorator.regex._Regex;
/**
 * @author Abdulrahman Abdulhamid Alsaedi
 * @since 1.0.1
 */
public final class DecorateValidation extends StringValidation{
	private _Regex regex;
	public DecorateValidation(_Regex regex) throws Exception{
		super(regex);
		this.regex=regex;
	}
	@Override public void setValid(Valid<String> valid) {}
	@Override public void setLength(Valid<String> length) {
		super.setLengthValue(length);
	}
	public String removeInvalidChar(){
		super.setInput(regex.repair(super.getInput()));
		return super.getInput();
	}	 
}
