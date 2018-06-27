package t5.comp3660.recipefinder;

import java.util.List;

public class RecipeInstruction {
    public String name;
    public List<RecipeStep> steps;

    public static class RecipeStep {
        public int number;
        public String step;
        public List<RecipeIngredient> ingredients;
    }

    public static class RecipeIngredient {
        public int id;
        public String name;
        public String image;
    }
}
