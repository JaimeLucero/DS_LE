package com.example.ds_le.recycler_view;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ds_le.R;

public class TaskItem extends RelativeLayout{
    private TextView titleTextView;
    private TextView dueDateTextView;
    private TextView descriptionTextView;
    private ImageView deleteImageView;
    private CheckBox checkbox;


    public TaskItem(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        // Inflate the layout
        LayoutInflater.from(context).inflate(R.layout.card_task_component, this, true);

        // Initialize views
        titleTextView = findViewById(R.id.card_title);
        dueDateTextView = findViewById(R.id.card_due);
        descriptionTextView = findViewById(R.id.card_desc);
        deleteImageView = findViewById(R.id.delete);
        checkbox = findViewById(R.id.done_card);

    }

    public void setTitleTextView(String title) {
        if (titleTextView != null) {
            titleTextView.setText(title);
        } else {
            System.out.println("titleTextView is null. Make sure it's initialized.");
        }
    }

    public void setDescriptionTextView(String description) {
        if (descriptionTextView != null) {
            descriptionTextView.setText(description);
        } else {
            System.out.println("descriptionTextView is null. Make sure it's initialized.");
        }
    }

    public void setDueDateTextView(String dueDate) {
        if (dueDateTextView != null) {
            dueDateTextView.setText(dueDate);
        } else {
            System.out.println("dueDateTextView is null. Make sure it's initialized.");
        }
    }
}
