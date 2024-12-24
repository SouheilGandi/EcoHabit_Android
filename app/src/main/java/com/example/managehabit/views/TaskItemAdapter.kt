package com.example.managehabit.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.managehabit.databinding.TaskItemCellBinding
import com.example.managehabit.models.Task

class TaskItemAdapter(
    private val taskItems: List<Task>,
    private val clickListener: TaskItemListener
):RecyclerView.Adapter<TaskItemViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskItemViewHolder {
       val from=LayoutInflater.from(parent.context)
        val binding=TaskItemCellBinding.inflate(from,parent,false)
        return TaskItemViewHolder(parent.context,binding,clickListener)
    }

    override fun getItemCount(): Int =taskItems.size

//oui
    override fun onBindViewHolder(holder: TaskItemViewHolder, position: Int) {
        holder.bindTaskItem((taskItems[position]))
    }
}