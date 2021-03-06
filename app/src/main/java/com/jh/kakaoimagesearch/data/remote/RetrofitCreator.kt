package com.jh.kakaoimagesearch.data.remote

import com.jh.kakaoimagesearch.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitCreator {
    companion object {
        private const val baseUrl = "https://dapi.kakao.com/v2/"

        fun createRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(createOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        private fun createOkHttpClient(): OkHttpClient {

            val interceptor = HttpLoggingInterceptor()
            if (BuildConfig.DEBUG) {
                interceptor.level = HttpLoggingInterceptor.Level.BODY
            }
            val builder = OkHttpClient.Builder()
            builder.addNetworkInterceptor(interceptor)

            return OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

        }
    }
}