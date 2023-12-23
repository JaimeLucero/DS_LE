package com.example.ds_le.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ds_le.R;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ImageButton back = findViewById(R.id.back_menu);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TextView archive = findViewById(R.id.menu_archives);
        archive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle button click here, navigate to the desired activity
                Intent intent = new Intent(MenuActivity.this, ArchivesActivity.class);
                startActivity(intent);
            }
        });

        TextView tasks = findViewById(R.id.menu_task);

        tasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle button click here, navigate to the desired activity
                Intent intent = new Intent(MenuActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        TextView priority = findViewById(R.id.menu_priorities);

        priority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle button click here, navigate to the desired activity
                Intent intent = new Intent(MenuActivity.this, PriorityActivity.class);
                startActivity(intent);
            }
        });
    }


}