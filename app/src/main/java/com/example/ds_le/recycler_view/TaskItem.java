package com.example.ds_le.recycler_view;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ds_le.R;
import com.example.ds_le.activity.HomeActivity;
import com.example.ds_le.objects.Task;

public class TaskItem extends RelativeLayout{
    private TextView titleTextView;
    private TextView dueDateTextView;
    private TextView descriptionTextView;
    private ImageView deleteImageView;
    private CheckBox checkbox;
    private boolean isDone;

    private Task task;
    private Context context;
    public TaskItem(Context context) {
        super(context);
        this.context=context;
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

        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Do something based on the CheckBox status
                if (isChecked) {
                    isDone = true;
                    // CheckBox is checked
                    setDone(isDone);
                    Toast.makeText(context, "Task done.", Toast.LENGTH_SHORT).show();
                } else {
                    isDone = false;
                    Toast.makeText(context, "Task undone.", Toast.LENGTH_SHORT).show();
                    // CheckBox is unchecked
                }
            }
        });

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

    public void setDone(boolean done) {
        task.setDone(done);
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
