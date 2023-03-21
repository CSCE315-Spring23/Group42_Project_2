import javafx.beans.property.SimpleObjectProperty;

public class Report {
    private SimpleObjectProperty<Integer> reportID;
    private SimpleObjectProperty<Integer> lastOrderID;
    private SimpleObjectProperty<String> zReportDate;
    private SimpleObjectProperty<Float> reportTotalCost;

    
    public Report(final Integer id, final Integer name, final String cost, final Float qty){
        this.reportID = new SimpleObjectProperty<>(id);
        this.lastOrderID = new SimpleObjectProperty<>(name);
        this.zReportDate = new SimpleObjectProperty<>(cost);
        this.reportTotalCost = new SimpleObjectProperty<>(qty);
    }

    public SimpleObjectProperty<Integer> getReportID(){
        return this.reportID;
    }

    public SimpleObjectProperty<Integer> getLastOrderID(){
        return this.lastOrderID;
    }

    public SimpleObjectProperty<String> getZReportDate(){
        return this.zReportDate;
    }

    public SimpleObjectProperty<Float> getReportTotalCost(){
        return this.reportTotalCost;
    }
}
