package com.dim3nsions.arch.sample.network.response

import com.google.gson.annotations.SerializedName

const val RESULTS_PARAM = "results"

abstract class BaseResponse<T> {

    @SerializedName(RESULTS_PARAM)
    val results: List<T> = mutableListOf()
}