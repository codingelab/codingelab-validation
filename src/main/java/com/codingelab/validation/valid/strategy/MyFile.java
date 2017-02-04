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
import com.codingelab.validation.Valid;
import com.codingelab.validation.errors.Error;
/**
 * @author Abdulrahman Abdulhamid Alsaedi
 * @since 1.0.1
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
