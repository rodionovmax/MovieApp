package com.gb.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class MainActivity : AppCompatActivity(), IFragments {

    private val carFragment = CarFragment()
    private val mainFragment = MainFragment()
    private val fruitsFragment = ObjectFragment()
    private val loopsFragment = LoopsFragment()
    private val fm: FragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Start Main Fragment when the app starts
        if (savedInstanceState == null) {
            fm
                .beginTransaction()
                .replace(R.id.fragment_holder, mainFragment)
                .commit()
        }

    }

    private fun replaceFragment(fragment : Fragment) {
        fm
            .beginTransaction()
            .replace(R.id.fragment_holder, fragment)
            .addToBackStack("")
            .commit()
    }

    override fun openCarFragment() {
        replaceFragment(carFragment)
    }

    override fun openMainFragment() {
        replaceFragment(mainFragment)
    }

    override fun openObjectFragment() {
        replaceFragment(fruitsFragment)
    }

    override fun openLoopsFragment() {
        replaceFragment(loopsFragment)
    }


}

interface IFragments {
    fun openCarFragment()
    fun openMainFragment()
    fun openObjectFragment()
    fun openLoopsFragment()
}