package com.example.android.musclegetter.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.android.musclegetter.data.GymContract.ExEntry;
import com.example.android.musclegetter.data.GymContract.RoutineEntry;
import com.example.android.musclegetter.data.GymContract.ExerciseInRoutineEntry;

/**
 * Created by Clayton on 4/5/18.
 * Contains string constants which create tables used by the app to store exercise/routine data.
 */

public class GymDbHelper extends SQLiteOpenHelper
{
    public GymDbHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static final String SQL_CREATE_EXERCISE_TABLE =
            "CREATE TABLE exercise(" +
                    ExEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    ExEntry.COLUMN_EXERCISE_TITLE + " TEXT NOT NULL, " +
                    ExEntry.COLUMN_EXERCISE_DESC + " TEXT, " +
                    ExEntry.COLUMN_EXERCISE_CATEGORY + " TEXT NOT NULL, " +
                    ExEntry.COLUMN_EXERCISE_GROUP_PRIMARY + " TEXT NOT NULL, " +
                    ExEntry.COLUMN_EXERCISE_GROUP_SECONDARY + " TEXT NOT NULL, " +
                    ExEntry.COLUMN_EXERCISE_REPS + " INTEGER NOT NULL DEFAULT -1, " +
                    ExEntry.COLUMN_EXERCISE_SETS + " INTEGER NOT NULL DEFAULT -1, " +
                    ExEntry.COLUMN_EXERCISE_REST + " INTEGER NOT NULL DEFAULT -1);";

    public static final String SQL_CREATE_ROUTINE_TABLE =
            "CREATE TABLE routine(" +
                    RoutineEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    RoutineEntry.COLUMN_ROUTINE_TITLE + " TEXT NOT NULL, " +
                    RoutineEntry.COLUMN_ROUTINE_LENGTH + " TEXT, " +
                    RoutineEntry.COLUMN_ROUTINE_CATEGORY + " TEXT NOT NULL, " +
                    RoutineEntry.COLUMN_ROUTINE_DESCRIPTION + " TEXT);";

    public static final String SQL_CREATE_EXERCISES_IN_ROUTINES_TABLE =
            "CREATE TABLE exercise_in_routine(" +
                    ExerciseInRoutineEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    ExerciseInRoutineEntry.COLUMN_EXERCISE_ID + "INTEGER NOT NULL, " +
                    ExerciseInRoutineEntry.COLUMN_ROUTINE_ID + " INTEGER NOT NULL;)";

    public static final String DATABASE_NAME = "gym.db";
    public static int DATABASE_VERSION = 1;

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(SQL_CREATE_EXERCISE_TABLE);
        db.execSQL(SQL_CREATE_ROUTINE_TABLE);
        db.execSQL(SQL_CREATE_EXERCISES_IN_ROUTINES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVerson)
    {
        return;
    }
}