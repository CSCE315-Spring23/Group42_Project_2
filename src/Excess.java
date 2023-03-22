import javafx.beans.property.SimpleObjectProperty;

public class Excess {
    private SimpleObjectProperty<Long> menuItemId;
    private SimpleObjectProperty<String> menuItemName;

    /**
     * Constructor for Excess
     * @param menuItemId
     * @param menuItemName
     */
    public Excess(Long menuItemId, String menuItemName) {
        this.menuItemId = new SimpleObjectProperty<>(menuItemId);
        this.menuItemName = new SimpleObjectProperty<>(menuItemName);
    }

    /**
     * 
     * @return menu item ID
     */
    public SimpleObjectProperty<Long> getMenuItemId() {
        return this.menuItemId;
    }

    /**
     * 
     * @return menu item name
     */
    public SimpleObjectProperty<String> getMenuItemName() {
        return this.menuItemName;
    }
}
