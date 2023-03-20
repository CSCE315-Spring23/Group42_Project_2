import javafx.beans.property.SimpleObjectProperty;

public class Combo {
    private SimpleObjectProperty<String> item1;
    private SimpleObjectProperty<String> item2;
    private SimpleObjectProperty<Long> numTimesOrdered;

    /**
     * Constructor for Combo
     * @param item1 first item of combination
     * @param item2 second item of combination
     * @param numTimesOrdered number of times the combo is ordered
     */
    public Combo(String item1, String item2, Long numTimesOrdered) {
        this.item1 = new SimpleObjectProperty<>(item1);
        this.item2 = new SimpleObjectProperty<>(item2);
        this.numTimesOrdered = new SimpleObjectProperty<>(numTimesOrdered);
    }

    /**
     * @return return first item
     */
    public SimpleObjectProperty<String> getItem1() {
        return this.item1;
    }

    /**
     * 
     * @return return second item
     */
    public SimpleObjectProperty<String> getItem2() {
        return this.item2;
    }

    /**
     * 
     * @return return number of times ordered
     */
    public SimpleObjectProperty<Long> getNumTimesOrdered() {
        return this.numTimesOrdered;
    }
}
