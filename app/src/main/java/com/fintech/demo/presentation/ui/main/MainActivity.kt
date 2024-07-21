package com.fintech.demo.presentation.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.fintech.demo.R
import com.fintech.demo.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private val mNavController by lazy {
        findNavController(R.id.nav_host_fragment_content_main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        mBinding.appBarMain.toolbar.visibility = View.GONE


        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        NavigationUI.setupWithNavController(bottomNavigationView, mNavController)

        mNavController.addOnDestinationChangedListener { _, destination, _ ->
            mBinding.appBarMain.fab.visibility =
                if (destination.id == R.id.camera || destination.id == R.id.transfer || destination.id == R.id.cards || destination.id == R.id.statistics) View.GONE else View.VISIBLE

            mBinding.appBarMain.fab.setOnClickListener {
                /* mBinding.navView.menu.performIdentifierAction(
                     if (destination.id == R.id.task_creation) R.id.task_listing
                     else R.id.task_creation, 0
                 )*/
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    /*override fun onSupportNavigateUp(): Boolean {
        return mNavController.navigateUp(mAppBarConfiguration) || super.onSupportNavigateUp()
    }*/
}