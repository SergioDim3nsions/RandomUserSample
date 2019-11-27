package com.dim3nsions.arch.sample.network.interactor

import com.dim3nsions.arch.sample.network.ApiService

class GetUserInteractor(private val service: ApiService) : BaseInteractor {

    override fun doRequest() {
        val response = service.getUsers().execute()
        if (response.isSuccessful) {

        } else {

        }
    }


}