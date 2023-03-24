import javafx.beans.property.SimpleObjectProperty;

/**
 * Represents a report in the system.
 */
public class Report {  
    /**
     * The ID of the report.
     */
    private SimpleObjectProperty<Integer> reportID;
    
    /**
     * The ID of the last order in the report.
     */
    private SimpleObjectProperty<Integer> lastOrderID;
    
    /**
     * The date the Z report was generated.
     */
    private SimpleObjectProperty<String> zReportDate;
    
    /**
     * The total cost of the report.
     */
    private SimpleObjectProperty<Float> reportTotalCost;

    /**
     * Creates a new report instance.
     *
     * @param id   The ID of the report.
     * @param name The ID of the last order in the report.
     * @param cost The date the Z report was generated.
     * @param qty  The total cost of the report.
     */
    public Report(final Integer id, final Integer name, final String cost, final Float qty) {
        this.reportID = new SimpleObjectProperty<>(id);
        this.lastOrderID = new SimpleObjectProperty<>(name);
        this.zReportDate = new SimpleObjectProperty<>(cost);
        this.reportTotalCost = new SimpleObjectProperty<>(qty);
    }

    /**
     * Returns the ID of the report.
     *
     * @return The ID of the report.
     */
    public SimpleObjectProperty<Integer> getReportID() {
        return this.reportID;
    }

    /**
     * Returns the ID of the last order in the report.
     *
     * @return The ID of the last order in the report.
     */
    public SimpleObjectProperty<Integer> getLastOrderID() {
        return this.lastOrderID;
    }

    /**
     * Returns the date the Z report was generated.
     *
     * @return The date the Z report was generated.
     */
    public SimpleObjectProperty<String> getZReportDate() {
        return this.zReportDate;
    }

    /**
     * Returns the total cost of the report.
     *
     * @return The total cost of the report.
     */
    public SimpleObjectProperty<Float> getReportTotalCost() {
        return this.reportTotalCost;
    }
}
