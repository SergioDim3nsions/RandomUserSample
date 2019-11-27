package com.dim3nsions.arch.sample

abstract class BasePresenter<V : View> : Presenter<V> {

    protected var view: V? = null

    override fun onCreate(view: V) {
        this.view = view
    }

    override fun onResume(view: V) {
        this.view = view
    }

    override fun onDestroy() {
        this.view = null
    }

}