import javafx.beans.property.SimpleObjectProperty;

public class Recipe {
    private SimpleObjectProperty<Long> recipeID;
    private SimpleObjectProperty<String> recipeName;
    private SimpleObjectProperty<Long> recipeInventoryID;
    private SimpleObjectProperty<Long> recipeMenuID;
    private SimpleObjectProperty<Double> recipeQuantity;


    /**
     * Recipe Constructor
     * @param id recipe ID
     * @param name recipe name
     * @param iid inventory ID
     * @param mid menu ID
     * @param quantity recipe quantity
     */
    public Recipe(final Long id, final String name, final Long iid, final Long mid, final Double quantity){
        this.recipeID = new SimpleObjectProperty<>(id);
        this.recipeName = new SimpleObjectProperty<>(name);
        this.recipeInventoryID = new SimpleObjectProperty<>(iid);
        this.recipeMenuID = new SimpleObjectProperty<>(mid);
        this.recipeQuantity = new SimpleObjectProperty<>(quantity);
    }
    
    /**
     * 
     * @return recipe ID
     */
    public SimpleObjectProperty<Long> getRecipeID(){
        return this.recipeID;
    }
    
    /**
     * 
     * @return recipe name
     */
    public SimpleObjectProperty<String> getRecipeName(){
        return this.recipeName;
    }

    /**
     * 
     * @return recipe ID
     */
    public SimpleObjectProperty<Long> getInventoryID(){
        return this.recipeInventoryID;
    }

    /**
     * 
     * @return menu ID
     */
    public SimpleObjectProperty<Long> getMenuID(){
        return this.recipeMenuID;
    }
    
    /**
     * 
     * @return recipe quantity
     */
    public SimpleObjectProperty<Double> getQuantity(){
        return this.recipeQuantity;
    }
    
}