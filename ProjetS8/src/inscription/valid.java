package inscription;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import DAO.jee.DAO;

public class valid {
	
	/**
	 * Valide la nationalité du client saisi.
	 */
	public static void validationNationalite( String nationalite ) throws Exception {
	    if ( nationalite != null && nationalite.trim().length() < 3 ) {
	        throw new Exception( "Le nationalité du client doit contenir au moins 3 caractères." );
	    }
	}
	/**
	 * Valide la date de naissance du client saisi.
	 */
	public static void validationDate( Date date ) throws Exception {
		Date today = new Date();
		if ( date.compareTo(today) > 18) { 
	        throw new Exception( "Vous devez avoir 18 ans." );
	    }
	}
	
	
	/**
	 * Valide l'adresse mail saisie.
	 */
	public static void validationEmail( String email ) throws Exception {
	    if ( email != null && email.trim().length() != 0 ) {
	        if ( !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
	            throw new Exception( "Merci de saisir une adresse mail valide." );
	        }
	    } else {
	        throw new Exception( "Merci de saisir une adresse mail." );
	    }
	}
	    /**
	     * Valide le téléphone du client saisi.
	     */
	public static void validationTelephone( String telephone ) throws Exception {
	        if ( telephone != null && telephone.trim().length() < 10) {
	            throw new Exception( "Le numero de téléphone du client doit contenir au moins 10 caractères." );
	        }
	    }
	    /**
	     * Valide l'adresse du client saisi.
	     */
	public static void validationAdresse( String adresse ) throws Exception {
	        if ( adresse.isEmpty() ) {
	            throw new Exception( "Veuillez enter une adresse postal" );
	        }
	    }
	   
	    /**
	     * Valide les mots de passe saisis.
	     */
	public static void validationMotsDePasse( String pwd, String pwd_confirm ) throws Exception{
	        if (pwd != null && pwd.trim().length() != 0 && pwd_confirm != null && pwd_confirm.trim().length() != 0) {
	            if (!pwd.equals(pwd_confirm)) {
	                throw new Exception("Les mots de passe entrés sont différents, merci de les saisir à nouveau.");
	            } else if (pwd.trim().length() < 3) {
	                throw new Exception("Les mots de passe doivent contenir au moins 3 caractères.");
	            } else if (!DAO.CheckMdp(pwd)) {
	            	 throw new Exception("Le mot de passe existe déjà.");
	            }    
	        } else {
	            throw new Exception("Merci de saisir et confirmer votre mot de passe.");
	        }
	    }

}
