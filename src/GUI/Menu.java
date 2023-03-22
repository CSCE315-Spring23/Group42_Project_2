import javafx.beans.property.SimpleObjectProperty;

public class Menu {
    private SimpleObjectProperty<Long> menuID;
    private SimpleObjectProperty<String> itemName;
    private SimpleObjectProperty<Double> itemCost;

    /**
     * Constructor for menu
     * @param id menu ID
     * @param name item name
     * @param cost cost of item
     */
    public Menu(final Long id, final String name, final Double cost){
        this.menuID = new SimpleObjectProperty<>(id);
        this.itemName = new SimpleObjectProperty<>(name);
        this.itemCost = new SimpleObjectProperty<>(cost);
    }
    
    /**
     * 
     * @return menu ID
     */
    public SimpleObjectProperty<Long> getMenuID(){
        return this.menuID;
    }
    
    /**
     * 
     * @return item name
     */
    public SimpleObjectProperty<String> getItemName(){
        return this.itemName;
    }
    
    /**
     * 
     * @return item cost
     */
    public SimpleObjectProperty<Double> getItemCost(){
        return this.itemCost;
    }
    
}