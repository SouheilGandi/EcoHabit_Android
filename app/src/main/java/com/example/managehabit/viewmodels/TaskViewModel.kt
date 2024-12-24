package com.example.managehabit.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.managehabit.api.RetrofitInstance
import com.example.managehabit.models.Task
import com.example.managehabit.views.TaskItem
import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class TaskViewModel :ViewModel(){
    var taskItems: MutableLiveData<List<Task>>
    init {
        taskItems = MutableLiveData()
    }
    fun getLiveDataObserver(): MutableLiveData<List<Task>> {
        return taskItems
    }
    fun addTaskItem(context: Context?, newTask: Task){
        val apiInterface = RetrofitInstance.api(context)

        apiInterface.addTask(newTask).enqueue(object : Callback<Task> {
            override fun onFailure(call: Call<Task>, t: Throwable) {
                taskItems.postValue(null)
                Log.e("CallF: ", t.message.toString())

            }

            override fun onResponse(
                call: Call<Task>,
                response: Response<Task>

            ) {
                Log.i(
                    "response",
                    response.body().toString()
                )

                taskItems.value = taskItems.value?.plus(response.body()!!)
            }
        })
    }
    /*

        fun updateTaskItem(id: UUID,name: String,desc:String,dueTime:LocalTime?){
         val list=taskItems.value
          val task=list!!.find{it.id==id}!!
            task.name=name
            task.desc=desc
            task.dueTime=dueTime
            taskItems.postValue(list)

        }
    */
    fun makeApiCall(context: Context?){
        val apiInterface = RetrofitInstance.api(context)
        //sharedPreferences = getApplication(this).getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        // apiInterface.getStade(sharedPreferences.getString("token",null)!!).enqueue(object : Callback<List<Stade>> {
        apiInterface.getAllTasks().enqueue(object : Callback<List<Task>> {
            override fun onFailure(call: Call<List<Task>>, t: Throwable) {
                taskItems.postValue(null)
                Log.e("CallF: ", t.message.toString())

            }

            override fun onResponse(
                call: Call<List<Task>>,
                response: Response<List<Task>>

            ) {
                Log.i(
                    "response",
                    response.body().toString()
                )
                taskItems.postValue(response.body())
            }
        })

    }


       fun setCompleted(task: Task)
    {
        val list=taskItems.value
        val task=list!!.find { it._id==task._id }!!
        if (task.completeDate==null)
        {
            task.completeDate= LocalDate.now().toString()
            taskItems.postValue(list)}
    }

}