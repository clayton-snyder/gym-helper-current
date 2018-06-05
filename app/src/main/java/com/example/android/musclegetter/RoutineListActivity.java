package com.example.android.musclegetter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

/**
 * Created by Clayton on 7/18/17.
 */




public class RoutineListActivity extends AppCompatActivity {

    private ArrayList<Routine> routines;
    private String category;
    private ArrayList<Routine> filteredRoutines;

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

    private ArrayList<String> categories;
    String[] categoryArray = { "Arms", "Legs", "Chest", "Shoulders", "Back", "Cardio" };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);
        this.category = getIntent().getStringExtra("category");
        exerciseListBundle = getIntent().getExtras();
        Log.i("ENTERED ACTIVITY: ", "RoutineListActivity");
        routines = new ArrayList<>();
        /* routines = new ArrayList<>
                ((ArrayList<Routine>) exerciseListBundle.getSerializable("routines")); */
        filteredRoutines = new ArrayList<>();


        ArrayList<Exercise> armL = new ArrayList<>(); armL.add(ezCurls); armL.add(pushDown);
        ArrayList<Exercise> legL = new ArrayList<>(); legL.add(squats); legL.add(dLift);
        legL.add(legPress); legL.add(calf); legL.add(e1); legL.add(e2); legL.add(e3); legL.add(e4);
        ArrayList<Exercise> chestL = new ArrayList<>(); chestL.add(bench); chestL.add(dbBench);
        ArrayList<Exercise> shldL = new ArrayList<>(); shldL.add(ohp); shldL.add(dbOhp);
        ArrayList<Exercise> backL = new ArrayList<>(); backL.add(rows);
        ArrayList<Exercise> cardioL = new ArrayList<>();  cardioL.add(hiit);


        arms = new Routine("Arms Routine", armL, "medium", "Arms", "Lorem ipsum dolor sit amet...");
        legs = new Routine("Legs Routine", legL, "long", "Legs", "Lorem ipsum dolor sit amet...");
        chest = new Routine("Chest Routine", chestL, "long", "Chest", "Lorem ipsum dolor sit amet...");
        shoulders = new Routine("Shoulders Routine", shldL, "medium", "Shoulders", "Lorem ipsum dolor sit amet...");
        back = new Routine("Back Routine", backL, "short", "Back", "Lorem ipsum dolor sit amet...");
        cardio = new Routine
                ("Cardio Routine", cardioL, "short", "Cardio", "Lorem ipsum dolor sit amet...");
        extra1 = new Routine(); extra2 = new Routine(); extra3 = new Routine();
        extra4 = new Routine(); extra5 = new Routine();



        routines.add(arms); routines.add(legs); routines.add(chest); routines.add(shoulders);
        routines.add(back); routines.add(cardio); routines.add(extra1); routines.add(extra2);
        routines.add(extra3); routines.add(extra4); routines.add(extra5);


        for (int i = 0; i < routines.size(); i++) {
            if (routines.get(i).getGroupWorked().equalsIgnoreCase(this.category))
                filteredRoutines.add(routines.get(i));
        }

        RoutineListAdapter adapter = new RoutineListAdapter(this, filteredRoutines);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

    }
}
