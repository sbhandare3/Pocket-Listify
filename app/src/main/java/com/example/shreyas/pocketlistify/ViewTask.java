package com.example.shreyas.pocketlistify;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import static com.example.shreyas.pocketlistify.MainActivity.taskItems;

public class ViewTask extends Activity {

    TextView vDetails, vDate, vPriority;
    String name, detail, date, priority;
    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_task);

        vDetails = (TextView) findViewById(R.id.disp_task_detail);
        vDate = (TextView) findViewById(R.id.disp_task_date);
        vPriority = (TextView) findViewById(R.id.disp_task_priority);

        Intent intent = getIntent();
        if(intent.hasExtra("position"))
        {
            pos = intent.getIntExtra("position",0);
            name = taskItems.get(pos).getName();
            if(taskItems.get(pos).getDetails().equals(""))
                detail = "-";
            else
                detail = taskItems.get(pos).getDetails();
            date = taskItems.get(pos).getDate();
            priority = taskItems.get(pos).getPriority();
        }
        else {
            // default values for date, priority and task detail.
            name = "failed to fetch data";
            detail = "failed to fetch data";
            date = "failed to fetch data";
            priority = "failed to fetch data";
        }

        switch (priority){
            case "High":
                vPriority.setTextColor(ContextCompat.getColor(this, R.color.colorRed));
                break;
            case "Med":
                vPriority.setTextColor(ContextCompat.getColor(this, R.color.colorYellow));
                break;
            case "Low":
                vPriority.setTextColor(ContextCompat.getColor(this, R.color.colorGreen));
                break;
            case "N/A (default)":
                vPriority.setTextColor(ContextCompat.getColor(this, R.color.colorBlack));
                break;
            default:
                break;
        }

        // set textviews with data
        setTitle(name);
        vDetails.setText(detail);
        vDate.setText(date);
        vPriority.setText(priority);
    }
}
