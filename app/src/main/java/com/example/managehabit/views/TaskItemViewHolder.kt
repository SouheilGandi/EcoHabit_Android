package com.example.managehabit.views

import android.content.Context
import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView
import com.example.managehabit.databinding.TaskItemCellBinding
import com.example.managehabit.models.Task
import java.time.format.DateTimeFormatter
//win tzid des dependences fel android ?
class TaskItemViewHolder(
    private val context: Context,
    private val binding: TaskItemCellBinding,
    private val clickListener: TaskItemListener
)
    :RecyclerView.ViewHolder(binding.root)
{

    private val timeFormat=DateTimeFormatter.ofPattern("HH:mm")


        fun bindTaskItem(task: Task)
        {
            binding.name.text=task.name

            if (task.isCompleted()){
                binding.name.paintFlags= Paint.STRIKE_THRU_TEXT_FLAG
                binding.dueTime.paintFlags= Paint.STRIKE_THRU_TEXT_FLAG
            }

            binding.completeButton.setImageResource(task.imageRessource())
            binding.completeButton.setColorFilter(task.imageColor(context))

            binding.completeButton.setOnClickListener {
                clickListener.completeTaskItem(task)
            }

            binding.taskCellContainer.setOnClickListener{
               clickListener.editTaskItem(task)
            }

            if(task.dueTime !=null)
                binding.dueTime.text= task.dueTime
        }
}