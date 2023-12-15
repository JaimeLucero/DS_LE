package com.example.ds_le.recycler_view;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ds_le.R;
import com.example.ds_le.objects.EntriesHash;
import com.example.ds_le.objects.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private EntriesHash hash;
    private Context context;
    private ItemTouchHelper itemTouchHelper;

    private List<Task> filteredList;
    public CustomAdapter(Context context, EntriesHash hash) {
        this.context = context;
        this.hash = hash;
        filteredList = new ArrayList<>();
    }

    public void filter(String category) {
        if (filteredList != null){
            filteredList.clear();
        }
        if (category.equalsIgnoreCase("All")) {
            // If "All" is selected, show all items
            filteredList.addAll(hash.getEntries().values());
        } else {
            // Filter the items based on the selected category
            for (Task task : hash.getEntries().values()) {
                if (task.getCategory().equalsIgnoreCase(category)) {
                    filteredList.add(task);
                }
            }
        }
        notifyDataSetChanged(); // Notify the adapter that the data set has changed
    }

    public void setItemTouchHelper(ItemTouchHelper itemTouchHelper) {
        this.itemTouchHelper = itemTouchHelper;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_task_component, parent, false);
        return new CustomViewHolder(view, context, itemTouchHelper);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {

        if (position >= 0 && position < filteredList.size()) {
            Task data = filteredList.get(position);

            // Assuming YourDataModel class has appropriate getter methods
            String title = data.getTitle();
            String dueDate = data.getDeadline();
            String description = data.getDescription();

            // Bind data to your TaskItem views
            holder.setTitle(title);
            holder.setDue(dueDate);
            holder.setDescription(description);
            holder.setTask(data);
        }


    }

    public void onItemMove(int fromPosition, int toPosition) {
        Collections.swap(filteredList, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
    }


    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    static class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
        TaskItem taskItem;
        ItemTouchHelper itemTouchHelper;
        public CustomViewHolder(@NonNull View itemView, Context context,ItemTouchHelper itemTouchHelper) {
            super(itemView);
            this.itemTouchHelper = itemTouchHelper; // Set the ItemTouchHelper
            itemView.setOnLongClickListener(this);
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

        public void setTask(Task task) {
            taskItem.setTask(task);
        }

        @Override
        public boolean onLongClick(View v) {
            itemTouchHelper.startDrag(this);
            return true;
        }

        @Override
        public boolean onLongClickUseDefaultHapticFeedback(@NonNull View v) {
            return View.OnLongClickListener.super.onLongClickUseDefaultHapticFeedback(v);
        }
    }
}


