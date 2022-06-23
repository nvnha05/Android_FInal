package com.example.android_final

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.android_final.Activity.HomeActivity
import com.example.android_final.Activity.LoginActivity

class SplashscreenActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences

    //key
    val Name = "nameKey"
    val Email = "emailKey"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        //goi SharedPreferences su dung phuong thuc getSharedPreferences(String name, int mode)
        sharedPreferences = getSharedPreferences(com.example.android_final.Activity.RegisterActivity.mypreference,
            Context.MODE_PRIVATE)

        //doc du lieu tu SharedPreferences
        val read = sharedPreferences.getString("Name","")

        //Object Expression
        val background = object : Thread(){
            override fun run() {
                try {
                    Thread.sleep(2000)

                    if ((read!!.length) > 0){
                        Log.d("state","hello")
                        val intent = Intent(baseContext,HomeActivity::class.java)
                        startActivity(intent)
                    }
                    else {
                        Log.d("state","bye bye")
                        val intent = Intent(baseContext,HomeActivity::class.java)
                        startActivity(intent)
                    }

                   /* val intent = Intent(this,HomeActivity::class.java)
                    startActivity(intent)*/
                }catch (e: Exception){
                    e.printStackTrace()
                }
            }
        }
        background.start()
    }
}