package id.go.kebumenkab.agendabupati.utils

import android.content.Context
import android.net.ConnectivityManager


object NetworkUtil {
    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = connectivityManager.activeNetworkInfo
        return netInfo != null && netInfo.isConnected
    }

    fun cekInternet(mContext: Context): Boolean {
        val conMgr = mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        run {
            return if (conMgr.activeNetworkInfo != null && conMgr.activeNetworkInfo!!.isAvailable
                && conMgr.activeNetworkInfo!!.isConnected
            ) {
                true
            } else {
                false
            }
        }
    }
}