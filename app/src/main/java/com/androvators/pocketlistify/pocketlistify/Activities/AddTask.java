package com.androvators.pocketlistify.pocketlistify.Activities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.androvators.pocketlistify.pocketlistify.R;
import com.androvators.pocketlistify.pocketlistify.UtilityClasses.SQLiteDatabaseHelper;
import com.androvators.pocketlistify.pocketlistify.UtilityClasses.TaskItem;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddTask extends Activity {

    EditText task_name;
    EditText task_details;
    EditText task_date;
    Spinner task_priority_spinner;

    String t_name_str;
    String t_detail_str;
    String t_date_str;
    String t_priority_str;

    TaskItem taskItem;
    final Calendar c=Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        taskItem = new TaskItem();

        t_detail_str = "N/A";
        t_date_str = "N/A";
        t_priority_str = getResources().getStringArray(R.array.priority_array)[3];

        // initializing views with id
        task_name = (EditText) findViewById(R.id.edit_name);
        task_details = (EditText) findViewById(R.id.edit_details);
        task_date = (EditText) findViewById(R.id.edit_date);

        // open date picker dialog
        task_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddTask.this, date, c.get( Calendar.YEAR ), c.get( Calendar.MONTH ), c.get( Calendar.DAY_OF_MONTH ) ).show();
            }
        });

        // spinner setup and default value
        task_priority_spinner = (Spinner) findViewById(R.id.edit_priority);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.priority_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        task_priority_spinner.setAdapter(adapter);
        task_priority_spinner.setSelection(3);
        task_priority_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                // Your code here
                if(position!=3){
                    t_priority_str = getResources().getStringArray(R.array.priority_array)[position];
                    //Toast.makeText(AddTask.this, "Selected: " + t_priority_str, Toast.LENGTH_LONG).show();
                }
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                t_priority_str = getResources().getStringArray(R.array.priority_array)[3];
            }
        });
    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth ) {
            c.set( Calendar.YEAR, year );
            c.set( Calendar.MONTH, monthOfYear );
            c.set( Calendar.DAY_OF_MONTH, dayOfMonth );
            String dateFormat = "MM/dd/yy";
            SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.US);
            task_date.setText(sdf.format(c.getTime()));
            t_date_str = sdf.format(c.getTime());
        }
    };

    public void saveTaskPressed(View v){
        if(task_name.getText().toString().trim().length() == 0)
            Toast.makeText(this,"Task name is necessary!",Toast.LENGTH_SHORT).show();
        else{
            t_name_str = task_name.getText().toString();
            //if(!t_detail_str.equals("N/A"))
            t_detail_str = task_details.getText().toString();

            taskItem.setName(t_name_str);
            taskItem.setDetails(t_detail_str);
            taskItem.setDate(t_date_str);
            taskItem.setPriority(t_priority_str);

            MainActivity.taskItems.add(taskItem); // add new task to list
            saveNewTask(); // save new task to db

            Toast.makeText(this,"Task Added!",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            setResult(Activity.RESULT_CANCELED, intent);
            finish();
        }
    }

    public void saveNewTask(){
        SQLiteDatabaseHelper myDB = new SQLiteDatabaseHelper(this);
        myDB.insertEntry(t_name_str,t_detail_str,t_date_str,t_priority_str);
    }
}
