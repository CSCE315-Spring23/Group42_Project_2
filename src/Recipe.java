import javafx.beans.property.SimpleObjectProperty;

public class Recipe {
    private SimpleObjectProperty<Long> recipeID;
    private SimpleObjectProperty<String> recipeName;
    private SimpleObjectProperty<Long> recipeInventoryID;
    private SimpleObjectProperty<Long> recipeMenuID;
    private SimpleObjectProperty<Double> recipeQuantity;

    public Recipe(final Long id, final String name, final Long iid, final Long mid, final Double quantity){
        this.recipeID = new SimpleObjectProperty<>(id);
        this.recipeName = new SimpleObjectProperty<>(name);
        this.recipeInventoryID = new SimpleObjectProperty<>(iid);
        this.recipeMenuID = new SimpleObjectProperty<>(mid);
        this.recipeQuantity = new SimpleObjectProperty<>(quantity);
    }
    
    public SimpleObjectProperty<Long> getRecipeID(){
        return this.recipeID;
    }
    
    public SimpleObjectProperty<String> getRecipeName(){
        return this.recipeName;
    }

    public SimpleObjectProperty<Long> getInventoryID(){
        return this.recipeInventoryID;
    }

    public SimpleObjectProperty<Long> getMenuID(){
        return this.recipeMenuID;
    }
    
    public SimpleObjectProperty<Double> getQuantity(){
        return this.recipeQuantity;
    }
    
}