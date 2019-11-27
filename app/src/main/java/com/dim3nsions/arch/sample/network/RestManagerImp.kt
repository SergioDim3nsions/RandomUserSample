package com.dim3nsions.arch.sample.network

import android.content.Context
import android.util.Log
import com.dim3nsions.arch.sample.App
import com.dim3nsions.arch.sample.network.response.UserResponse
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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

    fun <T> request(type: Response<T>) {
        apiService.getUsers<T>().enqueue()

        apiService.getUsers().enqueue(object : Callback<T> {
            override fun onFailure(call: Call<T>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<T>, response: Response<T>) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }

    override fun getService(): ApiService {
        return apiService
    }
}