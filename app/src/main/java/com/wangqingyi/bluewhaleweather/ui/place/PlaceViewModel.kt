package com.wangqingyi.bluewhaleweather.ui.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.wangqingyi.bluewhaleweather.logic.netWork.Repository
import com.wangqingyi.bluewhaleweather.model.Place

/**
 * 搜索城市页面 VM层
 *
 * @author WangQingYi
 * @since  2021/1/27
 */
class PlaceViewModel : ViewModel() {

    private val searchLiveData = MutableLiveData<String>()

    val placeList = ArrayList<Place>()

    val placeLiveData = Transformations.switchMap(searchLiveData) { query ->
        Repository.searchPlaces(query)
    }

    fun searchPlaces(query: String) {
        searchLiveData.value = query
    }
}