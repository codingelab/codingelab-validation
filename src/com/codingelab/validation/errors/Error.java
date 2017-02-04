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
package com.codingelab.validation.errors;

import com.codingelab.validation.languages.Language;

/** 
 * This interface apply the strategy design pattern to create many types of errors and each one of these errors 
 * represent a strategy that hold a unique number to help the {@link Language} interface to translate the error
 * into readable language.</br>
 * By using this concept know we no longer need to memorize the error number we just need to instantiate the right
 * object and that object hold the error number. We also can send a message to the Language {@link Language}
 * if needed by the help of {@link #get()} method
 * @author Abdulrahman Abdulhamid Alsaedi
 * @since 1.0.1
 */
public interface Error <R> {
	/**
	 * Hold a unique number that identify the error
	 * @return a number which helps the Language interface to translate the error into readable language
	 */
	int getErrorNumber();
	/**
	 * Hold a message that can be used by the language interface
	 * @return a message based on the type variable (Error&ltR&gt).
	 */
	R get();
}
