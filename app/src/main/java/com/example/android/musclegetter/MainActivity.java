package com.example.android.musclegetter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import static android.R.attr.id;

public class MainActivity extends AppCompatActivity {

    ArrayList<Exercise> exercises = new ArrayList<>();
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("ENTERED ACTIVITY: ", "MainActivity");

        exercises.add(squats); exercises.add(dLift); exercises.add(bench); exercises.add(ohp);
        exercises.add(dbOhp); exercises.add(legPress); exercises.add(calf); exercises.add(dbBench);
        exercises.add(rows); exercises.add(ezCurls); exercises.add(pushDown); exercises.add(hiit);

        TextView exerciseTv = (TextView) findViewById(R.id.main_exercise_view);
        exerciseTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), CategoryListActivity.class);
                Bundle b = new Bundle();
                b.putSerializable("exerciseList", exercises);
                i.putExtra("viewClicked", "exercises");
                i.putExtras(b);
                startActivity(i);
            }
        });

        TextView routineTv = (TextView) findViewById(R.id.main_routine_view);
        routineTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), CategoryListActivity.class);
                Bundle b = new Bundle();
                b.putSerializable("exerciseList", exercises);
                i.putExtra("viewClicked", "routines");
                i.putExtras(b);
                startActivity(i);
            }
        });
    }
}
