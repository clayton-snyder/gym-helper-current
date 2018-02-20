package com.example.android.musclegetter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by Clayton on 7/20/17.
 */

public class RoutineListAdapter extends ArrayAdapter {
    private LayoutInflater inflater;
    private String category;

    RoutineListAdapter(Context context, ArrayList<Routine> routines) {
        super(context, R.layout.list_view, routines);
        inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public View getView(int position, @Nullable View contentView, @NonNull ViewGroup parent) {

        if (contentView == null) {
            contentView = inflater.inflate(R.layout.menu_list_item, parent, false);
            TextView tv = contentView.findViewById(R.id.menu_list_item_title);
            tv.setText(((Routine) getItem(position)).getTitle());
            tv.setTextSize(30);
            final int POSITION_FOR_LISTENER = position;
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(getContext(), RoutineDisplayActivity.class);
                    i.putExtra("routine", (Serializable) getItem(POSITION_FOR_LISTENER));
                    getContext().startActivity(i);
                }
            });
        } else {
            TextView tv = contentView.findViewById(R.id.menu_list_item_title);
            tv.setText(((Routine) getItem(position)).getTitle());
            tv.setTextSize(30);
            final int POSITION_FOR_LISTENER = position;
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(getContext(), RoutineDisplayActivity.class);
                    i.putExtra("routine", (Serializable) getItem(POSITION_FOR_LISTENER));
                    getContext().startActivity(i);
                }
            });
        }

        return contentView;
    }

}
