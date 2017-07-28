
package com.example.android.musclegetter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by zoro on 7/13/17.
 */

public class CategoryListActivity extends AppCompatActivity {

    private final int ARRAY_SIZE = 6;

    ArrayList<String> categories;
    ArrayList<Exercise> exercises;
    String[] categoryArray = { "Arms", "Legs", "Chest", "Shoulders", "Back", "Cardio" };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);
        Log.i("ENTERED ACTIVITY: ", "CategoryListActivity");
        Bundle b = getIntent().getExtras();
        exercises = (ArrayList<Exercise>) b.getSerializable("exerciseList");

        categories = new ArrayList();

        for (int i = 0; i < ARRAY_SIZE; i++) {
            categories.add(categoryArray[i]);
        }

        if (getIntent().getStringExtra("viewClicked").equalsIgnoreCase("exercises")) {
            CategoryListAdapter adapter =
                    new CategoryListAdapter(this, categories, b);
            ListView listView = (ListView) findViewById(R.id.list);
            listView.setAdapter(adapter);
        } else if (getIntent().getStringExtra("viewClicked").equalsIgnoreCase("routines")) {
            RoutineCategoryListAdapter adapter =
                    new RoutineCategoryListAdapter(this, categories, b);
            ListView listView = (ListView) findViewById(R.id.list);
            listView.setAdapter(adapter);
        }

    }
}

