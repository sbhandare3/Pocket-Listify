package com.example.shreyas.pocketlistify;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.shreyas.pocketlistify.R;
import com.example.shreyas.pocketlistify.UtilityClasses.CustomAdapter;
import com.example.shreyas.pocketlistify.UtilityClasses.SQLiteDatabaseHelper;
import com.example.shreyas.pocketlistify.UtilityClasses.TaskItem;

import java.util.ArrayList;

public class NotificationActivity extends AppCompatActivity {

    Button dButton;
    ListView listView;
    CustomAdapter adapter;
    ArrayList<TaskItem> todaysTasks;
    SQLiteDatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        dButton = (Button) findViewById(R.id.button_done);
        todaysTasks = new ArrayList<>();
        helper = new SQLiteDatabaseHelper(this);
        listView = (ListView)findViewById(R.id.list_today);
        adapter = new CustomAdapter(this, todaysTasks);
        listView.setAdapter(adapter);

        loadTodaysData();

    }

    public void goToHome(View view){
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }

    public void loadTodaysData(){
        Cursor result = helper.getTodaysEntries();
        while(result.moveToNext()){
            TaskItem listItem = new TaskItem();
            listItem.setId(Integer.parseInt(result.getString(0)));
            listItem.setName(result.getString(1));
            listItem.setDetails(result.getString(2));
            listItem.setDate(result.getString(3));
            listItem.setPriority(result.getString(4));
            todaysTasks.add(listItem);
            adapter.notifyDataSetChanged();
        }
    }
}
