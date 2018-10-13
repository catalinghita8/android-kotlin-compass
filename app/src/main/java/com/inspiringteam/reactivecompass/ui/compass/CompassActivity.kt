package com.inspiringteam.reactivecompass.ui.compass

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import com.inspiringteam.reactivecompass.R
import com.inspiringteam.reactivecompass.utils.ActivityUtils
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class CompassActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var mInjectedFragment: CompassFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compass)

        // if permissions are granted, let's move on
        if (appHasPermissions()) launchFragment()
        else requestPermission()
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == 1) {
            //If permission is granted
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // if permissions are granted, let's move on
                launchFragment()
            } else requestPermission()
        }
    }

    // Basic method that launches specified fragment
    private fun launchFragment() {
        var fragment = supportFragmentManager.findFragmentById(R.id.contentFrame) as CompassFragment?
        if (fragment == null) {
            fragment = mInjectedFragment
            ActivityUtils.addFragmentToActivity(supportFragmentManager, fragment, R.id.contentFrame)
        }
    }

    private fun appHasPermissions(): Boolean {
        return checkFinePermission() && checkCoarsePermission()
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION), 1)
    }

    private fun checkFinePermission(): Boolean {
        val result = ContextCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION)

        return result == PackageManager.PERMISSION_GRANTED
    }

    private fun checkCoarsePermission(): Boolean {
        val result = ContextCompat.checkSelfPermission(this, ACCESS_COARSE_LOCATION)

        return result == PackageManager.PERMISSION_GRANTED
    }
}
