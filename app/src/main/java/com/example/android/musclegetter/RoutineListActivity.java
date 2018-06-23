package com.example.android.musclegetter;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.example.android.musclegetter.data.GymContract;
import com.example.android.musclegetter.data.GymContract.RoutineEntry;
import com.example.android.musclegetter.data.GymDbHelper;

import java.util.ArrayList;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

/**
 * Created by Clayton on 7/18/17.
 */

/* TODO: Cleanup this file after the new list population is implemented. Remove fields, fake
         exercises used for testing, filteredRoutines array, etc.
 */


public class RoutineListActivity extends AppCompatActivity {

    private ArrayList<Routine> categoryRoutines;
    private String category;

    // Use to get a readable database to populate the routine list
    private GymDbHelper gymDbHelper;

    // Used to get a cursor to iterate over desired DB records
    private SQLiteDatabase db;


    // ################################## BEGIN REMOVE #############################################
    // extra exercises for ListView scroll testing

    Exercise e1 = new Exercise("ExtraLeg", "","Legs",
            "Quads", "Calves", 10, 10, 10);
    Exercise e2 = new Exercise("ExtraLeg", "","Legs",
            "Quads", "Calves", 10, 10, 10);
    Exercise e3 = new Exercise("ExtraLeg", "","Legs",
            "Quads", "Calves", 10, 10, 10);
    Exercise e4 = new Exercise("ExtraLeg", "","Legs",
            "Quads", "Calves", 10, 10, 10);

    // end of extra exercises -- rest are srs

    Exercise squats = new Exercise("Squat", "", "Legs",
            "Quads", "Glutes", 5, 6, 90);
    Exercise dLift = new Exercise("Deadlift", "", "Legs",
            "Hamstrings", "Lower Back", 5, 6, 90);
    Exercise bench = new Exercise("Bench Press", "", "Chest",
            "Pecorals", "Triceps", 5, 6, 90);
    Exercise ohp = new Exercise("Overhead Press", "", "Shoulders",
            "Shoulders", "Triceps", 5, 6, 90);
    Exercise dbOhp = new Exercise("Dumbbell Overhead Press", "", "Shoulders",
            "Shoulders", "Traps", 6, 8, 90);
    Exercise legPress = new Exercise("Leg Press", "", "Legs",
            "Quads", "Glutes", 3, 12, 90);
    Exercise calf = new Exercise("Calf Raises", "", "Legs",
            "Calves", "Pretty much Calves", 50, 3, 60);
    Exercise dbBench = new Exercise("Dumbbell Bench Press", "", "Chest",
            "Pectorals", "Triceps", 3, 8, 60);
    Exercise rows = new Exercise("Rows", "", "Back",
            "Lats", "Buncha other back stuff", 10, 3, 75);
    Exercise ezCurls = new Exercise("EZ Bar Curls", "", "Arms",
            "Biceps", "Forearms", 10, 4, 60);
    Exercise pushDown = new Exercise("Tricep Pushdown", "", "Arms",
            "Triceps", "Forearms", 10, 6, 60);
    Exercise hiit = new Exercise("HIIT", "", "Cardio",
            "Quads", "Calves", 45, 4, 90);


    private Routine arms;
    private Routine legs;
    private Routine chest;
    private Routine shoulders;
    private Routine back;
    private Routine cardio;
    private Routine extra1; // extras for testing, remove later
    private Routine extra2;
    private Routine extra3;
    private Routine extra4;
    private Routine extra5;

    private Bundle exerciseListBundle;
    // ################################### END REMOVE #############################################

    private ArrayList<String> categories;
    String[] categoryArray = { "Arms", "Legs", "Chest", "Shoulders", "Back", "Cardio" };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);
        this.category = getIntent().getStringExtra("category");

        gymDbHelper = new GymDbHelper(this);
        db = gymDbHelper.getReadableDatabase();


        // Set up variables for the cursor call
        String table = RoutineEntry.TABLE_NAME;
        String[] columns = new String[] { "*" };
        String selection = RoutineEntry.COLUMN_ROUTINE_CATEGORY + " =?";
        String[] selectionArgs = new String[] { this.category };
        String groupBy = null;
        String having = null;
        String orderBy = null;
        String limit = null;

        // TODO: Here, make a query for all exercises matching category from bundle
        // Get a cursor for all records with the category passed in from the intent
        Cursor c = db.query("routine", columns, selection, selectionArgs,
                groupBy, having, orderBy, limit);

        categoryRoutines = new ArrayList<>();

        // Use the cursor to populate the routines list with routines of given category
        while (c.moveToNext())
        {
            String title = c.getString(c.getColumnIndex(RoutineEntry.COLUMN_ROUTINE_TITLE));
            String approxTime = c.getString(c.getColumnIndex(RoutineEntry.COLUMN_ROUTINE_LENGTH));
            // Don't get category since we know what it is based on the WHERE clause
            String desc = c.getString(c.getColumnIndex(RoutineEntry.COLUMN_ROUTINE_DESCRIPTION));

            // Build the routine with the variables populated by the cursor
            Routine builtFromRecord = new Routine();
            builtFromRecord.setTitle(title);
            builtFromRecord.setApproxTime(approxTime);
            builtFromRecord.setGroupWorked(category);
            builtFromRecord.setDescription(desc);

            // Add the routine to the list
            categoryRoutines.add(builtFromRecord);
        }

        RoutineListAdapter adapter = new RoutineListAdapter(this, categoryRoutines);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
    }
}
