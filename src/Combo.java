import javafx.beans.property.SimpleObjectProperty;

public class Combo {
    private SimpleObjectProperty<String> item1;
    private SimpleObjectProperty<String> item2;
    private SimpleObjectProperty<Long> numTimesOrdered;

    public Combo(String item1, String item2, Long numTimesOrdered) {
        this.item1 = new SimpleObjectProperty<>(item1);
        this.item2 = new SimpleObjectProperty<>(item2);
        this.numTimesOrdered = new SimpleObjectProperty<>(numTimesOrdered);
    }

    public SimpleObjectProperty<String> getItem1() {
        return this.item1;
    }

    public SimpleObjectProperty<String> getItem2() {
        return this.item2;
    }

    public SimpleObjectProperty<Long> getNumTimesOrdered() {
        return this.numTimesOrdered;
    }
}
