package com.dim3nsions.arch.sample.main.presenter

import com.dim3nsions.arch.sample.BasePresenter
import com.dim3nsions.arch.sample.core.IBackgroundManager
import com.dim3nsions.arch.sample.network.RestManager
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainPresenter : BasePresenter<IMainView>(), IMainPresenter {

    override fun getUsers() {


        IBackgroundManager.instance.execute()



        doAsync {
            val response = RestManager.instance.getService().getUsers().execute()
            uiThread {
                if (response.isSuccessful) {
                    val users = response.body()?.results

                    users?.let {
                        view?.loadUsers(users)
                    }
                } else {
                    view?.showError(response.message())
                }
            }
        }
    }
}