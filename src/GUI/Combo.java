import javafx.beans.property.SimpleObjectProperty;

/**
 * Stores combos
 */
public class Combo {
    /**
     * Stores item 1
     */
    private SimpleObjectProperty<String> item1;

    /**
     * Stores item 2
     */
    private SimpleObjectProperty<String> item2;

    /**
     * Stores the number of times this combo was ordered
     */
    private SimpleObjectProperty<Long> numTimesOrdered;

    /**
     * Constructor for Combo
     * 
     * @param item1           first item of combination
     * @param item2           second item of combination
     * @param numTimesOrdered number of times the combo is ordered
     */
    public Combo(String item1, String item2, Long numTimesOrdered) {
        this.item1 = new SimpleObjectProperty<>(item1);
        this.item2 = new SimpleObjectProperty<>(item2);
        this.numTimesOrdered = new SimpleObjectProperty<>(numTimesOrdered);
    }

    /**
     * returns the first item
     * 
     * @return return first item
     * @author Arjun
     */
    public SimpleObjectProperty<String> getItem1() {
        return this.item1;
    }

    /**
     * returns the first item
     * 
     * @return return second item
     * @author Arjun
     */
    public SimpleObjectProperty<String> getItem2() {
        return this.item2;
    }

    /**
     * returns second item
     * 
     * @return return number of times ordered
     * @author Arjun
     */
    public SimpleObjectProperty<Long> getNumTimesOrdered() {
        return this.numTimesOrdered;
    }
}
