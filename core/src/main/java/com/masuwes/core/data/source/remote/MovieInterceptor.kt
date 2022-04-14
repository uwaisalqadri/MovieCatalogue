package com.masuwes.core.data.source.remote

import com.masuwes.core.data.base.ApiException
import com.masuwes.core.utils.Constants
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.internal.http2.ConnectionShutdownException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class MovieInterceptor: Interceptor {

    companion object {
        const val API_KEY = "api_key"
        const val LANG = "language"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        try {
            val url = request.url
                .newBuilder()
//                .addQueryParameter(API_KEY, Constants.API_KEY)
                .addQueryParameter(LANG, Constants.LANG)
                .build()

            request = request
                .newBuilder()
                .url(url)
                .build()

            return chain.proceed(request)

        } catch (e: Exception) {
            e.printStackTrace()
            var msg = ""
            when (e) {
                is SocketTimeoutException -> {
                    msg = "Timeout - Please check your internet connection"
                }
                is UnknownHostException -> {
                    msg = "Unable to make a connection. Please check your internet"
                }
                is ConnectionShutdownException -> {
                    msg = "Connection shutdown. Please check your internet"
                }
                is IOException -> {
                    msg = "Server is unreachable, please try again later."
                }
                is IllegalStateException -> {
                    msg = "${e.message}"
                }
                else -> {
                    msg = "${e.message}"
                }
            }

            throw ApiException(statusCode = "404", statusMessage = msg)
        }
    }
}