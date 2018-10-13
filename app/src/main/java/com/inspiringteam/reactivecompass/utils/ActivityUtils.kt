package com.inspiringteam.reactivecompass.utils

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

class ActivityUtils {
    /**
     * The fragment is added to the container view with id frameId. The operation is
     * performed by the fragmentManager.
     */
    companion object {
        fun addFragmentToActivity(fragmentManager: FragmentManager,
                                  fragment: Fragment, frameId: Int) {
            checkNotNull(fragmentManager)
            checkNotNull(fragment)
            val transaction = fragmentManager.beginTransaction()
            transaction.add(frameId, fragment)
            transaction.commit()
        }
    }
}