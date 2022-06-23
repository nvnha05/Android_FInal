package com.example.android_final.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android_final.Adapter.RecyclerAdapter
import com.example.android_final.Database.DatabaseHandler
import com.example.android_final.Model.Tasks
import com.example.android_final.R
import com.example.android_final.databinding.ActivityModalBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ModalActivity : AppCompatActivity() {

    private lateinit var binding:ActivityModalBinding
    var taskRecyclerAdapter: RecyclerAdapter? = null;
    var fab: FloatingActionButton? = null
    var recyclerView: RecyclerView? = null
    var dbHandler: DatabaseHandler? = null
    var listTasks: List<Tasks> = ArrayList<Tasks>()
    var linearLayoutManager: LinearLayoutManager? = null
    var editdata = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_modal)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_modal)
        Log.d("oooo","kiko")

        binding.modalBtnClose.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        Log.d("oooo","kiko1")
/*
        binding.modalSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                TODO("Not yet implemented")

            }

            override fun onQueryTextChange(p0: String?): Boolean {
                TODO("Not yet implemented")

        })*/
        binding.modalBtnSearch.setOnClickListener {
            editdata = binding.modalSearchName.text.toString()
            initDB()
            initViews()
        }
        Log.d("oooo","kiko2")
//
        initViews()
        }

    fun initDB() {

        if(editdata.length > 0) {
            Log.d("ooooo",editdata)
            dbHandler = DatabaseHandler(this)
            listTasks = (dbHandler as DatabaseHandler).searchTask(editdata)
            taskRecyclerAdapter = RecyclerAdapter(tasksList = listTasks, context = applicationContext)
            (recyclerView as RecyclerView).adapter = taskRecyclerAdapter
        }
        else {
            Log.d("ooopp",editdata)
            dbHandler = DatabaseHandler(this)
            listTasks = (dbHandler as DatabaseHandler).task()
            taskRecyclerAdapter = RecyclerAdapter(tasksList = listTasks, context = applicationContext)
            (recyclerView as RecyclerView).adapter = taskRecyclerAdapter
        }
    }

    fun initViews() {
        Log.d("ooopp","view")
        recyclerView = binding.modalRecycleView
        taskRecyclerAdapter = RecyclerAdapter(tasksList = listTasks, context = applicationContext)
        linearLayoutManager = LinearLayoutManager(applicationContext)
        (recyclerView as RecyclerView).layoutManager = linearLayoutManager
    }

    override fun onResume() {
        super.onResume()
        initDB()
    }

}

