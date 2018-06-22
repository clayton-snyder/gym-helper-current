package com.example.android.musclegetter.data;

import android.provider.BaseColumns;

/**
 * Created by Clayton on 4/5/18.
 * Contains string constants for various table column names.
 */

public final class GymContract
{
    // Category String constants
    public final static String CATEGORY_LEGS = "Legs";
    public final static String CATEGORY_ARMS = "Arms";
    public final static String CATEGORY_CHEST = "Chest";
    public final static String CATEGORY_SHOULDERS = "Shoulders";
    public final static String CATEGORY_BACK = "Back";
    public final static String CATEGORY_CARDIO = "Cardio";

    // Approximate time length (for routines) constants
    public final static String TIME_LONG = "Long";
    public final static String TIME_MEDIUM = "Medium";
    public final static String TIME_SHORT = "Short";

    public static abstract class ExEntry implements BaseColumns
    {
        public final static String TABLE_NAME = "exercise";

        // exercise table columns
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_EXERCISE_TITLE = "title";
        public final static String COLUMN_EXERCISE_DESC = "description";
        public final static String COLUMN_EXERCISE_CATEGORY = "category";
        public final static String COLUMN_EXERCISE_GROUP_PRIMARY = "group_primary";
        public final static String COLUMN_EXERCISE_GROUP_SECONDARY = "group_secondary";
        public final static String COLUMN_EXERCISE_REPS = "reps";
        public final static String COLUMN_EXERCISE_SETS = "sets";
        public final static String COLUMN_EXERCISE_REST = "rest";
    }

    public static abstract class RoutineEntry implements BaseColumns
    {
        public final static String TABLE_NAME = "routine";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_ROUTINE_TITLE = "title";
        public final static String COLUMN_ROUTINE_LENGTH = "length";
        public final static String COLUMN_ROUTINE_CATEGORY = "category";
        public final static String COLUMN_ROUTINE_DESCRIPTION = "description";
    }

    public static abstract class ExerciseInRoutineEntry implements BaseColumns
    {
        /* This table is used to store data about which exercises are in which routines. This
           prevents a hard limit on exercises in a routine and accommodates one exercise being in
           multiple routines.
           To delete an exercise from a routine, query for a row with "routine_id" and "exercise_id"
           both matching values from the context with the delete button (this information should be
           contained in the view representing the exercise in the routine edit activity)
         */
        public final static String TABLE_NAME = "exercise_in_routine";

        public final static String _ID = BaseColumns._ID;

        // _id column (in the routine table) of the corresponding routine this exercise is in
        public final static String COLUMN_ROUTINE_ID = "routine_id";

        // _id column (in the exercise table) of the exercise represented by a row
        public final static String COLUMN_EXERCISE_ID = "exercise_id";
    }
}
