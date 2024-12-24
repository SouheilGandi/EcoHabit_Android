package com.example.managehabit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.managehabit.databinding.ActivityMainBinding
import com.example.managehabit.models.Task
import com.example.managehabit.viewmodels.TaskViewModel
import com.example.managehabit.views.NewTaskSheet
import com.example.managehabit.views.TaskItem
import com.example.managehabit.views.TaskItemAdapter
import com.example.managehabit.views.TaskItemListener

class MainActivity : AppCompatActivity() , TaskItemListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var taskViewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        taskViewModel=ViewModelProvider(this).get(TaskViewModel::class.java)
        taskViewModel.getLiveDataObserver().observe(this, Observer {
            if(it != null) {

                binding.todoListRecyclerView.apply{
                    layoutManager=LinearLayoutManager(applicationContext)
                    adapter= TaskItemAdapter(it,this@MainActivity)

                }
            } else {
                Toast.makeText(this, "Error in getting list", Toast.LENGTH_SHORT).show()
            }
        })
        taskViewModel.makeApiCall(this)


    binding.newTaskButton.setOnClickListener{
            NewTaskSheet(task  = null).show(supportFragmentManager,"newTaskTag")

        }
    }


    override fun editTaskItem(task: Task) {
        NewTaskSheet(task).show(supportFragmentManager,"newTaskTag")
    }


    override fun completeTaskItem(task: Task) {
        taskViewModel.setCompleted(task)
    }
}