package com.merry.android.util;

import java.io.File;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Reporter;

public class Log {
	private static Logger logger;

    private static String filePath = "src/log4j.properties";   
    
    static{
    	 logger = Logger.getLogger("TestProject");
         PropertyConfigurator.configure(new File(filePath).getAbsolutePath());
    }   

    public static void logInfo(Object message) {       
        logger.info(message);
        Reporter.log(new TimeString().getSimpleDateFormat()+" : "+message);
    }

    public static void logError(Object message) {        
        logger.error(message);
        Reporter.log(new TimeString().getSimpleDateFormat()+" : "+message);
    }

    public static void logWarn(Object message) {        
        logger.warn(message);
        Reporter.log(new TimeString().getSimpleDateFormat()+" : "+message);
    }
}
