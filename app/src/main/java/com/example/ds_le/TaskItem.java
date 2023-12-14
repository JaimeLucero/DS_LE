package com.example.ds_le;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
public class TaskItem extends RelativeLayout{
    private TextView titleTextView;
    private TextView dueDateTextView;
    private TextView descriptionTextView;
    private ImageView deleteImageView;
    private ImageView checkboxImageView;

    public TaskItem(Context context) {
        super(context);
        init(context);
    }

    public TaskItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TaskItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        // Inflate the layout
        LayoutInflater.from(context).inflate(R.layout.card_task_component, this, true);

        // Initialize views
        titleTextView = findViewById(R.id.title);
        dueDateTextView = findViewById(R.id.due_date);
        descriptionTextView = findViewById(R.id.lorem_ipsum);
        deleteImageView = findViewById(R.id.delete);
        checkboxImageView = findViewById(R.id.checkbox);

        // Set up any additional initialization or event listeners here
    }
}
