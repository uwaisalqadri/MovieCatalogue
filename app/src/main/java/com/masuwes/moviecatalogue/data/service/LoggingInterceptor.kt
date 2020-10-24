package com.masuwes.moviecatalogue.data.service

import okhttp3.Interceptor
import okhttp3.Response

class LoggingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder()
//            .addHeader("token_key", BuildConfig.TOKEN_KEY)
            .build()
        return chain.proceed(request)
    }
}