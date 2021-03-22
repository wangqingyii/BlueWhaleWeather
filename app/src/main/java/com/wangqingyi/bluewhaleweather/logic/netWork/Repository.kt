package com.wangqingyi.bluewhaleweather.logic.netWork

import androidx.lifecycle.liveData
import com.wangqingyi.bluewhaleweather.logic.model.Weather
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlin.coroutines.CoroutineContext

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
    fun searchPlaces(query: String) = fire(Dispatchers.IO) {
        val placeResponse = SunnyWeatherNetWork.searchPlaces(query)
        if (placeResponse.status == "ok") {
            val places = placeResponse.places
            Result.success(places)
        } else {
            Result.failure(RuntimeException("response status is${placeResponse.status}"))
        }
    }


    /**
     * 刷新天气
     */
     fun refreshWeather(lng: String, lat: String) = fire(Dispatchers.IO) {
        // 两个并行请求
        coroutineScope {
            // 创建一个新的协程请求
            val deferredRealtime = async {
                SunnyWeatherNetWork.getRealtimeWeather(lng, lat)
            }
            // 创建一个新的协程请求
            val deferredDailyResponse = async {
                SunnyWeatherNetWork.getDailyWeather(lng, lat)
            }
            // 对两个请求获取结果
            val realtimeResponse = deferredRealtime.await()
            val dailyResponse = deferredDailyResponse.await()
            // 对请求结果合并，并发射出去，如果有任何一个请求出问题就抛出异常给调用者
            if (realtimeResponse.status == "ok" && dailyResponse.status == "ok") {
                val weather =
                    Weather(realtimeResponse.result.realtime, dailyResponse.result.daily)
                Result.success(weather)
            } else {
                Result.failure(
                    RuntimeException(
                        "实时天气状态为 ${realtimeResponse.status}" +
                                "每日天气状态为 ${dailyResponse.status}"
                    )
                )
            }
        }
    }

    /**
     * 对入口函数进行封装
     * [context] 上下文
     * [block] 高阶函数
     */
    private fun <T> fire(context: CoroutineContext, block: suspend () -> Result<T>) =
        liveData(context) {
            val result = try {
                block()
            } catch (e: Exception) {
                Result.failure(e)
            }
            emit(result)
        }
}