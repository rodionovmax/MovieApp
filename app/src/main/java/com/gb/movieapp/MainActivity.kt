package com.gb.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnStart = findViewById<Button>(R.id.cta_button)
        val txtView = findViewById<TextView>(R.id.text_view)
        val btnReset = findViewById<Button>(R.id.reset_button)

        btnStart.setOnClickListener {
            txtView.text = "Let's get the party started!"
        }

        btnReset.setOnClickListener {
            txtView.text = ""
        }

        val carFragment = CarFragment()
        val fm: FragmentManager = supportFragmentManager
        fm
            .beginTransaction()
            .add(R.id.fragment_holder, carFragment)
            .addToBackStack(null)
            .commit()
    }


}