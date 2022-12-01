package com.mte.fitnessapp

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.mte.fitnessapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    lateinit var bottomNav : NavigationBarView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

        loadFragment(WorkoutFragment())

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.workoutpage -> {
                    loadFragment(WorkoutFragment())
                    true
                }
                R.id.guidepage -> {
                    loadFragment(GuideFragment())
                    true
                }
                R.id.socialpage -> {
                    loadFragment(SocialFragment())
                    true
                }
                R.id.utilitypage -> {
                    loadFragment(UtilityFragment())
                    true
                }
                R.id.profilepage -> {
                    loadFragment(ProfileFragment())
                    true
                }
                else -> {
                    false
                }
            }
        }

    }

    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}