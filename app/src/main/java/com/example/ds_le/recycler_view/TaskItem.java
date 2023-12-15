package com.example.ds_le.recycler_view;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ds_le.R;
import com.example.ds_le.activity.HomeActivity;
import com.example.ds_le.objects.EntriesHash;
import com.example.ds_le.objects.Task;
import com.google.android.material.snackbar.Snackbar;

public class TaskItem extends RelativeLayout{
    private TextView titleTextView;
    private TextView dueDateTextView;
    private TextView descriptionTextView;
    private ImageView deleteImageView;
    private CheckBox checkbox;
    private boolean isDone;

    private Task task;
    private Context context;
    private EntriesHash hash;

    private int position;
    CustomAdapter ca;
    public TaskItem(Context context, EntriesHash hash, CustomAdapter ca) {
        super(context);
        this.context=context;
        init(context);
        this.hash = hash;
        this.ca = ca;
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
                } else {
                    isDone = false;
                    // CheckBox is unchecked
                }
            }
        });

        deleteImageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                hash.deleteEntry(hash.getKeyByValue(task));
                showUndoSnackbar(v);
                ca.removeItem(position);
            }
        });

    }

    private void showUndoSnackbar(View view) {
        Snackbar snackbar = Snackbar.make(view, "Task deleted", Snackbar.LENGTH_LONG)
                .setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Undo action, handle accordingly
                        showToast("Undo clicked");
                        hash.addEntry(hash.generateUniqueUUID(),task);
                        ca.addItem(position, task);
                    }
                });

        snackbar.show();
    }

    private void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
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

    public void setPosition(int position) {
        this.position = position;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public void setCheckbox(boolean check) {
        this.checkbox.setChecked(check);
    }
}
