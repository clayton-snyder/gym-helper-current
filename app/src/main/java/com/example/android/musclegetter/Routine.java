package com.example.android.musclegetter;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by zoro on 7/12/17.
 */

public class Routine implements Serializable {
    ArrayList<Exercise> exercises;
    String title = "EMPTY";
    String approxTime; // Long, Medium, Short
    String groupWorked = "unset"; // category for now (Arms, Legs, Shoulders, Chest, Back, Cardio)
    String description;

    public Routine(String title, ArrayList<Exercise> listOfExercises, String approxTime,
                   String groupWorked, String description) {
        this.title = title;
        this.exercises = new ArrayList<>(listOfExercises);
        this.approxTime = approxTime;
        this.groupWorked = groupWorked;
        this.description = description;
    }

    public Routine() {
        this.exercises = new ArrayList<>();
        this.description = "no description";
    }

    public String getTitle() { return this.title; }

    public ArrayList<Exercise> getExercises() {
        return this.exercises;
    }

    public String getApproxTime() {
        return this.approxTime;
    }

    public String getGroupWorked() {
        return this.groupWorked;
    }

    public String getDescription() {
        return this.description;
    }

    public void addExercise(Exercise e) {
        this.exercises.add(e);
    }

    public void setTitle(String title) { this.title = title; }

    public void setApproxTime(String approxTime) {
        this.approxTime = approxTime;
    }

    public void setGroupWorked(String groupWorked) {
        this.groupWorked = groupWorked;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
