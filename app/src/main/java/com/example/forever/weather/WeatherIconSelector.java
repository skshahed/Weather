package com.example.forever.weather;

/**
 * Created by Forever on 4/22/2017.
 */

public class WeatherIconSelector {

    public int getIconName(String weatherCode, boolean day){
        int code = Integer.parseInt(weatherCode);


        if (day){
            if (code >= 200 && code <= 300){
                return R.drawable.elevend;
            }else if (code >= 300 && code <= 321){
                return R.drawable.nined;
            }else if (code >= 500 && code <= 504){
                return R.drawable.tend;
            }else if (code == 511){
                return R.drawable.thirtind;
            }else if (code >= 520 && code <= 531){
                return R.drawable.nined;
            }else if (code >= 600 && code <= 622){
                return R.drawable.thirtind;
            }else if (code >= 701 && code <= 781){
                return R.drawable.fiftid;
            }else if (code == 800){
                return R.drawable.oned;
            }else if (code == 801){
                return R.drawable.twod;
            }else if (code == 802){
                return R.drawable.threed;
            }else if (code >= 803 && code <= 804){
                return R.drawable.fourd;
            }else if (code >= 900 && code <= 906){
                return R.drawable.weather_demo;
            }
            else return R.drawable.weather_demo;
            }
            else {
            if (code >= 200 && code <= 300){
                return R.drawable.elevenn;
            }else if (code >= 300 && code <= 321){
                return R.drawable.ninen;
            }else if (code >= 500 && code <= 504){
                return R.drawable.tenn;
            }else if (code == 511){
                return R.drawable.thirtinn;
            }else if (code >= 520 && code <= 531){
                return R.drawable.ninen;
            }else if (code >= 600 && code <= 622){
                return R.drawable.thirtinn;
            }else if (code >= 701 && code <= 781){
                return R.drawable.fiftin;
            }else if (code == 800){
                return R.drawable.onen;
            }else if (code == 801){
                return R.drawable.twon;
            }else if (code == 802){
                return R.drawable.threen;
            }else if (code >= 803 && code <= 804){
                return R.drawable.fourn;
            }else if (code >= 900 && code <= 906){
                return R.drawable.weather_demo;
            }
            else return R.drawable.weather_demo;
        }
    }
}
