package logging;


import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class MyLogger {
	
    static private FileHandler fileTxt;
    static private SimpleFormatter formatterTxt;


    static public void setup() throws IOException {

    	
        // get the global logger to configure it
       Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        
        logger.setLevel(Level.INFO);
        //getRealpath()
        fileTxt = new FileHandler("%h/logs/logs.txt", true);
        //fileHTML = new FileHandler("Logging.html");

        // create a TXT formatter
        formatterTxt = new SimpleFormatter();
        fileTxt.setFormatter(formatterTxt);
        logger.addHandler(fileTxt);

    }
}