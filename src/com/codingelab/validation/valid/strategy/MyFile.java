package com.codingelab.validation.valid.strategy;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.spi.FileTypeDetector;
import java.util.ArrayList;
import java.util.List;

import javax.activation.MimetypesFileTypeMap;

import com.codingelab.validation.Valid;
import com.codingelab.validation.errors.Error;
import com.codingelab.validation.exceptions.ValidationException;
/*
 * @author Abdulrahman Abdulhamid Alsaedi
 * @version 1.0.1
 */
public class MyFile implements Valid<java.io.File>{
	private long size;
	private String [] extensions;
	public MyFile() {
		this.size=0;
		this.extensions=null;
	}
	@Override
	public boolean isValid(java.io.File input) {
		/*if(!input.isFile())return false;
		if(input.length()>size)return false;
		if(input.getName().contains("."))
			System.out.println(input.getName().substring(input.getName().lastIndexOf(".")+1));
		else{
			   try {
				System.out.println(Files.probeContentType(input.toPath()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}*/
		return false;
	}
	@Override
	public java.io.File repair(java.io.File input){
		
		return null;
	}
	@Override
	public List<Error> getErrors(java.io.File input){
		return new ArrayList<>();
	}
	public void setMaximumSize(long size){
		this.size=size;
	}



	

	

}
