import javafx.beans.property.SimpleObjectProperty;

/**
 * Represents the content of a report, containing the name of a menu item and its quantity for reports.
 */
public class ReportContent {
    /**
     * The name of the menu item for the report.
     */
    private SimpleObjectProperty<String> menuItemNameForReports;
    
    /**
     * The quantity of the menu item for the report.
     */
    private SimpleObjectProperty<Integer> menuItemQuantityForReports;
    
    /**
     * Constructs a new ReportContent object with the specified name and quantity.
     * 
     * @param name the name of the menu item for the report
     * @param quantity the quantity of the menu item for the report
     */
    public ReportContent(final String name, final Integer quantity){
        this.menuItemNameForReports = new SimpleObjectProperty<>(name);
        this.menuItemQuantityForReports = new SimpleObjectProperty<>(quantity);        
    }

    /**
     * Returns the name of the menu item for the report.
     * 
     * @return the name of the menu item for the report
     */
    public SimpleObjectProperty<String> getMenuItemNameForReports(){
        return this.menuItemNameForReports;
    }

    /**
     * Returns the quantity of the menu item for the report.
     * 
     * @return the quantity of the menu item for the report
     */
    public SimpleObjectProperty<Integer> getMenuItemQuantityForReports(){
        return this.menuItemQuantityForReports;
    }
}
