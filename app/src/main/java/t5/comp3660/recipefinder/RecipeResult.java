package t5.comp3660.recipefinder;

import java.util.List;

public class RecipeResult {
    public int id;
    public String title;
    public String image;
    public int usedIngredientCount;
    public int missedIngredientCount;
    public List<Ingredient> missedIngredients;
    public List<Ingredient> usedIngredients;
    public List<Ingredient> unusedIngredients;

    public static class Ingredient {
        public int id;
        public Double amount;
        public String unitLong;
        public String name;
    }
}
