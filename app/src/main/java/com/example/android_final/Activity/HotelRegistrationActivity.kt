package com.example.android_final.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android_final.Database.DatabaseHandler
import com.example.android_final.Model.Tasks
import com.example.android_final.R
import com.example.android_final.databinding.ActivityHotelRegistrationBinding

class HotelRegistrationActivity : AppCompatActivity() {

    var dbHandler: DatabaseHandler? = null
    var isEditMode = false
    private lateinit var binding:ActivityHotelRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel_registration)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initOperations()
    }

    private fun initOperations() {
        dbHandler = DatabaseHandler(this)
        binding.hotelRegistrationBtnSave.setOnClickListener{
            var success: Boolean = false

            val tasks: Tasks = Tasks()
            tasks.name = binding.hotelRegistrationEdtHotelName.text.toString()
            tasks.desc = binding.hotelRegistrationEdtRooms.text.toString()
            tasks.rate = binding.hotelRegistrationEdtRate.text.toString()
            success = dbHandler?.addTask(tasks) as Boolean

            if (success)
                finish()
        }
    }
}