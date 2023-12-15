package com.example.ds_le.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.ds_le.R;
import com.example.ds_le.objects.EntriesHash;
import com.example.ds_le.recycler_view.CustomAdapter;

public class HomeActivity extends AppCompatActivity {
    public static EntriesHash hash;
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
        CustomAdapter customAdapter = new CustomAdapter(this,hash);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(customAdapter);
    }


}