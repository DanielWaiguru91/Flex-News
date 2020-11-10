package tech.danielwaiguru.flexnews.networking

import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class NetworkStatusChecker(private val connectivityManager: ConnectivityManager?) {
    inline fun performIfConnectedToInternet(hasNoInternetConnection: () -> Unit,action: ()->Unit){
        if (hasInternetConnection()){
            action()
        }
        else
        {
            hasNoInternetConnection()
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