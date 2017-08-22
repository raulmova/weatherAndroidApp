package com.example.raul.listviewexample;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WeatherInfo extends AppCompatActivity {

    TextView temp, description, place;
    String code;
    String caso;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_info);

        temp = (TextView)findViewById(R.id.temp);
        description = (TextView)findViewById(R.id.description);
        place = (TextView)findViewById(R.id.place);
        imageView = (ImageView)findViewById(R.id.imageView);

        temp.setTextSize(30);
        place.setTextSize(25);
        description.setTextSize(30);
        //temp.setText(getIntent().getExtras().getString("Name"));
        code = getIntent().getExtras().getString("Code");


        RequestQueue queue = Volley.newRequestQueue(this);

        String url ="http://api.openweathermap.org/data/2.5/weather?id="+code+"&APPID=9ec589b4de3af3e87c4f31cc36be9ac7&units=metric";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        // test.setText("Response is: "+ response.substring(0,500));
                        try {

                            JSONObject jObj = new JSONObject(response);
                            Log.d("My app: ",jObj.toString());


                            JSONObject JSONWeather = jObj.getJSONObject("main");
                            temp.setText(JSONWeather.getString("temp")+ " ° ");
                            place.setText("City : "+getIntent().getExtras().getString("Name"));

                            JSONArray jArr = jObj.getJSONArray("weather");
                            JSONObject JSONWeatherTwo = jArr.getJSONObject(0);
                            description.setText(JSONWeatherTwo.getString("main"));
                            caso = JSONWeatherTwo.getString("main");
                            switch (caso){
                                case "Broken clouds" : imageView.setImageResource(R.drawable.broken_clouds);
                                    break;
                                case "Clear" : imageView.setImageResource(R.drawable.clear);
                                    break;
                                case "Clouds" : imageView.setImageResource(R.drawable.few_clouds);
                                    break;
                                case "Mist" : imageView.setImageResource(R.drawable.mist);
                                    break;
                                case "Rain" : imageView.setImageResource(R.drawable.rain);
                                    break;
                                case "Scattered clouds" : imageView.setImageResource(R.drawable.scattered_clouds);
                                    break;
                                case "Shower rain" : imageView.setImageResource(R.drawable.shower_rain);
                                    break;
                                case "Snow" : imageView.setImageResource(R.drawable.snow);
                                    break;
                                case "Thunderstorm" : imageView.setImageResource(R.drawable.thunderstorm);
                                    break;
                                default:  imageView.setImageResource(R.drawable.defa);
                                    break;
                            }


                           // test.setText(jsonObj.getString("temp"));
                           /* JSONArray temps = jsonObj.getJSONArray("main");
                            String temp = temps.getString(0);
                            test.setText(temp);
                           */

                        } catch (Throwable t) {
                            Log.e("My App", "Could not parse malformed JSON:"  +response);
                        }
                      /*  try {
                            JSONObject jObj = new JSONObject(response);
                            JSONArray jArray = jObj.getJSONArray("main");
                            test.setText(jArray.getString(0));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        */
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                temp.setText("That didn't work!");
            }
        });


        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }
}
