package com.inspiringteam.reactivecompass.ui.updatedestination

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.inspiringteam.reactivecompass.R
import com.inspiringteam.reactivecompass.utils.ActivityUtils
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

/**
 * Secondary light-weight Activity that acts as container to the Update Destination Screen
 */
class UpdateDestinationActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var mInjectedFragment: UpdateDestinationFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_destination)

        var fragment = supportFragmentManager.findFragmentById(R.id.contentFrame) as UpdateDestinationFragment?
        if (fragment == null) {
            fragment = mInjectedFragment
            ActivityUtils.addFragmentToActivity(supportFragmentManager, fragment, R.id.contentFrame)
        }
    }
}
