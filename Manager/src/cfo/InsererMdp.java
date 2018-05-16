package cfo;

import java.util.Random;
/**
 * Set a random password for account of front office advisor
 * @author Ibrahim
 *
 */
public class InsererMdp {

	public static String randomMdp(int min, int max, int len){  
	    //initialise une liste pour tous les valeur de min a max
	    String[] source = new String[len];  
	       for (int i = min; i < min+len; i++){  
	        source[i-min] = i+"";  
	       }  
	         
	       String result = "";  
	       Random rd = new Random();  
	       int index = 0;  
	       
	        //choisir une valeur dans list source
	           index = Math.abs(rd.nextInt() % len--);  
	           //mettre ce valeur a resultat  
	           result = source[index];  
	           //supprimer la valeur choisie dans source  
	           //source[index] = source[len];  
	        
	       return result;  
	}

}
