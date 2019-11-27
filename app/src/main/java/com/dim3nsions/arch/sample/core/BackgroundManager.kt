package com.dim3nsions.arch.sample.core

import android.os.Handler
import android.os.Looper
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

interface IBackgroundManager {

    companion object {
        val instance: IBackgroundManager by lazy {
            BackgroundManager()
        }
    }

    fun start()
    fun stop()
    fun execute()
}

class BackgroundManager : IBackgroundManager {

    private var thredPoolExecutor: ExecutorService? = null
    private var listener: BackgroundManagerListener? = null

    init {
        start()
    }

    override fun start() {
        thredPoolExecutor = Executors.newFixedThreadPool(10)
    }

    override fun stop() {
        thredPoolExecutor?.let {
            if (!it.isShutdown) {
                it.shutdown()
            }
        }

        thredPoolExecutor = null
    }

    override fun execute() {
        thredPoolExecutor?.let {
            it.execute {
                listener?.execute()
                val handler = Handler(Looper.getMainLooper())
                handler.post {
                    listener?.postExecute()
                }
            }
        }
    }

    fun setBackgroundManagerListener(listener: BackgroundManagerListener) {
        this.listener = listener
    }

    interface BackgroundManagerListener {
        fun execute()
        fun postExecute()
    }
}