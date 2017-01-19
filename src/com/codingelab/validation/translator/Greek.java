package com.codingelab.validation.translator;

import com.codingelab.validation.errors.Error;
import com.codingelab.validation.errors.ErrorType;
/*
 * @author Τρύφων Θεοδώρου
 * @author Abdulrahman Abdulhamid Alsaedi
 * @version 1.0.1
 */
public class Greek implements Language{
	
	@Override
	public String getVariable() {
		return "Στο πεδίο εισαγωγής ";
	}
	
	@Override
	public String translate(Error error) {
		int number=error.getErrorNumber();
		switch (number) {
											/*Length Errors(from 0 to 255)*/
			case 1://Length not equal
				int num=(int)error.get();
				String digit=num>1?"χαρακτήρες":"χαρακτήρας";
				return getVariable()+" το μήκος πρέπει να είναι ίσο με "+num+" "+digit;
			case 2://Length below minimum limit
				int limit=(int)error.get();
				digit=limit>1?"χαρακτήρες":"χαρακτήρας";
				return getVariable()+" το μήκος δεν μπορεί να είναι μικρότερο από "+limit+" "+digit;
			case 3://Length above maximum limit
				limit=(int)error.get();
				digit=limit>1?"χαρακτήρες":"χαρακτήρας";
				return getVariable()+" το μήκος δεν μπορεί να είναι μεγαλύτερο από "+limit+" "+digit;
			case 4://length not between
				int [] between=(int [])error.get();
				return getVariable()+ " το μήκος πρέπει να είναι μεταξύ των ορίων από: "
						+between[0]+" έως "+between[1]+" χαρακτήρες";
			case 5://length between
				between=(int [])error.get();
				return getVariable()+ " το μήκος πρέπει να είναι εκτός των ορίων από: "
				+between[0]+" έως "+between[1]+" χαρακτήρες";
			case 6://length not equal to one of
				int [] allLengths=(int [])error.get();
				String numbers=Integer.toString(allLengths[0]);
				if(allLengths.length==2)
					numbers+=" ή "+allLengths[1];
				else if(allLengths.length>2){
					for(int i=1;i<allLengths.length-1;i++)
						numbers+=", "+allLengths[i];
					numbers+=" ή "+allLengths[allLengths.length-1];
				}
				if(allLengths.length==1)
					return getVariable()+" το μήκος πρέπει να είναι ίσο με "+numbers;
				return getVariable()+" το μήκος πρέπει να είναι ίσο με ένα από τα παρακάτω νούμερα: "+numbers;
				
											/*Main Errors(from 256 to 1024)*/
			case 256://Empty input
				return getVariable()+" δεν επιτρέπεται να είναι κενό";
			case 257://Has letters
				return getVariable()+" δεν επιτρέπεται να περιέχει γράμματα";
			case 258://Has letters in range
				String range=(String)error.get();
				return getVariable()+" δεν επιτρέπεται να περιέχει γράμματα στην περιοχή: "+range;
			case 259://Has letters out range
				range=(String)error.get();
				return getVariable()+" δεν επιτρέπεται να περιέχει γράμματα έξω από την περιοχή: "+range;
			case 260://Required more letters
				num=(int)error.get();
				digit=num>1?"χαρακτήρες":"χαρακτήρας";
				return getVariable()+" απαιτούνται τουλάχιστον "+num+" "+digit;
			case 261://Has numbers
				return getVariable()+" δεν επιτρέπονται αριθμοί";
			case 262://Has numbers in range
				range=(String)error.get();
				return getVariable()+" δεν επιτρέπονται αριθμοί στην περιοχή: "+range;
			case 263://Has numbers out range
				range=(String)error.get();
				return getVariable()+" δεν επιτρέπονται αριθμοί έξω από την περιοχή: "+range;
			case 264://Required more numbers
				num=(int)error.get();
				digit=num>1?"αριθμοί":"αριθμός";				
				return getVariable()+" απαιτούνται τουλάχιστον"+num+" "+digit;
			case 265://Has special characters
				return getVariable()+" δεν επιτρέπονται ειδικοί χαρακτήρες";
			case 266://Has special characters in range
				range=(String)error.get();
				return getVariable()+" δεν επιτρέπονται ειδικοί χαρακτήρες την περιοχή: "+range;
			case 267://Has special characters out range
				range=(String)error.get();
				return getVariable()+" δεν επιτρέπονται ειδικοί χαρακτήρες έξω από την περιοχή: "+range;
			case 268://Required more special characters
				num=(int)error.get();
				digit=num>1?"ειδικοί χαρακτήρες":"ειδικός χαρακτήρας";					
				return getVariable()+" απαιτούνται "+num+" "+digit;
			case 269://Has white spaces
				return getVariable()+" δεν επιτρέπονται κενά (white spaces)";
			case 270://Has white spaces out range 
				return getVariable()+" επιτρέπεται μόνο ένα από τα (white spaces)";	
				
			case 301:return "Άκυρη διεύθυνση ηλεκτρονικού ταχυδρομείου";
			case 302:return "Ο πρώτος χαρακτήρας πρέπει να είναι είτε γράμμα ή αριθμός";
			case 303:return "Κάθε email έχει ένα όνομα. Όπως: example@";
			case 304:return "Το email δεν μπορεί να τελειώνει με τελεία(.)";
			case 305:return "Διαδοχική τελείες δεν επιτρέπονται";
			case 306:return "Κάθε email έχει ένα @ symbol";
			case 307:return "Κάθε email έχει μόνο ένα @ symbol";
			case 308:return "Κάθε email έχει ένα όνομα τομέα. π.χ. \"@gmail\"";
			case 309:return "Το όνομα τομέα αποτελείται από γράμματα και αριθμούς μόνο. π.χ. \"@gmail\"";
			case 310:return "Κάθε email έχει ένα Top Level τομέα. π.χ. \".com\"";
		//	case 311:return "Άκυρο όνομα Top Level τομέα. Έγκυρο π.χ. \".com\"";
		//	case 312:return "Άκυρο όνομα Sup-Level τομέα. Έγκυρο π.χ. \".com.gr\"";
			case 313:return "Άκυρο όνομα Top Level τομέα. Για παράδειγμα \".com.gr\"";		
		}
		return "";
	}

	

}
