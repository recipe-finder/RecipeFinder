package t5.comp3660.recipefinder;

import java.util.List;

public class Recipe {
    public int id;
    public String title;
    public int readyInMinutes;
    public List<RecipeInstruction> analyzedInstructions;
    public List<RecipeIngredient> extendedIngredients;
    public int servings;

    public static class RecipeInstruction {
        public String name;
        public List<RecipeStep> steps;

        public static class RecipeStep {
            public int number;
            public String step;
        }
    }

    public static class RecipeIngredient {
        public int id;
        public String name;
        public Double amount;
        public String unit;
        public Measurement measures;

        public static class Measurement {
            public MeasurementUs us;

            public static class MeasurementUs {
                public Double amount;
                public String unitLong;
            }
        }
    }
}


