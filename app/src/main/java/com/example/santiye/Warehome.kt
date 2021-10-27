package com.example.santiye

import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.santiye.adapter.OperatorRecyclerView
import com.example.santiye.databinding.ActivityWarehomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Warehome : AppCompatActivity() {

    private lateinit var binding: ActivityWarehomeBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWarehomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth

        val navView: BottomNavigationView = binding.navViewWarehome
        val navController = findNavController(R.id.nav_host_fragment_activity_warehome)
        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.navigation_product_arrangement,R.id.navigation_request,R.id.navigation_products)
        )

        setupActionBarWithNavController(navController,appBarConfiguration)
        navView.setupWithNavController(navController)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.ware_home, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.warehome_profile) {
            auth.signOut()
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
            finish()
        }

        return super.onOptionsItemSelected(item)
    }
}