import javafx.beans.property.SimpleObjectProperty;

/**
 * Represents a recipe in the system.
 */
public class Recipe {
    /**
     * The ID of the recipe.
     */
    private SimpleObjectProperty<Long> recipeID;
    
    /**
     * The name of the recipe.
     */
    private SimpleObjectProperty<String> recipeName;
    
    /**
     * The ID of the inventory used in the recipe.
     */
    private SimpleObjectProperty<Long> recipeInventoryID;
    
    /**
     * The ID of the menu to which the recipe belongs.
     */
    private SimpleObjectProperty<Long> recipeMenuID;
    
    /**
     * The quantity of the recipe.
     */
    private SimpleObjectProperty<Double> recipeQuantity;



    /**
     * Constructor for the Recipe class.
     * 
     * @param id       the recipe ID
     * @param name     the recipe name
     * @param iid      the inventory ID
     * @param mid      the menu ID
     * @param quantity the recipe quantity
     */
    public Recipe(final Long id, final String name, final Long iid, final Long mid, final Double quantity) {
        this.recipeID = new SimpleObjectProperty<>(id);
        this.recipeName = new SimpleObjectProperty<>(name);
        this.recipeInventoryID = new SimpleObjectProperty<>(iid);
        this.recipeMenuID = new SimpleObjectProperty<>(mid);
        this.recipeQuantity = new SimpleObjectProperty<>(quantity);
    }

    /**
     * Returns the recipe ID.
     * 
     * @return the recipe ID
     */
    public SimpleObjectProperty<Long> getRecipeID() {
        return this.recipeID;
    }

    /**
     * Returns the recipe name.
     * 
     * @return the recipe name
     */
    public SimpleObjectProperty<String> getRecipeName() {
        return this.recipeName;
    }

    /**
     * Returns the inventory ID.
     * 
     * @return the inventory ID
     */
    public SimpleObjectProperty<Long> getInventoryID() {
        return this.recipeInventoryID;
    }

    /**
     * Returns the menu ID.
     * 
     * @return the menu ID
     */
    public SimpleObjectProperty<Long> getMenuID() {
        return this.recipeMenuID;
    }

    /**
     * Returns the recipe quantity.
     * 
     * @return the recipe quantity
     */
    public SimpleObjectProperty<Double> getQuantity() {
        return this.recipeQuantity;
    }
    
}