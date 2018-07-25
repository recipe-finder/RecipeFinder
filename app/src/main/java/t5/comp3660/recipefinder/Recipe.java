package t5.comp3660.recipefinder;

import java.util.List;

public class Recipe {
    public int id;
    public String title;
    public int readyInMinutes;
    public int servings;
    public List<Instruction> analyzedInstructions;
    public List<Ingredient> extendedIngredients;

    public static class Instruction {
        public String name;
        public List<Step> steps;

        public static class Step {
            public int number;
            public String step;
        }
    }

    public static class Ingredient {
        public int id;
        public String name;
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


