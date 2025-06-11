package com.solo.mychat.data.remote.interceptors

import com.solo.mychat.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class MyInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val request = original.newBuilder()
            .addHeader("x-rapidapi-key", BuildConfig.API_KEY)
            .addHeader("x-rapidapi-host", BuildConfig.API_HOST)
            .build()
        return chain.proceed(request)
    }
}