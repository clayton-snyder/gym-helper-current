package com.example.android.musclegetter;

import java.io.Serializable;

public class Exercise implements Serializable {
    protected int _id; // Matches the SQLite DB primary key
    protected String title;
    protected String description = "No description set";
    protected String category = "NIL"; // legs, arms, shoulders, chest, back, cardio
    protected String muscleGroupPrimary = "NIL";
    protected String muscleGroupSecondary = "NIL";
    protected int reps = -1;
    protected int sets = -1;
    protected int rest = -1;

    private boolean checked = false; // used for completion tracking in Routine

    public Exercise(String title, String description, String category, String muscleGroupPrimary,
                    String muscleGroupSecondary, int reps, int sets, int rest)
    {
        this.title = title;
        this.description = description;
        this.category = category;
        this.muscleGroupPrimary = muscleGroupPrimary;
        this.muscleGroupSecondary = muscleGroupSecondary;
        this.reps = reps;
        this.sets = sets;
        this.rest = rest;
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

    public int getDbId() { return this._id; }

    public void setDbId(int idIn) { this._id = idIn; }

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
