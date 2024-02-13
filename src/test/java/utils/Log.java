package utils;

import org.apache.log4j.*;

public class Log {

    //initialize log4j logs



    public static Logger log = Logger.getLogger(Log.class.getName());
    //                                          we mean to get the current class name as parameter
    //and initialzie the log with the current class name

    //understand the scope
    //if my test case start what i should print
    //if my test case end i want print
    //if any massgae i need in between

    public static void startTestCase(String testCaseName){

        log.info("**************************************");
        log.info("############   " + testCaseName + " ################");
        log.info("*********************************************");
        log.info("**********************************************");
    }

    public static void endTestCase(String testCaseName){
        log.info("##################################");
        log.info("##########################################");
        log.info("############   " + testCaseName + " ################");
        log.info("##########################");
        log.info("##########################################");

    }

    //to print some text in between my code
    public static void info(String message){
// if we get any error in the meddle of running we can get the message using this method
        log.info(message);
    }

    public static void warning(String warning){

        log.info(warning);
    }
}
