package t5.comp3660.recipefinder;


import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeSearchFragment extends Fragment {
    String[] I = {"a","b","c","d","e","f","g"};
    public static final String MyINGREDIENTS = "MyIngredients" ;


    public RecipeSearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View rootview = inflater.inflate(R.layout.fragment_recipe_search, container, false);
        getActivity().setTitle("Search Recipes");

        final CheckedTextViewAdapter adapter;
        final ListView listView = (ListView) rootview.findViewById(R.id.lv);
        //CheckedTextView checkedTextView = (CheckedTextView) rootview.findViewById(R.id.ctv);
        adapter = new CheckedTextViewAdapter(listView.getContext());

        // ---------
        final SharedPreferences sharedpreferences =
                this.getActivity().getSharedPreferences(MyINGREDIENTS, Context.MODE_PRIVATE);

        final Map<String, ?> myingredients = sharedpreferences.getAll();

        int i = 0;
        for(String key : myingredients.keySet()){
            String ingredient = (String) myingredients.get(key);
            adapter.addRow(ingredient);
            adapter.checkedPos.put(i, true);
            i++;
        }
        // ---------

        listView.setAdapter(adapter);
        Button srch = rootview.findViewById(R.id.searchButton);
//        Log.v("myApp", Integer.toString(adapter.checkedPos.size()));
        if (i == 0) { // no ingredients form my fridge yet
            TextView e_txt = new TextView(getActivity());
            e_txt.setText("You have no items in your fridge. Please add some in order to search recipes.");
            e_txt.setGravity(Gravity.CENTER);
            e_txt.setPadding(0, 20, 0, 0);
            LinearLayout lin = rootview.findViewById(R.id.s_lin);
            lin.addView(e_txt);
            srch.setVisibility(View.INVISIBLE);
            listView.setVisibility(View.INVISIBLE);
        }



        srch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> ingreds = new ArrayList<>();
                for (Integer pos: adapter.checkedPos.keySet()) {
                    if (pos < adapter.getCount() && adapter.checkedPos.get(pos)) {
                        ingreds.add(adapter.getItem(pos));
                        Log.v("myApp", "ingred: " + adapter.getItem(pos));
                    } // else do nothing
                }
                if (ingreds.size() > 0) {
                    FragmentManager fm = getFragmentManager();
                    Fragment results = new RecipeResultsFragment();
                    Bundle searchData = new Bundle();
                    searchData.putStringArrayList("ingredients", ingreds);
                    results.setArguments(searchData);
                    fm.beginTransaction().replace(R.id.content_frame, results).commit();
                } else {
                    Toast.makeText(rootview.getContext(), "Please select at least one ingredient to search with", Toast.LENGTH_SHORT).show();
                }
            }
        });


       /* listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CheckedTextView ctv = (CheckedTextView) parent.getItemAtPosition(position);
                if (ctv.isChecked())
                    ctv.setChecked(false);
                else
                    ctv.setChecked(true);
            }
        });*/
        return rootview;
    }
}