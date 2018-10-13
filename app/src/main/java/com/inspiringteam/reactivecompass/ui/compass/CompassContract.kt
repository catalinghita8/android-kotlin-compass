package com.inspiringteam.reactivecompass.ui.compass

import com.inspiringteam.reactivecompass.BaseView
import com.inspiringteam.reactivecompass.ui.compass.models.DirectionsUiModel
import com.inspiringteam.reactivecompass.ui.compass.models.LocationUiModel

interface CompassContract {
    interface View : BaseView {
        fun updateDirections(directionsUiModel: DirectionsUiModel)

        fun updateCurrentLocation(locationUiModel: LocationUiModel)

        fun updateDestinationLocation(locationUiModel: LocationUiModel)

        fun goToUpdateDestinationPage()

        fun showOnErrorGettingDirections()

        fun showOnErrorGettingLocation()
    }

    interface ViewModel
}
