package com.example.i_am_open

//import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.i_am_open.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Back Button Navigation
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

//        navHostFragment = supportFragmentManager.findFragmentById(binding.fragmentContainerView.id) as NavHostFragment
//        navController = navHostFragment.findNavController()

        //Bottom navigation
        navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        setupWithNavController(bottomNavigationView, navController)
        setupActionBarWithNavController(navController)

        //Showing bottom navigation only for the main screens, and hiding for details screens
        navController.addOnDestinationChangedListener { _, nd: NavDestination, _ ->
            if (nd.id == R.id.homeFragment || nd.id == R.id.favouriteFragment || nd.id == R.id.profileFragment) {
                bottomNavigationView.visibility = View.VISIBLE
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
            }
//            else if (nd.id == R.id.companyFragment){
//                bottomNavigationView.visibility = View.VISIBLE
//                supportActionBar?.setDisplayHomeAsUpEnabled(true)
//            }
            else {
                bottomNavigationView.visibility = View.GONE
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
