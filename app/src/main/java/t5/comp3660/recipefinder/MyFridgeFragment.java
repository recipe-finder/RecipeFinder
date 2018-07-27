package t5.comp3660.recipefinder;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyFridgeFragment extends Fragment {

    private final String TAG = "RecipeFinder";
    public static final String MyINGREDIENTS = "MyIngredients" ;
    public List<IngredientListItem> ingredient_list = new ArrayList<IngredientListItem>();

    public MyFridgeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final SharedPreferences sharedpreferences =
                this.getActivity().getSharedPreferences(MyINGREDIENTS, Context.MODE_PRIVATE);

        final Map<String, ?> myingredients = sharedpreferences.getAll();

        for(String key : myingredients.keySet()){
            String ingredient = (String) myingredients.get(key);
            ingredient_list.add(new IngredientListItem(ingredient));
        }

        // Inflate the layout for this fragment
        final View rootview = inflater.inflate(R.layout.fragment_my_fridge, container, false);

        final IngredientListItemAdapter adapter;
        adapter = new IngredientListItemAdapter(getActivity(),0, ingredient_list);

        final TextView remove_txt = rootview.findViewById(R.id.remove_txt);
        remove_txt.setVisibility(View.INVISIBLE);

        if (ingredient_list.size() > 0){
            remove_txt.setVisibility(View.VISIBLE);

        }


        final ListView listView = (ListView) rootview.findViewById(R.id.ListView01);
        TextView textView = rootview.findViewById(R.id.empty);
        listView.setAdapter(adapter);
        listView.setEmptyView(textView);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view,
                                    int position,
                                    long id) {
                IngredientListItem clicked = adapter.getItem(position);
                String ingredient = clicked.name;

                listView.setItemChecked(position, true);

            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long id) {
                // TODO Auto-generated method stub
                IngredientListItem clicked = adapter.getItem(position);
                String ingredient = clicked.name;

                listView.setItemChecked(position, true);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                ingredient_list.remove(clicked);
                adapter.notifyDataSetChanged();
                editor.remove(ingredient);
                editor.commit();
                Toast.makeText(getActivity(), "Removed " + ingredient.toLowerCase() + " from your fridge.", Toast.LENGTH_SHORT).show();

                if(adapter.getCount() == 0){
                    remove_txt.setVisibility(View.INVISIBLE);
                }
                return true;
            }

        });


        FloatingActionButton fab = rootview.findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                final EditText editText = new EditText(getActivity());
                editText.requestFocus();
                builder.setTitle("Add an Item to Your Fridge.");
                builder.setView(editText);
                builder.setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String ingredient = editText.getText().toString();
                        if (isStringNullOrWhiteSpace(ingredient)) {
                            Toast toast = Toast.makeText(getActivity(), "Ingredient cannot be blank", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                        else if(sharedpreferences.getString(ingredient, null) != null){
                            CharSequence text = "Ingredient " + ingredient + " already exists in your fridge";
                            Toast toast = Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT);
                            toast.show();
                        }
                        else {
                            IngredientListItem new_ingredient = new IngredientListItem(ingredient);
                            ingredient_list.add(new_ingredient);
                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            editor.putString(ingredient,ingredient);
                            editor.commit();
                            adapter.notifyDataSetChanged();
                        }
                        remove_txt.setVisibility(View.VISIBLE);
                    }
                })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                Dialog d = builder.create();
                d.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
                d.show();

            }
        });
        return rootview;
    }

    public static boolean isStringNullOrWhiteSpace(String value) {
        if (value == null) {
            return true;
        }

        for (int i = 0; i < value.length(); i++) {
            if (!Character.isWhitespace(value.charAt(i))) {
                return false;
            }
        }

        return true;
    }
}
