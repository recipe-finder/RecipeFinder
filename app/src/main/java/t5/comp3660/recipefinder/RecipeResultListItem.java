package t5.comp3660.recipefinder;

import android.graphics.Bitmap;

import java.util.List;

public class RecipeResultListItem {

    public int id;
    public String title;
    public String imageUrl;
    public int usedIngredientCount;
    public int missingIngredientCount;
    public Bitmap image;
    public boolean imageLoaded = false;
    public String details;
    public List<Recipe.RecipeIngredient> missedIngredients;
    public List<Recipe.RecipeIngredient> usedIngredients;
    public List<Recipe.RecipeIngredient> unusedIngredients;

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
