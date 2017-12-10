package com.nawrot.mateusz.campaignapp.data.network

import android.net.ConnectivityManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject


class NetworkInterceptor @Inject constructor(private val connectivityManager: ConnectivityManager) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val connected = connectivityManager.activeNetworkInfo?.isConnected ?: false
        if (!connected) {
            throw NetworkException()
        }

        return chain.proceed(chain.request().newBuilder().build())
    }
}