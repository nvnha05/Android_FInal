package com.example.android_final.UI.Fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android_final.Adapter.TaskRecyclerAdapter
import com.example.android_final.Database.DatabaseHandler
import com.example.android_final.Model.Tasks
import com.example.android_final.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HotelActivity : AppCompatActivity() {

    var taskRecyclerAdapter: TaskRecyclerAdapter? = null;
    var fab: FloatingActionButton? = null
    var recyclerView: RecyclerView? = null
    var dbHandler: DatabaseHandler? = null
    var listTasks: List<Tasks> = ArrayList<Tasks>()
    var linearLayoutManager: LinearLayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel)

        initViews()

    }

    fun initDB() {
        dbHandler = DatabaseHandler(this)
        listTasks = (dbHandler as DatabaseHandler).task()
        taskRecyclerAdapter = TaskRecyclerAdapter(tasksList = listTasks, context = applicationContext)
        (recyclerView as RecyclerView).adapter = taskRecyclerAdapter
    }

    fun initViews() {
        recyclerView = findViewById(R.id.hotel_recyclerview) as RecyclerView
        taskRecyclerAdapter = TaskRecyclerAdapter(tasksList = listTasks, context = applicationContext)
        linearLayoutManager = LinearLayoutManager(applicationContext)
        (recyclerView as RecyclerView).layoutManager = linearLayoutManager
    }

    override fun onResume() {
        super.onResume()
        initDB()
    }
}