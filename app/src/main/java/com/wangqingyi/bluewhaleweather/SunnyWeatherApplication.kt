package com.wangqingyi.bluewhaleweather

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

/**
 * @Author: WangQingYi
 * @Time:   2020/10/11
 * @Class:  SunnyWeatherApplication
 * @Remark: 全局获取Context
 */
class SunnyWeatherApplication : Application() {

    //companion object 修饰为伴生对象,伴生对象在类中只能存在一个，类似于java中的静态方法 Java 中使用类访问静态成员，静态方法
    companion object {
        //令牌值
        const val TOKEN = "O31LkuJ9O2vmilv3"

        @SuppressLint("StaticFieldLeak")
        //lateinit var：让编译器在检查时不要因为属性变量未被初始化而报错
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}