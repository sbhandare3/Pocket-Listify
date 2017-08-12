package com.example.shreyas.pocketlistify;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.shreyas.pocketlistify.UtilityClasses.CustomAdapter;
import com.example.shreyas.pocketlistify.UtilityClasses.SQLiteDatabaseHelper;
import com.example.shreyas.pocketlistify.UtilityClasses.TaskItem;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    CustomAdapter adapter;
    public static ArrayList<TaskItem>taskItems;
    SQLiteDatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskItems = new ArrayList<>();
        helper = new SQLiteDatabaseHelper(this);
        listView = (ListView)findViewById(R.id.taskList);
        adapter = new CustomAdapter(this, taskItems);
        listView.setAdapter(adapter);
        notifyTasks();

        //if(!helper.isEmpty())
        loadData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.delete_task, menu);
        return true;
    }

    // open add task activity
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.delTask:
                deleteSelectedEntries();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 123 || requestCode==700){
            adapter.notifyDataSetChanged();
        }
    }

    public void addTaskPressed(View view){
        startActivityForResult(new Intent(MainActivity.this, AddTask.class),123);
    }

    //Delete function
    public void deleteSelectedEntries(){
        for(int i=0;i<taskItems.size();i++) {
            if (taskItems.get(i).getForDeletion()) {
                Integer deletedRows = helper.deleteData(Integer.toString(taskItems.get(i).getId()));
                if(deletedRows > 0)
                    Toast.makeText(this,"Record Deleted", Toast.LENGTH_SHORT).show();
                taskItems.remove(i);
            }
        }
        //After deleting, look at the db and reload the listview
        adapter.notifyDataSetChanged();
    }

    public void loadData(){
        Cursor result = helper.getEntries();
        while(result.moveToNext()){
            TaskItem listItem = new TaskItem();
            listItem.setId(Integer.parseInt(result.getString(0)));
            listItem.setName(result.getString(1));
            listItem.setDetails(result.getString(2));
            listItem.setDate(result.getString(3));
            listItem.setPriority(result.getString(4));
            taskItems.add(listItem);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void notifyTasks(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,4);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);

        Intent intent = new Intent(this,NotificationReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,100,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);
    }
}
