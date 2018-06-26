
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
 * Created by Clayton on 7/13/17.
 */

public class CategoryListActivity extends AppCompatActivity {

    private final int ARRAY_SIZE = 6;

    ArrayList<String> categories;
    ArrayList<Exercise> exercises;
    // TODO: Place to add "core"
    String[] categoryArray = { "Arms", "Legs", "Chest", "Shoulders", "Back", "Cardio" };

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // Set the view to display a list
        setContentView(R.layout.list_view);
        Log.i("ENTERED ACTIVITY: ", "CategoryListActivity");

        // Grab the bundle passed in through the Intent
        Bundle b = getIntent().getExtras();

        // I think this might be unnecessary? The CategoryListAdapter grabs the exercises from
        // the bundle b above
        exercises = (ArrayList<Exercise>) b.getSerializable("exerciseList");

        categories = new ArrayList();

        // Eventually this categories ArrayList will be built from user stored data
        for (int i = 0; i < ARRAY_SIZE; i++)
        {
            categories.add(categoryArray[i]);
        }

        // Check the "viewClicked" extra passed through to identify which adapter to use
        if (getIntent().getStringExtra("viewClicked").equalsIgnoreCase("routines"))
        {
            RoutineCategoryListAdapter adapter =
                    new RoutineCategoryListAdapter(this, categories, b);
            ListView listView = (ListView) findViewById(R.id.list);
            listView.setAdapter(adapter);
        }
        else // They tapped "exercises" or this is from "Add an exercise to this routine"
        {
            CategoryListAdapter adapter = new CategoryListAdapter(this, categories, b);
            ListView listView = (ListView) findViewById(R.id.list);
            listView.setAdapter(adapter);
        }
    }
}

