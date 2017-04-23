package com.example.forever.weather;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Forever on 4/21/2017.
 */

public class Location {
    private static final String PREFERENCE_NAME = "Location";
    private static final String CITYNAME = "CityName";
    private static final String LAT = "Latitude";
    private static final String LNG = "Longitude";
    private static final String MESSAGE = "";
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Context context;

    public Location(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();

    }



    public void addLocation(String latitude,String longitude,String cityname){
        editor.putString(LNG,longitude);
        editor.putString(LAT,latitude);
        editor.putString(CITYNAME,cityname);
        editor.commit();
    }

    public String getCity(){
        return preferences.getString(CITYNAME,MESSAGE);

    }

    public String getLatitude(){
        return preferences.getString(LAT,MESSAGE);

    }
    public String getLongitude(){
        return preferences.getString(LNG,MESSAGE);

    }

    public void clearLocation(){
        editor.clear();
        editor.commit();
    }
}
