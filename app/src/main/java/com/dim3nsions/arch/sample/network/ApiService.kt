package com.dim3nsions.arch.sample.network

import com.dim3nsions.arch.sample.network.response.UserResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("/api/?page=3&results=10&seed=abc")
    fun getUsers(): Call<UserResponse>
}