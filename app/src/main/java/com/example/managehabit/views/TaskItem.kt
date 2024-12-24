package com.example.managehabit.views

import android.content.Context
import androidx.core.content.ContextCompat
import com.example.managehabit.R
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

class TaskItem (
    var name:String?,
    var desc:String?,
    var dueTime : LocalTime?,
    var completeDate: LocalDate?,
    var id:String?
)
{
    fun isCompleted()=completeDate!=null
    fun imageRessource():Int=if(isCompleted()) R.drawable.checked_24 else R.drawable.unchecked_24
    fun imageColor(context: Context):Int=if (isCompleted()) purple(context) else black(context)

    private fun purple(context: Context)=ContextCompat.getColor(context, R.color.purple_500)
    private fun black(context: Context)=ContextCompat.getColor(context, R.color.black)

}
