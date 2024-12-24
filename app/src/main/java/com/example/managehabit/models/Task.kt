package com.example.managehabit.models
import android.content.Context
import androidx.core.content.ContextCompat
import com.example.managehabit.R
import java.io.Serializable

data class Task(
    var _id: String ? = null,
    var name: String ? = null,
    var desc: String ? = null,
    var completeDate:String ? = null,
    var dueTime: String ? = null,
    var createdAt: String ? = null,
    var updatedAt: String ? = null,
) : Serializable
{
    fun isCompleted()=completeDate!=null
    fun imageRessource():Int=if(isCompleted()) R.drawable.checked_24 else R.drawable.unchecked_24
    fun imageColor(context: Context):Int=if (isCompleted()) purple(context) else black(context)

    private fun purple(context: Context)= ContextCompat.getColor(context, R.color.purple_500)
    private fun black(context: Context)= ContextCompat.getColor(context, R.color.black)
}