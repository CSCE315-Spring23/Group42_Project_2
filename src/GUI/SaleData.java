import javafx.beans.property.SimpleObjectProperty;

/**
 * Represents the sale data for a specific menu item, including the item ID, name, and total quantity sold.
 */
public class SaleData {
    /**
     * The ID of the menu item.
     */
    
    private SimpleObjectProperty<Long> menuItemId;
   
    /**
     * The ID of the menu item.
     */
    private SimpleObjectProperty<String> menuItemName;
    
    /**
     * The ID of the menu item.
     */
    private SimpleObjectProperty<Long> totalQuantity;

    /**
     * Constructor for creating a new instance of SaleData.
     * @param menuItemId The ID of the menu item.
     * @param menuItemName The name of the menu item.
     * @param totalQuantity The total quantity sold of the menu item.
     */
    public SaleData(Long menuItemId, String menuItemName, Long totalQuantity) {
        this.menuItemId = new SimpleObjectProperty<>(menuItemId);
        this.menuItemName = new SimpleObjectProperty<>(menuItemName);
        this.totalQuantity = new SimpleObjectProperty<>(totalQuantity);
    }

    /**
     * Gets the menu item ID.
     * @return The menu item ID.
     */
    public SimpleObjectProperty<Long> getMenuItemId() {
        return this.menuItemId;
    }

    /**
     * Gets the menu item name.
     * @return The menu item name.
     */
    public SimpleObjectProperty<String> getMenuItemName() {
        return this.menuItemName;
    }

    /**
     * Gets the total quantity sold of the menu item.
     * @return The total quantity sold of the menu item.
     */
    public SimpleObjectProperty<Long> getTotalQuantity() {
        return this.totalQuantity;
    }
}
