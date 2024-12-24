package com.example.managehabit.api

import com.example.managehabit.models.Task
import okhttp3.MultipartBody
import retrofit2.Call
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.*

interface TaskApi {

    @GET("/task")
    fun getAllTasks():Call<List<Task>>

    @POST("/task")
    fun addTask(@Body task:Task):Call<Task>

}