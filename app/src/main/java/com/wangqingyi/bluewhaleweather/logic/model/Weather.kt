package com.wangqingyi.bluewhaleweather.logic.model

/**
 * 封装Rraltime与Daily对象
 *
 * @author WangQingYi
 * @since  2021/3/22
 */
data class Weather(val realtime:RealtimeResponse.Realtime,val daily :DailyResponse.Daily)