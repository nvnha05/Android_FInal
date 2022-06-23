package com.example.android_final.Activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.android_final.Database.UserDbHandler
import com.example.android_final.R
import com.example.android_final.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    companion object {
        val mypreference = "myPref"
        val name = "nameKey"
        val email = "emailKey"
        val hotelName = "hotelKey"
        val hotelLoc = "locKey"
        val hotelRate = "hotelRate"
    }

    lateinit var sharedpreferences: SharedPreferences
    lateinit var binding:ActivityRegisterBinding

    var dbHandler: UserDbHandler? = null
    var isEditMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_register)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)

        //actionBar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE)

        binding.registerBtnSignUp.setOnClickListener {
            save()
        }

    }

    fun save(){
        val el = binding.registerEdtEmail.text.toString().trim()
        val pw = binding.registerEdtPassword.text.toString().trim()

        if((el.length == 0) && (pw.length == 0)){
            Toast.makeText(this, "Fill all the fields", Toast.LENGTH_LONG).show()
        } else{
            var editor = sharedpreferences.edit()
            editor.putString(name, pw)
            editor.putString(email, el)
            editor.commit()
            Toast.makeText(this, "Successfully registered", Toast.LENGTH_LONG).show()
            val intent = Intent(this, LoginActivity::class.java)
            intent.putExtra("Username","1")
            startActivity(intent)
        }
    }

}