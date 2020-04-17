package com.ironelder.withinno.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.ironelder.withinno.R

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val navController: NavController by lazy {
        findNavController(R.id.nav_host_fragment)
    }

    override fun onSupportNavigateUp() = navController.navigateUp()

}
