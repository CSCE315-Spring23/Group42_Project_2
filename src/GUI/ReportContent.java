import javafx.beans.property.SimpleObjectProperty;

public class ReportContent {
    private SimpleObjectProperty<String> menuItemNameForReports;
    private SimpleObjectProperty<Integer> menuItemQuantityForReports;
    
    public ReportContent(final String id, final Integer name){
        this.menuItemNameForReports = new SimpleObjectProperty<>(id);
        this.menuItemQuantityForReports = new SimpleObjectProperty<>(name);
        
    }

    public SimpleObjectProperty<String> getMenuItemNameForReports(){
        return this.menuItemNameForReports;
    }

    public SimpleObjectProperty<Integer> getMenuItemQuantityForReports(){
        return this.menuItemQuantityForReports;
    }
}
