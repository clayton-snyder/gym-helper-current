package com.example.android.musclegetter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import static com.example.android.musclegetter.R.layout.list_view;

/**
 * Created by zoro on 7/14/17.
 */

public class ExerciseListActivity extends AppCompatActivity {

    protected String category = "NOT SET";
    ArrayList<Exercise> exercises = new ArrayList<>();
    ArrayList<Exercise> filteredExercises = new ArrayList<>();
    /*
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(list_view);
        Log.i("ENTERED ACTIVITY: ", "ExerciseListActivity");
        this.category = getIntent().getStringExtra("category");
        Bundle exerListBundle = getIntent().getExtras();
        this.exercises = new ArrayList<Exercise>
                ((ArrayList<Exercise>) exerListBundle.getSerializable("exerciseList"));

        /*
        exercises.add(squats); exercises.add(dLift); exercises.add(bench); exercises.add(ohp);
        exercises.add(dbOhp); exercises.add(legPress); exercises.add(calf); exercises.add(dbBench);
        exercises.add(rows); exercises.add(ezCurls); exercises.add(pushDown); exercises.add(hiit);
        */

        for (int i = 0; i < exercises.size(); i++) {
            Log.i("e:a ", exercises.get(i).getCategory() + " : " + category);
            if (exercises.get(i).getCategory().equalsIgnoreCase(this.category)) {
                filteredExercises.add(exercises.get(i));
                Log.i("added? ", "YES");
            } else Log.i("added? ", "NO");
        }

        // menu_list_item is used here because this is the individual exercises menu
        ExerciseListItemAdapter listAdapter =
                new ExerciseListItemAdapter(this, filteredExercises, R.layout.menu_list_item);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(listAdapter);
    }
}