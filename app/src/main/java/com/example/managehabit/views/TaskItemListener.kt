package com.example.managehabit.views

import com.example.managehabit.models.Task

interface TaskItemListener {
    fun editTaskItem(task: Task)
    fun completeTaskItem(task: Task)
}