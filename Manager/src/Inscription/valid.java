package Inscription;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import DAO.manager.DAO;

public class valid {
	
	/**
	 * Valide le nom du client saisi.
	 */
	public static void validationNom( String nom ) throws Exception {
	    if ( nom.isEmpty()) {
	        throw new Exception( "Le nom d'utilisateur doit contenir au moins 1 caract¨¨re." );
	    }
	}
	/**
	 * Valide le pr¨¦nom du client saisi.
	 */
	public static void validationPrenom( String prenom ) throws Exception {
	    if ( prenom != null && prenom.trim().length() < 3 ) {
	        throw new Exception( "Le prenom du client doit contenir au moins 1 caract¨¨re." );
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
	     * Valide le t¨¦l¨¦phone du client saisi.
	     */
	public static void validationTelephone( String telephone ) throws Exception {
	        if ( telephone != null && telephone.trim().length() < 10) {
	            throw new Exception( "Le numero de t¨¦l¨¦phone du client doit contenir au moins 10 caract¨¨res." );
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
	   


}