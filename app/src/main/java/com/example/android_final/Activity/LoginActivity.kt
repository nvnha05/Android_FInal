package com.example.android_final.Activity

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.android_final.R
import com.example.android_final.Services.RandomNumberService
import com.example.android_final.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    lateinit var binding:ActivityLoginBinding
    lateinit var sharedpreferences: SharedPreferences
    val Name = "nameKey"
    val Email = "emailKey"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //setContentView(R.layout.activity_login)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        // Variable to hold service class name
        val serviceClass = RandomNumberService::class.java

        // Initialize a new Intent instance
        val intent = Intent(applicationContext, serviceClass)

        val cc =intent.getStringExtra("Username")

        if(cc == "1") {
            Toast.makeText(this, "You have been successfully registered ", Toast.LENGTH_LONG).show()
        }

        sharedpreferences = getSharedPreferences(
            com.example.android_final.Activity.RegisterActivity.mypreference, Context.MODE_PRIVATE)

        binding.loginBtnLogin.setOnClickListener {

            if(binding.loginEdtPassword.text.toString().length == 0 && binding.loginEdtEmail.text.toString().length == 0){
                Toast.makeText(this, "Enter the correct login details ", Toast.LENGTH_LONG).show()
            }

            if(sharedpreferences.contains(Name)){
                if (binding.loginEdtPassword.text.toString().trim() == sharedpreferences.getString(Name, "")){

                    if(sharedpreferences.contains(Email)) {
                        if (binding.loginEdtEmail.text.toString().trim() == sharedpreferences.getString(Email, "")) {

                            if (!isServiceRunning(serviceClass)) {
                                // Start the service
                                startService(intent)
                            } else {
                                toast("Service already running.")
                            }
                            val intent = Intent(this, HomeActivity::class.java)
//                             Toast.makeText(this, "Login Successfull ", Toast.LENGTH_LONG).show()

                            startActivity(intent)
                        } else{
                            Toast.makeText(this, "Enter the correct login details ", Toast.LENGTH_LONG).show()
                        }
                    }
                } else{
                    Toast.makeText(this, "Enter the correct login details ", Toast.LENGTH_LONG).show()
                }
            }
        }

        binding.tvBtnRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    // Custom method to determine whether a service is running
    private fun isServiceRunning(serviceClass: Class<*>): Boolean {
        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

        // Loop through the running services
        for (service in activityManager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.name == service.service.className) {
                // If the service is running then return true
                return true
            }
        }
        return false
    }


}
// Extension function to show toast message
fun Context.toast(message:String){
    Toast.makeText(applicationContext,message, Toast.LENGTH_SHORT).show()
}
