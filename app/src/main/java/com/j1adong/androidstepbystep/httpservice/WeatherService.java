package com.j1adong.androidstepbystep.httpservice;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by J1aDong on 2016/11/17.
 */

public interface WeatherService
{

	@GET("weatherservice/cityname")
	Observable<String> getWeather(@Header("apikey")String apikey,@Query("cityname") String cityname);
}
