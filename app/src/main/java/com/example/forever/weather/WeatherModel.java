package com.example.forever.weather;

/**
 * Created by Forever on 4/19/2017.
 */

public class WeatherModel {
    private String weatherDay;
    private int weatherIcon;
    private String highTemp;
    private String lowTemp;

    private int morningWeatherIcon;
    private String morningWeather;

    private int dayWeatherIcon;
    private String dayWeather;

    private int eveningWeatherIcon;
    private String eveningWeather;

    private int nightWeatherIcon;
    private String nightWeather;
    private String weatherId;


    //all constructor
    public WeatherModel(String weatherDay, int weatherIcon, String highTemp, String lowTemp, int morningWeatherIcon, String morningWeather, int dayWeatherIcon, String dayWeather, int eveningWeatherIcon, String eveningWeather, int nightWeatherIcon, String nightWeather) {
        this.weatherDay = weatherDay;
        this.weatherIcon = weatherIcon;
        this.highTemp = highTemp;
        this.lowTemp = lowTemp;
        this.morningWeatherIcon = morningWeatherIcon;
        this.morningWeather = morningWeather;
        this.dayWeatherIcon = dayWeatherIcon;
        this.dayWeather = dayWeather;
        this.eveningWeatherIcon = eveningWeatherIcon;
        this.eveningWeather = eveningWeather;
        this.nightWeatherIcon = nightWeatherIcon;
        this.nightWeather = nightWeather;
    }

    public WeatherModel(String weatherDay, String highTemp, String lowTemp, String morningWeather, String dayWeather,
                        String eveningWeather, String nightWeather, String weatherId) {
        this.weatherDay = weatherDay;
        this.highTemp = highTemp;
        this.lowTemp = lowTemp;
        this.morningWeather = morningWeather;
        this.dayWeather = dayWeather;
        this.eveningWeather = eveningWeather;
        this.nightWeather = nightWeather;
        this.weatherId = weatherId;
    }

    public WeatherModel(String weatherDay, int weatherIcon, String highTemp, String lowTemp) {
        this.weatherDay = weatherDay;
        this.weatherIcon = weatherIcon;
        this.highTemp = highTemp;
        this.lowTemp = lowTemp;
    }

    public WeatherModel() {
    }


    // all getter and setter method
    public String getWeatherDay() {
        return weatherDay;
    }


    public String getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(String weatherId) {
        this.weatherId = weatherId;
    }
    public void setWeatherDay(String weatherDay) {
        this.weatherDay = weatherDay;
    }

    public int getWeatherIcon() {
        return weatherIcon;
    }

    public void setWeatherIcon(int weatherIcon) {
        this.weatherIcon = weatherIcon;
    }

    public String getHighTemp() {
        return highTemp;
    }

    public void setHighTemp(String highTemp) {
        this.highTemp = highTemp;
    }

    public String getLowTemp() {
        return lowTemp;
    }

    public void setLowTemp(String lowTemp) {
        this.lowTemp = lowTemp;
    }

    public int getMorningWeatherIcon() {
        return morningWeatherIcon;
    }

    public void setMorningWeatherIcon(int morningWeatherIcon) {
        this.morningWeatherIcon = morningWeatherIcon;
    }

    public String getMorningWeather() {
        return morningWeather;
    }

    public void setMorningWeather(String morningWeather) {
        this.morningWeather = morningWeather;
    }

    public int getDayWeatherIcon() {
        return dayWeatherIcon;
    }

    public void setDayWeatherIcon(int dayWeatherIcon) {
        this.dayWeatherIcon = dayWeatherIcon;
    }

    public String getDayWeather() {
        return dayWeather;
    }

    public void setDayWeather(String dayWeather) {
        this.dayWeather = dayWeather;
    }

    public int getEveningWeatherIcon() {
        return eveningWeatherIcon;
    }

    public void setEveningWeatherIcon(int eveningWeatherIcon) {
        this.eveningWeatherIcon = eveningWeatherIcon;
    }

    public String getEveningWeather() {
        return eveningWeather;
    }

    public void setEveningWeather(String eveningWeather) {
        this.eveningWeather = eveningWeather;
    }

    public int getNightWeatherIcon() {
        return nightWeatherIcon;
    }

    public void setNightWeatherIcon(int nightWeatherIcon) {
        this.nightWeatherIcon = nightWeatherIcon;
    }

    public String getNightWeather() {
        return nightWeather;
    }

    public void setNightWeather(String nightWeather) {
        this.nightWeather = nightWeather;
    }
}
