package com.example.android.musclegetter;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

    /* Currently using these hard-coded exercises and routines to fill in while all screens are made
    *  Ultimately these will be custom-created by the user.
    *  Exercise objects are put into the "exercises" ArrayList<Exercise> and passed through to other
    *  activities which will do different things with them (i.e., "Routines" categorizes and makes
    *  routines out of them, "Exercises" categorizes and displays them w/out creating any routines
    *  Objects are created here to reduce redundancy in other Activity classes, but they have to be
    *  passed through to other classes in Bundles. Eventually, all of these will be stored in
    *  SQLite as persistent data, modifiable by the user.
    Exercise squats = new Exercise("Squat", 5, 6, 90, "Legs", "Quads", "Glutes");
    Exercise dLift = new Exercise("Deadlift", 5, 6, 90, "Legs", "Hamstrings", "Lower Back");
    Exercise bench = new Exercise("Bench Press", 5, 6, 90, "Chest", "Pecorals", "Triceps");
    Exercise ohp = new Exercise("Overhead Press", 5, 6, 90, "Shoulders", "Shoulders", "Triceps");
    Exercise dbOhp = new Exercise("Dumbbell Overhead Press", 6, 8, 90, "Shoulders",
            "Shoulders", "Traps");
    Exercise legPress = new Exercise("Leg Press", 3, 12, 90, "Legs", "Quads", "Glutes");
    Exercise calf = new Exercise("Calf Raises", 50, 3, 60, "Legs","Calves", "Pretty much Calves");
    Exercise dbBench = new Exercise("Dumbbell Bench Press", 3, 8, 60, "Chest",
            "Pectorals", "Triceps");
    Exercise rows = new Exercise("Rows", 10, 3, 75, "Back", "Lats", "Buncha other back stuff");
    Exercise ezCurls = new Exercise("EZ Bar Curls", 10, 4, 60, "Arms", "Biceps", "Forearms");
    Exercise pushDown = new Exercise("Tricep Pushdown", 10, 6, 60, "Arms", "Triceps", "Forearms");
    Exercise hiit = new Exercise("HIIT", 45, 4, 90, "Cardio", "Quads", "Calves");
    */

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

            exercise.setDbId(c.getInt(c.getColumnIndex(ExEntry._ID)));

            // Add the newly constructed exercise to the Exercises ArrayList
            exercises.add(exercise);
        }

        // Add all the dummy exercises
        /*
        exercises.add(squats); exercises.add(dLift); exercises.add(bench); exercises.add(ohp);
        exercises.add(dbOhp); exercises.add(legPress); exercises.add(calf); exercises.add(dbBench);
        exercises.add(rows); exercises.add(ezCurls); exercises.add(pushDown); exercises.add(hiit);
        */

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
}
