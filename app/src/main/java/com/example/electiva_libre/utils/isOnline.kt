package com.example.electiva_libre.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import timber.log.Timber

fun isOnline (context: Context): Boolean {
    val connectivityManager =  context.getSystemService (Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val capabilities =  connectivityManager.getNetworkCapabilities (connectivityManager.activeNetwork)
    if (capabilities != null) {
        if (capabilities.hasTransport (NetworkCapabilities.TRANSPORT_CELLULAR)) {
            Timber.e("internet for datos")
            return true
        } else if (capabilities.hasTransport (NetworkCapabilities.TRANSPORT_WIFI)) {
            Timber.e("internet for wifi")
            return true
        } else if (capabilities.hasTransport (NetworkCapabilities.TRANSPORT_ETHERNET)) {
            Timber.e("internet for ethernet")
            return true
        }
    }
    return false
}