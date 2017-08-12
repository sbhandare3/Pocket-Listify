package com.example.shreyas.pocketlistify.UtilityClasses;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shreyas.pocketlistify.EditTask;
import com.example.shreyas.pocketlistify.R;
import com.example.shreyas.pocketlistify.ViewTask;

import java.util.ArrayList;

/**
 * Created by shreyas on 8/9/2017.
 */

public class CustomAdapter extends ArrayAdapter<TaskItem> {
    //int index;
    CheckBox checkBox;
    TextView textName;
    ImageView editTask;
    Context context;
    int REQUEST_CODE_FOR_EDIT = 700;
    int REQUEST_CODE_FOR_VIEW = 701;

    public CustomAdapter(Context context, ArrayList<TaskItem> resource) {
        super(context, R.layout.task_list_adapter, resource);
        this.context = context;
    }



    @Override
    public View getView(final int position, View row, final ViewGroup parent) {

        LayoutInflater minflater = LayoutInflater.from(getContext());
        row = minflater.inflate(R.layout.task_list_adapter, parent, false);
        checkBox=(CheckBox)row.findViewById(R.id.checkBox);

        String name=getItem(position).getName();
        textName=(TextView)row.findViewById(R.id.txt_name);
        textName.setText(name);

        editTask = (ImageView)row.findViewById(R.id.button_edit_task);
        editTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editIntent = new Intent(context, EditTask.class);
                editIntent.putExtra("position", position);
                ((Activity) context).startActivityForResult(editIntent,REQUEST_CODE_FOR_EDIT);
            }
        });

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox cb=(CheckBox)v.findViewById(R.id.checkBox);
                if(cb.isChecked()) {
                    getItem(position).setForDeletion();
                }
                else if(!checkBox.isChecked()){
                    getItem(position).resetToRetain();
                }
            }
        });

        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewIntent = new Intent(context, ViewTask.class);
                viewIntent.putExtra("position", position);
                ((Activity) context).startActivityForResult(viewIntent,REQUEST_CODE_FOR_VIEW);
            }
        });

        return row;
    }
}
