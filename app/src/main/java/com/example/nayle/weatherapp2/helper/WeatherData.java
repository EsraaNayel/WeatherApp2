package com.example.nayle.weatherapp2.helper;

import java.io.Serializable;

/**
 * Created by Nayle on 3/31/2016.
 */
public class WeatherData implements Serializable{
    //-- Put variable :
    private String day;

    private String MonthNameShort;

    private String Year;

    private int TempcelsiusHigh;

    private int TempcelsiusLow;


    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonthNameShort() {
        return MonthNameShort;
    }

    public void setMonthNameShort(String monthNameShort) {
        MonthNameShort = monthNameShort;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public int getTempcelsiusHigh() {
        return TempcelsiusHigh;
    }

    public void setTempcelsiusHigh(int tempcelsiusHigh) {
        TempcelsiusHigh = tempcelsiusHigh;
    }

    public int getTempcelsiusLow() {
        return TempcelsiusLow;
    }

    public void setTempcelsiusLow(int tempcelsiusLow) {
        TempcelsiusLow = tempcelsiusLow;
    }


}
