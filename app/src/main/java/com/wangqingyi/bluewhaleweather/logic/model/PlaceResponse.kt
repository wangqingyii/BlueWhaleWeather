package com.wangqingyi.bluewhaleweather.logic.model

import com.google.gson.annotations.SerializedName

/**
 * @Author: WangQingYi
 * @Time:   2020/10/11
 * @Class:  PlaceResponse
 * @Remark: 定义数据模型
 * [status] 状态
 */
data class PlaceResponse(val status: String, val places: List<Place>)

data class Place(
    val name: String, val location: Location,
    // 注解用于重命名，让JSON字段和Kotlin中的字段之间建立映射关系
    @SerializedName("formatted_address") val address: String
)

data class Location(val lng: String, val lat: String)