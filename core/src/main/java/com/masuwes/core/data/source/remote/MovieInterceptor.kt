package com.masuwes.core.data.source.remote

import com.masuwes.core.utils.Constants
import okhttp3.Interceptor
import okhttp3.Response

class MovieInterceptor: Interceptor {

    companion object {
        const val API_KEY = "api_key"
        const val LANG = "language"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url = request.url
            .newBuilder()
            .addQueryParameter(API_KEY, Constants.API_KEY)
            .addQueryParameter(LANG, Constants.LANG)
            .build()

        request = request
            .newBuilder()
            .url(url)
            .build()

        return chain.proceed(request)
    }
}