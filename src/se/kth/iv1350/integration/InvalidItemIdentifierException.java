package se.kth.iv1350.integration;

/**
 * Exception representing an invalid item id error
 */
public class InvalidItemIdentifierException extends Exception {

    /** 
    * Creates a new instance of InvalidItemIdentifierException
    * @param message takes a given message to be shown
    */
    public InvalidItemIdentifierException(String message) {
        super(message);
        }
        
}
