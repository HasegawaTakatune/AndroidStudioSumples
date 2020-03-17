package com.reryka.weatherforecasts;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherApi {
    // http://weather.livedoor.com/forecast/rss/primary_area.xml
    public static final String API_ENDPOINT = "http://weather.livedoor.com/forecast/webservice/json/v1?city=";
    public  static  WeatherForecast getWeather(String cityId) throws IOException, JSONException {
        URL url = new URL(API_ENDPOINT + cityId);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();

        StringBuilder builder = new StringBuilder();

        try{
            BufferedReader buffer = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line;
            while ((line = buffer.readLine()) != null){builder.append(line);}
        }finally {
            connection.disconnect();
        }
        return  new WeatherForecast(new JSONObject(builder.toString()));
    }
}
