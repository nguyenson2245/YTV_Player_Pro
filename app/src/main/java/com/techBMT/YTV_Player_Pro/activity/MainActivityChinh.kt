package com.techBMT.YTV_Player_Pro.activity

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.techBMT.YTV_Player_Pro.R
import com.techBMT.YTV_Player_Pro.databinding.ActivityMainChinhBinding
import com.techBMT.YTV_Player_Pro.fragment.Contact_Fragment
import com.techBMT.YTV_Player_Pro.fragment.Home_Fragment
import com.techBMT.YTV_Player_Pro.fragment.Privacy_Fragment
import com.techBMT.YTV_Player_Pro.fragment.Rate_Fragment

class MainActivityChinh : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var binding: ActivityMainChinhBinding
    private var backPressedTime: Long = 0
    private var toatm: Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainChinhBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ToolBar_()
        Navigationview_()
        binding.navigationView.setNavigationItemSelectedListener(this)
        binding.navigationView.menu.findItem(R.id.home).isChecked = true

        replaceFragment(Home_Fragment())
    }

    private fun Navigationview_() {
        val toggle = ActionBarDrawerToggle(
            this, binding.drawerLayout, binding.toolBar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        toggle.setHomeAsUpIndicator(R.drawable.navigation24)
        toggle.setDrawerIndicatorEnabled(true)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        binding.navigationView.setNavigationItemSelectedListener(this)
    }

    private fun ToolBar_() {
        setSupportActionBar(binding.toolBar)
        supportActionBar?.apply {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowTitleEnabled(false)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.outapp, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.outAPP -> {
                val intent = Intent(Intent.ACTION_MAIN)
                intent.addCategory(Intent.CATEGORY_HOME)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.home) {
            replaceFragment(Home_Fragment())
        } else if (id == R.id.rate_us) {
            replaceFragment(Rate_Fragment())
        } else if (id == R.id.shareApp) {
            shareApp()
        } else if (id == R.id.privacyPoloicy) {
            replaceFragment(Privacy_Fragment())
        } else if (id == R.id.contactUs) {
            replaceFragment(Contact_Fragment())
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        val backStackEntryCount = supportFragmentManager.backStackEntryCount
        Log.d("TAG", "replaceFragment: $backStackEntryCount")
        for (i in 1 until backStackEntryCount) {
            supportFragmentManager.popBackStack()
        }
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    private fun shareApp() {
        val packageName = packageName
        val message = "Try This App !"
        val shareIntent = ShareCompat.IntentBuilder.from(this)
            .setType("text/plain")
            .setText(message)
            .setChooserTitle("Share The Application")
            .createChooserIntent()
            .apply {
                val activities =
                    packageManager.queryIntentActivities(this, PackageManager.MATCH_DEFAULT_ONLY)
                for (activity in activities) {
                    val packageName = activity.activityInfo.packageName
                    grantUriPermission(
                        packageName,
                        null,
                        Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                    )
                }
            }

        startActivity(shareIntent)
    }

    override fun onBackPressed() {
        Log.d("TAG", "onBackPressed: ")
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            val backStackEntryCount = supportFragmentManager.backStackEntryCount
            Log.d("TAG", "onBackPressed:$backStackEntryCount ")
            if (backStackEntryCount >= 2) {
                super.onBackPressed()
            } else if (backPressedTime + 2000 > System.currentTimeMillis()) {
                toatm?.cancel()
                super.onBackPressed()
                finish()
                return
            } else {
                Toast.makeText(this, "Press back again to exit the application", Toast.LENGTH_SHORT)
                toatm?.show()
            }
            backPressedTime = System.currentTimeMillis()
        }

    }

}

