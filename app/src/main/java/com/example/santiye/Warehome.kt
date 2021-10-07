package com.example.santiye

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.santiye.databinding.ActivityWarehomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class Warehome : AppCompatActivity() {

    private lateinit var binding: ActivityWarehomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWarehomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navViewWarehome
        val navController = findNavController(R.id.nav_host_fragment_activity_warehome)
        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.navigation_product_arrangement,R.id.navigation_request,R.id.navigation_products)
        )

        setupActionBarWithNavController(navController,appBarConfiguration)
        navView.setupWithNavController(navController)


    }
}