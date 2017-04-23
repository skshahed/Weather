package com.example.forever.weather;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Forever on 4/19/2017.
 */

public interface WeatherApi {
    @GET("data/2.5/forecast/daily?q=Dhaka&appid=a226ec225f23ea5717f7fa94ce785237")
    Call<WeatherForecastModelClass> getWeatherData();
}
