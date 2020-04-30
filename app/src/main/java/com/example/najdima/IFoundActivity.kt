package com.example.najdima

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.najdima.nextversionsactivities.Pet
import com.google.android.material.navigation.NavigationView
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_i_found.*

class IFoundActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_i_found)

        val activityType: ActivityType = intent.getSerializableExtra("activityType") as ActivityType
        val activityName: TextView = findViewById(R.id.activityName)
        activityName.text = if (activityType == ActivityType.FOUND )
            resources.getString(R.string.menu_i_found)
        else
            resources.getString(R.string.menu_i_lost)

        val sizes = arrayOf(
            resources.getString(R.string.size_small),
            resources.getString(R.string.size_middle),
            resources.getString(R.string.size_big))
        val sex = arrayOf(
            resources.getString(R.string.sex_female),
            resources.getString(R.string.sex_male))

        val btnMenu: ImageButton = findViewById(R.id.btn_menu)
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        navView.setNavigationItemSelectedListener(this)

        btnMenu.setOnClickListener {
            drawerLayout.openDrawer(Gravity.LEFT);
        }

        val at_size: AutoCompleteTextView = findViewById(R.id.at_size)
        val sizeAdapter: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_1, sizes)
        at_size.setAdapter(sizeAdapter)
        at_size.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                at_size.showDropDown()
                return v?.onTouchEvent(event) ?: false
            }
        })

        val at_sex: AutoCompleteTextView = findViewById(R.id.at_sex)
        val sexAdapter: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_1, sex)
        at_sex.setAdapter(sexAdapter)
        at_sex.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                at_sex.showDropDown()
                return v?.onTouchEvent(event) ?: false
            }
        })

        val photos: ImageView = findViewById(R.id.photos)
        val btn_addPhoto: ImageView = findViewById(R.id.btn_add_photo)
        btn_addPhoto.setOnClickListener {
            //check runtime permission
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_DENIED){
                    //permission denied
                    val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE);
                    //show popup to request runtime permission
                    requestPermissions(permissions, PERMISSION_CODE);
                }
                else{
                    //permission already granted
                    pickImageFromGallery();
                }
            }
            else{
                //system OS is < Marshmallow
                pickImageFromGallery();
            }
        }

        val txtAnimalSpecies: EditText = findViewById(R.id.et_animalSpecies)
        val txtBreed: EditText = findViewById(R.id.et_breed)
        val txtSize: AutoCompleteTextView = findViewById(R.id.at_size)
        val txtSex: AutoCompleteTextView = findViewById(R.id.at_sex)
        val txtCity: EditText = findViewById(R.id.et_city)
        val txtMoreDetails: EditText = findViewById(R.id.et_info)

        val btn_ok: ImageButton = findViewById(R.id.btn_ok)
        btn_ok.setOnClickListener {
            val animalSpecies = txtAnimalSpecies.text.toString()
            val breed = txtBreed.text.toString()
            val size = txtSize.text.toString()
            val sex = txtSex.text.toString()
            val city = txtCity.text.toString()
            val moreDetails = txtMoreDetails.text.toString()
            val pet = Pet(
                animalSpecies = animalSpecies,
                breed = breed,
                size = size,
                sex = sex,
                city = city,
                moreDetails = moreDetails
            )
            val firebaseDatabase = FirebaseDatabase.getInstance()
            val databaseReference = firebaseDatabase.reference
            databaseReference.child("pet").push().setValue(pet)
        }
    }

    private fun pickImageFromGallery() {
        //Intent to pick image
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    companion object {
        //image pick code
        private val IMAGE_PICK_CODE = 1000;
        //Permission code
        private val PERMISSION_CODE = 1001;
    }

    //handle requested permission result
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            PERMISSION_CODE -> {
                if (grantResults.size > 0 && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED){
                    //permission from popup granted
                    pickImageFromGallery()
                }
                else{
                    //permission from popup denied
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    //handle result of picked image
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE){
            photos.setImageURI(data?.data)
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
