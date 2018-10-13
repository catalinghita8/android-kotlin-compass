package com.inspiringteam.reactivecompass.ui.compass

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.Toast
import com.inspiringteam.reactivecompass.R
import com.inspiringteam.reactivecompass.di.scopes.ActivityScoped
import com.inspiringteam.reactivecompass.ui.compass.models.DirectionsUiModel
import com.inspiringteam.reactivecompass.ui.compass.models.LocationUiModel
import com.inspiringteam.reactivecompass.ui.updatedestination.UpdateDestinationActivity
import com.inspiringteam.reactivecompass.utils.FormatUtils
import dagger.android.support.DaggerFragment
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_compass.*
import javax.inject.Inject


@ActivityScoped
class CompassFragment @Inject constructor() : DaggerFragment(), CompassContract.View {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var viewModel: CompassViewModel? = null

    private var mSubscription = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // get ViewModel
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(CompassViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_compass, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonEnterDestination.setOnClickListener{ goToUpdateDestinationPage() }
    }

    override fun onResume() {
        super.onResume()
        bindViewModel()
    }

    override fun onPause() {
        super.onPause()
        unbindViewModel()
    }

    override fun bindViewModel() {
        // Using a CompositeDisposable to gather all the subscriptions, so all of them can be
        // later unsubscribed together
        mSubscription = CompositeDisposable()

        // collect subscriptions
        mSubscription.add(viewModel?.getCompassUiModel()
                ?.subscribe(
                        // onNext
                        { uiModel -> this.updateDirections(uiModel) },
                        // onError
                        { showOnErrorGettingDirections() })!!)

        mSubscription.add(viewModel?.getCurrentLocationUiModel()
                ?.subscribe(
                        // onNext
                        { uiModel -> this.updateCurrentLocation(uiModel) },
                        // onError
                        { showOnErrorGettingLocation() })!!)

        mSubscription.add(viewModel?.getDestinationLocationUiModel()
                ?.subscribe(
                        // onNext
                        { uiModel -> this.updateDestinationLocation(uiModel) },
                        // onError
                        { showOnErrorGettingLocation() })!!)
    }

    override fun unbindViewModel() {
        // dump subscriptions
        mSubscription.clear()
    }

    override fun updateDirections(directionsUiModel: DirectionsUiModel) {
        val cO = directionsUiModel.compassOrientation

        // adjust both arrows
        adjustArrow(cO.polesDirection, cO.lastPolesDirection, imageMainHands)
        adjustArrow(cO.destinationDirection, cO.lastDestinationDirection, imageMainDirection)
    }

    override fun updateCurrentLocation(locationUiModel: LocationUiModel) {
        val currentPosition = locationUiModel.location

        textCurrentLat.text = FormatUtils.getStringFromDouble(currentPosition.latitude)
        textCurrentLong.text = FormatUtils.getStringFromDouble(currentPosition.longtitude)

    }

    override fun updateDestinationLocation(locationUiModel: LocationUiModel) {
        val currentDestination = locationUiModel.location

        textDestinationLat.text = FormatUtils.getStringFromDouble(currentDestination.latitude)
        textDestinationLong.text = FormatUtils.getStringFromDouble(currentDestination.longtitude)

    }

    override fun showOnErrorGettingDirections() {
        showErrorMessage("Error loading directions")
    }

    override fun showOnErrorGettingLocation() {
        showErrorMessage("Error loading location")
    }

    override fun goToUpdateDestinationPage() {
        val intent = Intent(context, UpdateDestinationActivity::class.java)
        startActivity(intent)
    }

    /**
     * Internal helper methods
     */
    private fun adjustArrow(azimuth: Float, currentAzimuth: Float, targetView: View) {
        val an = RotateAnimation(-currentAzimuth, -azimuth,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f)

        an.duration = 500
        an.repeatCount = 0
        an.fillAfter = true

        targetView.startAnimation(an)
    }

    private fun showErrorMessage(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

}
