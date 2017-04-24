package com.example.forever.weather;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by Forever on 4/20/2017.
 */

public class AdapterListView extends ArrayAdapter<WeatherModel> {

    private Context context;
    private ArrayList <WeatherForecastModelClass> weatherForecastModelClasses;
    private ArrayList<WeatherModel> weatherForecast;
    private boolean isFormatCelsius;
    private WeatherIconSelector iconSelector = new WeatherIconSelector();

    public AdapterListView(@NonNull Context context, @NonNull ArrayList<WeatherModel> objects, boolean isFormatCelsius) {
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
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final ViewHolder holder;
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

        holder.weatherIcon.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                String dayTemp = holder.dayTv.getText().toString();
                String highTemp = holder.tempHighTv.getText().toString();
                String lowTemp = holder.tempLowTv.getText().toString();
                //     String description = weatherForecastModelClasses.get(position).getList().get(position).getWeather().get(position).getDescription();
                holder.weatherIcon.buildDrawingCache();
                Bitmap icon= holder.weatherIcon.getDrawingCache();

                final Dialog dialog = new Dialog(getContext());

                dialog.setContentView(R.layout.details_popup);
                dialog.setTitle(" Weather Forecasting Details ");

                ImageView iconSet = (ImageView) dialog.findViewById(R.id.showIcon);
                // TextView descrip         = (TextView) dialog.findViewById(R.id.showDescp);
                TextView temperature         = (TextView) dialog.findViewById(R.id.showTemp);
                TextView maxTemp         = (TextView) dialog.findViewById(R.id.showMax);
                TextView minTemp         = (TextView) dialog.findViewById(R.id.showMin);
                Button btnBack       = (Button) dialog.findViewById(R.id.cancel);

                iconSet.setImageBitmap(icon);
                // descrip.setText(description);
                temperature.setText(dayTemp);
                maxTemp.setText(highTemp+"\u2103");
                minTemp.setText(lowTemp+"\u2103");
                btnBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                //dialog.setCanceledOnTouchOutside(true);

                dialog.show();

                return false;
            }

        /*    @Override
            public void onClick(View v) {
                String dayTemp = holder.dayTv.getText().toString();
                String highTemp = holder.tempHighTv.getText().toString();
                String lowTemp = holder.tempLowTv.getText().toString();
           //     String description = weatherForecastModelClasses.get(position).getList().get(position).getWeather().get(position).getDescription();
                holder.weatherIcon.buildDrawingCache();
                Bitmap icon= holder.weatherIcon.getDrawingCache();

                final Dialog dialog = new Dialog(getContext());

                dialog.setContentView(R.layout.details_popup);
                dialog.setTitle(" Weather Forecasting Details ");

                ImageView iconSet = (ImageView) dialog.findViewById(R.id.showIcon);
               // TextView descrip         = (TextView) dialog.findViewById(R.id.showDescp);
                TextView temperature         = (TextView) dialog.findViewById(R.id.showTemp);
                TextView maxTemp         = (TextView) dialog.findViewById(R.id.showMax);
                TextView minTemp         = (TextView) dialog.findViewById(R.id.showMin);
                Button btnBack       = (Button) dialog.findViewById(R.id.cancel);

                iconSet.setImageBitmap(icon);
               // descrip.setText(description);
                temperature.setText(dayTemp);
                maxTemp.setText(highTemp);
                minTemp.setText(lowTemp);
                btnBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                //dialog.setCanceledOnTouchOutside(true);

                dialog.show();
            }*/

        });

        return convertView;
    }

    //convert kelvin to fahrenheit
    public String getFahrenheit(String kelvin){
        return new DecimalFormat("##.##").format(Float.parseFloat(kelvin)-459);
    }

    //convert kelvin to celsius
    public String getCelsius(String kelvin){
        return new DecimalFormat("##.##").format(Float.parseFloat(kelvin)-273);
    }
}
