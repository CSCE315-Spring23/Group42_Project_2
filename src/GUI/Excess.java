import javafx.beans.property.SimpleObjectProperty;

public class Excess {
    private SimpleObjectProperty<Long> menuItemId;
    private SimpleObjectProperty<String> menuItemName;

    /**
     * Constructor for Excess
     * @param menuItemId
     * @param menuItemName
     * @author Arjun
     */
    public Excess(Long menuItemId, String menuItemName) {
        this.menuItemId = new SimpleObjectProperty<>(menuItemId);
        this.menuItemName = new SimpleObjectProperty<>(menuItemName);
    }

    /**
     * 
     * @return menu item ID
     * @author Arjun
     */
    public SimpleObjectProperty<Long> getMenuItemId() {
        return this.menuItemId;
    }

    /**
     * 
     * @return menu item name
     * @author Arjun
     */
    public SimpleObjectProperty<String> getMenuItemName() {
        return this.menuItemName;
    }
}
