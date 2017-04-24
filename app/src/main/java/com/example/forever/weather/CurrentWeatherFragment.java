package com.example.forever.weather;


import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class CurrentWeatherFragment extends Fragment {
    private TextView tempTV,dateTV,cityTV,sunriseTV,sunsetTV,humidityTV,conditionTV;

    private String apiTemp,apiDate, apiCity,weatherCondition,apiSunrise,apiSunset,apiHumidity ;

    private String currentLat = null;
    private String currentLng = null;
    private Calendar calendar;
   // private Location location;

    private final String BASE_URL = "http://api.openweathermap.org/";
    private WeatherCurentData weatherData;
    private WeatherCurrentServiceAPI weatherServiceAPI;
    private SharedPreferences sharedPreferences;

    public CurrentWeatherFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        Location location = new Location(getActivity());
        currentLat = location.getLatitude();
        currentLng = location.getLongitude();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        currentLat = getArguments().getString("lat");
  //      currentLng = getArguments().getString("lng");



        onStart();

        //Toast.makeText(getActivity(), "lat: "+currentLat+", lon: "+currentLng, Toast.LENGTH_SHORT).show();

        String dynamicUrl = BASE_URL+"data/2.5/weather?lat="+currentLat+"&lon="+currentLng+"&APPID=8e401c96e74d2f0c07da113eb27d51d0";

        Call<WeatherCurentData> weatherResponse = weatherServiceAPI.getWeatherResponse(dynamicUrl);
        weatherResponse.enqueue(new Callback<WeatherCurentData>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<WeatherCurentData> call, Response<WeatherCurentData> response) {
                if(response.code() == 200){
                    WeatherCurentData weatherData=response.body();
                    //Toast.makeText(MainActivity.this, "Got It", Toast.LENGTH_SHORT).show();
                    //apiTemp = weatherData.getMain().getTemp().toString();

                    String date = (DateFormat.format("dd-MM-yyyy", new Date()).toString());
                    //String date= new SimpleDateFormat("dd-MM-yyyy").format(new Date(apiDate * 1000L));
                    //String mytime = java.text.DateFormat.getTimeInstance().format(Calendar.getInstance().getTime());
                    apiCity = weatherData.getName().toString();
                    apiSunrise = weatherData.getSys().getSunrise().toString();
                    apiSunset = weatherData.getSys().getSunset().toString();
                    apiHumidity = weatherData.getMain().getHumidity().toString();
                    weatherCondition = weatherData.getWeather().get(0).getMain().toString();

                    String getSunrise = getTime(apiSunrise);
                    String getSunset = getTime(apiSunset);
                    //float convertToCelsius = Float.parseFloat(apiTemp);
                    double convertToCelsius = (weatherData.getMain().getTemp());

                    double convertedTemp = Math.round(TemperatureConverter.convertKelvinToCel(convertToCelsius));
                    // showSunTV.setText(convertedTemp);
                    int myInt = (int) convertedTemp;

                    String temperature = String.valueOf(myInt);

                    tempTV.setText(temperature+"\u2103");
                    dateTV.setText(date);
                    cityTV.setText(apiCity);
                    sunriseTV.setText(getSunrise);
                    sunsetTV.setText(getSunset);
                    humidityTV.setText(apiHumidity+"%");
                    conditionTV.setText(weatherCondition);
                }else{
                    Toast.makeText(getActivity(), "response not found", Toast.LENGTH_SHORT).show();
                }

                //Toast.makeText(CurrentWeatherFragment.this, " Temperature :"+apiTemp, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<WeatherCurentData> call, Throwable t) {
                //Toast.makeText(CurrentWeatherFragment.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("weather", "onFailure: "+t.getMessage() );

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        //Retrofit Starts ************************************
        View view = inflater.inflate(R.layout.fragment_fragment_currrent, container, false);
        dateTV = (TextView) view.findViewById(R.id.showDate);
        cityTV = (TextView) view.findViewById(R.id.showCity);
        tempTV = (TextView) view.findViewById(R.id.showTemperature);
        sunriseTV = (TextView) view.findViewById(R.id.showSunrise);
        sunsetTV = (TextView) view.findViewById(R.id.showSunset);
        humidityTV = (TextView) view.findViewById(R.id.showHumidity);
        conditionTV = (TextView) view.findViewById(R.id.showCondition);

        //Location clear = new Location(getActivity());
        //clear.clearLocation();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        weatherServiceAPI = retrofit.create(WeatherCurrentServiceAPI.class);

        //Retrofit Ends ************************************


        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public String getTime(String time){
//        calendar = Calendar.getInstance();
        long convert = Long.parseLong(time);
//        calendar.setTimeInMillis(convert*1000);
//        String sdf = new SimpleDateFormat("hh:mm a").format(calendar.getTime());
        /*SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = formatter.format(newDate(Long.parseLong(time)));*/
       // String times = DateUtils.formatDateTime(getActivity(), convert, DateUtils.FORMAT_SHOW_TIME);

        String times = new SimpleDateFormat("hh:mm a").
                format(new Date(convert * 1000));

        return times;
    }

}
