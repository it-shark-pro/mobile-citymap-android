package com.example.android.itsharkandroidproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
}
