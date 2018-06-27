package t5.comp3660.recipefinder;

import android.app.FragmentManager;
import android.content.res.Resources;
import android.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class NavDrawerActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerListView;
    private RelativeLayout mDrawerRelativeLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    String[] mDrawerOptionLabels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_drawer);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        mDrawerListView = (ListView) findViewById(R.id.left_drawer);
        mDrawerRelativeLayout = (RelativeLayout) findViewById(R.id.left_drawer_layout);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close);

        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        // Populating ListView and Hadnling Selection
        Resources resources = getResources();
        mDrawerOptionLabels = resources.getStringArray(R.array.sliding_drawer_array);
        ArrayAdapter<String> drawerAdapter = new ArrayAdapter<String>(this, R.layout.drawer_list_item, mDrawerOptionLabels);

        mDrawerListView.setAdapter(drawerAdapter);

        mDrawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentManager fm = getFragmentManager();
                Fragment fragment = new MyFridgeFragment();

                switch (position) {
                    case 0:
                        fragment = new MyFridgeFragment();
                        break;
                    case 1:
                        fragment = new RecipeResultsFragment();
                        break;
                }

                fm.beginTransaction().replace(R.id.content_frame, fragment).commit();
                setTitle(mDrawerOptionLabels[position]);
                mDrawerListView.setItemChecked(position, true);
                mDrawerLayout.closeDrawer(mDrawerRelativeLayout);
            }
        });

        if (savedInstanceState == null) {
            FragmentManager fm = getFragmentManager();
            Fragment fragment = new MyFridgeFragment();
            fm.beginTransaction().replace(R.id.content_frame, fragment).commit();
            setTitle(mDrawerOptionLabels[0]);
        }


    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(mDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
