package com.inspiringteam.reactivecompass.utils

import com.inspiringteam.reactivecompass.data.models.GeoPosition

object Constants {
    var LOCATION_DATA_SOURCE = 0
    var COMPASS_DATA_SOURCE = 1
    var REMOTE_DATA_SOURCE = 2

    var DESTINATION_API_BASE_URL = "http://transferx.ddns.net:3000/"

    var SAMPLE_GEOPOSITION = GeoPosition(44.345116, 25.959433)
    var SAMPLE_GEOPOSITION2 = GeoPosition(4.345839, 2.959957)
}
