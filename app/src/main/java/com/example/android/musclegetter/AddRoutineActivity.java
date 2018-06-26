package com.example.android.musclegetter;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.android.musclegetter.data.GymContract;
import com.example.android.musclegetter.data.GymContract.RoutineEntry;
import com.example.android.musclegetter.data.GymDbHelper;

import java.util.ArrayList;

public class AddRoutineActivity extends AppCompatActivity {
    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ FIELDS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    /* ########################################################################################## */

    // Use these to get the text entered by the user
    private EditText titleEditText;
    private EditText descriptionEditText;

    // Strings to hold the user's selection from spinner menu
    private String routineCategory;
    private String approxTime;

    // Use these to get the information from the spinner
    private Spinner categorySpinner;
    private Spinner approxTimeSpinner;

    // Use these to set the button behavior
    private Button saveButton;
    private Button cancelButton;

    // Use to create/open and return references to the database
    GymDbHelper gymDbHelper;

    // Use to hold readable or writeable references to the database.
    SQLiteDatabase db;

    /* ####################################### END FIELDS ####################################### */



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_routine);

        Log.i("ENTERED ACTIVITY: ", "AddRoutineActivity");

        gymDbHelper = new GymDbHelper(this);

        // Initialize all of the necessary object references
        titleEditText = (EditText) findViewById(R.id.edit_routine_title);
        descriptionEditText = (EditText) findViewById(R.id.edit_routine_description);
        saveButton = (Button) findViewById(R.id.button_save_new_routine);
        cancelButton = (Button) findViewById(R.id.button_cancel_new_routine);

        categorySpinner = (Spinner) findViewById(R.id.spinner_category);
        approxTimeSpinner = (Spinner) findViewById(R.id.spinner_approx_time);

        setupCategorySpinner();
        setupApproxTimeSpinner();


        // Set up the save button, adding click listeners to save the exercise properly
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertRoutine();
                // Start an activity to add exercises to a routine
                //  NOTE: DO ^THIS^ BY JUST ADDING ROWS TO THE LINK TABLE
                finish();
            }
        });
    }

    private void insertRoutine()
    {
        // Get a writeable reference to the routine table
        db = gymDbHelper.getWritableDatabase();
        String title = titleEditText.getText().toString().trim();
        String desc = descriptionEditText.getText().toString().trim();

        // Create and load a ContentValues in preparation for record insertion to routines table
        ContentValues cv = new ContentValues();
        cv.put(RoutineEntry.COLUMN_ROUTINE_TITLE, title);
        cv.put(RoutineEntry.COLUMN_ROUTINE_LENGTH, approxTime);
        cv.put(RoutineEntry.COLUMN_ROUTINE_CATEGORY, routineCategory);
        cv.put(RoutineEntry.COLUMN_ROUTINE_DESCRIPTION, desc);

        // Insert a record with info from the EditText references
        db.insert(GymContract.RoutineEntry.TABLE_NAME, null, cv);
    }

    private void setupCategorySpinner()
    {
        // Create adapter for spinner. The list options are from the String array it will use
        ArrayAdapter categorySpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_exercise_category_options, android.R.layout.simple_spinner_item);

        // Specify dropdown layout style - simple list view with 1 item per line
        categorySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter
        categorySpinner.setAdapter(categorySpinnerAdapter);

        // Define the behavior for when an item is selected
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equalsIgnoreCase("Legs")) {
                        routineCategory = GymContract.CATEGORY_LEGS;
                    } else if (selection.equalsIgnoreCase("Arms")) {
                        routineCategory = GymContract.CATEGORY_ARMS;
                    } else if (selection.equalsIgnoreCase("Chest")) {
                        routineCategory = GymContract.CATEGORY_CHEST;
                    } else if (selection.equalsIgnoreCase("Shoulders")) {
                        routineCategory = GymContract.CATEGORY_SHOULDERS;
                    } else if (selection.equalsIgnoreCase("Back")) {
                        routineCategory = GymContract.CATEGORY_BACK;
                    } else if (selection.equalsIgnoreCase("Cardio")) {
                        routineCategory = GymContract.CATEGORY_CARDIO;
                    } else {
                        routineCategory = "Not set";
                    }
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                routineCategory = "Not set";
            }
        });
    }

    private void setupApproxTimeSpinner()
    {
        // Create adapter for spinner. The list options are from the String array it will use
        final ArrayAdapter approxTimeAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_routine_approx_time_options, android.R.layout.simple_spinner_item);

        // Specify dropdown layout style - simple list view with 1 item per line
        approxTimeAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter
        approxTimeSpinner.setAdapter(approxTimeAdapter);

        // Define the behavior for when an item is selected
        approxTimeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals("Long")) {
                        approxTime = GymContract.TIME_LONG;
                    } else if (selection.equals("Medium")) {
                        approxTime = GymContract.TIME_MEDIUM;
                    } else if (selection.equals("Short")) {
                        approxTime = GymContract.TIME_SHORT;
                    }
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                approxTime = "Not set";
            }
        });
    }
}
