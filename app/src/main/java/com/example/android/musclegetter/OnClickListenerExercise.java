package com.example.android.musclegetter;

import android.content.DialogInterface;

/**
 * Created by zoro on 7/26/17.
 */

public class OnClickListenerExercise implements DialogInterface.OnClickListener {

    protected Exercise exercise;

    // allow constructor with exercise passed in so values can be changed within OnClickListener
    public OnClickListenerExercise(Exercise exercise) { this.exercise = exercise; }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {

    }
}
