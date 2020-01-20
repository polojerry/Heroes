package com.polotechnologies.heroes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.polotechnologies.heroes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setUpBottomNav()
    }

    private fun setUpBottomNav() {
        val navController  = findNavController(R.id.nav_host_main)
        NavigationUI.setupWithNavController(mainBinding.bottomNavMain,navController)
    }
}
