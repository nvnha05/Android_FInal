package com.example.android_final

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android_final.UI.Fragments.LayoutOne

class DisplayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.home_container, LayoutOne.newInstance()).commitNow()
        }
    }
}