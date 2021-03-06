package com.wangqingyi.bluewhaleweather.logic.model

import com.google.gson.annotations.SerializedName

/**
 * 实时天气
 *
 * @author WangQingYi
 * @since  2021/3/21
 */
data class RealtimeResponse(val status: String, val result: Result) {
    data class Result(val realtime: Realtime)

    data class Realtime(
        val skycon: String,
        val temperature: Float,
        @SerializedName("air_quality") val airQuality: AirQuality
    )

    data class AirQuality(val qpi: AQI)

    data class AQI(val chn: Float)
}