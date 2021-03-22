package com.wangqingyi.bluewhaleweather.logic.netWork

import com.wangqingyi.bluewhaleweather.SunnyWeatherApplication
import com.wangqingyi.bluewhaleweather.logic.model.DailyResponse
import com.wangqingyi.bluewhaleweather.logic.model.RealtimeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * 访问天气信息接口
 *
 * @author WangQingYi
 * @since  2021/3/22
 */
interface WeatherService {
    /**
     * 获取实时天气
     * [lng] 经度
     * [lat] 纬度
     */
    @GET("v2.5/${SunnyWeatherApplication.TOKEN}/{lng},{lat}/realtime.json")
    suspend fun getRealtimeWeather(
        @Path("lng") lng: String,
        @Path("lat") lat: String
    ): Call<RealtimeResponse>

    /**
     * 获取未来几天天气
     * [lng] 经度
     * [lat] 纬度
     */
    @GET("v2.5/${SunnyWeatherApplication.TOKEN}/{lng},{lat}/daily.json")
    suspend fun getDailyWeather(
        @Path("lng") lng: String,
        @Path("lat") lat: String
    ): Call<DailyResponse>

}