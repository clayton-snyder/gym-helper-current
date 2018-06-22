package com.example.android.musclegetter;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.android.musclegetter.data.GymContract;
import com.example.android.musclegetter.data.GymContract.ExEntry;
import com.example.android.musclegetter.data.GymDbHelper;

/**
 * Created by Clayton on 4/5/18.
 */

public class AddExerciseActivity extends AppCompatActivity
{
    private EditText titleEditText;
    private EditText descriptionEditText;
    private EditText primaryGroupEditText;
    private EditText secondaryGroupEditText;
    private EditText repsEditText;
    private EditText setsEditText;
    private EditText restEditText;

    private Spinner categorySpinner;
    private String exerciseCategory;

    // Used to create/open and return references to the SQLite database
    GymDbHelper dbHelper;

    // Used to hold readable or writeable references to the database
    SQLiteDatabase db;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise);

        dbHelper = new GymDbHelper(this);

        // Find all relevant EditText fields that we will need to read user input from
        titleEditText = (EditText) findViewById(R.id.edit_exercise_title);
        descriptionEditText = (EditText) findViewById(R.id.edit_exercise_description);
        primaryGroupEditText = (EditText) findViewById(R.id.edit_group_primary);
        secondaryGroupEditText = (EditText) findViewById(R.id.edit_group_secondary);
        repsEditText = (EditText) findViewById(R.id.edit_reps);
        setsEditText = (EditText) findViewById(R.id.edit_sets);
        restEditText = (EditText) findViewById(R.id.edit_rest);


        // Set up the save button, adding click listeners to save the exercise properly
        Button saveButton = (Button) findViewById(R.id.button_save_new_exercise);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertExercise();
                finish();
            }
        });

        categorySpinner = (Spinner) findViewById(R.id.spinner_category);

        setupSpinner();
    }

    private void setupSpinner() {
        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout
        ArrayAdapter categorySpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_exercise_category_options, android.R.layout.simple_spinner_item);

        // Specify dropdown layout style - simple list view with 1 item per line
        categorySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter to the spinner
        categorySpinner.setAdapter(categorySpinnerAdapter);

        // Set the integer mSelected to the constant values
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals("Legs")) {
                        exerciseCategory = GymContract.CATEGORY_LEGS;
                    } else if (selection.equals("Arms")) {
                        exerciseCategory = GymContract.CATEGORY_ARMS;
                    } else if (selection.equals("Chest")) {
                        exerciseCategory = GymContract.CATEGORY_CHEST;
                    } else if (selection.equals("Shoulders")) {
                        exerciseCategory = GymContract.CATEGORY_SHOULDERS;
                    } else if (selection.equals("Back")) {
                        exerciseCategory = GymContract.CATEGORY_BACK;
                    } else if (selection.equals("Cardio")) {
                        exerciseCategory = GymContract.CATEGORY_CARDIO;
                    } else {
                        exerciseCategory = "Not set";
                    }
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                exerciseCategory = "Not set";
            }
        });

    }

    public Boolean insertExercise()
    {
        String title = titleEditText.getText().toString().trim();
        String description = descriptionEditText.getText().toString().trim();
        String group_primary = primaryGroupEditText.getText().toString().trim();
        String group_secondary = secondaryGroupEditText.getText().toString().trim();
        int reps = Integer.parseInt(repsEditText.getText().toString());
        int sets = Integer.parseInt(setsEditText.getText().toString());
        String rest = restEditText.getText().toString().trim();

        // Content values for inserting into the gym database
        ContentValues cv = new ContentValues();
        cv.put(ExEntry.COLUMN_EXERCISE_TITLE, title);
        cv.put(ExEntry.COLUMN_EXERCISE_DESC, description);
        cv.put(ExEntry.COLUMN_EXERCISE_CATEGORY, exerciseCategory);
        cv.put(ExEntry.COLUMN_EXERCISE_GROUP_PRIMARY, group_primary);
        cv.put(ExEntry.COLUMN_EXERCISE_GROUP_SECONDARY, group_secondary);
        cv.put(ExEntry.COLUMN_EXERCISE_REPS, reps);
        cv.put(ExEntry.COLUMN_EXERCISE_SETS, sets);
        cv.put(ExEntry.COLUMN_EXERCISE_REST, rest);

        db = dbHelper.getWritableDatabase();

        long newRow = db.insert("exercise", null, cv);

        Toast toast;
        String toastTxt;
        if (newRow == -1)
        {
            toastTxt = "Failed to add exercise.";
            toast = Toast.makeText(this, toastTxt, Toast.LENGTH_LONG);
            toast.show();
            return false;
        }
        toastTxt = "Successfully added exercise " + title + " (" + reps + " for " + sets + " with "
                + rest + "sec rest) in category " + exerciseCategory;
        toast = Toast.makeText(this, toastTxt, Toast.LENGTH_LONG);
        toast.show();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        return true;

    }


}
