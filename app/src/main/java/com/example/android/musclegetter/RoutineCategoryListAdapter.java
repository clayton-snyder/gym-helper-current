package com.example.android.musclegetter;

import android.app.ListActivity;
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
/**
 * Created by zoro on 7/18/17.
 */

public class RoutineCategoryListAdapter extends ArrayAdapter {
    private LayoutInflater inflater;
    private Bundle exerciseListBundle;

    public RoutineCategoryListAdapter(Context context, ArrayList<String> categories, Bundle b) {
        super(context, R.layout.list_view, categories);
        inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.exerciseListBundle = b;
        Log.i("INSTANTIATED ADAPTER: ", "RoutineCategoryListAdapter");
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
                    Intent i = new Intent(view.getContext(), RoutineListActivity.class);
                    i.putExtra("category", CATEGORY);
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
                    Intent i = new Intent(view.getContext(), RoutineListActivity.class);
                    i.putExtra("category", CATEGORY);
                    System.out.println("on intent: " + CATEGORY);
                    Log.e("tag", "category: " + CATEGORY);
                    view.getContext().startActivity(i);
                }
            });
        }

        return convertView;
    }
}
