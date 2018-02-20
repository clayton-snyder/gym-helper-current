package com.example.android.musclegetter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
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
        bundledRoutine = getIntent().getExtras();
        routine = (Routine) bundledRoutine.getSerializable("routine");

        RoutineDisplayAdapter adapter = new RoutineDisplayAdapter(this, routine.getExercises());
        ListView listView = (ListView) findViewById(R.id.routine_list_view);
        listView.setAdapter(adapter);
    }

}
