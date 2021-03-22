package com.wangqingyi.bluewhaleweather.logic.netWork

import androidx.lifecycle.liveData
import com.wangqingyi.bluewhaleweather.logic.model.Place
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import java.lang.RuntimeException

/**
 * @Author: WangQingYi
 * @Time:   2020/11/3
 * @Class:  Repository
 * @Remark:仓库层的统一封装入口
 */
object Repository {

    /**
     * 搜索地点
     * [query] 查询的字段
     */
    fun searchPlaces(query: String) = liveData(Dispatchers.IO) {
        val result = try {
            val placeResponse = SunnyWeatherNetWork.searchPlaces(query)
            if (placeResponse.status == "ok") {
                val places = placeResponse.places
                Result.success(places)
            } else {
                Result.failure(RuntimeException("response status is${placeResponse.status}"))
            }
        } catch (e: Exception) {
            Result.failure<List<Place>>(e)
        }
        // 用于发射数据
        emit(result)
    }


    fun refreshWeather(lng: String, lat: String) = liveData(Dispatchers.IO) {
        val result = try {
            coroutineScope {
                val deferredRealtime = async {
                    SunnyWeatherNetWork.getRealtimeWeather(lng, lat)
                }
                val deferredDailyResponse = async {
                    SunnyWeatherNetWork.getDailyWeather(lng, lat)
                }
                val realtimeResponse = deferredRealtime.await()
                val dailyResponse = deferredDailyResponse.await()
            }
        } catch (e: Exception) {

        }
        emit(result)
    }

}