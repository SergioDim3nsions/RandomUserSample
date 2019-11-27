package com.dim3nsions.arch.sample

interface Presenter<V : View> {

    fun onCreate(view: V)
    fun onResume(view: V)
    fun onDestroy()
}