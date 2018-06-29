
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

    // Is the user trying to add an exercise to a routine?
    public Boolean addingToRoutine;

    // Stores the DbId of a routine the user is attempting to add an exercise to. If not applicable,
    // initialized to -1
    int routineDbId;

    // TODO: Place to add "core"
    String[] categoryArray = { "Arms", "Legs", "Chest", "Shoulders", "Back", "Cardio" };

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addingToRoutine = getIntent().getBooleanExtra("addingToRoutine", false);
        this.routineDbId = getIntent().getIntExtra("routineDbId", -1);

        // Set the view to display a list
        setContentView(R.layout.list_view);
        Log.i("ENTERED ACTIVITY: ", "CategoryListActivity");

        // Grab the bundle passed in through the Intent
        Bundle b = getIntent().getExtras();
        b.putBoolean("addingToRoutine", this.addingToRoutine);
        b.putInt("routineDbId", this.routineDbId);

        // I think this might be unnecessary? The CategoryListAdapter grabs the exercises from
        // the bundle b above
        // TODO: Investigate if this should be removed
        exercises = (ArrayList<Exercise>) b.getSerializable("exerciseList");

        categories = new ArrayList();

        // Eventually this categories ArrayList will be built from user stored data
        for (int i = 0; i < ARRAY_SIZE; i++)
        {
            categories.add(categoryArray[i]);
        }

        // First, check if we got here from the user tapping "Add an exercise to this routine"
        // NOTE: defaults to false if this extra doesn't exist
        if (getIntent().getBooleanExtra("addingToRoutine", false))
        {
            // The adapter handles onClick behavior by reading variables from this context
            RoutineCategoryListAdapter adapter =
            new RoutineCategoryListAdapter(this, categories, b);
            ListView listView = (ListView) findViewById(R.id.list);
            listView.setAdapter(adapter);
        }
        else
        {
            if (getIntent().getStringExtra("viewClicked").equalsIgnoreCase("routines"))
            {
                RoutineCategoryListAdapter adapter =
                        new RoutineCategoryListAdapter(this, categories, b);
                ListView listView = (ListView) findViewById(R.id.list);
                listView.setAdapter(adapter);
            }
            else // They tapped "exercises"
            {
                CategoryListAdapter adapter = new CategoryListAdapter(this, categories, b);
                ListView listView = (ListView) findViewById(R.id.list);
                listView.setAdapter(adapter);
            }
        }
    }
}

