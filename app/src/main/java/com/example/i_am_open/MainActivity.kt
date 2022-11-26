package com.example.i_am_open

//import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.i_am_open.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        //var toolbar =

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


            //val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
            //val navController = findNavController(R.id.fragmentContainerView)
            //bottomNavigationView.setupWithNavController(navController)

            /*
//        teachers way
        val builder = AppBarConfiguration.Builder(navController.graph)
        val appBarConfiguration = builder.build()
        bottomNavigationView.setupWithNavController(navController)
         */

            //val appBarConfiguration = AppBarConfiguration(setOf(R.id.homeFragment, R.id.companyFragment, R.id.favouriteFragment, R.id.profileFragment))
            //setupActionBarWithNavController(navController, appBarConfiguration)
        }

//    teacher's way uncomment teachers way in main function to use this
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.bottom_menu, menu)
//        return super.onCreateOptionsMenu(menu)
//    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        val navController = findNavController(R.id.fragmentContainerView)
//        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
//    }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
