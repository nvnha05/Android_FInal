package com.example.android_final.Receiver

import android.annotation.SuppressLint
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.android_final.R
import com.example.android_final.databinding.ActivityHomeBinding
import com.google.android.material.snackbar.Snackbar


@SuppressLint("Registered")
open class BaseActivity:AppCompatActivity(), ConnectivityReceiver.ConnectivityReceiverListener {
    private lateinit var binding: ActivityHomeBinding
    private var mSnackBar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerReceiver(ConnectivityReceiver(), IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    override fun onResume() {
        super.onResume()
        ConnectivityReceiver.connectivityReceiverListener = this
    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        showMessage(isConnected)
    }

    private fun showMessage(isConnected: Boolean) {

        if (!isConnected) {

            val messageToUser = "You are offline now."
            mSnackBar = Snackbar.make((binding.homeContainer), messageToUser, Snackbar.LENGTH_LONG)
            //Assume "rootLayout" as the root layout of every activity.
            mSnackBar?.duration = Snackbar.LENGTH_LONG
            mSnackBar?.show()
        } else {
            mSnackBar?.dismiss()
        }
    }
}