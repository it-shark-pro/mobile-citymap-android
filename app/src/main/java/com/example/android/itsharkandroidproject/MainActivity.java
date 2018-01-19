package com.example.android.itsharkandroidproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemClickListener{

    public static String EXTRA_ITEM_NAME = "EXTRA_ITEM_NAME";
    public static String DEFAULT_ITEM_NAME = "";
    public static String EXTRA_ITEM_DESCRIPTION = "EXTRA_ITEM_DESCRIPTION";
    public static String DEFAULT_ITEM_DESCRIPTION = "";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initList();
    }

    private void initList() {
        final RecyclerView listRecyclerView = findViewById(R.id.recycler_view_main_list);

        final LinearLayoutManager listLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        listRecyclerView.setLayoutManager(listLayoutManager);

        final ListAdapter listAdapter = new ListAdapter();
        listRecyclerView.setAdapter(listAdapter);
        listAdapter.setItemClickListener(this);

        final List<ListItemModel> itemModels = initListItemModels();
        listAdapter.update(itemModels);

        final DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                listRecyclerView.getContext(),
                listLayoutManager.getOrientation()
        );
        listRecyclerView.addItemDecoration(dividerItemDecoration);
    }

    private List<ListItemModel> initListItemModels() {
        final int FIRST_ITEM_ID = 1;
        final int LAST_ITEM_ID = 10;

        final List<ListItemModel> items = new ArrayList<>();

        for (int i = FIRST_ITEM_ID; i <= LAST_ITEM_ID; i++) {
            final ListItemModel listItemModel = new ListItemModel(i);
            items.add(listItemModel);
        }

        return items;
    }

    @Override
    public void onItemClick(final View view, final int position, final RecyclerView.Adapter adapter) {
        final ListItemModel listItemModel = ((ListAdapter) adapter).getItemByPostion(position);

        final Intent detailedActivityIntent = new Intent(this, DetailedActivity.class);
        detailedActivityIntent.putExtra(EXTRA_ITEM_NAME, listItemModel.getName());
        detailedActivityIntent.putExtra(EXTRA_ITEM_DESCRIPTION, listItemModel.getDescription());

        startActivity(detailedActivityIntent);
    }
}
