package t5.comp3660.recipefinder;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;



/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeSearchFragment extends Fragment {
    String[] I = {"a","b","c","d","e","f","g"};


    public RecipeSearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View rootview = inflater.inflate(R.layout.fragment_recipe_search, container, false);
        getActivity().setTitle("Search Recipes");

        CheckedTextViewAdapter adapter;
        ListView listView = (ListView) rootview.findViewById(R.id.lv);
        //CheckedTextView checkedTextView = (CheckedTextView) rootview.findViewById(R.id.ctv);
        adapter = new CheckedTextViewAdapter(listView.getContext());
        for(int i = 0; i<50; i++){
            //put the text you want to display
            adapter.addRow("item_"+i);
        }
        listView.setAdapter(adapter);


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