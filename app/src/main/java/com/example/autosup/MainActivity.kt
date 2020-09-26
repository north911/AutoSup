package com.example.autosup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.autosup.ui.main.MainFragment
import com.example.autosup.utils.OnBackPressed

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }

    override fun onBackPressed() {
        val fragment =
            this.supportFragmentManager.findFragmentById(R.id.container)
        (fragment as? OnBackPressed)?.onBackPressed()?.not()?.let {
            super.onBackPressed()
        }
    }
}