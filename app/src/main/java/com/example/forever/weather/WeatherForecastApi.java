package com.example.forever.weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by Forever on 4/19/2017.
 */

public interface WeatherForecastApi {
    /*@GET("data/2.5/forecast/daily?q=Dhaka&appid=8e401c96e74d2f0c07da113eb27d51d0")
    Call<WeatherForecastModelClass> getWeatherData();*/

    @GET
    Call<WeatherForecastModelClass> getWeatherData(
            @Url String url
    );
}
