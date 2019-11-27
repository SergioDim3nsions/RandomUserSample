package com.dim3nsions.arch.sample.main.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dim3nsions.arch.sample.R
import com.dim3nsions.arch.sample.main.presenter.IMainView
import com.dim3nsions.arch.sample.main.presenter.MainPresenter
import com.dim3nsions.arch.sample.model.User

class MainFragment : Fragment(), IMainView {

    lateinit var presenter: MainPresenter

    companion object {
        const val FRAGMENT_TAG = "MainFragment"

        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = MainPresenter()
        presenter.onCreate(this)
        presenter.getUsers()
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun loadUsers(users: List<User>) {
        Log.d("MainFragment", users[0].email)
    }

    override fun showError(message: String) {
        Log.d("MainFragment", message)
    }
}