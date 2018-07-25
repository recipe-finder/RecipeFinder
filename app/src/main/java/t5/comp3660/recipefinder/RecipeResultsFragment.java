package t5.comp3660.recipefinder;

import android.app.FragmentManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RecipeResultsFragment extends Fragment {

    private List<RecipeResultListItem> recipe_results_list = new ArrayList<RecipeResultListItem>();
    public List<RecipeResult> recipes;

    public RecipeResultsFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootview = inflater.inflate(R.layout.fragment_recipe_results, container, false);

        getActivity().setTitle("Recipe Results");

        final Bitmap defaultImage;
        defaultImage = BitmapFactory.decodeResource(getResources(), R.drawable.img_placeholder);

        final RecipeResultListItemAdapter adapter = new RecipeResultListItemAdapter(getActivity(), 0, recipe_results_list);

        final ListView rcpListView = (ListView) rootview.findViewById(R.id.RecipeResultsListView);
        final ProgressBar rcpEmpty = rootview.findViewById(R.id.rr_loading);
        rcpListView.setAdapter(adapter);
        rcpListView.setEmptyView(rcpEmpty);
        final ArrayList<String> searchIngredients = new ArrayList<String>();
        searchIngredients.add("rice");
        searchIngredients.add("chicken");
        searchIngredients.add("butter");
        searchIngredients.add("salt");
        searchIngredients.add("pepper");
        searchIngredients.add("onion");
        searchIngredients.add("parmesan");
        searchIngredients.add("paprika");




        SpoonacularRequest getRequest = new SpoonacularRequest(new OnTaskComplete() {
            @Override
            public void onTaskCompleteRequest(String result) {
                if (result == null) {
                    TextView txt = new TextView(getActivity());
                    txt.setText("No recipes were found. Please try again.");
                    LinearLayout lin = getActivity().findViewById(R.id.lin_list);
                    rcpEmpty.setVisibility(View.INVISIBLE);
                    lin.addView(txt);
                    return;
                }
                Gson gson = new Gson();
                Type listType = new TypeToken<List<RecipeResult>>(){}.getType();
                recipes = gson.fromJson(result, listType);
                if (recipes != null) {
                    for (RecipeResult recipeResult: recipes) {
                        RecipeResultListItem item = new RecipeResultListItem(recipeResult);
                        item.image = defaultImage;
                        item.details = "You have " + item.usedIngredientCount + " out of the required " + (item.usedIngredientCount + item.missingIngredientCount) + " ingredients";
                        recipe_results_list.add(item);
                    }
                    Collections.sort(recipe_results_list, new Comparator<RecipeResultListItem>() {
                        @Override
                        public int compare(RecipeResultListItem o1, RecipeResultListItem o2) {
                            return o1.usedIngredientCount < o2.usedIngredientCount ? 0 : -1;
                        }
                    });
                    adapter.notifyDataSetChanged();
                }

            }
        });
        String path = "/recipes/findByIngredients";
        String ingredients = "";
        for (String ingredient : searchIngredients) {
            ingredients += ingredient + ",";

        }
        ingredients = ingredients.substring(0, ingredients.length() - 1); // remove extra comma
        Log.v("myApp", ingredients);
        String params = "?ingredients=" + ingredients + "&number=10&ranking=1&fillIngredients=true";
        getRequest.execute(path, params);


        rcpListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentManager fm = getFragmentManager();
                Fragment recipe = new RecipeFragment();
                Bundle recipeData = new Bundle();
                Gson gson = new Gson();
                RecipeResultListItem clicked = recipe_results_list.get(position);
                recipeData.putInt("id", clicked.id);
                recipeData.putString("title", clicked.title);
                recipeData.putString("imageUrl", clicked.imageUrl);
                recipeData.putInt("usedIngredients", clicked.usedIngredientCount);
                recipeData.putString("missedIngredients", gson.toJson(clicked.missedIngredients));
                recipeData.putString("usedIngredients", gson.toJson(clicked.usedIngredients));
                recipeData.putString("unusedIngredients", gson.toJson(clicked.unusedIngredients));
                recipe.setArguments(recipeData);
                fm.beginTransaction().replace(R.id.content_frame, recipe).commit();
            }
        });

        Log.v("myApp", "returning view");

        return rootview;
    }
}
