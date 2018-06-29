package t5.comp3660.recipefinder;

import java.util.List;

public class RecipeResult {
    public int id;
    public String title;
    public String image;
    public String imageType;
    public int usedIngredientCount;
    public int missedIngredientCount;
    public int likes;
    public List<Recipe.RecipeIngredient> missedIngredients;
    public List<Recipe.RecipeIngredient> usedIngredients;
    public List<Recipe.RecipeIngredient> unusedIngredients;

//    public RecipeResult(int id, String title, String image, String imageType, int usedIngredientCount, int missedIngredientCount, int likes) {
//        this.id = id;
//        this.title = title;
//        this.image = image;
//        this.imageType = imageType;
//        this.usedIngredientCount = usedIngredientCount;
//        this.missedIngredientCount = missedIngredientCount;
//        this.likes = likes;
//    }
}
