package t5.comp3660.recipefinder;

import android.graphics.Bitmap;

import java.util.List;

public class RecipeResultListItem {

    public int id;
    public String title;
    public Bitmap image;
    public String imageUrl;
    public int usedIngredientCount;
    public int missingIngredientCount;
    public boolean imageLoaded = false;
    public String details;
    public List<RecipeResult.Ingredient> missedIngredients;
    public List<RecipeResult.Ingredient> usedIngredients;
    public List<RecipeResult.Ingredient> unusedIngredients;

    public RecipeResultListItem(RecipeResult recipeResult) {
        this.id = recipeResult.id;
        this.title = recipeResult.title;
        this.usedIngredientCount = recipeResult.usedIngredientCount;
        this.missingIngredientCount = recipeResult.missedIngredientCount;
        this.imageUrl = recipeResult.image;
        this.missedIngredients = recipeResult.missedIngredients;
        this.usedIngredients = recipeResult.usedIngredients;
        this.unusedIngredients = recipeResult.unusedIngredients;
    }
}
