package com.example.android.musclegetter;

import java.io.Serializable;

/**
 * Created by Clayton on 7/12/17.
 */

public class Exercise implements Serializable {
    protected String title;
    protected int reps = -1;
    protected int sets = -1;
    protected int rest = -1;
    protected String category = "NIL"; // legs, arms, shoulders, chest, back, cardio
    protected String muscleGroupPrimary = "NIL";
    protected String muscleGroupSecondary = "NIL";
    protected String description = "No description set";

    private boolean checked = false; // used for completion tracking in Routine

    public Exercise(String title, int reps, int sets, int rest, String category,
                    String muscleGroupPrimary, String muscleGroupSecondary) {
        this.title = title;
        this.reps = reps;
        this.sets = sets;
        this.rest = rest;
        this.category = category;
        this.muscleGroupPrimary = muscleGroupPrimary;
        this.muscleGroupSecondary = muscleGroupSecondary;
    }

    public String getTitle() {
        return this.title;
    }

    public int getReps() {
        return this.reps;
    }

    public int getSets() { return this.sets; }

    public int getRest() {
        return this.rest;
    }

    public String getCategory() { return this.category; }

    public String getMuscleGroupPrimary() {
        return this.muscleGroupPrimary;
    }

    public String getMuscleGroupSecondary() {
        return this.muscleGroupSecondary;
    }

    public String getDescription() {
        return this.description;
    }

    public void setReps(int reps) { this.reps = reps; }

    public void setSets(int sets) { this.sets = sets; }

    public void setRest(int rest) { this.rest = rest; }

    public void setCategory(String category) { this.category = category; }

    public void setMuscleGroupPrimary(String group) { this.muscleGroupPrimary = group; }

    public void setMuscleGroupSecondary(String secondary) { this.muscleGroupSecondary = secondary; }

    public boolean isChecked() { return checked; } // used for completion tracking in Routine

    public void setChecked(boolean arg) { this.checked = arg; }

    public void setDescription(String description) {
        this.description = description;
    }
}
