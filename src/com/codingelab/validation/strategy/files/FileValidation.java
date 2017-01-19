package com.codingelab.validation.strategy.files;

import java.io.File;

import com.codingelab.validation.Valid;
import com.codingelab.validation.Validation;
import com.codingelab.validation.valid.strategy.MyFile;
/*
 * @author Abdulrahman Abdulhamid Alsaedi
 * @version 1.0.1
 */
public class FileValidation extends Validation<File> {
	private MyFile myFile;
	
	public FileValidation() {
		this.myFile=new MyFile();
		super.setValidValue(this.myFile);
	}
	@Override public void setValid(Valid<File> valid) {}
	public void setMaximumSize(int bytes){
		this.myFile.setMaximumSize(bytes);;
	}

}
