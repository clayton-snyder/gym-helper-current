package com.example.android.musclegetter;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import com.example.android.musclegetter.Exercise;
import com.example.android.musclegetter.data.GymContract;
import com.example.android.musclegetter.data.GymContract.ExEntry;
import com.example.android.musclegetter.data.GymDbHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Clayton on 7/13/17.
 */

public class ExerciseListItemAdapter extends ArrayAdapter<Exercise> {
    private LayoutInflater inflater;
    protected ArrayList<Exercise> exercises;
    protected int layout;

    GymDbHelper dbHelper = new GymDbHelper(getContext());
    SQLiteDatabase db = dbHelper.getWritableDatabase();

    /* "layout" parameter takes a layout for how to display the list item -- for viewing individual
                   exercises (from CategoryListActivity), use menu_list_item; for viewing exercises
                   as part of a routine, use exercise_list_item */
    public ExerciseListItemAdapter(Context context, List<Exercise> exercises, int layout) {
        super(context, R.layout.list_view, exercises);
        this.exercises = new ArrayList<>(exercises);
        this.layout = layout;
        Log.i("INSTANTIATED ADAPTER: ", "ExerciseListItemAdapter");
        inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = inflater.inflate(layout, parent, false);

            // figure out which id to call based on which layout is being used
            if (layout == R.layout.exercise_list_item) {
                // THIS IS ALL WRONG YOU'LL NEED TO CHANGE THIS WHEN THIS BLOCK MATTERS UGH
                TextView tv = convertView.findViewById(R.id.exercise_title);
                tv.setText(exercises.get(position).getTitle());
                tv.setTextSize(30);
                tv.setGravity(Gravity.CENTER_VERTICAL);
            } else {
                TextView tv = convertView.findViewById(R.id.menu_list_item_title);
                tv.setText(exercises.get(position).getTitle());
                tv.setTextSize(30);
                tv.setGravity(Gravity.CENTER_VERTICAL);
            }

        } else {
            if (layout == R.layout.exercise_list_item) {
                TextView tv = convertView.findViewById(R.id.exercise_title);
                tv.setText(getItem(position).getTitle());
                tv.setTextSize(30);
                tv.setGravity(Gravity.CENTER_VERTICAL);
            } else {
                TextView tv = convertView.findViewById(R.id.menu_list_item_title);
                tv.setText(getItem(position).getTitle());
                tv.setTextSize(30);
                tv.setGravity(Gravity.CENTER_VERTICAL);
            }

            /*
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(view.getContext(), ExerciseDetailActivity.class);
                    view.getContext().startActivity(i);
                }
            }); */
        }



        final int FINAL_POSITION = position;

        Button button = convertView.findViewById(R.id.delete_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int idToDelete = exercises.get(FINAL_POSITION)._id;
                db.execSQL("DELETE FROM " + ExEntry.TABLE_NAME + " WHERE " + ExEntry._ID +
                    " = " + idToDelete + ";");
                Intent i = new Intent(getContext(), MainActivity.class);
                getContext().startActivity(i);
            }
        });

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), ExerciseDetailActivity.class);
                Bundle exerciseBundle = new Bundle();
                exerciseBundle.putSerializable("exercise", getItem(FINAL_POSITION));
                i.putExtras(exerciseBundle);
                view.getContext().startActivity(i);
            }
        });


        return convertView;
    }
}
