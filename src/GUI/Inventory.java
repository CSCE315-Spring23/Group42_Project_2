import javafx.beans.property.SimpleObjectProperty;

/**
 * Represents an inventory item and stores information such as inventory ID, item name, item cost, and item quantity.
 */
public class Inventory {
    /**
     *  Stores the inventory ID of the item.
     */
    private SimpleObjectProperty<Long> inventoryID;
    
    /**
     * Stores the name of the inventory item.
     */
    private SimpleObjectProperty<String> itemName;
    
    /**
     * Stores the total cost of the item.
     */
    private SimpleObjectProperty<Double> itemCost;
    
    /**
     * Stores the quantity of the item.
     */
    private SimpleObjectProperty<Long> itemQuantity;

    /**
     * Constructor for Inventory
     * @param id inventory ID
     * @param name name of inventory
     * @param cost total cost of inventory
     * @param qty quantity of items in inventory
     */
    public Inventory(final Long id, final String name, final Double cost, final Long qty){
        this.inventoryID = new SimpleObjectProperty<>(id);
        this.itemName = new SimpleObjectProperty<>(name);
        this.itemCost = new SimpleObjectProperty<>(cost);
        this.itemQuantity = new SimpleObjectProperty<>(qty);
    }
    
    /**
     * Returns the inventory ID.
     * @return inventory ID
     */
    public SimpleObjectProperty<Long> getInventoryID(){
        return this.inventoryID;
    }
    
    /**
     * Returns the name of the inventory item.
     * @return item name
     */
    public SimpleObjectProperty<String> getItemName(){
        return this.itemName;
    }
    
    /**
     * Returns the total cost of the inventory item.
     * @return item cost
     */
    public SimpleObjectProperty<Double> getItemCost(){
        return this.itemCost;
    }
    
    /**
     * Returns the quantity of items in the inventory item.
     * @return item quantity
     */
    public SimpleObjectProperty<Long> getItemQuantity(){
        return this.itemQuantity;
    }
}
