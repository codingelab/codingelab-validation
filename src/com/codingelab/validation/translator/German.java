package com.codingelab.validation.translator;

import com.codingelab.validation.errors.Error;
import com.codingelab.validation.errors.ErrorType;
/*
 * @author Abdulrahman Abdulhamid Alsaedi
 * @version 1.0.1
 */
public class German implements Language{
	
	@Override
	public String getVariable() {
		return "Eingabe";
	}
	
	@Override
	public String translate(Error error) {
		int number=error.getErrorNumber();
		switch (number) {
											/*Length Errors(from 0 to 255)*/
			case 1://Length not equal
				int num=(int)error.get();
				String digit=num>1?"Zeichen":"Zeichen";
				return getVariable()+" Erforderliche Länge: "+num+" "+digit;
			case 2://Length below minimum limit
				int limit=(int)error.get();
				digit=limit>1?"Zeichen":"Zeichen";
				return getVariable()+" Darf nicht kürzer sein als "+limit+" "+digit;
			case 3://Length above maximum limit
				limit=(int)error.get();
				digit=limit>1?"Zeichen":"Zeichen";
				return getVariable()+" Darf nicht länger sein als "+limit+" "+digit;
			case 4://length not between
				int [] between=(int [])error.get();
				return getVariable()+ " Länge muss zwischen "
						+between[0]+" und "+between[1]+" Zeichen liegen";
			case 5://length between
				between=(int [])error.get();
				return getVariable()+ " Länge darf nicht zwischen "
				+between[0]+" und "+between[1]+" Zeichen liegen";
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
				if(allLengths.length==1)
					return getVariable()+" Erforderliche Länge: "+numbers;
				return getVariable()+" Eine dieser Längen ist erforderlich: "+numbers;
				
											/*Main Errors(from 256 to 1024)*/
			case 256://Empty input
				return getVariable()+" darf nicht leer sein";
			case 257://Has letters
				return getVariable()+" darf keine Buchstaben enthalten";
			case 258://Has letters in range
				String range=(String)error.get();
				return getVariable()+" darf keine dieser Buchstaben enthalten: "+range;
			case 259://Has letters out range
				range=(String)error.get();
				return getVariable()+" darf keine Buchstaben enthalten, ausgenommen: "+range;
			case 260://Required more letters
				num=(int)error.get();
				return getVariable()+" erfordert "+num+" Buchstaben";
			case 261://Has numbers
				return getVariable()+" darf keine Zahlen enthalten";
			case 262://Has numbers in range
				range=(String)error.get();
				return getVariable()+" darf keine dieser Zahlen enthalten: "+range;
			case 263://Has numbers out range
				range=(String)error.get();
				return getVariable()+" darf keine Zahlen enthalten, ausgenommen: "+range;
			case 264://Required more numbers
				num=(int)error.get();
				return getVariable()+" erfordert "+num+" Zahlen";
			case 265://Has special characters
				return getVariable()+" darf keine Sonderzeichen enthalten";
			case 266://Has special characters in range
				range=(String)error.get();
				return getVariable()+" darf keine dieser Sonderzeichen enthalten: "+range;
			case 267://Has special characters out range
				range=(String)error.get();
				return getVariable()+" darf keine Sonderzeichen enthalten, ausgenommen: "+range;
			case 268://Required more special characters
				num=(int)error.get();
				return getVariable()+" erfordert "+num+" Sonderzeichen";
			case 269://Has white spaces
				return getVariable()+" darf keine Leerzeichen enthalten";
			case 270://Has white spaces out range 
				return getVariable()+" darf nur Leerzeichen enthalten";
				
				
				
				
			case 301:return "Ungültige Emailadresse";
			case 302:return "Das erste Zeichen muss ein Buchstabe oder eine Zahl sein";
			case 303:return "Namen eingeben. Z.B.: peter@";
			case 304:return "Name darf nicht mit einem Punkt (.) enden";
			case 305:return "Aufeinanderfolgende Punkte (..) sind nicht erlaubt";
			case 306:return "Adresse muss ein At-Symbol (@) enthalten";
			case 307:return "Adresse darf nur ein At-Symbol (@) enthalten";
			case 308:return "Adresse muss einen Domain-Namen enthalten, z.B. \"@gmail\"";
			case 309:return "Domain-Namen müssen aus Buchstaben oder Zahlen bestehen \"@gmail\"";
			case 310:return "Adresse muss eine Top-Level-Domain enthalten, z.B. \".com\"";
			// For top level domain
			//case 311:return "Email end with . followed by two letters at least.";
			/*case 311:return "Ungültige Top-Level-Domain \".com\"";
			// For sub-level domain
			case 312:return "Ungültige Sub-Level-Domain \".com.sa\"";
			*///case 312:return "Email end with . followed by two letters at least.";
			
			case 313:return "Ungültige TLD oder SLD. Gültig z.B. \".com.sa\"";
		}
		return "";
	}

	

}
