package com.dim3nsions.arch.sample.main.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dim3nsions.arch.sample.R
import com.dim3nsions.arch.sample.main.fragment.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragmentManager = supportFragmentManager
        val ft = fragmentManager.beginTransaction()
        fragmentManager.findFragmentById(R.id.fragment_container) as MainFragment?
            ?: MainFragment.newInstance().also {
                ft.replace(R.id.fragment_container, it,
                    MainFragment.FRAGMENT_TAG
                ).commit()
            }
    }
}
