package com.example.najdima

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.view.GravityCompat

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        //val toolbar: Toolbar = findViewById(R.id.toolbar)
        //setSupportActionBar(toolbar)

        val btnMenu: ImageButton = findViewById(R.id.btn_menu)
        //val btnCloseMenu: ImageButton? = findViewById(R.id.btn_close_menu)
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        appBarConfiguration = AppBarConfiguration(
            topLevelDestinationIds = setOf(
                R.id.nav_home, R.id.nav_i_found, R.id.nav_i_lost,
                R.id.nav_found, R.id.nav_lost
            ), drawerLayout = drawerLayout
        )

        btnMenu.setOnClickListener {
            drawerLayout.openDrawer(Gravity.LEFT);
        }

        navView.setNavigationItemSelectedListener(this)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val i = Intent()
        when (item.itemId) {
            R.id.nav_home -> {
                Toast.makeText(this, "HOME", Toast.LENGTH_SHORT).show()
                i.setClass(this, HomeActivity::class.java)
                startActivity(i)
            }
            R.id.nav_i_found -> {
                Toast.makeText(this, "I FOUND", Toast.LENGTH_SHORT).show()
                //i.setClass(this, IFoundActivity::class.java)
                startActivity(i)
            }
            R.id.nav_i_lost -> {
                i.setClass(this, HomeActivity::class.java)
                startActivity(i)
            }
            R.id.nav_found -> {
                i.setClass(this, HomeActivity::class.java)
                startActivity(i)
            }
            R.id.nav_lost -> {
                i.setClass(this, HomeActivity::class.java)
                startActivity(i)
            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

}
