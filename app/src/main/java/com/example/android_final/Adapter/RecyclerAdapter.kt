package com.example.android_final.Adapter

import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.android_final.Activity.BookActivity
import com.example.android_final.Model.Tasks
import com.example.android_final.R
import java.util.ArrayList

class RecyclerAdapter(tasksList: List<Tasks>, internal var context: Context) : RecyclerView.Adapter<RecyclerAdapter.TaskViewHolder>() {

    internal var tasksList: List<Tasks> = ArrayList()

    init {
        this.tasksList = tasksList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.hotel_list, parent, false)
        return TaskViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val tasks = tasksList[position]
        holder.name.text = tasks.name
        holder.desc.text = tasks.desc

        holder.itemView.setOnClickListener {
            val i = Intent(context, BookActivity::class.java)
            i.putExtra("Mode", "E")
            i.putExtra("Id", tasks.id)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(i)
        }



    }

    override fun getItemCount(): Int {
        return tasksList.size
    }

    inner class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var name: TextView = view.findViewById(R.id.hotel_list_hdbname) as TextView
        var desc: TextView = view.findViewById(R.id.hotel_list_hdblocation) as TextView
        var list_item: LinearLayout = view.findViewById(R.id.hotel_list_disphcontainer) as LinearLayout
    }

}