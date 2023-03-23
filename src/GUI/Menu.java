import javafx.beans.property.SimpleObjectProperty;

/**
 * Represents an item on the restaurant's menu.
 */
public class Menu {
    /** 
     * The ID of the menu item. 
     */
    private SimpleObjectProperty<Long> menuID;
    
    /** 
     * The name of the menu item. 
     */
    private SimpleObjectProperty<String> itemName;
    
    /** 
     * The cost of the menu item. 
     */
    private SimpleObjectProperty<Double> itemCost;
    
    /**
     * Constructs a new Menu item.
     * 
     * @param id the menu item's ID
     * @param name the name of the menu item
     * @param cost the cost of the menu item
     */
    public Menu(final Long id, final String name, final Double cost){
        this.menuID = new SimpleObjectProperty<>(id);
        this.itemName = new SimpleObjectProperty<>(name);
        this.itemCost = new SimpleObjectProperty<>(cost);
    }
    
    /**
     * Returns the menu item's ID.
     * 
     * @return the menu item's ID
     */
    public SimpleObjectProperty<Long> getMenuID(){
        return this.menuID;
    }
    
    /**
     * Returns the name of the menu item.
     * 
     * @return the name of the menu item
     */
    public SimpleObjectProperty<String> getItemName(){
        return this.itemName;
    }
    
    /**
     * Returns the cost of the menu item.
     * 
     * @return the cost of the menu item
     */
    public SimpleObjectProperty<Double> getItemCost(){
        return this.itemCost;
    }
}