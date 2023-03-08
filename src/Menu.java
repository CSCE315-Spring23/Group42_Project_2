import javafx.beans.property.SimpleObjectProperty;

public class Menu {
    private SimpleObjectProperty<Long> menuID;
    private SimpleObjectProperty<String> itemName;
    private SimpleObjectProperty<Double> itemCost;

    public Menu(final Long id, final String name, final Double cost){
        this.menuID = new SimpleObjectProperty<>(id);
        this.itemName = new SimpleObjectProperty<>(name);
        this.itemCost = new SimpleObjectProperty<>(cost);
    }
    
    public SimpleObjectProperty<Long> getInventoryID(){
        return this.menuID;
    }
    
    public SimpleObjectProperty<String> getItemName(){
        return this.itemName;
    }
    
    public SimpleObjectProperty<Double> getItemCost(){
        return this.itemCost;
    }
    
}