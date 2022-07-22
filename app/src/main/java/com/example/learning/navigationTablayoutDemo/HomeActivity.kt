package com.example.learning.navigationTablayoutDemo

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.example.learning.R
import com.example.learning.databinding.ActivityHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView


class HomeActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding : ActivityHomeBinding
    private lateinit var appBarConfiguration :AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_home)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.container_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        findViewById<BottomNavigationView>(R.id.bottomNav).setupWithNavController(navController)
        findViewById<NavigationView>(R.id.navView).setupWithNavController(navController)
        appBarConfiguration=AppBarConfiguration(topLevelDestinationIds = setOf(
            R.id.homeFragment,
            R.id.scanImageFragment,
            R.id.whereYouTravellingFragment,
            R.id.notificationFragment,
            R.id.profileFragment
        ),binding.drawerLayout)

        setSupportActionBar(binding.toolbar)

        //Displays hamburger icon(drawer icon) when in top-level destination
                //Display up icon when not in top-level destination
                //Update title text based on the destination’s label
        setupActionBarWithNavController(navController,appBarConfiguration)
        binding.navView.setNavigationItemSelectedListener(this)

        navController.addOnDestinationChangedListener {_,destination,_ ->
            if(destination.id==R.id.aboutFragment || destination.id== R.id.settingFragment)
                binding.bottomNav.visibility=View.GONE
            else
                binding.bottomNav.visibility=View.VISIBLE
        }
    }

    //to handle hamburger click
    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.container_fragment).navigateUp(appBarConfiguration)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.share->{
                var share = Intent(Intent.ACTION_SEND)
                share.type="text/plain"
                share.putExtra(Intent.EXTRA_TEXT,"testing sharing")
                startActivity(Intent.createChooser(share,"heading"))
                binding.drawerLayout.close()
            }
            R.id.aboutFragment->{
                findNavController(R.id.container_fragment).navigate(R.id.aboutFragment)
                binding.drawerLayout.close()
            }
            R.id.settingFragment->{
                findNavController(R.id.container_fragment).navigate(R.id.settingFragment)
                binding.drawerLayout.close()
            }
        }
        return true
    }
}