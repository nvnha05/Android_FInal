package com.example.android_final.Activity

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.android_final.R
import com.example.android_final.Receiver.BaseActivity
import com.example.android_final.UI.Fragments.HotelActivity
import com.example.android_final.UI.Fragments.LayoutOne
import com.example.android_final.databinding.ActivityHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class HomeActivity : BaseActivity() {

    lateinit var sharedpreferences: SharedPreferences
    private lateinit var binding:ActivityHomeBinding

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {

            R.id.navigation_home -> {
                supportFragmentManager.beginTransaction().replace(R.id.home_container, LayoutOne.newInstance()).commitNow()
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_location -> {
                val intent = Intent(this, ModalActivity::class.java)
                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_hotels -> {
                val intent = Intent(this, HotelActivity::class.java)
                startActivity(intent)

//                supportFragmentManager.beginTransaction().replace(R.id.container, Hotel.newInstance()).commitNow()

                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_account -> {
                sharedpreferences = getSharedPreferences(RegisterActivity.mypreference, Context.MODE_PRIVATE)
                val editor = sharedpreferences.edit()
                editor.clear()
                editor.commit()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_home)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        binding.homeHreg.setOnClickListener {
            val intent = Intent(this, HotelRegistrationActivity::class.java)
            startActivity(intent)
        }

        binding.homeSearchBox.setOnClickListener {
            val intent = Intent(this, ModalActivity::class.java)
            startActivity(intent)
        }

        supportFragmentManager.beginTransaction().replace(R.id.home_container, LayoutOne.newInstance()).commitNow()

        binding.homeBottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

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

    fun Context.toast(message:String){
        Toast.makeText(applicationContext,message, Toast.LENGTH_SHORT).show()
    }

}