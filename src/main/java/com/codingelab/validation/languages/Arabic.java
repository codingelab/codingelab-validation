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
				//ÇÏÎÇá 8 ÎÇäÇÊ Úáì ÇáÃŞá
				// ÇÏÎÇá 8 ÎÇäÇÊ
				// ÇÏÎÇá 8 ÎÇäÇÊ Úáì ÇáÃßËÑ
				// ÚÏÏ ÇáÎÇäÇÊ íÌÈ Ãä Êßæä ÅÍÏì ÇáÃÑŞÇã ÇáÊÇáíÉ: 5,10,20 
				// ÚÏÏ ÇáÎÇäÇÊ íÌÈ Ãä Êßæä ãä 5 Çáì 10
				String temp="ÇÏÎá ÎÇäÉ æÇÍÏÉ";
				if(num==2)temp="ÇÏÎá ÎÇäÊíä ÇËäÊíä";
				else if(num>2)temp="ÇÏÎá "+num+" ÎÇäÇÊ";
				return temp;
			case 2://Length below minimum limit
				int limit=(int)error.get();
				temp="ÇÏÎá ÎÇäÉ æÇÍÏÉ Úáì ÇáÃŞá";
				if(limit==2)temp="ÇÏÎá ÎÇäÊíä ÇËäÊíä Úáì ÇáÃŞá";
				else if(limit>2)temp="ÇÏÎá "+limit+" ÎÇäÇÊ Úáì ÇáÃŞá";
				return temp;
			case 3://Length above maximum limit
				limit=(int)error.get();
				temp="áÇ íãßä ÇÏÎÇá ÃßËÑ ãä ÎÇäÉ æÇÍÏÉ";
				if(limit==2)temp="áÇ íãßä ÇÏÎÇá ÃßËÑ ãä ÎÇäÊíä ÇËäÊíä";
				else if(limit>2)temp="áÇ íãßä ÇÏÎÇá ÃßËÑ ãä "+limit+" ÎÇäÇÊ";
				return temp;
			case 4://length not between
				int [] between=(int [])error.get();
				return "ÚÏÏ ÇáÎÇäÇÊ íÌÈ Ãä Êßæä ãä "+between[0]+" Çáì "+between[1];
			case 5://length between
				between=(int [])error.get();
				return "ÚÏÏ ÇáÎÇäÇÊ íÌÈ ÃáÇ Êßæä ãä "+between[0]+" Çáì "+between[1];
			case 6://length not equal to one of
				int [] allLengths=(int [])error.get();
				int first=allLengths[0];
				if(allLengths.length==1){
					temp="ÇÏÎá ÎÇäÉ æÇÍÏÉ";
					if(first==2)temp="ÇÏÎá ÎÇäÊíä ÇËäÊíä";
					else if(first>2)temp="ÇÏÎá "+first+" ÎÇäÇÊ";
					return temp;
				}else{
					temp="ÚÏÏ ÇáÎÇäÇÊ íÌÈ Ãä Êßæä ÅÍÏì ÇáÃÑŞÇã ÇáÊÇáíÉ: "+first;
					for(int i=1;i<allLengths.length;i++)
						temp+=" Ãæ "+allLengths[i];
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
			case 256://Empty input           Êßæä
				return "áÇ íãßäß ÊÑß ÇáÎÇäÉ İÇÑÛÉ";
			case 257://Has letters
				return "áÇ íãßäß ÇÏÎÇá ÇáÃÍÑİ";
			case 258://Has letters in range
				String range=(String)error.get();
				return "áÇ íãßäß ÇÏÎÇá ÇáÃÍÑİ ÇáÊÇáíÉ: "+range;
			case 259://Has letters out range
				range=(String)error.get();
				return "íãßäß ÇÏÎÇá ÇáÃÍÑİ ÇáÊÇáíÉ İŞØ: "+range;
			case 260://Required more letters
				num=(int)error.get();
				if(num<=1)temp="íÌÈ ÇÏÎÇá ÍÑİ æÇÍÏ Úáì ÇáÃŞá";
				else if(num==2)temp="íÌÈ ÇÏÎÇá ÍÑİíä ÇËäíä Úáì ÇáÃŞá";
				else temp="íÌÈ ÇÏÎÇá "+num+" ÃÍÑİ Úáì ÇáÃŞá";
				return temp;
			case 261://Has numbers
				return getVariable()+"áÇ íãßäß ÇÏÎÇá ÇáÃÑŞÇã";
			case 262://Has numbers in range
				range=(String)error.get();
				return "áÇ íãßäß ÇÏÎÇá ÇáÃÑŞÇã ÇáÊÇáíÉ: "+range;
			case 263://Has numbers out range
				range=(String)error.get();
				return "íãßäß ÇÏÎÇá ÇáÃÑŞÇã ÇáÊÇáíÉ İŞØ: "+range;
			case 264://Required more numbers
				num=(int)error.get();
				if(num<=1)temp="íÌÈ ÇÏÎÇá ÑŞã æÇÍÏ Úáì ÇáÃŞá";
				else if(num==2)temp="íÌÈ ÇÏÎÇá ÑŞãíä ÇËäíä Úáì ÇáÃŞá";
				else temp="íÌÈ ÇÏÎÇá "+num+" ÃÑŞÇã Úáì ÇáÃŞá";
				return temp;
			case 265://Has special characters
				return "áÇ íãßäß ÇÏÎÇá ÇáÑãæÒ";
			case 266://Has special characters in range
				range=(String)error.get();
				return "áÇ íãßäß ÇÏÎÇá ÇáÑãæÒ ÇáÊÇáíÉ: "+range;
			case 267://Has special characters out range
				range=(String)error.get();
				return "íãßäß ÇÏÎÇá ÇáÑãæÒ ÇáÊÇáíÉ İŞØ: "+range;
			case 268://Required more special characters
				num=(int)error.get();
				if(num<=1)temp="íÌÈ ÇÏÎÇá ÍÑİ ÎÇÕ æÇÍÏ Úáì ÇáÃŞá";
				else if(num==2)temp="íÌÈ ÇÏÎÇá ÍÑİíä ÎÇÕíä ÇËäíä Úáì ÇáÃŞá";
				else temp="íÌÈ ÇÏÎÇá "+num+" ÃÍÑİ ÎÇÕÉ Úáì ÇáÃŞá";
				return temp;
			case 269://Has white spaces
				return "áÇ íãßäß ÇÏÎÇá ÇáãÓÇİÇÊ ";
			case 270://Has white spaces out range 
				return "áÇ íãßäß ÇÏÎÇá ÇáãÓÇİÇÊ ÇáÊÇáíÉ: "+"Tab/Enter";
				
				
				
				
			case 301:return "ÚäæÇä ÇáÈÑíÏ ÇáÅáßÊÑæäí ÛíÑ ÕÍíÍ"; 
			case 302:return "íÈÏÃ ÚäæÇä ÇáÈÑíÏ ÇáÅáßÊÑæäí ÈÍÑİ Ãæ ÑŞã";
			case 303:return "íÍÊæí ßá ÚäæÇä ÈÑíÏ ÅáßÊÑæäí Úáì ÇÓã ŞÈá ÚáÇãÉ \"@\"";
			case 304:return "áÇ íäÊåí ÇÓã ÇáÈÑíÏ ÇáÅáßÊÑæäí ÈäŞØÉ (ŞÈá ÚáÇãÉ \"@\") ";
			case 305:return "áÇ íÍÊæí ÚäæÇä ÇáÈÑíÏ ÇáÅáßÊÑæäí Úáì äŞÇØ ãÊÊÇáíÉ";
			case 306:return "íÍÊæí ßá ÚäæÇä ÈÑíÏ ÅáßÊÑæäí Úáì ÚáÇãÉ \"@\" ";
			case 307:return "íÍÊæí ßá ÚäæÇä ÈÑíÏ ÅáßÊÑæäí Úáì ÚáÇãÉ \"@\" æÇÍÏÉ İŞØ";
			case 308:return "íÍÊæí ßá ÚäæÇä ÈÑíÏ ÅáßÊÑæäí Úáì ÇÓã äØÇŞ ãËá \"gmail@\"";
			case 309:return "íÊßæä ÇÓã ÇáäØÇŞ ãä ÍÑæİ æÃÑŞÇã İŞØ ãËá \"gmail@\"";
			case 310:return "íÍÊæí ßá ÚäæÇä ÈÑíÏ ÅáßÊÑæäí Úáì \"TLD\" ãËá \"com.\"";
			/*// For top level domain
			case 311:return "TLD íÊßæä ãä äŞØÉ ãÊÈæÚÉ ÈÍÑİíä Úáì ÇáÃŞá ãËá \"com.\"";
			// For sub-level domain
			case 312:return "SLD íÊßæä ãä äŞØÉ ãÊÈæÚÉ ÈÍÑİíä Úáì ÇáÃŞá ãËá \"com.sa.\"";
			*/
			case 313:return "TLD Ãæ SLD ÛíÑ ÕÍíÍ. ÇáãËÇá ÇáÕÍíÍ ÚáíåãÇ ßÇáÊÇáí \"com.sa.\"";
		}
		return "";
	}
 
}
