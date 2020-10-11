package com.wangqingyi.bluewhaleweather.model

import com.google.gson.annotations.SerializedName

/**
 * @Author: WangQingYi
 * @Time:   2020/10/11
 * @Class:  PlaceResponse
 * @Remark: 定义数据模型
 */
data class PlaceResponse(val status: String, val places: List<Place>)


data class Place(
    val name: String, val location: Location,
    @SerializedName("formatted_address") val address: String
)

data class Location(val lng: String, val lat: String)