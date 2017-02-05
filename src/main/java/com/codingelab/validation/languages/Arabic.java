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
 * @author Abdulrahman Abdulhamid Alsaedi
 * @since 1.0.1
 */
class Arabic implements Language{
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
				//ادخال 8 خانات على الأقل
				// ادخال 8 خانات
				// ادخال 8 خانات على الأكثر
				// عدد الخانات يجب أن تكون إحدى الأرقام التالية: 5,10,20 
				// عدد الخانات يجب أن تكون من 5 الى 10
				String temp="ادخل خانة واحدة";
				if(num==2)temp="ادخل خانتين اثنتين";
				else if(num>2)temp="ادخل "+num+" خانات";
				return temp;
			case 2://Length below minimum limit
				int limit=(int)error.get();
				temp="ادخل خانة واحدة على الأقل";
				if(limit==2)temp="ادخل خانتين اثنتين على الأقل";
				else if(limit>2)temp="ادخل "+limit+" خانات على الأقل";
				return temp;
			case 3://Length above maximum limit
				limit=(int)error.get();
				temp="لا يمكن ادخال أكثر من خانة واحدة";
				if(limit==2)temp="لا يمكن ادخال أكثر من خانتين اثنتين";
				else if(limit>2)temp="لا يمكن ادخال أكثر من "+limit+" خانات";
				return temp;
			case 4://length not between
				int [] between=(int [])error.get();
				return "عدد الخانات يجب أن تكون من "+between[0]+" الى "+between[1];
			case 5://length between
				between=(int [])error.get();
				return "عدد الخانات يجب ألا تكون من "+between[0]+" الى "+between[1];
			case 6://length not equal to one of
				int [] allLengths=(int [])error.get();
				int first=allLengths[0];
				if(allLengths.length==1){
					temp="ادخل خانة واحدة";
					if(first==2)temp="ادخل خانتين اثنتين";
					else if(first>2)temp="ادخل "+first+" خانات";
					return temp;
				}else{
					temp="عدد الخانات يجب أن تكون إحدى الأرقام التالية: "+first;
					for(int i=1;i<allLengths.length;i++)
						temp+=" أو "+allLengths[i];
				}
				return temp;
				/*if(allLengths.length==2)
					numbers+=" or "+allLengths[1];
				else if(allLengths.length>2){
					for(int i=1;i<allLengths.length-1;i++)
						numbers+=", "+allLengths[i];
					numbers+=" or "+allLengths[allLengths.length-1];
				}
				if(allLengths.length==1)
					return getVariable()+" length must be equal to "+numbers;
				return getVariable()+" length must be equal to one of this lengths: "+numbers;*/
				
											/*Main Errors(from 256 to 1024)*/
			case 256://Empty input           تكون
				return "لا يمكنك ترك الخانة فارغة";
			case 257://Has letters
				return "لا يمكنك ادخال الأحرف";
			case 258://Has letters in range
				String range=(String)error.get();
				return "لا يمكنك ادخال الأحرف التالية: "+range;
			case 259://Has letters out range
				range=(String)error.get();
				return "يمكنك ادخال الأحرف التالية فقط: "+range;
			case 260://Required more letters
				num=(int)error.get();
				if(num<=1)temp="يجب ادخال حرف واحد على الأقل";
				else if(num==2)temp="يجب ادخال حرفين اثنين على الأقل";
				else temp="يجب ادخال "+num+" أحرف على الأقل";
				return temp;
			case 261://Has numbers
				return getVariable()+"لا يمكنك ادخال الأرقام";
			case 262://Has numbers in range
				range=(String)error.get();
				return "لا يمكنك ادخال الأرقام التالية: "+range;
			case 263://Has numbers out range
				range=(String)error.get();
				return "يمكنك ادخال الأرقام التالية فقط: "+range;
			case 264://Required more numbers
				num=(int)error.get();
				if(num<=1)temp="يجب ادخال رقم واحد على الأقل";
				else if(num==2)temp="يجب ادخال رقمين اثنين على الأقل";
				else temp="يجب ادخال "+num+" أرقام على الأقل";
				return temp;
			case 265://Has special characters
				return "لا يمكنك ادخال الرموز";
			case 266://Has special characters in range
				range=(String)error.get();
				return "لا يمكنك ادخال الرموز التالية: "+range;
			case 267://Has special characters out range
				range=(String)error.get();
				return "يمكنك ادخال الرموز التالية فقط: "+range;
			case 268://Required more special characters
				num=(int)error.get();
				if(num<=1)temp="يجب ادخال حرف خاص واحد على الأقل";
				else if(num==2)temp="يجب ادخال حرفين خاصين اثنين على الأقل";
				else temp="يجب ادخال "+num+" أحرف خاصة على الأقل";
				return temp;
			case 269://Has white spaces
				return "لا يمكنك ادخال المسافات ";
			case 270://Has white spaces out range 
				return "لا يمكنك ادخال المسافات التالية: "+"Tab/Enter";
				
				
				
				
			case 301:return "عنوان البريد الإلكتروني غير صحيح"; 
			case 302:return "يبدأ عنوان البريد الإلكتروني بحرف أو رقم";
			case 303:return "يحتوي كل عنوان بريد إلكتروني على اسم قبل علامة \"@\"";
			case 304:return "لا ينتهي اسم البريد الإلكتروني بنقطة (قبل علامة \"@\") ";
			case 305:return "لا يحتوي عنوان البريد الإلكتروني على نقاط متتالية";
			case 306:return "يحتوي كل عنوان بريد إلكتروني على علامة \"@\" ";
			case 307:return "يحتوي كل عنوان بريد إلكتروني على علامة \"@\" واحدة فقط";
			case 308:return "يحتوي كل عنوان بريد إلكتروني على اسم نطاق مثل \"gmail@\"";
			case 309:return "يتكون اسم النطاق من حروف وأرقام فقط مثل \"gmail@\"";
			case 310:return "يحتوي كل عنوان بريد إلكتروني على \"TLD\" مثل \"com.\"";
			/*// For top level domain
			case 311:return "TLD يتكون من نقطة متبوعة بحرفين على الأقل مثل \"com.\"";
			// For sub-level domain
			case 312:return "SLD يتكون من نقطة متبوعة بحرفين على الأقل مثل \"com.sa.\"";
			*/
			case 313:return "TLD أو SLD غير صحيح. المثال الصحيح عليهما كالتالي \"com.sa.\"";
		}
		return "";
	}
 
}
