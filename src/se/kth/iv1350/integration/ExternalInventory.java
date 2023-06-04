package se.kth.iv1350.integration;

import java.util.*;
import se.kth.iv1350.model.*;


  /**
   * An external inventory system that holds information about the items in the store.
   */
 
public class ExternalInventory {
    private List<Item> storeItems = new ArrayList<>(); 
	private List<ItemDescription> itemDescriptions = new ArrayList<>(); 
    

    
    /**
     * Creates a new instance of ExternalInventory and adds items to the store.
     */
    public ExternalInventory(){
        addItem(); 
    }
    
    /**
      Adds item descriptions to the itemDescriptions list and creates corresponding Item objects
      that are added to the storeItems list.
     */
    public void addItem() {
        this.itemDescriptions.add(new ItemDescription("Banan", 10.0, 0.25)); 
		this.itemDescriptions.add(new ItemDescription("Köttbullar 500g", 43.0, 0.12));
		this.itemDescriptions.add(new ItemDescription("Mjölk", 17.0, 0.06));
		this.itemDescriptions.add(new ItemDescription("Cola 2l", 29.0, 0.12));

		this.storeItems.add(new Item(1, itemDescriptions.get(0), 300.0)); 
		this.storeItems.add(new Item(2, itemDescriptions.get(1), 400.0));
		this.storeItems.add(new Item(3, itemDescriptions.get(2), 100.0));
		this.storeItems.add(new Item(4, itemDescriptions.get(3), 150.0));
    }

    /**
      Retrieves an Item object from the storeItems list based on its itemIdentifier.
      @param itemIdentifier the unique identifier of the item to be retrieved
      @return the Item object with the given itemIdentifier, or null if it is not found
      @throws DatabaseFailureException Exception for when an invalid item id is entered
      @throws InvalidItemIdentifierException Simulated exception to represent a database could not be reached
     */
    public Item retrieveItem(int itemIdentifier) throws DatabaseFailureException, InvalidItemIdentifierException{
        if (itemIdentifier == 6){

            throw new DatabaseFailureException("ERROR: Database could not be accessed");
        }
        for (Item item: storeItems){ 
            if (item.getItemIdentifier() == itemIdentifier){ 
                return item; 
            }
        }
        throw new InvalidItemIdentifierException("ERROR: Item Identifier " + itemIdentifier + " not valid, item not found in inventory");
    }

    /**
      Updates the storeItems list by subtracting the quantity of each item in the Sale object
      from the corresponding item in the store.
      @param sale the Sale object containing the items to be updated
     */
    public void updateInventory(Sale sale) {
        List<Item> items = sale.getItems(); 
        for (Item item : items) { 
            for (Item storeItem : storeItems) { 
                if (item.getItemIdentifier() == (storeItem.getItemIdentifier())) { 
                    double itemQuantity = item.getQuantity();
                    double storeItemQuantity = storeItem.getStoreQuantity();

                    storeItem.setStoreQuantity(storeItemQuantity - itemQuantity); 
                }
            }
        }
    }
}
