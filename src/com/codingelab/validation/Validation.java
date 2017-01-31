package com.codingelab.validation;

import java.util.ArrayList;
import java.util.List;

import com.codingelab.validation.errors.EmptyInput;
import com.codingelab.validation.errors.Error;
import com.codingelab.validation.errors.NoError;
import com.codingelab.validation.event.Event;
import com.codingelab.validation.languages.Lang;
import com.codingelab.validation.languages.Language;
import com.codingelab.validation.languages.LanguageFactory;
import com.codingelab.validation.languages.Translator;
/*
 * @author Abdulrahman Abdulhamid Alsaedi
 * @version 1.0.1
 */
public abstract class Validation<T> {
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
	public abstract void setValid(final Valid<T> valid);
	protected void setValidValue(final Valid<T> valid){
		this.valid=valid;
	}
	protected final Valid<T> getValid(){
		return this.valid;
	}
	
	public void setInput(T input){
		this.input=input;
	}
	public T getInput(){
		return this.input;
	}
	
	public final void setLanguage(Language language){
		this.translator.setLanguage(language);
	}
	public final void selectLanguage(Lang lang){
		this.translator.setLanguage(LanguageFactory.get(lang));
	}
	
	public final void setOnValid(Event event){this.onValid=event;}
	public final void setOnInvalid(Event event){this.onInvalid=event;}
	
	public final void setNull(boolean isNull){
		this.isNull=isNull;
	}
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
	
	public final String getErrors(){
		return getErrors(null);
	}
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
	
	public final T repair(){
		try{
			if(this.input!=null && this.valid!=null){
				T x=this.valid.repair(input);
				if(x!=null)this.setInput(x);
			}
		}catch(Exception exception){}
		
		return this.input;
	}
	public static String versionNo(){return "Codingelab Validation 1.0.1";}
}
