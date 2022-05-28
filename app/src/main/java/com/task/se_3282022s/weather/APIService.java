package com.task.se_3282022s.weather;


import com.task.se_3282022s.weather.WeatherModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * The interface Api service.
 */
public interface APIService {


    /**
     * Gets weather.
     *
     * @param city  the city
     * @param units the units
     * @param appid the appid
     * @return the weather
     */
    @GET("2.5/weather")
    Call<WeatherModel> getWeather(@Query("q") String city,
                                  @Query("units") String units,
                                  @Query("appid") String appid);

}

