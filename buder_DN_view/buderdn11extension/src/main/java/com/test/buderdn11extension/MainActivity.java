package com.test.buderdn11extension;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.test.buderdn11extension.itemTouchHelper.ItemTouchHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MainRecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MainRecyclerAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.updateData(createTestDatas());

        ItemTouchHelpCallback callback = new ItemTouchHelpCallback();
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    private List<TestModel> createTestDatas() {
        List<TestModel> result = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            TestModel testModel = new TestModel(i, ":Item Swipe Action Button Container Width");
            if (i == 1) {
                testModel = new TestModel(i, "Item Swipe with Action container width and no spring");
            }
            if (i == 2) {
                testModel = new TestModel(i, "Item Swipe with RecyclerView Width");
            }
            result.add(testModel);
        }
        return result;
    }
}
