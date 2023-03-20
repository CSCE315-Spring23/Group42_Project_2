import javafx.beans.property.SimpleObjectProperty;

public class SaleData {
    private SimpleObjectProperty<Long> menuItemId;
    private SimpleObjectProperty<String> menuItemName;
    private SimpleObjectProperty<Long> totalQuantity;

    /**
     * Constructor for SaleData
     * @param menuItemId
     * @param menuItemName
     * @param totalQuantity
     */
    public SaleData(Long menuItemId, String menuItemName, Long totalQuantity) {
        this.menuItemId = new SimpleObjectProperty<>(menuItemId);
        this.menuItemName = new SimpleObjectProperty<>(menuItemName);
        this.totalQuantity = new SimpleObjectProperty<>(totalQuantity);
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

    /**
     * 
     * 
     * @return total quantity of sales
     */
    public SimpleObjectProperty<Long> getTotalQuantity() {
        return this.totalQuantity;
    }
}
