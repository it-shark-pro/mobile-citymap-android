package com.example.android.itsharkandroidproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemClickListener, IMainActivityView{

    public static String EXTRA_ITEM_NAME = "EXTRA_ITEM_NAME";
    public static String DEFAULT_ITEM_NAME = "";
    public static String EXTRA_ITEM_DESCRIPTION = "EXTRA_ITEM_DESCRIPTION";
    public static String DEFAULT_ITEM_DESCRIPTION = "";

    private ListAdapter listAdapter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecyclerView();

        final LoadCitiesTask loadCitiesTask = new LoadCitiesTask(this);
        loadCitiesTask.execute();
    }

    private void initRecyclerView() {
        final RecyclerView listRecyclerView = findViewById(R.id.recycler_view_main_list);

        final LinearLayoutManager listLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        listRecyclerView.setLayoutManager(listLayoutManager);

        listAdapter = new ListAdapter();
        listRecyclerView.setAdapter(listAdapter);
        listAdapter.setItemClickListener(this);

        final DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                listRecyclerView.getContext(),
                listLayoutManager.getOrientation()
        );
        listRecyclerView.addItemDecoration(dividerItemDecoration);
    }

    public void showCities(final List<ListItemModel> cities) {
        for (final ListItemModel item : cities) {
            Log.d("MainActivity", "item " +item.getId() + " " +item.getUrl());
        }
    }

    @Override
    public void onItemClick(final View view, final int position, final RecyclerView.Adapter adapter) {
        final ListItemModel listItemModel = ((ListAdapter) adapter).getItemByPostion(position);

        final Intent detailedActivityIntent = new Intent(this, DetailedActivity.class);
        detailedActivityIntent.putExtra(EXTRA_ITEM_NAME, listItemModel.getTitle());
        detailedActivityIntent.putExtra(EXTRA_ITEM_DESCRIPTION, listItemModel.getDescription());

        startActivity(detailedActivityIntent);
    }
}
