package com.example.nayle.weatherapp2;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.nayle.weatherapp2.helper.WeatherAdapter;
import com.example.nayle.weatherapp2.helper.WeatherData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rslList;
    private String urlJsonArry = "http://api.wunderground.com/api/838ed9367e8876bf%20/forecast/q/EG/Cairo.json";
    private static String TAG = MainActivity.class.getSimpleName();

    private List<WeatherData> datalist  = new ArrayList<WeatherData>();

    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rslList = (RecyclerView) findViewById(R.id.relList);

        //-- set Attribute :
        rslList.setHasFixedSize(false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rslList.setLayoutManager(layoutManager);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);

        makeJsonArrayRequest();
    }

    //-- Create method handel request and get Responce

    private void makeJsonArrayRequest() {
        showpDialog();

        JsonObjectRequest jsonArrReq = new JsonObjectRequest(Request.Method.GET,
                urlJsonArry, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());
                hidepDialog();

                //*********************************************************/
                try {

                    JSONObject Forecast = response.getJSONObject("forecast");
                    JSONObject simpleforecast = Forecast.getJSONObject("simpleforecast");


                    JSONArray ForcastArray = simpleforecast.getJSONArray("forecastday");
//                    JSONObject DateObject = finalO.getJSONObject("date");

                    StringBuffer finalBufferData = new StringBuffer();



                    for (int j = 0; j < ForcastArray.length(); j++) {

                        JSONObject finalObject = ForcastArray.getJSONObject(j);
                        WeatherData datamodel = new WeatherData();
                        JSONObject Date = finalObject.getJSONObject("date");
                        JSONObject high = finalObject.getJSONObject("high");
                        JSONObject low = finalObject.getJSONObject("low");

                        datamodel.setDay(Date.getString("day"));
                        datamodel.setMonthNameShort(Date.getString("monthname_short"));
                        datamodel.setYear(Date.getString("year"));

                        datamodel.setTempcelsiusHigh(high.getInt("celsius"));
                        datamodel.setTempcelsiusLow(low.getInt("celsius"));


//                        int TempcelsiusHigh = high.getInt("celsius");
//                        int TempcelsiusLow = low.getInt("celsius");
//
//                        String day = Date.getString("day");//weekday_short
//                        String MonthNameShort = Date.getString("monthname_short");
//                        String Year = Date.getString("year");
//
//                        finalBufferData.append("\n\n\n\n" + day + " " + MonthNameShort + Year + " " + TempcelsiusHigh + "/" + TempcelsiusLow);

                        //adding final Object in the list
                        datalist.add(datamodel);

                    }

                    WeatherAdapter adapter = new WeatherAdapter(MainActivity.this, datalist);
                    rslList.setAdapter(adapter);

                    //********************************************************/

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }

            }


        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                // hide the progress dialog
                hidepDialog();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonArrReq, "Weather_responce");
    }


    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

}
