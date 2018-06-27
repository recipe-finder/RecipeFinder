package t5.comp3660.recipefinder;

public class RecipeResultListItem {

    public int id;
    public String title;
    public String imageUrl;
    public int usedIngredients;
    public int missingIngredients;

    public RecipeResultListItem(RecipeResult recipeResult) {
        this.id = recipeResult.getId();
        this.title = recipeResult.getTitle();
        this.usedIngredients = recipeResult.getUsedIngredientCount();
        this.missingIngredients = recipeResult.getMissedIngredientCount();
        this.imageUrl = recipeResult.getImage();
    }
}
