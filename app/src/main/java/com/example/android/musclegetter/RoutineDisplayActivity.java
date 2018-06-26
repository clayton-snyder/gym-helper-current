package com.example.android.musclegetter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Clayton on 7/20/17.
 */

public class RoutineDisplayActivity extends AppCompatActivity {

    private Bundle bundledRoutine;
    private Routine routine;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine);

        Log.i("ENTERED ROUTINE: ", "RoutineDisplayActivity.class");

        bundledRoutine = getIntent().getExtras();
        routine = (Routine) bundledRoutine.getSerializable("routine");

        RoutineDisplayAdapter adapter = new RoutineDisplayAdapter(this, routine.getExercises());
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
                Intent i = new Intent(this, AddExerciseActivity.class);
                i.putExtra("addingToRoutine", true);
                i.putExtra("routineId", this.routine._id);
                startActivity(i);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
