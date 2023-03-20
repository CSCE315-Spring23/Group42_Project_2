
import javafx.beans.property.SimpleObjectProperty;

public class Inventory {
    private SimpleObjectProperty<Long> inventoryID;
    private SimpleObjectProperty<String> itemName;
    private SimpleObjectProperty<Double> itemCost;
    private SimpleObjectProperty<Long> itemQuantity;

    /**
     * Constructor for Inventory
     * @param id inventoryID
     * @param name name of inventory
     * @param cost total cost
     * @param qty quantity of items
     */
    public Inventory(final Long id, final String name, final Double cost, final Long qty){
        this.inventoryID = new SimpleObjectProperty<>(id);
        this.itemName = new SimpleObjectProperty<>(name);
        this.itemCost = new SimpleObjectProperty<>(cost);
        this.itemQuantity = new SimpleObjectProperty<>(qty);
    }
    
    /**
     * 
     * @return inventoryID
     */
    public SimpleObjectProperty<Long> getInventoryID(){
        return this.inventoryID;
    }
    
    /**
     * 
     * @return itemName
     */
    public SimpleObjectProperty<String> getItemName(){
        return this.itemName;
    }
    
    /**
     * 
     * @return itemCost
     */
    public SimpleObjectProperty<Double> getItemCost(){
        return this.itemCost;
    }
    
    /**
     * 
     * @return itemQuantity
     */
    public SimpleObjectProperty<Long> getItemQuantity(){
        return this.itemQuantity;
    }
}




