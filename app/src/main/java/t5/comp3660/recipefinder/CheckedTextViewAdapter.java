package t5.comp3660.recipefinder;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CheckedTextViewAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private Context context;
    private ArrayList<String> rows;
    public HashMap<Integer, Boolean> checkedPos;

    public CheckedTextViewAdapter(@NonNull Context context) {
        super();

        this.context = context;
        this.inflater = (LayoutInflater.from(context));
        this.rows = new ArrayList<>();
        this.checkedPos = new HashMap<Integer, Boolean>();
    }

    public void addRow(String txt) {
        rows.add(txt);
    }

    @Override
    public int getCount() {
        return rows.size();
    }

    @Override
    public String getItem(int position) {
        return rows.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        view = inflater.inflate(R.layout.recipe_search_list_item, null);
        final CheckedTextView simpleCheckedTextView = (CheckedTextView) view.findViewById(R.id.ctv);
        simpleCheckedTextView.setText(rows.get(position));
        simpleCheckedTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (simpleCheckedTextView.isChecked()) {
                    simpleCheckedTextView.setChecked(false);
                    checkedPos.put(position, false);

                } else {
                    simpleCheckedTextView.setChecked(true);
                    checkedPos.put(position, true);
                }
            }
        });
        return view;
    }
}
