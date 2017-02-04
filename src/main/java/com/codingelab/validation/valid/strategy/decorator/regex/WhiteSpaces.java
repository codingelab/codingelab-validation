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

import com.codingelab.validation.errors.NoError;
/**
 * @author Abdulrahman Abdulhamid Alsaedi
 * @since 1.0.1
 */
public final class WhiteSpaces extends _RegexDecorator{
	public WhiteSpaces(_Regex compoment) {
		super(compoment,0);
		super.setRegex("[\\s]");
		//super.setRequired(0);
		super.setRequiredError(new NoError());
	}

	@Override protected String typeToString() {
		return RegexType.WHITE_SPACES.toString().toLowerCase()
				+super.getComponent().typeToString();
	}
}
