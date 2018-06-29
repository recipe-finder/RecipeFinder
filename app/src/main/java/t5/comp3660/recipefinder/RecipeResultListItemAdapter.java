package t5.comp3660.recipefinder;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RecipeResultListItemAdapter extends ArrayAdapter<RecipeResultListItem> {

    private LayoutInflater rrInflater;

    public RecipeResultListItemAdapter(Context context, int rid, List<RecipeResultListItem> list) {
        super(context, rid, list);
        rrInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        final RecipeResultListItem item = (RecipeResultListItem)getItem(position);

        View view = rrInflater.inflate(R.layout.recipe_result_list_item, null);

        TextView title = (TextView)view.findViewById(R.id.rr_title);
        title.setText(item.title);

        final ImageView r_image = view.findViewById(R.id.rr_image);
        r_image.setImageBitmap(item.image);
        Bitmap defaultImage = BitmapFactory.decodeResource(r_image.getContext().getResources(), R.drawable.img_placeholder);
        if (item.image.sameAs(defaultImage) && !item.imageLoaded) {
            new DownloadImageBitmap(new OnImageBitMapDownloaded() {
                @Override
                public void OnBitMapDownloaded(Bitmap result) {
                    item.image = result;
                    r_image.setImageBitmap(result);
                    item.imageLoaded = true;
                }
            }).execute(item.imageUrl);
        }

        TextView usedIng = (TextView)view.findViewById(R.id.rr_used);
        usedIng.setText(item.details);
        return view;
    }
}
