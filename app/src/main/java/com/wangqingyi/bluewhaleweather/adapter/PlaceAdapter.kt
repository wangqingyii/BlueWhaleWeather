package com.wangqingyi.bluewhaleweather.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.wangqingyi.bluewhaleweather.R
import com.wangqingyi.bluewhaleweather.model.Place

/**
 * 地点适配器
 *
 * @author WangQingYi
 * @since  2020/6/27
 */
class PlaceAdapter(list: MutableList<Place>):BaseQuickAdapter<Place,BaseViewHolder>(R.layout.place_item,list) {

    override fun convert(holder: BaseViewHolder, item: Place) {
        holder.setText(R.id.placeName,item.name)
        holder.setText(R.id.placeAddress,item.address)
    }
}