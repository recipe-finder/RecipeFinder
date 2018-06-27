package t5.comp3660.recipefinder;


import android.os.Bundle;
import android.app.Fragment;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
        Log.v("myApp", recipeName);
        int recipeId = bundle.getInt("id");
        final int usedIngredients = bundle.getInt("usedIngredients");
        TextView r_title = rootView.findViewById(R.id.r_title);
        r_title.setText(recipeName);

        ImageView r_image = rootView.findViewById(R.id.r_image);

        new DownloadImageTask(r_image).execute(recipeImageUrl);



        SpoonacularRequest getRequest = new SpoonacularRequest(new OnTaskComplete() {
            @Override
            public void onTaskCompleteRequest(String result) {
                Gson gson = new Gson();
                Type listType = new TypeToken<List<RecipeInstruction>>(){}.getType();
                List<RecipeInstruction> instructions = gson.fromJson(result, listType);
                RecipeInstruction actualInstructions = instructions.get(0);
                TextView inst_content = rootView.findViewById(R.id.r_instructions_content);
                for (RecipeInstruction.RecipeStep step : actualInstructions.steps) {
                    String currText = inst_content.getText().toString();
                    String additionalText = currText + "\n" + step.number + ". " + step.step + "\n";
                    inst_content.setText(additionalText);
                }
                rootView.findViewById(R.id.r_loading).setVisibility(View.GONE);
                rootView.findViewById(R.id.r_scrollview).setVisibility(View.VISIBLE);


            }
        });
        String path = "/recipes/" + recipeId + "/analyzedInstructions";
        getRequest.execute(path, "");

        return rootView;
    }

}
