package com.mobile.dailytaskmanager;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.mobile.dailytaskmanager.Models.Task;
import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private ArrayList<Task> taskList;
    private Context context;

    public TaskAdapter(ArrayList<Task> taskList, Context context) {
        this.taskList = taskList;
        this.context = context;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task currentTask = taskList.get(position);
        holder.textViewTaskName.setText(currentTask.getName());

        holder.imageViewEdit.setOnClickListener(v -> {
            Intent intent = new Intent(context, EditTaskActivity.class);
            intent.putExtra("TASK_NAME", currentTask.getName());
            intent.putExtra("TASK_HOUR", currentTask.getHour());
            intent.putExtra("TASK_MINUTE", currentTask.getMinute());
            intent.putExtra("TASK_POSITION", position);
            ((TaskListActivity) context).startActivityForResult(intent, TaskListActivity.EDIT_TASK_REQUEST);
        });

        // Set click listener for delete icon
        holder.imageViewDelete.setOnClickListener(v -> {
            taskList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, taskList.size());
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTaskName;
        ImageView imageViewEdit, imageViewDelete;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTaskName = itemView.findViewById(R.id.textViewTaskName);
            imageViewEdit = itemView.findViewById(R.id.imageViewEdit);
            imageViewDelete = itemView.findViewById(R.id.imageViewDelete);
        }
    }
}
