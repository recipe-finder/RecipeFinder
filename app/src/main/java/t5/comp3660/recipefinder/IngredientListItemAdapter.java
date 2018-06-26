package t5.comp3660.recipefinder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class IngredientListItemAdapter extends ArrayAdapter<IngredientListItem> {

    private LayoutInflater mInflater;
    public IngredientListItemAdapter(Context context, int rid, List<IngredientListItem> list){
        super(context, rid, list);
        mInflater =
                (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }
    public View getView(int position, View convertView, ViewGroup parent){
        // Retrieve data
        IngredientListItem item = (IngredientListItem)getItem(position);
        // Use layout file to generate View
        View view = mInflater.inflate(R.layout.ingredient_list_item, null);
        // Set user name
        TextView name;
        name = (TextView)view.findViewById(R.id.name);
        name.setText(item.name);
        // Set comment

        return view;
    }
}
