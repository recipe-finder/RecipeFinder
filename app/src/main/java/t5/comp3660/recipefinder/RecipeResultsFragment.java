package t5.comp3660.recipefinder;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rapidapi.rapidconnect.RapidApiConnect;


/**
 * A simple {@link Fragment} subclass.
 */

/* Search Recipes by Ingredients
 * Get Analyzed Recipe Instructions
 * https://market.mashape.com/spoonacular/recipe-food-nutrition#analyze-a-recipe-search-query-1
 */
public class RecipeResultsFragment extends Fragment
{
    RapidApiConnect connect = new RapidApiConnect("default-application_5b3263fbe4b0547c4a074f27",
            "56a06aa1-dcfc-431d-b1a8-37744f390a9f");


    public RapidApiConnect getConnect()
    {
        return connect;
    }

    public void setConnect(RapidApiConnect connect)
    {
        this.connect = connect;
    }


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
