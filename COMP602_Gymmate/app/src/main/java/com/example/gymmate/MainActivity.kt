package com.example.gymmate

import android.content.Intent
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.gymmate.databinding.ActivityMainBinding
import com.example.gymmate.ui.questionpage.Calories

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setOnItemSelectedListener { item ->
            when (item.itemId) {
//                R.id.navigation_home -> {
//                    // Handle Home fragment or activity navigation
//                    // For example, navigate to a HomeFragment
//                    navController.navigate(R.id.navigation_home_fragment)
//                }
                R.id.navigation_dashboard -> {
                    // Handle Dashboard (Calories activity) navigation
                    // Start the Calories activity
                    val intent = Intent(this@MainActivity, Calories::class.java)
                    startActivity(intent)
                }
//                R.id.navigation_notifications -> {
//                    // Handle Notifications fragment or activity navigation
//                    // For example, navigate to a NotificationsFragment
//                    navController.navigate(R.id.navigation_notifications_fragment)
//                }
            }
            true
        }
    }
}