package tech.danielwaiguru.flexnews.networking

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi

class NetworkStatusChecker(private val connectivityManager: ConnectivityManager?) {
    inline fun performIfConnectedToInternet(action: ()->Unit){
        if (hasInternetConnection()){
            action()
        }
    }

    fun hasInternetConnection(): Boolean {
        val network = connectivityManager?.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN)
                || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
    }

}