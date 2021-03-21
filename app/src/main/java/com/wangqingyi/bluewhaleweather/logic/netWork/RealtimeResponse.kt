package com.wangqingyi.bluewhaleweather.logic.netWork

import com.google.gson.annotations.SerializedName

/**
 *
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