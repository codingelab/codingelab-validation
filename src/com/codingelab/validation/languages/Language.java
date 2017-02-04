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
package com.codingelab.validation.languages;

import com.codingelab.validation.errors.Error;
/**
 * This Interface is responsible for translating the error number into readable language.
 * If the language support the concept of "language variable" then the {@link Translator} class have the ability
 * to change the "language variable" to any word that clients want. To understand the concept of 
 * Language variable see {@link #getVariable()} method 
 * @author Abdulrahman Abdulhamid Alsaedi
 * @since 1.0.1
 * 
 */
public interface Language {
	/**
	 * This method may or may not give the {@link Translator} class the ability to change the 
	 * "Language variable" based on the implementation detail. For example:</br>
	 * <ul>
	 * 		<li>
	 * 			Support the concept of "language variable"
	 * <pre>
	 * public class English implements Language{
	 *       public String getVariable() {
	 *          // support the "language variable" concept since I did not return
	 *          // empty String or null value
	 *             return <b>"Input"</b>; 
	 *       }
	 *       public String translate(Error error) {
	 *             int number=error.getErrorNumber();
	 *             switch (number) {
	 *             case 1://Length not equal
	 *                   int num=(int)error.get();
	 *                   String word=num>1?"characters":"character";
	 *                   return <b>getVariable()</b>+" length must be equal to "+num+" "+word;
	 *             }
	 *       }
	 * }
	 * sudo codes 1 :
	 *             System.out.println(validation.getError());
	 *             output: <b>input</b> length must be equal to 3 characters
	 * sudo codes 2 :
	 *             System.out.println(validation.getError(<b>"Username"</b>));
	 *             // replace <b>"input"</b> with <b>"Username"</b>
	 *             output: <b>Username</b> length must be equal to 3 characters
	 * 
	 * </pre>
	 * 		</li>
	  * 		<li>
	 * 			Does not Support the concept of "language variable"
	 * <pre>
	 * public class English implements Language{
	 *       public String getVariable() {
	 *          // Does not support the concept of "language variable" since
	 *          // I return empty String
	 *             return <b>""</b>; 
	 *       }
	 *       public String translate(Error error) {
	 *             int number=error.getErrorNumber();
	 *             switch (number) {
	 *             case 1://Length not equal
	 *                   int num=(int)error.get();
	 *                   String word=num>1?"characters":"character";
	 *                   return "<b>Input</b> length must be equal to "+num+" "+word;
	 *             }
	 *       }
	 * }
	 * sudo codes 1 :
	 *             System.out.println(validation.getError());
	 *             output: <b>input</b> length must be equal to 3 characters
	 * sudo codes 2 :
	 *             System.out.println(validation.getError("Username"));
	 *             // Could not replace <b>"input"</b> with <b>"Username"</b>
	 *             output: <b>input</b> length must be equal to 3 characters
	 * 
	 * </pre>
	 * 		</li>
	 * </ul> 
	 * @return the value of the "language variable"
	 * 
	 */
	public String getVariable();
	/**
	 * Translate the error number into readable language
	 * @param error is an object that hold an error number which needed to be translate 
	 *        as well as it holds a message that helps us to write a better translation
	 * @return the translation of the error
	 * @see Error
	 */
	public String translate(Error error);
}
