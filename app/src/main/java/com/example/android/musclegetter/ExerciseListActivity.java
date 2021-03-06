package com.example.android.musclegetter;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.android.musclegetter.data.GymDbHelper;
import com.example.android.musclegetter.data.GymContract.ExEntry;

import java.util.ArrayList;

import static com.example.android.musclegetter.R.layout.list_view;

/**
 * Created by Clayton on 7/14/17.
 */

public class ExerciseListActivity extends AppCompatActivity {

    protected String category = "NOT SET";
    ArrayList<Exercise> exercises = new ArrayList<>();
    ArrayList<Exercise> filteredExercises = new ArrayList<>();

    GymDbHelper dbHelper;
    SQLiteDatabase db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(list_view);
        Log.i("ENTERED ACTIVITY: ", "ExerciseListActivity");
        this.category = getIntent().getStringExtra("category");
        Bundle exerListBundle = getIntent().getExtras();
        this.exercises = new ArrayList<Exercise>
                ((ArrayList<Exercise>) exerListBundle.getSerializable("exerciseList"));

        dbHelper = new GymDbHelper(this);
        db = dbHelper.getWritableDatabase();

        // Get the entire table in a cursor to populate the exercises ArrayList
        Cursor c = db.query(ExEntry.TABLE_NAME, null, null, null,
                null, null, null);
        String title;
        String description;
        String category;
        String groupPrimary;
        String groupSecondary;
        int reps;
        int sets;
        int rest;

        exercises.clear();

        while (c.moveToNext())
        {
            title = c.getString(c.getColumnIndex(ExEntry.COLUMN_EXERCISE_TITLE));
            description = c.getString(c.getColumnIndex(ExEntry.COLUMN_EXERCISE_DESC));
            category = c.getString(c.getColumnIndex(ExEntry.COLUMN_EXERCISE_CATEGORY));
            groupPrimary = c.getString(c.getColumnIndex(ExEntry.COLUMN_EXERCISE_GROUP_PRIMARY));
            groupSecondary = c.getString(c.getColumnIndex(ExEntry.COLUMN_EXERCISE_GROUP_SECONDARY));
            reps = c.getInt(c.getColumnIndex(ExEntry.COLUMN_EXERCISE_REPS));
            sets = c.getInt(c.getColumnIndex(ExEntry.COLUMN_EXERCISE_SETS));
            rest = c.getInt(c.getColumnIndex(ExEntry.COLUMN_EXERCISE_REST));

            // Construct a new Exercise object from the data read from the database
            Exercise exercise = new Exercise(title, description, category, groupPrimary,
                    groupSecondary, reps, sets, rest);

            exercise.setDbId(c.getInt(c.getColumnIndex(ExEntry._ID)));

            // Add the newly constructed exercise to the Exercises ArrayList
            exercises.add(exercise);
        }

        for (int i = 0; i < exercises.size(); i++) {
            if (exercises.get(i).getCategory().equalsIgnoreCase(this.category)) {
                filteredExercises.add(exercises.get(i));
                Log.i("added? ", "YES");
            } else Log.i("added? ", "NO");
        }

        // exercise_menu_list_item is used here because this is the individual exercises menu
        ExerciseListItemAdapter listAdapter =
                new ExerciseListItemAdapter(this, filteredExercises,
                        R.layout.exercise_menu_list_item);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(listAdapter);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        // Get the entire table in a cursor to populate the exercises ArrayList
        Cursor c = db.query(ExEntry.TABLE_NAME, null, null, null,
                null, null, null);
        String title;
        String description;
        String category;
        String groupPrimary;
        String groupSecondary;
        int reps;
        int sets;
        int rest;

        exercises.clear();

        while (c.moveToNext())
        {
            title = c.getString(c.getColumnIndex(ExEntry.COLUMN_EXERCISE_TITLE));
            description = c.getString(c.getColumnIndex(ExEntry.COLUMN_EXERCISE_DESC));
            category = c.getString(c.getColumnIndex(ExEntry.COLUMN_EXERCISE_CATEGORY));
            groupPrimary = c.getString(c.getColumnIndex(ExEntry.COLUMN_EXERCISE_GROUP_PRIMARY));
            groupSecondary = c.getString(c.getColumnIndex(ExEntry.COLUMN_EXERCISE_GROUP_SECONDARY));
            reps = c.getInt(c.getColumnIndex(ExEntry.COLUMN_EXERCISE_REPS));
            sets = c.getInt(c.getColumnIndex(ExEntry.COLUMN_EXERCISE_SETS));
            rest = c.getInt(c.getColumnIndex(ExEntry.COLUMN_EXERCISE_REST));

            // Construct a new Exercise object from the data read from the database
            Exercise exercise = new Exercise(title, description, category, groupPrimary,
                    groupSecondary, reps, sets, rest);

            exercise.setDbId(c.getInt(c.getColumnIndex(ExEntry._ID)));

            // Add the newly constructed exercise to the Exercises ArrayList
            exercises.add(exercise);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_add_exercise:
                Intent i = new Intent(this, AddExerciseActivity.class);
                startActivity(i);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}