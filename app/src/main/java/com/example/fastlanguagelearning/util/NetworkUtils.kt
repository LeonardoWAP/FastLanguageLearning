package com.example.fastlanguagelearning.util

import android.content.Context
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkUtils {
    companion object {
        private const val CACHE_SIZE_BYTES = 10 * 1024 * 1024 // 10 MB
        fun getRetrofitInstance( path: String, context: Context) : Retrofit{
            val cache = Cache(context.cacheDir, CACHE_SIZE_BYTES.toLong())

            val okHttpClient = OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor { chain ->
                    val request: Request = chain.request().newBuilder()
                        .header("Cache-Control", "public, max-age=86400") //Cache from 86400 sec (24 hrs)
                        .build()
                    chain.proceed(request)
                }
                .build()

            return Retrofit.Builder()
                .baseUrl(path)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}