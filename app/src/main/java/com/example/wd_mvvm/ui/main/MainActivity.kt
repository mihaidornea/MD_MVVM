package com.example.wd_mvvm.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.wd_mvvm.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadFragment()
    }

    private fun loadFragment(){
        val fragment = MainFragment()
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.act_main_fl_content, fragment)
        fragmentTransaction.commit()
    }
}
