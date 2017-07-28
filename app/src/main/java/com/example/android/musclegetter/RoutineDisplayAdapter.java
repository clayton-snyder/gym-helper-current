package com.example.android.musclegetter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static android.R.attr.id;
import static com.example.android.musclegetter.R.id.exercise_title;

/**
 * Created by zoro on 7/23/17.
 */

public class RoutineDisplayAdapter extends ArrayAdapter {

    private String m_Text = "";
    private LayoutInflater inflater;
    private ArrayList<Exercise> exercisesInRoutine;

    /* used to track if an exercise was "checked" in a routine (without this, recycled views will
       cause inaccuracies.) init to exercisesInRoutine.length() */ // doesn't exist
    private boolean[] exerciseCompleted;

    public RoutineDisplayAdapter(Context context, ArrayList<Exercise> exercisesInRoutine) {
        super(context, R.layout.list_view, exercisesInRoutine);
        this.exercisesInRoutine = new ArrayList<>(exercisesInRoutine);
        exerciseCompleted = new boolean[exercisesInRoutine.size()];
        inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final Exercise currExercise = (Exercise) getItem(position);


        // Commented code below was idiotic of me and insanely redundant
        /*
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.exercise_list_item, parent, false);
            TextView title = (TextView) convertView.findViewById(exercise_title);
            Button reps = (Button) convertView.findViewById(R.id.reps_qty);
            Button sets = (Button) convertView.findViewById(R.id.sets_qty);
            Button rest = (Button) convertView.findViewById(R.id.rest_qty);
            CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.checkbox);
            Button detailsButton = (Button) convertView.findViewById(R.id.details_button);
            // TODO: reps, sets, rest, name, checkbox, deets
            // TODO: instantiate the rest of the views above this line, set them below
            title.setText(currExercise.getTitle()); reps.setText(currExercise.getReps() + "");
            sets.setText(currExercise.getSets() + ""); rest.setText(currExercise.getRest() + "");
            checkBox.setChecked(currExercise.isChecked());
            checkBox.setOnClickListener(new OnCheckExerciseListener(currExercise));
            detailsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(view.getContext(), ExerciseDetailActivity.class);
                    getContext().startActivity(i);
                }
            });

        } else {
            convertView = inflater.inflate(R.layout.exercise_list_item, parent, false);
            TextView title = (TextView) convertView.findViewById(exercise_title);
            Button reps = (Button) convertView.findViewById(R.id.reps_qty);
            Button sets = (Button) convertView.findViewById(R.id.sets_qty);
            Button rest = (Button) convertView.findViewById(R.id.rest_qty);
            CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.checkbox);
            Button detailsButton = (Button) convertView.findViewById(R.id.details_button);
            // TODO: reps, sets, rest, name, checkbox, deets
            // TODO: instantiate the rest of the views above this line, set them below
            title.setText(currExercise.getTitle()); reps.setText(currExercise.getReps() + "");
            sets.setText(currExercise.getSets() + ""); rest.setText(currExercise.getRest() + "");
            checkBox.setChecked(currExercise.isChecked());
            checkBox.setOnClickListener(new OnCheckExerciseListener(currExercise));
            detailsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(view.getContext(), ExerciseDetailActivity.class);
                    getContext().startActivity(i);
                }
            });

        } */

        if (convertView == null)
            convertView = inflater.inflate(R.layout.exercise_list_item, parent, false);

        TextView title = (TextView) convertView.findViewById(exercise_title);
        Button reps = (Button) convertView.findViewById(R.id.reps_qty);
        Button sets = (Button) convertView.findViewById(R.id.sets_qty);
        Button rest = (Button) convertView.findViewById(R.id.rest_qty);
        CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.checkbox);
        Button detailsButton = (Button) convertView.findViewById(R.id.details_button);
        // TODO: reps, sets, rest, name, checkbox, deets
        // TODO: instantiate the rest of the views above this line, set them below
        title.setText(currExercise.getTitle());
        reps.setText(currExercise.getReps() + "");
        sets.setText(currExercise.getSets() + "");
        rest.setText(currExercise.getRest() + "s");
        checkBox.setChecked(currExercise.isChecked());
        checkBox.setOnClickListener(new OnCheckExerciseListener(currExercise));


        // COMMENTS ON THIS CODE ARE IN THE SECTION BELOW (SAME SHIT)
        reps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("How many reps?");

                final EditText input = new EditText(getContext());
                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                builder.setView(input);

                builder.setPositiveButton("OK", new OnClickListenerExercise(currExercise) {
                    int userInput = -1;

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        try {
                            userInput = Integer.parseInt(input.getText().toString());
                        } catch (NumberFormatException exception) {
                            Log.e("Exception", "NumberFormat", exception);
                        }


                        currExercise.setReps(userInput);
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                builder.show();

                Button button = (Button) view;
                button.setText(currExercise.getReps() + "");

            }


        });

        sets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Construct alert to hold editable text field
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("How many sets?");

                // Construct editable text object to allow user to enter text
                final EditText editText = new EditText(getContext());
                // force user to use int
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                // attach editable text to alert dialog
                builder.setView(editText);

                /* SET COMMIT/CANCEL BUTTONS -- logic of the entire AlertDialog is contained in
                   these ClickListeners. Custom OnClickListener was implemented to allow passing in
                   of an Exercise object as parameter -- corresponding field is updated in the
                   onClick function to allow for consistent updating */
                builder.setPositiveButton("OK", new OnClickListenerExercise(currExercise) {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int userInput = -1;
                        try {
                            userInput = Integer.parseInt(editText.getText().toString());
                        } catch (NumberFormatException e) {
                            Log.e("Exception", "NumberFormat", e);
                        }

                        currExercise.setSets(userInput);
                    }
                });

                builder.setNegativeButton("nvm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                builder.show();
            }
        });

        // COMMENTS ON THIS CODE ARE IN THE SECTION ABOVE (SAME SHIT)
        rest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                final EditText editText = new EditText(getContext());
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                builder.setView(editText);

                builder.setTitle("How many seconds of rest?");

                builder.setPositiveButton("OK", new OnClickListenerExercise(currExercise) {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int userInput = -1;
                        try {
                            userInput = Integer.parseInt(editText.getText().toString());
                        } catch (NumberFormatException e) {
                            Log.e("Exception", "NumberFormat", e);
                        }

                        currExercise.setRest(userInput);
                    }
                });

                builder.setNegativeButton("nvm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                builder.show();
            }
        });

        detailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), ExerciseDetailActivity.class);
                Bundle exerciseBundle = new Bundle();
                exerciseBundle.putSerializable("exercise", currExercise);
                i.putExtras(exerciseBundle);
                getContext().startActivity(i);
            }
        });


        return convertView;
    }
}
