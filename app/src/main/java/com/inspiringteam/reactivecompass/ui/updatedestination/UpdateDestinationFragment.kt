package com.inspiringteam.reactivecompass.ui.updatedestination


import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Pair
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.inspiringteam.reactivecompass.R
import com.inspiringteam.reactivecompass.data.models.GeoPosition
import com.inspiringteam.reactivecompass.ui.compass.CompassViewModel


import dagger.android.support.DaggerFragment
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_update_destination.*
import javax.inject.Inject


class UpdateDestinationFragment @Inject constructor() : DaggerFragment(), UpdateDestinationContract.View {
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
        return inflater.inflate(R.layout.fragment_update_destination, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonSave.setOnClickListener{
            val latText = editLat.text.toString()
            val longText = editLong.text.toString()
            if (latText.isEmpty() || longText.isEmpty()) showInputError()
            else {
                // Update destination in repository
                updateDestinationLocation(latText, longText)

                // Get back to the main screen
                goToUpdateDestinationPage()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        bindViewModel()
    }

    override fun onPause() {
        super.onPause()
        unbindViewModel()
    }

    override fun updateDestinationLocation(latText: String, longText: String) {
        viewModel?.setDestinationLocation(GeoPosition(java.lang.Double.parseDouble(latText),
                java.lang.Double.parseDouble(longText)))
    }

    override fun goToUpdateDestinationPage() {
        activity?.finish()
    }

    override fun bindViewModel() {

    }

    override fun unbindViewModel() {

    }

    private fun showInputError() {
        Toast.makeText(activity, getString(R.string.error_on_input), Toast.LENGTH_LONG).show()
    }
}
