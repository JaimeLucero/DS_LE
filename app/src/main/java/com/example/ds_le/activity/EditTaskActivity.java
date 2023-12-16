package com.example.ds_le.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.ds_le.R;
import com.example.ds_le.objects.EntriesHash;
import com.example.ds_le.objects.Task;
import com.google.android.material.snackbar.Snackbar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class EditTaskActivity extends AppCompatActivity {
    private Button datePickerButton;
    private EditText inTitle;
    private EditText inDescription;
    private String title;
    private String description;
    private String selectedDateTime;
    private String category;
    private boolean isPriority;
    private boolean isDone;
    public static EntriesHash hash;

    String editKey;
    Task editTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);
        hash = EntriesHash.getInstance(this);

        editKey = getIntent().getSerializableExtra("taskObject").toString();
        editTask = hash.getEntry(editKey);
        datePickerButton = findViewById(R.id.edit_date);
        // Get the current date and time
        Calendar calendar = Calendar.getInstance();

        // Create a SimpleDateFormat object with the desired format
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm", Locale.getDefault());

        // Format the current date and time
        String formattedDateTime = dateFormat.format(calendar.getTime());


        inTitle = findViewById(R.id.edit_title);
        inDescription = findViewById(R.id.description_edit);

        datePickerButton.setText(editTask.getDeadline());
        inTitle.setText(editTask.getTitle());
        inDescription.setText(editTask.getDescription());

        // Set up click listener for the button
        datePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimePickerDialog();
            }
        });

        Button edit_task = findViewById(R.id.confirm_edit);
        edit_task.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                title = inTitle.getText().toString();

                description = inDescription.getText().toString();
                if(selectedDateTime == null){
                    selectedDateTime = editTask.getDeadline();
                }
                if (title.isEmpty() || description.isEmpty() || selectedDateTime == null || selectedDateTime.isEmpty()) {
                    // Show a Toast or Snackbar indicating that all fields are required
                    Toast.makeText(EditTaskActivity.this, "Title, description, and date are required", Toast.LENGTH_SHORT).show();
                    return; // Don't proceed further if any field is empty
                }

                updateHash();
                remainingTime();

                // Assuming you want to go back to the HomeActivity
                Intent intent = new Intent(EditTaskActivity.this, HomeActivity.class);
                startActivity(intent);

                // Optionally, you can finish the current activity if you don't want to come back to it
                finish();
            }
        });




        Spinner spinner = findViewById(R.id.edit_spinner);

        // Create a list of items you want to add to the Spinner
        List<String> itemList = new ArrayList<>();
        itemList.add("Work");
        itemList.add("School");
        itemList.add("Personal");

        // Create an ArrayAdapter using the list of items and a default layout
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, itemList);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        //get the selected spinner item
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Retrieve the selected item
                category = (String) parentView.getItemAtPosition(position);

            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });

        //priority checbox
        CheckBox priority = findViewById(R.id.priority_edit);
        priority.setChecked(editTask.isPriority());
        priority.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Do something based on the CheckBox status
                isPriority = isChecked;
            }
        });
    }

    private void showDateTimePickerDialog() {
        // Get the current date and time
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        // Create a combined date and time picker dialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // Handle the selected date, then show the time picker dialog
                        final int selectedYear = year;
                        final int selectedMonth = month;
                        final int selectedDayOfMonth = dayOfMonth;

                        TimePickerDialog timePickerDialog = new TimePickerDialog(
                                EditTaskActivity.this,
                                new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                        // Handle the selected time (e.g., update a TextView)
                                        selectedDateTime = String.format(Locale.getDefault(), "%d/%02d/%d %02d:%02d", (selectedMonth + 1), selectedDayOfMonth, selectedYear, hourOfDay, minute);
                                        datePickerButton.setText(selectedDateTime);

                                    }
                                },
                                hour,
                                minute,
                                true // set to true if you want 24-hour format
                        );

                        // Show the time picker dialog
                        timePickerDialog.show();
                    }
                },
                year,
                month,
                day
        );

        // Show the date picker dialog
        datePickerDialog.show();
    }

    private void remainingTime(){
        try {
            final Calendar calendar = Calendar.getInstance();
            final int currentYear = calendar.get(Calendar.YEAR);
            final int currentMonth = calendar.get(Calendar.MONTH);
            final int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
            final int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
            final int currentMinute = calendar.get(Calendar.MINUTE);

            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm", Locale.getDefault());
            Date currentDate = dateFormat.parse(currentMonth + 1 + "/" + currentDay + "/" + currentYear + " " + currentHour + ":" + currentMinute);
            Date selectedDate = dateFormat.parse(selectedDateTime);

            long timeDifference = 0;
            if (currentDate != null) {
                timeDifference = Objects.requireNonNull(selectedDate).getTime() - currentDate.getTime();
            }

            long remainingHours = timeDifference / (60 * 60 * 1000);
            long remainingMinutes = (timeDifference / (60 * 1000)) % 60;

            // Display the remaining time in a Snackbar
            String remainingTimeMessage = "Remaining Time: " + remainingHours + " hours and " + remainingMinutes + " minutes";
            showSnackbar(remainingTimeMessage);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void showSnackbar(String message) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG).show();
    }

    private void updateHash(){
        Task task = new Task(EntriesHash.generateUniqueUUID(),title,description,selectedDateTime,category,isPriority,isDone);
        //hash.addEntry(EntriesHash.generateHashKey(task),task);
        hash.updateEntry(editKey,task);
        EntriesHash.saveHashMap(this, EntriesHash.getEntries());
    }
}