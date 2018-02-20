package com.example.android.musclegetter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Clayton on 7/14/17.
 */

// TODO: implement images

// TODO: gotta setup putextra for every intent call and set view texts from that

public class ExerciseDetailActivity extends AppCompatActivity {

    protected Exercise exercise;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_detail);
        Log.i("ENTERED ACTIVITY: ", "ExerciseDetailActivity");

        // get Exercise to be detailed from extras
        Bundle bundle = getIntent().getExtras();

        exercise = (Exercise) bundle.getSerializable("exercise");

        // TODO: set Title, reps, sets, rest, description

        // instantiate objects for views in the layout

        TextView title = (TextView) findViewById(R.id.edetail_title);
        TextView reps = (TextView) findViewById(R.id.edetail_reps_qty);
        TextView sets = (TextView) findViewById(R.id.edetail_sets_qty);
        TextView rest = (TextView) findViewById(R.id.edetail_rest_qty);
        TextView description = (TextView) findViewById(R.id.edetail_description);
        TextView primaryTxt = (TextView) findViewById(R.id.edetail_primary_txt);
        TextView secondaryTxt = (TextView) findViewById(R.id.edetail_secondary_txt);
        ImageView primaryImg = (ImageView) findViewById(R.id.edetail_primary_img);
        ImageView secondaryImg = (ImageView) findViewById(R.id.edetail_secondary_img);


        // set fields with information from Exercise object passed in through Bundle

        title.setText(exercise.getTitle());
        reps.setText(exercise.getReps() + "");
        sets.setText(exercise.getSets() + "");
        rest.setText(exercise.getRest() + "s");
        description.setText(exercise.getDescription());
        primaryTxt.setText(exercise.getMuscleGroupPrimary());
        secondaryTxt.setText(exercise.getMuscleGroupSecondary());


    }
}
