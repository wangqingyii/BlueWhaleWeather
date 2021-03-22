package com.wangqingyi.bluewhaleweather.logic.netWork

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * @Author: WangQingYi
 * @Time:   2020/11/3
 * @Class:  SunnyWeatherNetWork
 * @Remark:统一封装的网络数据源访问入口，用于对所有网络请求的API进行封装
 */
object SunnyWeatherNetWork {

    private val weatherService = ServiceCreator.create(WeatherService::class.java)

    /**
     * 获取每日天气
     * [lng] 经度
     * [lat] 纬度
     */
    suspend fun getDailyWeather(lng: String, lat: String) =
        weatherService.getDailyWeather(lng, lat).await()


    /**
     * 获取实时天气
     * [lng] 经度
     * [lat] 纬度
     */
    suspend fun getRealtimeWeather(lng: String, lat: String) =
        weatherService.getRealtimeWeather(lng, lat).await()

    //创建PlaceService动态代理对象
    private val placeService = ServiceCreator.create(PlaceService::class.java)

    //用于发起搜索城市数据请求
    suspend fun searchPlaces(query: String) = placeService.searchPlaces(query).await()

    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null) continuation.resume(body)
                    else continuation.resumeWithException(
                        RuntimeException("响应主体为null")
                    )
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }

            })
        }
    }


}