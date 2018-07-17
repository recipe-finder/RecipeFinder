package t5.comp3660.recipefinder;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 */

/* Search Recipes by Ingredients
 * Get Analyzed Recipe Instructions
 * https://market.mashape.com/spoonacular/recipe-food-nutrition#analyze-a-recipe-search-query-1
 */
public class RecipeResultsFragment extends Fragment
{

    public RecipeResultsFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipe_results, container, false);
    }

}
