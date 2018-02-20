package com.example.android.musclegetter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import static android.R.attr.start;

/**
 * Created by Clayton on 7/14/17.
 */

public class CategoryListAdapter extends ArrayAdapter {
    private LayoutInflater inflater;
    private Bundle exerciseListBundle;
    private ArrayList<Exercise> exercises;

    public CategoryListAdapter(Context context, ArrayList<String> categories, Bundle b) {
        super(context, R.layout.list_view, categories);
        inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.exerciseListBundle = b;
        Log.i("INSTANTIATED ADAPTER: ", "CategoryListAdapter");
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.menu_list_item, parent, false);
            TextView tv = convertView.findViewById(R.id.menu_list_item_title);
            tv.setText((CharSequence) getItem(position));
            final String CATEGORY = (String) getItem(position);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(view.getContext(), ExerciseListActivity.class);
                    i.putExtra("category", CATEGORY);
                    i.putExtras(exerciseListBundle);
                    System.out.println("in intent: " + CATEGORY);
                    Log.e("tag", "categry: " + CATEGORY);
                    view.getContext().startActivity(i);
                }
            });
        } else {
            TextView tv = convertView.findViewById(R.id.menu_list_item_title);
            tv.setText((CharSequence) getItem(position));
            final String CATEGORY = (String) getItem(position);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(view.getContext(), ExerciseListActivity.class);
                    i.putExtra("category", CATEGORY);
                    i.putExtras(exerciseListBundle);
                    System.out.println("on intent: " + CATEGORY);
                    Log.e("tag", "category: " + CATEGORY);
                    view.getContext().startActivity(i);
                }
            });
        }

        return convertView;
    }
}
