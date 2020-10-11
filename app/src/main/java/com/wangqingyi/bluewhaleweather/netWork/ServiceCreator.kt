package com.wangqingyi.bluewhaleweather.netWork

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

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
    fun <T> create(service: Class<T>): T = retrofit.create(service)

    /**
     * 获取service动态代理对象
     * 范型实化
     */
    inline fun <reified T> crate(): T = create(T::class.java)
}
