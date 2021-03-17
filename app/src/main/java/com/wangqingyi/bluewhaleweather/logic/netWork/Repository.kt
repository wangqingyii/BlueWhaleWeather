package com.wangqingyi.bluewhaleweather.logic.netWork

import androidx.lifecycle.liveData
import com.wangqingyi.bluewhaleweather.model.Place
import com.wangqingyi.bluewhaleweather.logic.netWork.SunnyWeatherNetWork
import kotlinx.coroutines.Dispatchers
import java.lang.RuntimeException

/**
 * @Author: WangQingYi
 * @Time:   2020/11/3
 * @Class:  Repository
 * @Remark:仓库层的统一封装入口
 */
object Repository {
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
}