import javafx.beans.property.SimpleObjectProperty;

public class SaleData {
    private SimpleObjectProperty<Long> menuItemId;
    private SimpleObjectProperty<String> menuItemName;
    private SimpleObjectProperty<Long> totalQuantity;

    public SaleData(Long menuItemId, String menuItemName, Long totalQuantity) {
        this.menuItemId = new SimpleObjectProperty<>(menuItemId);
        this.menuItemName = new SimpleObjectProperty<>(menuItemName);
        this.totalQuantity = new SimpleObjectProperty<>(totalQuantity);
    }

    public SimpleObjectProperty<Long> getMenuItemId() {
        return this.menuItemId;
    }

    public SimpleObjectProperty<String> getMenuItemName() {
        return this.menuItemName;
    }

    public SimpleObjectProperty<Long> getTotalQuantity() {
        return this.totalQuantity;
    }
}
