package t5.comp3660.recipefinder;

public class RecipeResultListItem {

    public int id;
    public String title;
    public String imageUrl;
    public int usedIngredients;
    public int missingIngredients;

    public RecipeResultListItem(RecipeResult recipeResult) {
        this.id = recipeResult.id;
        this.title = recipeResult.title;
        this.usedIngredients = recipeResult.usedIngredientCount;
        this.missingIngredients = recipeResult.missedIngredientCount;
        this.imageUrl = recipeResult.image;
    }
}
