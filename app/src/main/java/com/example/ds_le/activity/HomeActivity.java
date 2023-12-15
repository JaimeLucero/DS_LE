package com.example.ds_le.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ds_le.R;
import com.example.ds_le.objects.EntriesHash;
import com.example.ds_le.recycler_view.CustomAdapter;
import com.example.ds_le.recycler_view.ItemTouchHelperCallback;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    public static EntriesHash hash;
    private String filter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        hash = EntriesHash.getInstance(this);
        Button addTask = findViewById(R.id.add_task);

        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle button click here, navigate to the desired activity
                Intent intent = new Intent(HomeActivity.this, NewTaskActivity.class);
                startActivity(intent);
            }
        });


        RecyclerView recyclerView = findViewById(R.id.home_recycler);
        CustomAdapter customAdapter = new CustomAdapter(HomeActivity.this,hash);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(customAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelperCallback(customAdapter));
        customAdapter.setItemTouchHelper(itemTouchHelper);
        itemTouchHelper.attachToRecyclerView(recyclerView);



        Spinner spinner = findViewById(R.id.task_spinner);

        // Create a list of items you want to add to the Spinner
        List<String> itemList = new ArrayList<>();
        itemList.add("All");
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
                filter = (String) parentView.getItemAtPosition(position);
                Toast.makeText(HomeActivity.this, filter+" selected.", Toast.LENGTH_SHORT).show();
                // Call the filter method in the adapter
                customAdapter.filter(filter);
                recyclerView.setAdapter(customAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });
    }


}