package com.yvkalume.dcplus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.viewbinding.library.activity.viewBinding
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.yvkalume.dcplus.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by viewBinding()
    private val navController by lazy {
        Navigation.findNavController(this,R.id.fragment)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpNavigation()

    }

    private fun setUpNavigation() {
        binding.bottomNavigationView.let {
            NavigationUI.setupWithNavController(it,navController)
        }
    }
}