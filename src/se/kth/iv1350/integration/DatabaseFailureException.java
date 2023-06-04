package se.kth.iv1350.integration;
/**
 * Exception representing a Database Failure
 */
public class DatabaseFailureException extends Exception {

    /** 
    * Creates a new instance of DatabaseFailureException
    * @param message takes a given message to be shown
    */
    public DatabaseFailureException(String message){
        super(message); 
    }
    
}
