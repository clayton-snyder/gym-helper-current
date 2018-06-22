package com.example.android.musclegetter;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.android.musclegetter.data.GymContract;
import com.example.android.musclegetter.data.GymContract.ExEntry;
import com.example.android.musclegetter.data.GymDbHelper;

import java.util.ArrayList;

import static android.R.attr.id;

public class MainActivity extends AppCompatActivity {

    private GymDbHelper dbHelper;
    private SQLiteDatabase db;
    ArrayList<Exercise> exercises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        exercises = new ArrayList<Exercise>();
        Log.i("ENTERED ACTIVITY: ", "MainActivity");

        dbHelper = new GymDbHelper(this);
        db = dbHelper.getReadableDatabase();

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

            // Set the _id field (matches the Exercise table primary key) in the exercise object
            exercise.setDbId(c.getInt(c.getColumnIndex(ExEntry._ID)));

            // Add the newly constructed exercise to the Exercises ArrayList
            exercises.add(exercise);
        }

        // Instantiate the TextView for "exercises" on the main screen
        TextView exerciseTv = (TextView) findViewById(R.id.main_exercise_view);
        exerciseTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an intent to open the Category List Activity
                Intent i = new Intent(view.getContext(), CategoryListActivity.class);

                // This bundle will hold the exercise list to pass to the next view
                Bundle b = new Bundle();

                // Add the exercises ArrayList to the Bundle as a serializable
                b.putSerializable("exerciseList", exercises);

                /* This extra is used to inform CategoryListActivity which view the user selected
                *  The CategoryListActivity will get a string using the "viewClicked" key and will
                *  use string comparision to decide the correct view. */
                i.putExtra("viewClicked", "exercises");
                i.putExtras(b);

                // Finally, start the new activity
                startActivity(i);
            }
        });

        // Instantiate the TextView for "routines" on the main screen
        TextView routineTv = (TextView) findViewById(R.id.main_routine_view);
        routineTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an intent to open the Category List activity
                Intent i = new Intent(view.getContext(), CategoryListActivity.class);

                // This bundle will hold the exercise list to pass to the next view
                Bundle b = new Bundle();

                // Add the exercises ArrayList to the Bundle as a serializable
                b.putSerializable("exerciseList", exercises);

                /* This extra is used to inform CategoryListActivity which view the user selected
                *  The CategoryListActivity will get a string using the "viewClicked" key and will
                *  use string comparision to decide the correct view. */
                i.putExtra("viewClicked", "routines");
                i.putExtras(b);

                // Finally, start the new activity
                startActivity(i);
            }
        });
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
            case R.id.action_add_exercise:
                Intent i = new Intent(this, AddExerciseActivity.class);
                startActivity(i);
                return true;
            case R.id.action_add_routine:
                Intent j = new Intent(this, AddRoutineActivity.class);
                startActivity(j);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
