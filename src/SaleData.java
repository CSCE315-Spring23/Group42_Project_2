import javafx.beans.property.SimpleObjectProperty;

public static class SaleData {
    private SimpleObjectProperty<Integer> menuItemId;
    private SimpleObjectProperty<String> menuItemName;
    private SimpleObjectProperty<Integer> totalQuantity;
    
    public SaleData(int menuItemId, String menuItemName, int totalQuantity) {
        this.menuItemId = new SimpleObjectProperty<Integer>(menuItemId);
        this.menuItemName = new SimpleObjectProperty<String>(menuItemName);
        this.totalQuantity = new SimpleObjectProperty<Integer>(totalQuantity);
    }

    public Integer getMenuItemId() {
        return menuItemId.get();
    }

    public void setMenuItemId(Integer menuItemId) {
        this.menuItemId.set(menuItemId);
    }

    public String getMenuItemName() {
        return menuItemName.get();
    }

    public void setMenuItemName(String menuItemName) {
        this.menuItemName.set(menuItemName);
    }

    public Integer getTotalQuantity() {
        return totalQuantity.get();
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity.set(totalQuantity);
    }
}






