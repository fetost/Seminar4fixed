package test.kth.iv1350.model;

import se.kth.iv1350.integration.*; 
import org.junit.Test;
import static org.junit.Assert.*;

public class ExceptionTest {
    
    private int itemIdentifier;
    private ExternalInventory inventory;
    
    /**
     * Test to check functionality of InvalidItemIdentifierException, simulates a sale with a hardcoded item identifier not in the system.
     */
    @Test
    public void testInvalidItemIdentifierException(){
        try{
            inventory = new ExternalInventory();
            itemIdentifier = 7;
            inventory.retrieveItem(itemIdentifier);
            fail("Item found");
        }catch(InvalidItemIdentifierException exception){
            assertTrue("InvalidItemIdentifierException caught", true);
        }catch(DatabaseFailureException exception){
        }
    }

    /**
     * Test to check functionality of DatabaseFailureException, simulates a sale with a hardcoded failure condition.
     */
    @Test
    public void testDatabaseFailureException(){
        try{
            inventory = new ExternalInventory();
            itemIdentifier = 6;
            inventory.retrieveItem(itemIdentifier);
            fail("Database up and running");
        }catch(DatabaseFailureException exception){
            assertTrue("DatabaseFailureException caught", true);
        }catch(InvalidItemIdentifierException exception){
        }
    }
}
