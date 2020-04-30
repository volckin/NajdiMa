package com.example.najdima

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val btnMenu: ImageButton = findViewById(R.id.btn_menu)
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        navView.setNavigationItemSelectedListener(this)


        btnMenu.setOnClickListener {
            drawerLayout.openDrawer(Gravity.LEFT)
            println("MENU BUTTON")
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val i = Intent()
        when (item.itemId) {
            R.id.nav_home -> {
                println("HOME MENU BUTTON")
                Toast.makeText(this, "HOME", Toast.LENGTH_SHORT).show()
                i.setClass(this, HomeActivity::class.java)
                startActivity(i)
            }
            R.id.nav_i_found -> {
                println("I FOUND MENU BUTTON")
                Toast.makeText(this, "I FOUND", Toast.LENGTH_SHORT).show()
                i.setClass(this, IFoundActivity::class.java)
                i.putExtra("activityType", ActivityType.FOUND)
                startActivity(i)
            }
            R.id.nav_i_lost -> {
                Toast.makeText(this, "I LOST", Toast.LENGTH_SHORT).show()
                i.setClass(this, IFoundActivity::class.java)
                i.putExtra("activityType", ActivityType.LOST)
                //startActivity(i)
            }
            R.id.nav_found -> {
                Toast.makeText(this, "FOUND", Toast.LENGTH_SHORT).show()
                //i.setClass(this, HomeActivity::class.java)
                //i.putExtra("activityType", ActivityType.FOUND)
                //startActivity(i)
            }
            R.id.nav_lost -> {
                Toast.makeText(this, "LOST", Toast.LENGTH_SHORT).show()
                //i.setClass(this, HomeActivity::class.java)
                //i.putExtra("activityType", ActivityType.LOST)
                //startActivity(i)
            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        Toast.makeText(this, "ITEM SELECTED", Toast.LENGTH_SHORT).show()
        return true
    }

}

enum class ActivityType {
    FOUND, LOST
}

