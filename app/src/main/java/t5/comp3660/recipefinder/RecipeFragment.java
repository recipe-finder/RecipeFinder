package t5.comp3660.recipefinder;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeFragment extends Fragment {


    public RecipeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_recipe, container, false);
        Bundle bundle = this.getArguments();
        String recipeName = bundle.getString("title");
        String recipeImageUrl = bundle.getString("imageUrl");
        final String missedIngredientsJson = bundle.getString("missedIngredients");
        final String usedIngredientsJson = bundle.getString("usedIngredients");
        final String unusedIngredientsJson = bundle.getString("unusedIngredients");
        final int recipeId = bundle.getInt("id");
        Log.v("myApp", Integer.toString(recipeId));
        TextView r_title = rootView.findViewById(R.id.r_title);
        r_title.setText(recipeName);

        final ImageView r_image = rootView.findViewById(R.id.r_image);

        new DownloadImageBitmap(new OnImageBitMapDownloaded() {
            @Override
            public void OnBitMapDownloaded(Bitmap result) {
                r_image.setImageBitmap(result);
            }
        }).execute(recipeImageUrl);

        SpoonacularRequest getRequest = new SpoonacularRequest(new OnTaskComplete() {
            @Override
            public void onTaskCompleteRequest(String result) {
                Gson gson = new Gson();
                Type jsonType = new TypeToken<Recipe>(){}.getType();
                Recipe recipe = gson.fromJson(result, jsonType);
                LinearLayout r_inst = rootView.findViewById(R.id.r_instructions);
                for (Recipe.Instruction instruction : recipe.analyzedInstructions) {
                    TextView rInstText = new TextView(rootView.getContext());
                    String instText = "";
                    instText += instruction.name + "\n";
                    for (Recipe.Instruction.Step step : instruction.steps) {
                        instText += step.number + ". " + step.step + "\n\n";
                    }
                    rInstText.setText(instText);
                    rInstText.setPadding(0, 20, 0, 0);
                    rInstText.setTextSize(18);
                    r_inst.addView(rInstText);
                }
                int green = rootView.getContext().getResources().getColor(R.color.colorIngredientInclusion);

                Type listType = new TypeToken<List<RecipeResult.Ingredient>>(){}.getType();
                List<RecipeResult.Ingredient> missing = gson.fromJson(missedIngredientsJson, listType);
                List<RecipeResult.Ingredient> used = gson.fromJson(usedIngredientsJson, listType);
                List<RecipeResult.Ingredient> unused = gson.fromJson(unusedIngredientsJson, listType);
                String requiredIngredients = "";
                String additionalIngredients = "";
                HashMap<Integer, RecipeResult.Ingredient> missingMap = new HashMap<Integer, RecipeResult.Ingredient>();
                HashMap<Integer, RecipeResult.Ingredient> usedMap = new HashMap<Integer, RecipeResult.Ingredient>();
                HashMap<Integer, RecipeResult.Ingredient> unUsedMap = new HashMap<Integer, RecipeResult.Ingredient>();
                for (RecipeResult.Ingredient miss: missing) {
                    missingMap.put(miss.id, miss);
                }
                for (RecipeResult.Ingredient use: used) {
                    usedMap.put(use.id, use);
                }
                for (RecipeResult.Ingredient un: unused) {
                    unUsedMap.put(un.id, un);
                }
                for (Recipe.Ingredient ext_ing : recipe.extendedIngredients) {
                    if (usedMap.containsKey(ext_ing.id)) {
                        requiredIngredients += "<font color='" + green + "'>" + ext_ing.name + "</font> - " + String.format("%.2f", ext_ing.measures.us.amount) + " " + ext_ing.measures.us.unitLong + "<br/>";
                    } else if (missingMap.containsKey(ext_ing.id)) {
                        requiredIngredients += ext_ing.name + " - " + String.format("%.2f", ext_ing.measures.us.amount) + " " + ext_ing.measures.us.unitLong + " <br/>";
                    } else if (unUsedMap.containsKey(ext_ing.id)) {
                        additionalIngredients += "<font color='" + green + "'>" + ext_ing.name + "</font> - " + String.format("%.2f", ext_ing.measures.us.amount) + " " + ext_ing.measures.us.unitLong + "<br/>";
                    } else {
                        additionalIngredients += ext_ing.name + " - " + String.format("%.2f", ext_ing.measures.us.amount) + " " + ext_ing.measures.us.unitLong + " <br/>";
                    }
                }
                TextView r_required_ingredients_content = rootView.findViewById(R.id.r_required_ingredients_content);
                r_required_ingredients_content.setText(Html.fromHtml(requiredIngredients), TextView.BufferType.NORMAL);

                TextView r_additional_ingredients_content = rootView.findViewById(R.id.r_additional_ingredients_content);
                r_additional_ingredients_content.setText(Html.fromHtml(additionalIngredients), TextView.BufferType.NORMAL);
                TextView r_servings = rootView.findViewById(R.id.r_servings);
                r_servings.setText("Servings: " + Integer.toString(recipe.servings));
                TextView r_readyin = rootView.findViewById(R.id.r_ready_in);
                r_readyin.setText("Ready In: " + Integer.toString(recipe.readyInMinutes) + " minutes");

                rootView.findViewById(R.id.r_loading).setVisibility(View.GONE);
                rootView.findViewById(R.id.r_scrollview).setVisibility(View.VISIBLE);
            }
        });
        String path = "/recipes/" + recipeId + "/information";
        getRequest.execute(path, "");

        return rootView;
    }

}
