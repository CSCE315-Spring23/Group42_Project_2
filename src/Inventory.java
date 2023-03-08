
import javafx.beans.property.SimpleObjectProperty;

public class Inventory {
    private SimpleObjectProperty<Long> inventoryID;
    private SimpleObjectProperty<String> itemName;
    private SimpleObjectProperty<Double> itemCost;
    private SimpleObjectProperty<Long> itemQuantity;

    public Inventory(final Long id, final String name, final Double cost, final Long qty){
        this.inventoryID = new SimpleObjectProperty<>(id);
        this.itemName = new SimpleObjectProperty<>(name);
        this.itemCost = new SimpleObjectProperty<>(cost);
        this.itemQuantity = new SimpleObjectProperty<>(qty);
    }
    
    public SimpleObjectProperty<Long> getInventoryID(){
        return this.inventoryID;
    }
    
    public SimpleObjectProperty<String> getItemName(){
        return this.itemName;
    }
    
    public SimpleObjectProperty<Double> getItemCost(){
        return this.itemCost;
    }
    
    public SimpleObjectProperty<Long> getItemQuantity(){
        return this.itemQuantity;
    }
}




