package com.gb.movieapp.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.gb.movieapp.R
import com.gb.movieapp.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.getRoot()
        setContentView(view)

        val toolbar: androidx.appcompat.widget.Toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        initDrawer(toolbar)

        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {
                    openFragment(HomeFragment.newInstance())
                    true
                }
                R.id.nav_favorites -> {
                    openFragment(FavoritesFragment.newInstance())
                    true
                }
                R.id.nav_ratings -> {
                    openFragment(RatingsFragment.newInstance())
                    true
                }
                else -> false
            }
        }

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_fragment_holder, HomeFragment.newInstance())
                .commitNow()
        }


    }

    private fun initDrawer(toolbar: androidx.appcompat.widget.Toolbar) {
        // Находим drawer
        val drawer = binding.drawerLayout

        // Создаем ActionBarDrawerToggle
        val toggle = ActionBarDrawerToggle(
            this,
            drawer,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        // Обработка навигационного меню
        val navigationView: NavigationView = binding.navigationView
        navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.drawer_menu_home -> {
                    openFragment(HomeFragment.newInstance())
                    drawer.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.drawer_menu_favorites -> {
                    openFragment(FavoritesFragment.newInstance())
                    drawer.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.drawer_menu_ratings -> {
                    openFragment(RatingsFragment.newInstance())
                    drawer.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.drawer_menu_about -> {
                    Toast.makeText(this, "Menu About clicked", Toast.LENGTH_SHORT).show()
                    drawer.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.drawer_menu_search -> {
                    Toast.makeText(this, "Menu Search clicked", Toast.LENGTH_SHORT).show()
                    drawer.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.drawer_menu_details -> {
                    Toast.makeText(this, "Menu Details clicked", Toast.LENGTH_SHORT).show()
                    drawer.closeDrawer(GravityCompat.START)
                    true
                }
                else -> false
            }
        }
    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_fragment_holder, fragment)
            .addToBackStack("")
            .commit()
    }
    
}