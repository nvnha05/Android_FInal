package com.example.android_final.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.example.android_final.Database.BookingsDbHandler
import com.example.android_final.Database.DatabaseHandler
import com.example.android_final.Model.BookingTasks
import com.example.android_final.Model.Tasks
import com.example.android_final.R
import com.example.android_final.UI.Fragments.HotelActivity
import com.example.android_final.databinding.ActivityBookBinding

class BookActivity : AppCompatActivity() {

    private lateinit var binding:ActivityBookBinding
    var dbHandler: DatabaseHandler? = null
    var isEditMode = false
    var BookdbHandler: BookingsDbHandler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_book)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_book)

        initDB()

        binding.bookBtnClose.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        binding.bookBtnBook.setOnClickListener {
            val dialog = AlertDialog.Builder(this).setTitle("Info").setMessage("Click 'YES' to book the room")
                .setPositiveButton("YES", { dialog, i ->
                    BookdbHandler = BookingsDbHandler(this)
                    binding.bookBtnBook.setOnClickListener ({

                        var success: Boolean = false
                        val tasks: BookingTasks = BookingTasks()

                        tasks.name = binding.bookTvName.text.toString()
                        tasks.desc = binding.bookTvLocation.text.toString()
                        tasks.rate = binding.bookTvRate.text.toString()
                        success = BookdbHandler?.addTask(tasks) as Boolean

                            if (success)
                                finish()
                    })
                    Toast.makeText(this, "Thanks for booking", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, HotelActivity::class.java)
                    startActivity(intent)
                    dialog.dismiss()
                    })
                .setNegativeButton("NO", { dialog, i ->
                    dialog.dismiss()
                })
            dialog.show()
        }

        binding.bookBtnClose.setOnClickListener({
            val dialog = AlertDialog.Builder(this).setTitle("Info").setMessage("Click 'YES' Delete the Task.")
                .setPositiveButton("YES", { dialog, i ->
                    val success = dbHandler?.deleteTask(intent.getIntExtra("Id", 0)) as Boolean
                    if (success)
                        finish()

                    dialog.dismiss()
                })
                .setNegativeButton("NO", { dialog, i ->
                    dialog.dismiss()
                })
            dialog.show()
        })

    }

    private fun initDB() {
        dbHandler = DatabaseHandler(this)
        if (intent != null && intent.getStringExtra("Mode") == "E") {
            isEditMode = true
            val tasks: Tasks = dbHandler!!.getTask(intent.getIntExtra("Id",0))
            binding.bookTvName.text = tasks.name
            binding.bookTvLocation.text = tasks.desc
            binding.bookTvRate.text = tasks.rate
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}