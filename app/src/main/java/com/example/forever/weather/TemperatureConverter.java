package com.example.forever.weather;

/**
 * Created by Forever on 4/22/2017.
 */

public class TemperatureConverter {

    public static double convertFahrenToCel(double fahrenheit){
        return ((fahrenheit - 32)*5/9);
    }

    public static double convertCelToFahren(double celsius){
        return ((celsius*9)/5) + 32;
    }
    public static double convertKelvinToCel(double kelvin){
        return (kelvin - 273);
    }

    public static double convertKelvinToFahren(double kelvin){
        return (9/5 *(kelvin - 273) + 32);
    }
}
