package com.example.ds_le;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.ds_le.activity.HomeActivity;
import com.example.ds_le.objects.EntriesHash;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EntriesHash hash = new EntriesHash(this);
        findViewById(android.R.id.content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start SecondActivity when the user taps anywhere on MainActivity
                startActivity(new Intent(MainActivity.this, HomeActivity.class));
            }
        });
    }
}

