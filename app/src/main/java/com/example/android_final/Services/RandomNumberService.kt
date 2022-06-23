package com.example.android_final.Services

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.widget.Toast
import com.example.android_final.Activity.toast
import java.util.*

class RandomNumberService: Service() {
    private lateinit var mHandler: Handler
    private lateinit var mRunnable: Runnable

    //duoc goi khi thanh phan khac muon lien ket voi cac dich vu
    override fun onBind(intent: Intent): IBinder? {
        throw UnsupportedOperationException("Not yet implemented")
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        // Send a notification that service is started
        toast("Service started.")

        // Do a periodic task
        mHandler = Handler()
        mRunnable = Runnable { showRandomNumber() }
        mHandler.postDelayed(mRunnable, 5000)
        return Service.START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        toast("Service destroyed.")
        mHandler.removeCallbacks(mRunnable)
        //Xoa bat ki tin nhan đang cho xu ly cua mRunnable nam trong hang doi tin nhan.
    }

    // Custom method to do a task
    private fun showRandomNumber() {
        val rand = Random()
        val number = rand.nextInt(100)
        //toast("Random Number : $number")
        mHandler.postDelayed(mRunnable, 5000)
        // mRunnable duoc them vao hang doi tin nhan, se duoc chay sau 5000 ms
    }

}