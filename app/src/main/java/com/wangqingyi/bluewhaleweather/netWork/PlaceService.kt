package com.wangqingyi.bluewhaleweather.netWork

import com.wangqingyi.bluewhaleweather.SunnyWeatherApplication
import com.wangqingyi.bluewhaleweather.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @Author: WangQingYi
 * @Time:   2020/10/11
 * @Class:  PlaceService
 * @Remark:
 */
interface PlaceService {
    /**
     * 搜索城市接口
     * @param query 查询的字段
     */
    @GET("v2/place?token=${SunnyWeatherApplication.TOKEN}&lang=zh_CN")
    fun searchPlaces(@Query("query") query: String): Call<PlaceResponse>
}