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
import com.codingelab.validation.errors.ErrorType;
/**
 * @author Abdulrahman Abdulhamid Alsaedi
 * @since 1.0.1
 */
class France implements Language{
	
	@Override
	public String getVariable() {
		return "";
	}
	
	@Override
	public String translate(Error error) {
		int number=error.getErrorNumber();
		switch (number) {
											/*Length Errors(from 0 to 255)*/
			case 1://Length not equal
				int num=(int)error.get();
				String digit=num>1?"characters":"character";
				//return getVariable()+" length must be equal to "+num+" "+digit;
				return "You must insert "+num+" "+digit;
			case 2://Length below minimum limit
				int limit=(int)error.get();
				digit=limit>1?"characters":"character";
				//return getVariable()+" length cannot be smaller than "+limit+" "+digit;
				return "You cannot insert less than "+limit+" "+digit;
			case 3://Length above maximum limit
				limit=(int)error.get();
				digit=limit>1?"characters":"character";
				//return getVariable()+" length cannot be bigger than "+limit+" "+digit;
				return "You cannot insert more than "+limit+" "+digit;
			case 4://length not between
				int [] between=(int [])error.get();
				/*return getVariable()+ " length must be between this range: "
						+between[0]+" to "+between[1]+" characters";*/
				return "You have to insert between "+between[0]+" to "+between[1]+" characters ";
			case 5://length between
				between=(int [])error.get();
				/*return getVariable()+ " length must be out of this range: "
				+between[0]+" to "+between[1]+" characters";*/
				return "You cannot insert between "+between[0]+" to "+between[1]+" characters ";
			case 6://length not equal to one of
				int [] allLengths=(int [])error.get();
				String numbers=Integer.toString(allLengths[0]);
				if(allLengths.length==2)
					numbers+=" or "+allLengths[1];
				else if(allLengths.length>2){
					for(int i=1;i<allLengths.length-1;i++)
						numbers+=", "+allLengths[i];
					numbers+=" or "+allLengths[allLengths.length-1];
				}
				if(allLengths.length==1){
					digit=allLengths[0]>1?"characters":"character";
					//return getVariable()+" length must be equal to "+numbers;
					return "You must insert "+number+" "+digit;
				}
				return "You must insert either : "+numbers +" characters";
				
											/*Main Errors(from 256 to 1024)*/
			case 256://Empty input
				return "You cannot leave this empty";
			case 257://Has letters
				return "You cannot insert letters";
			case 258://Has letters in range
				String range=(String)error.get();
				return "You cannot insert letters in this range: "+range;
			case 259://Has letters out range
				range=(String)error.get();
				return "You cannot insert letters, except from this range: "+range;
			case 260://Required more letters
				num=(int)error.get();
				digit=(num>1)?"letters":"letter";
				return "You have to insert "+num+" "+digit+" at least";
			case 261://Has numbers
				return "You cannot insert numbers";
			case 262://Has numbers in range
				range=(String)error.get();
				return "You cannot insert numbers in this range: "+range;
			case 263://Has numbers out range
				range=(String)error.get();
				return "You cannot insert numbers, except from this range: "+range;
			case 264://Required more numbers
				num=(int)error.get();
				digit=(num>1)?"numbers":"number";
				return "You have to insert "+num+" "+digit+" at least";
			case 265://Has special characters
				return "You cannot insert special characters";
			case 266://Has special characters in range
				range=(String)error.get();
				return "You cannot insert special characters in this range: "+range;
			case 267://Has special characters out range
				range=(String)error.get();
				return "You cannot insert special characters, except from this range: "+range;
			case 268://Required more special characters
				num=(int)error.get();
				return getVariable()+" required "+num+" special characters";
			case 269://Has white spaces
				return "You cannot insert white spaces";
			case 270://Has white spaces out range 
				// "input can only contain spaces from the white spaces range"
				return "You cannot insert any white spaces, except spaces";
				
				
				
				
			case 301:return "Invalid Email Address";
			case 302:return "First character must be either letter or number";
			case 303:return "Each Email Address has a name. Like: example@";
			case 304:return "Email name cannot end with dot(.)";
			case 305:return "Sequential dots are not allowed";
			case 306:return "Each Email Address has an @ symbol";
			case 307:return "Each Email Address has only one @ symbol";
			case 308:return "Each Email Address has a domain name. e.g. \"@gmail\"";
			case 309:return "Domain name consist of letters and numbers only. e.g. \"@gmail\"";
			case 400:return "Each email has a Top Level Domain. e.g. \".com\"";
			
			case 401:return "Invalid Top Level Domain. Valid e.g. \".com\"";
			case 402:return "Invalid Sub-Level Domain. Valid e.g. \".com.sa\"";
			
			case 403:return "Invalid TLD or SLD. Valid e.g. \".com.sa\"";
		}
		return "";
	}

	

}
