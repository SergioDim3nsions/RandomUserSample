package com.dim3nsions.arch.sample.main.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dim3nsions.arch.sample.R
import com.dim3nsions.arch.sample.network.RestManager
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.support.v4.runOnUiThread

class MainFragment : Fragment() {

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

        doAsync {
            val response = RestManager.instance.getService().getUsers().execute().body()
            runOnUiThread {
                response?.let {
                    Log.d("BODY", it.results[0].email)
                }
            }
        }
    }
}