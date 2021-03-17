package com.wangqingyi.bluewhaleweather.logic.netWork

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @Author: WangQingYi
 * @Time:   2020/10/11
 * @Class:  PlaceService
 * @Remark:Retrofit动态代理对象获取封装
 */
object ServiceCreator {

    private const val BASE_URL = "https://api.caiyunapp.com/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    /**
     * 获取service动态代理对象
     * @param serviceClass 接口Class对象
     */
    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)

    /**
     * 获取service动态代理对象
     * 范型实化
     */
    inline fun <reified T> crate(): T = create(T::class.java)
}
