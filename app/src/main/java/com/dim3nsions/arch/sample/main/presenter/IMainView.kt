package com.dim3nsions.arch.sample.main.presenter

import com.dim3nsions.arch.sample.View
import com.dim3nsions.arch.sample.model.User

interface IMainView : View {
    fun loadUsers(users: List<User> = mutableListOf())
    fun showError(message: String)
}