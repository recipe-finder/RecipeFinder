package t5.comp3660.recipefinder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class RecipeResultListItemAdapter extends ArrayAdapter<RecipeResultListItem> {

    private LayoutInflater rrInflater;

    public RecipeResultListItemAdapter(Context context, int rid, List<RecipeResultListItem> list) {
        super(context, rid, list);
        rrInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        RecipeResultListItem item = (RecipeResultListItem)getItem(position);

        View view = rrInflater.inflate(R.layout.recipe_result_list_item, null);

        TextView title = (TextView)view.findViewById(R.id.rr_title);
        title.setText(item.title);

//        TextView usedIng = (TextView)view.findViewById(R.id.rr_used);
//        usedIng.setText(item.usedIngredients);
//
//        TextView missIng = (TextView)view.findViewById(R.id.rr_missing);
//        missIng.setText(item.missingIngredients);

        return view;
    }
}
