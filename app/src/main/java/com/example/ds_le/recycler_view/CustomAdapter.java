package com.example.ds_le.recycler_view;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ds_le.R;
import com.example.ds_le.objects.EntriesHash;
import com.example.ds_le.objects.Task;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private EntriesHash hash;
    private Context context;

    public CustomAdapter(Context context, EntriesHash hash) {
        this.context = context;
        this.hash = hash;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_task_component, parent, false);
        return new CustomViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        String key = hash.getKeyAtPosition(position);
        Task data = hash.getTask(key);

        // Assuming YourDataModel class has appropriate getter methods
        String title = data.getTitle();
        String dueDate = data.getDeadline();
        String description = data.getDescription();

        // Bind data to your TaskItem views
        holder.setTitle(title);
        holder.setDue(dueDate);
        holder.setDescription(description);
    }

    @Override
    public int getItemCount() {
        return hash.getSize();
    }

    static class CustomViewHolder extends RecyclerView.ViewHolder {
        TaskItem taskItem;
        public CustomViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            try {
                taskItem = new TaskItem(context);

                // If the itemView is a ViewGroup (e.g., a RelativeLayout or a LinearLayout),
                // you can directly set the TaskItem as the content of the item view
                if (itemView instanceof ViewGroup) {
                    ViewGroup itemViewGroup = (ViewGroup) itemView;
                    itemViewGroup.addView(taskItem);
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        public void setDescription(String description) {
            taskItem.setDescriptionTextView(description);
        }

        public void setDue(String due) {
            taskItem.setDueDateTextView(due);
        }

        public void setTitle(String title) {
            taskItem.setTitleTextView(title);
        }
    }
}
