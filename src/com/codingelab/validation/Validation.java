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
package com.codingelab.validation;

import java.util.ArrayList;
import java.util.List;

import com.codingelab.validation.errors.EmptyInput;
import com.codingelab.validation.errors.Error;
import com.codingelab.validation.event.Event;
import com.codingelab.validation.languages.Lang;
import com.codingelab.validation.languages.Language;
import com.codingelab.validation.languages.LanguageFactory;
import com.codingelab.validation.languages.Translator;
/** 
 * Validation is a generic abstract class for data entry validation. This class can be implemented to validate any 
 * type. However, this class does not know how to validate the type variable. Therefore, it delegates the task
 * to an interface which is called {@link Valid}
 * @param <T> the object which you will validate
 * @author Abdulrahman Abdulhamid Alsaedi
 * @since 1.0.1
 */
public abstract class Validation<T> {
	private static final String version="Codingelab Validation 1.0.1";
	private Valid<T> valid;
	private T input;
	
	private boolean isNull;
	private Translator translator;
	private Event onValid;
	private Event onInvalid;
	private boolean isEventActive;
	
	
	public Validation(){
		this.valid=null;
		this.input=null;
		this.isNull=false;
		this.translator=new Translator();
		this.onValid=null;
		this.onInvalid=null;
		this.isEventActive=true;
	}
	/**
	 * By extending this class, this abstract method may or may not give the clients of this API library the ability to 
	 * change the behaviour of data entry validation at run-time based on the implementation detail. For example:<br/>
	 * <ul>
	 * 		<li> 
	 * 			If you want to give the client the ability to change the behaviour of data entry validation at run-time then
	 * 			delegate the task of setting the valid object to {@link #setValidValue(Valid)} method like in the
	 * 			following codes:
	 * <pre>
	 * public void setValid(final Valid<T> valid){
	 *    super.setValidValue(valid);
	 * }
	 * </pre>
	 * 		</li>
	 * 		<li> 
	 * 			If you want to prevent the client from changing the behaviour at run-time then do not delegate the task 
	 * 			as shown in the following codes: 
	 * <pre>
	 * public void setValid(final Valid<T> valid){
	 *    // leave this method empty.
	 * }
	 * </pre>
	 * 		</li>
	 * </ul>
	 * @param valid has the logic of how to validate the data entry and how to fix the errors if there is an error.
	 */
	public abstract void setValid(final Valid<T> valid);
	/**
	 * A setter method 
	 * @param valid set the value of valid property
	 * @see Validation#setValid(Valid)
	 */
	protected void setValidValue(final Valid<T> valid){
		this.valid=valid;
	}
	/**
	 * Getter method
	 * @return return the value of the valid property which holds the logic of how to validate the input
	 */
	protected final Valid<T> getValid(){ // TODO create getValidValue method
		return this.valid;
	}
	/**
	 * This method responsible for setting the input value. The input will be used either by
	 * {@link #isValid()} method to see if the input is valid or not or by {@link #getErrors()} methods
	 * to see what are the actual errors in the given input if there are. 
	 * @param input is the value which needed to be checked whether it is valid or invalid.
	 */
	public void setInput(T input){
		this.input=input;
	}
	/**
	 * This method returns the value of the input:T. The value of the input could be affected if the {@link #repair()} method 
	 * invoked.
	 * @return the value which needed to be checked whether it is valid or invalid.
	 */
	public T getInput(){
		return this.input;
	}
	/**
	 * This method gives you the ability to add your language if the API library does not support it. 
	 * By setting a new language using this method, only the current object will be affected by the given language.<br/>
	 * To add a new language implements {@link com.codingelab.validation.languages.Language}
	 * @param language set a new language for error notifications, if null value was passed to the argument then 
	 * the English language will be selected.
	 */
	public final void setLanguage(Language language){
		this.translator.setLanguage(language);
	}
	/**
	 * This method gives you the ability to select one of the languages that exist in this API library for error notifications.
	 * By selecting a language using this method, only the current object will be affected by the selected language
	 * @param lang select one of the built-in language in the API library for error notification,if null value 
	 * was passed to the argument then the English language will be selected.
	 */
	public final void selectLanguage(Lang lang){
		this.translator.setLanguage(LanguageFactory.get(lang));
	}
	/**
	 * If an object of type {@link Event} was passed to the argument of this method,
	 * the {@link #isValid()} method invokes {@link Event#call()} method
	 * whenever the API library consider the input as a valid input.
	 * @param event which will be triggered before the {@link #isValid()} method return true
	 */
	public final void setOnValid(Event event){this.onValid=event;}
	/**
	 * If an object of type {@link Event} was passed to the argument of this method,
	 * the {@link #isValid} method invokes {@link Event#call()} method
	 * whenever the API library consider the input as an invalid input.
	 * @param event which will be triggered before the {@link #isValid()} method return false
	 */
	public final void setOnInvalid(Event event){this.onInvalid=event;}
	/**
	 * By passing true to the argument of this method, the {@link #isValid()} method considers null and empty String
	 * value as a valid input and by passing false, the {@link #isValid()} method considers the null value as well as the
	 * empty String as an invalid data entry.
	 * @param isNull is the value which determines whether the null value or empty String is a valid or an invalid data entry.
	 */
	public final void setNull(boolean isNull){
		this.isNull=isNull;
	}
	/**
	 * This method returns true in-case the current object considers null value or empty String as a valid data entry else
	 * it will return false.
	 * @return the value of the isNull property 
	 */
	public final boolean isNull(){return this.isNull;}
	/*protected boolean checkNullConstraint(){
		//if(!this.isNull && this.input==null)return false;
		if(!this.isNull && this.input==null)return false;
		else if(!this.isNull && String.class.isAssignableFrom(this.input.getClass())
				&&((String)this.input).isEmpty()){
			return false;
		}
		return true;
	}*/
	/**
	 * This method knows how to check whether the given input is valid or invalid based on the implementation detail.
	 * @return boolean which determines whether the data entry is valid or not
	 * @see Validation#setInput(T input)
	 */
	public final boolean isValid(){
		if(this.valid==null)return true;
		if(this.isNull && this.input==null){
			return true;
		}
		else if(!this.isNull && this.input==null){
			return false;
		}
		else if(this.isNull && String.class.isAssignableFrom(this.input.getClass())
				&&((String)this.input).isEmpty()){
			return true;
		}else if(!this.isNull && String.class.isAssignableFrom(this.input.getClass())
				&&((String)this.input).isEmpty()){
			return false;
		}
		
	/*	boolean isValid=this.checkNullConstraint();
		if(!isValid){
			if(this.onInvalid!=null && this.isEventActive)this.onInvalid.call();
			return false;
		}
		*/
		//boolean isValid=true;
		boolean isValid=this.valid.isValid(this.input);
		if(isValid && this.onValid!=null && this.isEventActive) 
			this.onValid.call();
		else if(!isValid && this.isEventActive && this.onInvalid!=null) 
			this.onInvalid.call();
		return isValid;
	}
	/**
	 * This method knows how to find the errors in the given input and translates these errors into readable language.
	 * @return the errors into readable language if there
	 */
	public final String getErrors(){
		return getErrors(null);
	}
	/**
	 * This method knows how to find the errors in the given input and translates these errors into readable language.
	 * @param errorName allows you to change the language variable if and only if the current language support it.
	 * @return the errors into readable language if there
	 */
	public final String getErrors(String errorName){
		this.isEventActive=false;
		boolean isValid=this.isValid();
		if(isValid)return "";
		List<Error>errors=new ArrayList<>();
		//boolean isValid=this.checkNullConstraint();
		
		if(!this.isNull && this.input==null){
			errors.add(new EmptyInput());
		}else if(!this.isNull && String.class.isAssignableFrom(this.input.getClass())
				&&((String)this.input).isEmpty()){
			errors.add(new EmptyInput());
		}
		else{
			List<Error> x=this.valid.getErrors(input);
			if(x!=null)errors.addAll(x);
		}
		
		String err="";
		for(int i=0;i<errors.size();i++){
			String variable=this.translator.getLanguageVariable();
			if(variable==null || variable.isEmpty() 
					|| errorName==null || errorName.isEmpty())
				errorName=null;
			err+=this.translator.translate(errorName,errors.get(i));
			if(i<errors.size()-1)err+="\n";
		}
		this.isEventActive=true;
		return err;
	}
	/**
	 * This method knows how to fix the given input based on the implementation detail. It also reset the value of the 
	 * input property after fixing it.
	 * @return the given input which has been fixed 
	 */
	public final T repair(){
		try{
			if(this.input!=null && this.valid!=null){
				T x=this.valid.repair(input);
				if(x!=null)this.setInput(x);
			}
		}catch(Exception exception){}
		
		return this.input;
	}
	/**
	 * This method holds the current version of the API library
	 * @return current version of the API library
	 */
	public static String versionNo(){return Validation.version;}
}
