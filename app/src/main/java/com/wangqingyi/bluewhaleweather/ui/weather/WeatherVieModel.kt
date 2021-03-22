package com.wangqingyi.bluewhaleweather.ui.weather

import android.location.Location
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.wangqingyi.bluewhaleweather.logic.netWork.Repository

/**
 * 天气VM层
 *
 * @author WangQingYi
 * @since  2021/3/22
 */
class WeatherVieModel : ViewModel() {

    private val locationLiveData =
        MutableLiveData<com.wangqingyi.bluewhaleweather.logic.model.Location>()

    // 经度
    var locationLng = ""

    // 纬度
    var locationLat = ""

    // 地名
    var placeName = ""

    val weatherLiveData = Transformations.switchMap(locationLiveData) { location ->
        Repository.refreshWeather(location.lng, location.lat)
    }

    fun refreshWeather(lng: String, lat: String) {
        locationLiveData.value = com.wangqingyi.bluewhaleweather.logic.model.Location(lng, lat)
    }
}