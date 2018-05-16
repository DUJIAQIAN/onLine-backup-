package Cpt;

import java.util.Random;

public class InsererCpt {

	
	public static int randomNumeroCompte(int min, int max, int len){  
	    //initialise une liste pour tous les valeur de min a max
	    int[] source = new int[len];  
	       for (int i = min; i < min+len; i++){  
	        source[i-min] = i;  
	       }  
	         
	       int result = 0;  
	       Random rd = new Random();  
	       int index = 0;  
	       
	        //choisir une valeur dans list source
	           index = Math.abs(rd.nextInt() % len--);  
	           //mettre ce valeur a resultat  
	           result = source[index];  
	           //supprimer la valeur choisie dans source  
	           source[index] = source[len];  
	        
	       return result;  
	}
			
}
