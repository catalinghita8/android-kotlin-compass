package com.inspiringteam.reactivecompass.utils;

import com.inspiringteam.reactivecompass.data.models.GeoPosition;

public class Constants {
    public static int LOCATION_DATA_SOURCE = 0;
    public static int COMPASS_DATA_SOURCE = 1;
    public static int REMOTE_DATA_SOURCE = 2;

    public static String DESTINATION_API_BASE_URL = "http://transferx.ddns.net:3000/";

    public static GeoPosition SAMPLE_GEOPOSITION = new GeoPosition(44.345116, 25.959433);
    public static GeoPosition SAMPLE_GEOPOSITION2 = new GeoPosition(4.345839, 2.959957);
}
