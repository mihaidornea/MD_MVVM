package com.example.wd_mvvm.utils

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import androidx.annotation.WorkerThread


@WorkerThread
fun isInternetAvailable(context: Context?): Boolean {
    val connectivityManager = context?.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager?
    val activeNetworkInfo = connectivityManager!!.activeNetworkInfo
    return activeNetworkInfo != null && activeNetworkInfo.isConnected
}