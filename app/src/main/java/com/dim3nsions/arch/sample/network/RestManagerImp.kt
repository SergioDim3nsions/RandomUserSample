package com.dim3nsions.arch.sample.network

import android.content.Context
import android.util.Log
import com.dim3nsions.arch.sample.App
import okhttp3.Cache
import okhttp3.OkHttpClient
import org.jetbrains.anko.doAsync
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

interface RestManager {

    fun getService(): ApiService

    companion object {
        val instance: RestManager by lazy {
            RestManagerImp()
        }
    }
}

class RestManagerImp(context: Context = App.instance) : RestManager {

    private val apiService: ApiService

    init {
        Log.d("RestManager", "INIT")
        val cache = Cache(context.cacheDir, 10 * 1024 * 1024)

        val okHttpClient = OkHttpClient.Builder()
            .cache(cache)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://randomuser.me/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)
    }

    override fun getService(): ApiService {
        return apiService
    }
}