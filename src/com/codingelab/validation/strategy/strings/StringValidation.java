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
import com.codingelab.validation.Validation;
import com.codingelab.validation.exceptions.EmptyArrayException;
import com.codingelab.validation.valid.strategy.Strings;
import com.codingelab.validation.valid.strategy.length.AnyLength;
import com.codingelab.validation.valid.strategy.length.Between;
import com.codingelab.validation.valid.strategy.length.Equal;
import com.codingelab.validation.valid.strategy.length.LengthOneOf;
import com.codingelab.validation.valid.strategy.length.Maximum;
import com.codingelab.validation.valid.strategy.length.Minimum;
import com.codingelab.validation.valid.strategy.length.NotBetween;
/**
 * @author Abdulrahman Abdulhamid Alsaedi
 * @since 1.0.1
 */
public abstract class StringValidation extends Validation<String>{
	private Strings stringValidation;
	protected StringValidation(Valid<String> valid) {
		this.stringValidation=new Strings(valid);
		super.setValidValue(stringValidation);
		this.setInput("");
	}
	/*protected Validation(String input,ValidString valid){
		this.setInput(input);
		this.valid=valid;
		this.length=new AnyLength();
		this.isNull=false;
		this.translator=new Translator();
		this.onValid=null;
		this.onInvalid=null;
		this.isEventActive=true;
	}*/
	
	
/*	@Override protected boolean checkNullConstraint() {
		if(!super.isNull() && (super.getInput()==null || super.getInput().isEmpty()))
			return false;
		return true;
	}*/
							/*Setter method(s)*/
	@Override public final void setInput(String input){
		if(input==null)super.setInput("");
		else super.setInput(input);
	}
	
	// useful for overriding part of language
	//public String getLanguageVariable(){return this.translator.getLanguageVariable();}
	
	protected void setValidValue(final Valid<String> valid){
		this.stringValidation.setValid(valid);
	}
	public abstract void setLength(final Valid<String> length);
	protected final void setLengthValue(final Valid<String> length){
		if(length!=null)
			this.stringValidation.setLength(length);
		else this.resetLength();
	}
	
	public final void resetLength(){
		this.setLengthValue(new AnyLength());
	}
	public final void setLengthBetween(int start,int end){
		if(start<1 || end <1)resetLength();
		else this.setLengthValue(new Between(start, end));
	}
	public final void setLengthNotBetween(int start,int end){
		if(start<1 || end <1)resetLength();
		else this.setLengthValue(new NotBetween(start, end));
	}
	public final void setMaximumLength(int max){
		if(max<1)resetLength();
		else this.setLengthValue(new Maximum(max));
	}
	public final void setMinimumLength(int min){
		if(min<1)resetLength();
		else this.setLengthValue(new Minimum(min));
	}
	public final void setLengthEqualTo(int equal){
		if(equal<1)resetLength();
		else this.setLengthValue(new Equal(equal));
	}
	public final void setLengthToOneOf(int ... lengths) throws Exception{
		if(lengths==null)throw new NullPointerException("Validation.LengthOneOf(null) is invalid argument."
				+ " LengthOneOf(int ... lengths) method must contains one length at least."
				+ " For example, Validation.LengthOneOf(5,7,9);");
		if(lengths.length==0)throw new EmptyArrayException("LengthOneOf(int ... lengths) method must contains one length at least."
				+ " For example, Validation.LengthOneOf(5,7,9);");
		boolean isValid=true;
		for(int i=0;i<lengths.length;i++)
			if(lengths[i]<1)isValid=false;
		if(isValid)this.setLengthValue(new LengthOneOf(lengths));
		else resetLength();
	}
}
