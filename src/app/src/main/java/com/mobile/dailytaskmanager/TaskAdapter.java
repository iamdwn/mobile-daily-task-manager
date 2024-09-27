package com.mobile.dailytaskmanager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile.dailytaskmanager.Models.Task;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    private ArrayList<Task> taskList;

    public TaskAdapter(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.taskName.setText(task.getName());
        holder.taskTime.setText(task.getCompletionTime());
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView taskName;
        TextView taskTime;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            taskName = itemView.findViewById(android.R.id.text1);
            taskTime = itemView.findViewById(android.R.id.text2);
        }
    }
}
