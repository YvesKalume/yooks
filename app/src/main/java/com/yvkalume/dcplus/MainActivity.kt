package com.yvkalume.dcplus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.viewbinding.library.activity.viewBinding
import androidx.core.view.isVisible
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.yvkalume.dcplus.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by viewBinding()
    private val navController by lazy {
        Navigation.findNavController(this,R.id.fragment)
    }
    private val bottomNav by lazy { binding.bottomNavigationView }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpNavigation()

    }



    private fun setUpNavigation() {

        NavigationUI.setupWithNavController(bottomNav,navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            bottomNav.isVisible = when(destination.id) {
                R.id.previewFragment -> false
                else -> true
            }
        }

    }
}