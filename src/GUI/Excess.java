import javafx.beans.property.SimpleObjectProperty;

/**
 * Creates class Excess that holds information for menu items with excess stock
 */
public class Excess {
    /**
     * ID of the menu item with excess stock
     */
    private SimpleObjectProperty<Long> menuItemId;

    /**
     * Name of the menu item with excess stock
     */
    private SimpleObjectProperty<String> menuItemName;
    
    /**
     * Constructor for Excess class
     * @param menuItemId the ID of the menu item
     * @param menuItemName the name of the menu item
     * @author Arjun
     */
    public Excess(Long menuItemId, String menuItemName) {
        this.menuItemId = new SimpleObjectProperty<>(menuItemId);
        this.menuItemName = new SimpleObjectProperty<>(menuItemName);
    }

    /**
     * Returns the menu item ID
     * @return menu item ID as a SimpleObjectProperty<Long>
     */
    public SimpleObjectProperty<Long> getMenuItemId() {
        return this.menuItemId;
    }

    /**
     * Returns the menu item name
     * @return menu item name as a SimpleObjectProperty<String>
     */
    public SimpleObjectProperty<String> getMenuItemName() {
        return this.menuItemName;
    }

}
