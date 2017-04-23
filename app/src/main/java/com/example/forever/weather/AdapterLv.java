package com.example.forever.weather;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by Forever on 4/20/2017.
 */

public class AdapterLv extends ArrayAdapter<WeatherModel> {

    private Context context;
    private ArrayList<WeatherModel> weatherForecast;
    private boolean isFormatCelsius;
    private WeatherIconSelector iconSelector = new WeatherIconSelector();

    public AdapterLv(@NonNull Context context, @NonNull ArrayList<WeatherModel> objects, boolean isFormatCelsius) {
        super(context, R.layout.forecast_layout, objects);
        this.context = context;
        this.weatherForecast = objects;
        this.isFormatCelsius = isFormatCelsius;//if weather format celsius = false it means weather format will be fahrenheit
    }


    class ViewHolder{
        TextView dayTv,tempHighTv,tempLowTv;
        ImageView weatherIcon;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder holder;
        if (convertView ==null){
            holder = new ViewHolder();

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.forecast_layout,parent,false);

            holder.dayTv = (TextView) convertView.findViewById(R.id.forecastDayNameTv);
            holder.tempHighTv = (TextView) convertView.findViewById(R.id.forecastDayHighTempTv);
            holder.tempLowTv = (TextView) convertView.findViewById(R.id.forecastDayLowTempTv);
            holder.weatherIcon = (ImageView) convertView.findViewById(R.id.forecastIconTv);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.dayTv.setText(weatherForecast.get(position).getWeatherDay());
        holder.weatherIcon.setImageResource(iconSelector.getIconName(weatherForecast.get(position).getWeatherId(),true));

        if (isFormatCelsius){
            holder.tempHighTv.setText(getCelsius(weatherForecast.get(position).getHighTemp()));
            holder.tempLowTv.setText(getCelsius(weatherForecast.get(position).getLowTemp()));
        }else {
            holder.tempHighTv.setText(getFahrenheit(weatherForecast.get(position).getHighTemp()));
            holder.tempLowTv.setText(getFahrenheit(weatherForecast.get(position).getLowTemp()));
        }

        return convertView;
    }

    //convert kelvin to fahrenheit
    public String getFahrenheit(String kelvin){
        return new DecimalFormat("##.##").format(Float.parseFloat(kelvin)-459)+"F";
    }

    //convert kelvin to celsius
    public String getCelsius(String kelvin){
        return new DecimalFormat("##.##").format(Float.parseFloat(kelvin)-273)+"C";
    }
}
