package com.example.managehabit.views

import android.app.TimePickerDialog
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.managehabit.databinding.FragmentNewTaskSheetBinding
import com.example.managehabit.models.Task
import com.example.managehabit.viewmodels.TaskViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalTime
import java.util.Locale





class NewTaskSheet (var task: Task?): BottomSheetDialogFragment() {

    private lateinit var binding: FragmentNewTaskSheetBinding
    private lateinit var taskViewModel: TaskViewModel
    private var dueTime:LocalTime?=null

    private var taskItem : TaskItem?=null



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity=requireActivity()
        taskItem?.id =  task?._id
        taskItem?.name =  task?.name
        taskItem?.desc =  task?.desc
        taskItem?.dueTime =  LocalTime.parse(task?.dueTime)
        taskItem?.completeDate =  LocalDate.parse(task?.completeDate)


        if(taskItem?.id!=null)
        {
          binding.taskTitle.text="Edit Task"
          val editable=Editable.Factory.getInstance()
          binding.name.text=editable.newEditable(taskItem!!.name)
            binding.desc.text=editable.newEditable(taskItem!!.desc)
            if (taskItem!!.dueTime!=null){
              dueTime=taskItem!!.dueTime!!
                //updateTimeButtonText()

            }
        }
        else{
            binding.taskTitle.text="New Task"
        }

        taskViewModel=ViewModelProvider(activity).get(TaskViewModel::class.java)
        binding.saveButton.setOnClickListener{
            saveAction()
        }
        binding.timePickerButton.setOnClickListener{
            openTimePicker()

        }
    }

    private fun openTimePicker() {
        if (dueTime==null)
            dueTime= LocalTime.now()
        val listener=TimePickerDialog.OnTimeSetListener {_,selectedHour,selectedMinute->
            dueTime= LocalTime.of(selectedHour,selectedMinute)
            updateTimeButtonText()
        }
        val dialog=TimePickerDialog(activity,listener,dueTime!!.hour,dueTime!!.minute,true)
        dialog.setTitle("Task Due!")
        dialog.show()
    }

    private fun updateTimeButtonText() {
    binding.timePickerButton.text= String.format("%02d:%02d",dueTime!!.hour,dueTime!!.minute)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding= FragmentNewTaskSheetBinding.inflate(inflater,container,false)
return binding.root   }


    private fun saveAction(){
        val name=binding.name.text.toString()
        val desc=binding.desc.text.toString()
        if(taskItem==null){
            val newTask= Task(name,desc, dueTime = null,completeDate = null)
            taskViewModel.addTaskItem(context,newTask)
        }
        else{
           // taskViewModel.updateTaskItem(taskItem!!.id,name,desc,dueTime = null)
        }

        binding.name.setText("")
        binding.desc.setText("")
        dismiss()
    }

}