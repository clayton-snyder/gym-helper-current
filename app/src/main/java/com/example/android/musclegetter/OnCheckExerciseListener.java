package com.example.android.musclegetter;

import android.view.View;

/**
 * Created by Clayton on 7/25/17.
 */

public class OnCheckExerciseListener implements View.OnClickListener {

    private Exercise exercise;

    public OnCheckExerciseListener(Exercise exercise) {
        this.exercise = exercise;
    }

    @Override
    public void onClick(View view) {
        exercise.setChecked(!(exercise.isChecked()));
    }
}
