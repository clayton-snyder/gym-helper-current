package com.example.android.musclegetter;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.musclegetter.data.GymContract;
import com.example.android.musclegetter.data.GymContract.ExerciseInRoutineEntry;
import com.example.android.musclegetter.data.GymContract.ExEntry;
import com.example.android.musclegetter.data.GymDbHelper;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Clayton on 7/20/17.
 */

public class RoutineDisplayActivity extends AppCompatActivity {

    private Bundle bundledRoutine;
    private Routine routine;
    private int routineDbId;
    private ArrayList<Exercise> exercises;

    // Use to get a readable database to populate the routine list
    private GymDbHelper gymDbHelper;

    // Used to get a cursor to iterate over desired DB records
    private SQLiteDatabase db;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine);

        gymDbHelper = new GymDbHelper(this);
        db = gymDbHelper.getReadableDatabase();

        exercises = new ArrayList<Exercise>();

        Log.i("ENTERED ROUTINE: ", "RoutineDisplayActivity.class");

        this.bundledRoutine = getIntent().getExtras();
        this.routine = (Routine) bundledRoutine.getSerializable("routine");
        this.routineDbId = this.routine.getDbId();

        // Set the routine display's title
        TextView titleTv = (TextView) findViewById(R.id.routine_title);
        titleTv.setText(routine.getTitle());

        // Set the routine display's description
        TextView descTv = (TextView) findViewById(R.id.routine_description);
        descTv.setText(routine.getDescription());

        // Set up variables for the cursor call
        String table = ExerciseInRoutineEntry.TABLE_NAME;
        String[] columns = new String[] { "*" };
        String selection = ExerciseInRoutineEntry.COLUMN_ROUTINE_ID + " =?";
        String[] selectArgs = new String[] { this.routineDbId + "" };
        String groupBy = null;
        String having = null;
        String orderBy = null;
        String limit = null;

        // Get a cursor for all records with the given routine id
        Cursor linkC = db.query(table, columns, selection, selectArgs, groupBy, having, orderBy, limit);
        int exerciseId = -1;

        // Use the cursor to populate the routines list with exercises of given routine
        while (linkC.moveToNext())
        {
            // Get another cursor holding the exercise with the given id from linkC
            exerciseId = linkC.getInt(linkC.getColumnIndex
                    (ExerciseInRoutineEntry.COLUMN_EXERCISE_ID));

            String exTable = ExEntry.TABLE_NAME;
            String[] exColumns = new String[] { "*" };
            String exSelection = ExEntry._ID + " =?";
            String[] exSelectArgs = new String[] { exerciseId + "" };
            String exGroupBy = null;
            String exHaving = null;
            String exOrderBy = null;
            String exLimit = null;
            Cursor exC = db.query(exTable, exColumns, exSelection, exSelectArgs, exGroupBy,
                    exHaving, exOrderBy, exLimit);

            // Make sure the cursor got something and move to the first entry
            if (exC.moveToNext())
            {
                // Create variables to hold the data from the DB record to build the routine object
                int dbId = exC.getInt(exC.getColumnIndex((ExEntry._ID)));
                String title = exC.getString(exC.getColumnIndex(ExEntry.COLUMN_EXERCISE_TITLE));
                String desc = exC.getString(exC.getColumnIndex(ExEntry.COLUMN_EXERCISE_DESC));
                String category = exC.getString(exC.getColumnIndex(ExEntry.COLUMN_EXERCISE_CATEGORY));
                String primaryGroup = exC.getString(exC.getColumnIndex
                        (ExEntry.COLUMN_EXERCISE_GROUP_PRIMARY));
                String auxiliaryGroup = exC.getString(exC.getColumnIndex
                        (ExEntry.COLUMN_EXERCISE_GROUP_SECONDARY));
                int reps = exC.getInt(exC.getColumnIndex(ExEntry.COLUMN_EXERCISE_REPS));
                int sets = exC.getInt(exC.getColumnIndex(ExEntry.COLUMN_EXERCISE_SETS));
                int rest = exC.getInt(exC.getColumnIndex(ExEntry.COLUMN_EXERCISE_REST));

                // Build the routine with the variables populated by the cursor
                Exercise builtFromRecord = new Exercise(title, desc, category, primaryGroup,
                        auxiliaryGroup, reps, sets, rest);
                builtFromRecord.setDbId(dbId);

                // Add the routine to the list
                this.exercises.add(builtFromRecord);
            }
        }

        // Setup the routine display's list adapter to show the exercises
        RoutineDisplayAdapter adapter = new RoutineDisplayAdapter(this, this.exercises);
        ListView listView = (ListView) findViewById(R.id.routine_list_view);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.options_menu_routine_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId())
        {
            case R.id.action_add_exercise_to_routine:
                Intent i = new Intent(this, CategoryListActivity.class);
                i.putExtra("addingToRoutine", true);
                i.putExtra("routineDbId", this.routine._id);
                Log.i("addingToRoutine:", "true");
                Log.i("routineDbId", this.routine._id + "");
                startActivity(i);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
