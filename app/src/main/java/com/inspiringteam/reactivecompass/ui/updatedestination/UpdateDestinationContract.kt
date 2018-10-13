package com.inspiringteam.reactivecompass.ui.updatedestination

import com.inspiringteam.reactivecompass.BaseView
import com.inspiringteam.reactivecompass.ui.compass.models.DirectionsUiModel
import com.inspiringteam.reactivecompass.ui.compass.models.LocationUiModel

interface UpdateDestinationContract {
    interface View : BaseView {
        fun updateDestinationLocation(latText: String, longText: String)

        fun goToUpdateDestinationPage()

    }
}