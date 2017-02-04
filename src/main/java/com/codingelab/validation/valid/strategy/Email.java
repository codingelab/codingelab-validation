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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.codingelab.validation.Valid;
import com.codingelab.validation.errors.Error;
import com.codingelab.validation.errors.email.InvalidDomainName;
import com.codingelab.validation.errors.email.InvalidEmail;
import com.codingelab.validation.errors.email.InvalidFirstChar;
import com.codingelab.validation.errors.email.InvalidTLD_SLD;
import com.codingelab.validation.errors.email.MoreThanOneAT;
import com.codingelab.validation.errors.email.NameEndWIthDot;
import com.codingelab.validation.errors.email.NoAtSymbol;
import com.codingelab.validation.errors.email.NoDomainName;
import com.codingelab.validation.errors.email.NoEmailName;
import com.codingelab.validation.errors.email.NoTLD;
import com.codingelab.validation.errors.email.SequenceDots;
/**
 * @author Abdulrahman Abdulhamid Alsaedi
 * @since 1.0.1
 */
public class Email implements Valid<String>{

	@Override
	public boolean isValid(String input){
		String regex2="([a-zA-Z0-9]([.]?[a-zA-Z0-9_-]+)*@[a-zA-Z0-9]+(\\.[a-zA-Z]{2,})+)";
		Pattern p=Pattern.compile(regex2);
		return p.matcher(input).matches();
	}
	/*undo point*/
	@Override public List<Error> getErrors(String input){
		List<Error>errors=new ArrayList<>();
		String invalidEmail="Invalid email";
		try{
			if(input==null || input.isEmpty()){
				errors.add(new InvalidEmail());
				return errors;
			}
			if(!isValid(input)){
				if(containsInvalidChar(input)){
					// if email contains  invalid characters return directly
					errors.add(new InvalidEmail());
					return errors;
				}
											/*check email name */
				String name="";
				int indexFirstAt=input.indexOf('@');
				if(indexFirstAt==-1)name=input;
				else name=input.substring(0,indexFirstAt);
				if(name.isEmpty())
					errors.add(new NoEmailName());
				else{
					char firstLetter=name.charAt(0);
					if(name.length()==1 && firstLetter=='.'){
						
						errors.add(new InvalidFirstChar());
					}else{
						boolean isFirstLetterValid=match(Character.toString(firstLetter),"[a-zA-Z0-9]");
						if(!isFirstLetterValid){
							
							errors.add(new InvalidFirstChar());
						}
						// if length is 2 char 
						String newName=name.substring(0);
						if(newName.length()==2 && newName.charAt(1)=='.'){
							errors.add(new NameEndWIthDot());
						}else{
							// stop undo point
							//boolean isValidName=match(newName,"([.]?[a-zA-Z0-9_-]+)");
							//if(!isValidName){
								boolean hasDots=match(newName,"([\\.a-zA-Z0-9_-]*[\\.]{2,}[\\.a-zA-Z0-9_-]*)*");
								if(hasDots)errors.add(new SequenceDots());
								//if(containsInvalidChar(newName))errors.add("Invalid char at name");
							//}
							char lastNameLetter=newName.charAt(newName.length()-1);
							if(lastNameLetter=='.')errors.add(new NameEndWIthDot());
						}
					}
				}
											/*email name end here*/
				
											/*check @ symbol */
				int numberOfAt=0;
				for(int i=0;i<input.length();i++)
					if(input.charAt(i)=='@')numberOfAt++;
				if(numberOfAt==0)errors.add(new NoAtSymbol());
				else if(numberOfAt>1)errors.add(new MoreThanOneAT());
											/*end of @ symbol here */
				
											/*check domain name here */
				int indexOfAt=input.lastIndexOf('@');
				if(indexOfAt==-1)errors.add(new NoDomainName());
				else if(numberOfAt==1){/////////////////////////
					String domainName=input.substring(indexOfAt+1);
					int indexOfTLD=domainName.indexOf("."); // if there is . before the TLD
					if(indexOfTLD!=-1){
						domainName=domainName.substring(0,indexOfTLD);
					}
					if(domainName.isEmpty())errors.add(new NoDomainName());
					else{
						boolean isValidDomain=match(domainName,"[a-zA-Z0-9]+");
						if(!isValidDomain)errors.add(new InvalidDomainName());
					}
				}
											/*end of domain name here */
				
											/*start of TLD (Top Level Domain)*/
				if(indexOfAt==-1){ // if there is not @
					errors.add(new NoTLD());
				}
				else {
					String domainName=input.substring(indexOfAt+1);
					int indexOfTLDDot=domainName.indexOf(".");
					if(indexOfTLDDot==-1){// if there is no . after @
						errors.add(new NoTLD());
					}
					else{
						String TLD=domainName.substring(indexOfTLDDot+1);
						String SLD="";
						int indexOfSLDDot=TLD.indexOf('.');
						if(indexOfSLDDot!=-1){
							SLD=TLD.substring(indexOfSLDDot);
							TLD="."+TLD.substring(0,indexOfSLDDot);
						}
						if(TLD.isEmpty()){// if there is @ then . but the TLD is empty
							errors.add(new NoTLD());
						}else{
							//TLD="."+TLD;
							boolean isTLDValid=match(TLD,"([\\.]?[a-zA-Z]{2,})+");
							/*if(!isTLDValid){
								errors.add(new InvalidTLD());
							}*/
							boolean isSLDValid=true;
							if(!SLD.isEmpty()){
								isSLDValid=match(SLD, "(\\.[a-zA-Z]{2,})*");
								/*if(!isSLDValid){
									errors.add(new InvalidSLD());
								}*/
							}
							if(!isTLDValid || !isSLDValid){
								errors.add(new InvalidTLD_SLD());
							}
							
						}
						//int indexOfSLD=
					}
					
					//if(indexOfSLD==-1)
				}
				if(errors.isEmpty())errors.add(new InvalidEmail());
				// if list is empty add invalid Emaill error
			}
		}catch(Exception exception){
			errors.clear();
			errors.add(new InvalidEmail());
		}
		return errors;
	}
	private boolean containsInvalidChar(String input){
		String regex="[^\\w@.-]";
		Pattern pattern=Pattern.compile(regex);
		Matcher matcher=pattern.matcher(input);
		return matcher.find();
	}
	private boolean match(String input,String regex){
		Pattern pattern=Pattern.compile(regex);
		Matcher matcher=pattern.matcher(input);
		return matcher.matches();
	}
	@Override
	public String repair(String input){
		return input.replaceAll("[^.a-zA-Z0-9_@-]","");
	}
}
