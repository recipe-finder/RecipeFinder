package t5.comp3660.recipefinder;


import android.app.FragmentManager;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */

/* Search Recipes by Ingredients
 * Get Analyzed RecipeResult Instructions
 * https://market.mashape.com/spoonacular/recipe-food-nutrition#analyze-a-recipe-search-query-1
 */
public class RecipeResultsFragment extends Fragment {

    private List<RecipeResultListItem> recipe_results_list = new ArrayList<RecipeResultListItem>();

    public RecipeResultsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootview = inflater.inflate(R.layout.fragment_recipe_results, container, false);

        // Inflate the layout for this fragment

//        String base = "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com";
//        String recipePath = "/recipes/findByIngredients";
//        String params = "?ingredients=apples,flour,sugar&number=3";
//
//        //Some url endpoint that you may have
//        String myUrl = base + recipePath + params;
//        String result;
//        //Instantiate new instance of our class
//        HttpGetRequest getRequest = new HttpGetRequest();
//        //Perform the doInBackground method, passing in our url
//        getRequest.execute(myUrl);

        final RecipeResultListItemAdapter adapter = new RecipeResultListItemAdapter(getActivity(), 0, recipe_results_list);

        ListView rcpListView = (ListView) rootview.findViewById(R.id.RecipeResultsListView);
        TextView rcpEmpty = (TextView) rootview.findViewById(R.id.RecipeResultsEmpty);
        rcpListView.setAdapter(adapter);
        rcpListView.setEmptyView(rcpEmpty);

        rcpListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentManager fm = getFragmentManager();
                Fragment recipe = new RecipeFragment();
                Bundle recipeData = new Bundle();
                RecipeResultListItem clicked = recipe_results_list.get(position);
                recipeData.putInt("id", clicked.id);
                recipeData.putString("title", clicked.title);
                recipe.setArguments(recipeData);
                fm.beginTransaction().replace(R.id.content_frame, recipe).commit();
            }
        });

        FakeHttpClient getRequest = new FakeHttpClient(new OnTaskComplete() {
            @Override
            public void onTaskCompleteRequest(String result) {
                Gson gson = new Gson();
                Type listType = new TypeToken<List<RecipeResult>>(){}.getType();
                List<RecipeResult> recipes = gson.fromJson(result, listType);
                for (RecipeResult recipeResult: recipes) {
                    recipe_results_list.add(new RecipeResultListItem(recipeResult));
                }
                adapter.notifyDataSetChanged();
            }
        }, 1);
        getRequest.execute();



        return rootview;
    }
}
