package com.example.forever.weather;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class ForecastFragment extends Fragment {

    private String TAG = "WeatherApp";
    private WeatherForecastApi weatherForecastApi;
    private WeatherIconSelector iconSelector = new WeatherIconSelector();


    private Context context;
    private TextView morningTempTv,dayTempTv,eveningTempTv,nightTempTv;
    private ImageView morningIconIv,dayIconIv,eveningIconIv,nightIconIv;
    private View inflatedView;
    private String currentCity;
    private ListView forecastWeatherLv;
    private WeatherForecastModelClass weatherForecastData;
    private ArrayList<WeatherModel> weatherData;
    private boolean temperatureFormatCelsius = true;
    private String Url = "http://api.openweathermap.org/data/2.5/forecast/daily?q=Dhaka&appid=8e401c96e74d2f0c07da113eb27d51d0";
    private String BASE_URL="http://api.openweathermap.org/";
    public ForecastFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflatedView =  inflater.inflate(R.layout.fragment_forecast_weather, container, false);
        initializeAll();
        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        weatherForecastApi = retrofit.create(WeatherForecastApi.class);
*/


        return inflatedView;
    }

    private void initializeAll() {
        morningTempTv = (TextView) inflatedView.findViewById(R.id.forecastDayMorningWeatherTemp);
        dayTempTv = (TextView) inflatedView.findViewById(R.id.forecastDayWeatherTemp);
        eveningTempTv = (TextView) inflatedView.findViewById(R.id.forecastEveningWeatherTemp);
        nightTempTv = (TextView) inflatedView.findViewById(R.id.forecastNightWeatherTemp);

        morningIconIv = (ImageView) inflatedView.findViewById(R.id.forecastDayMorningWeatherIcon);
        dayIconIv = (ImageView) inflatedView.findViewById(R.id.forecastDayWeatherIcon);
        eveningIconIv = (ImageView) inflatedView.findViewById(R.id.forecastEveningWeatherIcon);
        nightIconIv = (ImageView) inflatedView.findViewById(R.id.forecastNightWeatherIcon);

        morningIconIv = (ImageView) inflatedView.findViewById(R.id.forecastDayMorningWeatherIcon);
        dayIconIv = (ImageView) inflatedView.findViewById(R.id.forecastDayWeatherIcon);
        eveningIconIv = (ImageView) inflatedView.findViewById(R.id.forecastEveningWeatherIcon);
        nightIconIv = (ImageView) inflatedView.findViewById(R.id.forecastNightWeatherIcon);

        forecastWeatherLv = (ListView) inflatedView.findViewById(R.id.weatherForecastLv);


       // weatherData = new ArrayList<>();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        weatherData = new ArrayList<>();
        weatherForecastData = new WeatherForecastModelClass();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        weatherForecastApi = retrofit.create(WeatherForecastApi.class);


       // WeatherIconSelector weatherIconSelector = new WeatherIconSelector();
        Location location = new Location(getActivity());
        currentCity = location.getCity();

        String dynamicUrl = BASE_URL+"data/2.5/forecast/daily?q="+currentCity+"&appid=8e401c96e74d2f0c07da113eb27d51d0";

        Call<WeatherForecastModelClass> getWeatherData = weatherForecastApi.getWeatherData(dynamicUrl);
        getWeatherData.enqueue(new Callback<WeatherForecastModelClass>() {
            @Override
            public void onResponse(Call<WeatherForecastModelClass> call, Response<WeatherForecastModelClass> response) {

                weatherForecastData = response.body();
                java.util.List<WeatherForecastModelClass.List> weatherList = weatherForecastData.getList();


                //retrieve data and store into weatherData arrayList
                for(WeatherForecastModelClass.List weather : weatherList){

                    int day = weather.getDt();
                    String maximumTemp = new DecimalFormat("##.#").format(weather.getTemp().getMax());
                    String minimumTemp = new DecimalFormat("##.#").format(weather.getTemp().getMin());

                    String morningTemp = new DecimalFormat("##.#").format(weather.getTemp().getMorn());
                    String dayTemp = new DecimalFormat("##.#").format(weather.getTemp().getDay());
                    String eveningTemp = new DecimalFormat("##.#").format(weather.getTemp().getEve());
                    String nightTemp = new DecimalFormat("##.#").format(weather.getTemp().getNight());
                    String date= new SimpleDateFormat("dd-MM-yyyy").format(new Date(day * 1000L));
                    String weatherCode = weather.getWeather().get(0).getId().toString();

                    Log.d(TAG, "onResponse: ---------------- "+weather.getWeather().get(0).getDescription());
                    Log.d(TAG, "onResponse: ---------------- "+weather.getWeather().get(0).getId());
                    //storing data into arrayList of weatherModel class
                    weatherData.add(new WeatherModel(date,maximumTemp,minimumTemp,morningTemp,dayTemp,eveningTemp,nightTemp,weatherCode));
                }
                updateListView();//update list view data

            }

            @Override
            public void onFailure(Call<WeatherForecastModelClass> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });

        forecastWeatherLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                updateDayWeather(position);
            }
        });
    }

    //update weather forecast ListView
    public void updateListView(){
        if (weatherData.size() > 0 ){
            AdapterListView adapter = new AdapterListView(context,weatherData,temperatureFormatCelsius);
            forecastWeatherLv.setAdapter(adapter);
            updateDayWeather(0);
        }
    }

    public void updateDayWeather(int position){


        morningIconIv.setImageResource(iconSelector.getIconName(weatherData.get(position).getWeatherId(),true));
        dayIconIv.setImageResource(iconSelector.getIconName(weatherData.get(position).getWeatherId(),true));
        eveningIconIv.setImageResource(iconSelector.getIconName(weatherData.get(position).getWeatherId(),true));
        nightIconIv.setImageResource(iconSelector.getIconName(weatherData.get(position).getWeatherId(),false));

        if (temperatureFormatCelsius){
            morningTempTv.setText(getCelsius(weatherData.get(position).getMorningWeather()));
            dayTempTv.setText(getCelsius(weatherData.get(position).getDayWeather()));
            eveningTempTv.setText(getCelsius(weatherData.get(position).getEveningWeather()));
            nightTempTv.setText(getCelsius(weatherData.get(position).getNightWeather()));

        }else {
            morningTempTv.setText(getFahrenheit(weatherData.get(position).getMorningWeather()));
            dayTempTv.setText(getFahrenheit(weatherData.get(position).getDayWeather()));
            eveningTempTv.setText(getFahrenheit(weatherData.get(position).getEveningWeather()));
            nightTempTv.setText(getFahrenheit(weatherData.get(position).getNightWeather()));
        }
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    //convert kelvin to fahrenheit
    public String getFahrenheit(String kelvin){
        return new DecimalFormat("##.##").format(Float.parseFloat(kelvin)-459)+"\u2103";
    }

    //convert kelvin to celsius
    public String getCelsius(String kelvin){
        return new DecimalFormat("##.##").format(Float.parseFloat(kelvin)-273)+"\u2103";
    }

}
