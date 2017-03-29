package eu.szwiec.pixelperfect;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class ScrollingActivity extends AppCompatActivity {
    private List<Item> itemList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MoviesAdapter mAdapter;
    private AppBarLayout.OnOffsetChangedListener mListener;
    private CollapsingToolbarLayout collapsingToolbar;
    private AppBarLayout appBar;
    private View collapsingToolbarContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        appBar = (AppBarLayout) findViewById(R.id.app_bar);
        collapsingToolbarContent = findViewById(R.id.content);

        mAdapter = new MoviesAdapter(itemList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        prepareMovieData();

        mListener = new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                float percentage = ((float)Math.abs(verticalOffset)/appBarLayout.getTotalScrollRange());
                collapsingToolbarContent.setAlpha(1-percentage);
            }
        };

        appBar.addOnOffsetChangedListener(mListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void prepareMovieData() {
        itemList.add(new Item("Extra Espresso", "Add a little kick to your coffee lorem ipsum dolores.", "25", true, getResources().getDrawable(R.drawable.espresso)));
        itemList.add(new Item("Cafe Latte", "Add a little kick to your coffee lorem ipsum dolores.", "25", true, getResources().getDrawable(R.drawable.latte)));
        itemList.add(new Item("Chocolate", "Add a little kick to your coffee lorem ipsum dolores.", "25", false, getResources().getDrawable(R.drawable.chocolate)));
        itemList.add(new Item("Cafe Latte", "Add a little kick to your coffee lorem ipsum dolores.", "25", false, getResources().getDrawable(R.drawable.latte)));
        itemList.add(new Item("Chocolate", "Add a little kick to your coffee lorem ipsum dolores.", "25", false, getResources().getDrawable(R.drawable.chocolate)));

        mAdapter.notifyDataSetChanged();
    }
}
