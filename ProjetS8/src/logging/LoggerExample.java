package logging;

import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;


public class LoggerExample {
    // use the classname for the logger, this way you can refactor
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);


    public void LoginClient(String logs, String Ip, int check) {
        // set the LogLevel to Info, only info Messages will be written
        LOGGER.setLevel(Level.INFO);
        /*LOGGER.severe("Info Log");
        LOGGER.warning("Info Log");*/
        if (check==1) {
        	LOGGER.info("Log, client: " + logs + " Ip address: " + Ip);
        }else {
        	LOGGER.warning("echec connexion, client: " + logs + " Ip address: " + Ip);
        }
        LOGGER.finest("Really not important");
    }

    public void LogoutClient(String logs, String Ip) {
        // set the LogLevel to Info, only info Messages will be written
        LOGGER.setLevel(Level.INFO);
        /*LOGGER.severe("Info Log");
        LOGGER.warning("Info Log");*/
        LOGGER.info("Logout, client: " + logs + " Ip address: " + Ip);
        LOGGER.finest("Really not important");
    }
    
    public void VirementInterneClient(String logs, String Ip, int check) {
        // set the LogLevel to Info, only info Messages will be written
    	LOGGER.setLevel(Level.INFO);
        /*LOGGER.severe("Info Log");
        LOGGER.warning("Info Log");*/
        if (check == 1) {
        	LOGGER.info("Virement Interne reussi, client: " + logs + " Ip address: " + Ip);
        }else {
        	LOGGER.warning("Virement Interne echec, client: " + logs + " Ip address: " + Ip);
        }
        LOGGER.finest("Really not important");
    }
    
    public void VirementExterneClient(String logs, String Ip, int check) {
        // set the LogLevel to Info, only info Messages will be written
    	LOGGER.setLevel(Level.INFO);
        /*LOGGER.severe("Info Log");
        LOGGER.warning("Info Log");*/
        if (check == 1) {
        	LOGGER.info("Info Virement Externe reussi, client: " + logs + " Ip address: " + Ip);
        }else {
        	LOGGER.warning("Warning Virement Externe echec, client: " + logs + " Ip address: " + Ip);
        }
        LOGGER.finest("Really not important");
    }
    
    public void AchatAction(String logs, String Ip, int check) {
        // set the LogLevel to Info, only info Messages will be written
    	LOGGER.setLevel(Level.INFO);
        /*LOGGER.severe("Info Log");
        LOGGER.warning("Info Log");*/
        if (check == 1) {
        	LOGGER.info("Achat Action reussi, client: " + logs + " Ip address: " + Ip);
        }else {
        	LOGGER.warning("Achat action echec, client: " + logs + " Ip address: " + Ip);
        }
        LOGGER.finest("Really not important");
    }
    
    public void VenteAction(String logs, String Ip, int check) {
        // set the LogLevel to Info, only info Messages will be written
    	LOGGER.setLevel(Level.INFO);
        /*LOGGER.severe("Info Log");
        LOGGER.warning("Info Log");*/
        if (check == 1) {
        	LOGGER.info("Vente Action reussi, client: " + logs + " Ip address: " + Ip);
        }else {
        	LOGGER.warning("Vente action echec, client: " + logs + " Ip address: " + Ip);
        }
        LOGGER.finest("Really not important");
    }

}