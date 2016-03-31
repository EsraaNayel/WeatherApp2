package com.example.nayle.weatherapp2.helper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nayle.weatherapp2.R;

import java.util.List;

/**
 * Created by Nayle on 3/31/2016.
 */
public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>{

    //-- Declare Object we need it .
    private Context context;
    private List<WeatherData> lstData;

    private WeatherData weatherData;

    public WeatherAdapter(Context context, List<WeatherData> lstData) {
        this.context = context;
        this.lstData = lstData;
    }

    @Override
    public WeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View itemView = LayoutInflater.from(parent.getContext()).
               inflate(R.layout.list_item, parent, false);


        return new WeatherViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WeatherViewHolder holder, int position) {

        weatherData = lstData.get(position);

        holder.tvDay.setText(weatherData.getDay());
        holder.tvMonth.setText(weatherData.getMonthNameShort());
        holder.tvYear.setText(weatherData.getYear());
        holder.tvTemp.setText(weatherData.getTempcelsiusHigh()+" / "
                                      +weatherData.getTempcelsiusLow());


    }

    @Override
    public int getItemCount() {
        return lstData.size();
    }

    class WeatherViewHolder extends RecyclerView.ViewHolder{
        //-- Declare all item in list_item
        ImageView image;
        TextView tvDay
                , tvMonth
                , tvYear
                , tvTemp;
        LinearLayout lDate;


        public WeatherViewHolder(View itemView) {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.image_weather);

            tvDay = (TextView) itemView.findViewById(R.id.tv_Day);
            tvMonth = (TextView) itemView.findViewById(R.id.tv_Month);
            tvYear = (TextView) itemView.findViewById(R.id.tv_Year);
            tvTemp = (TextView) itemView.findViewById(R.id.tv_temp);

            lDate = (LinearLayout) itemView.findViewById(R.id.tv_Date);





        }



    }
}
