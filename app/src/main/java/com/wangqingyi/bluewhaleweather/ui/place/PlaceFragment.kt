package com.wangqingyi.bluewhaleweather.ui.place

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.wangqingyi.bluewhaleweather.adapter.PlaceAdapter
import com.wangqingyi.bluewhaleweather.R
import kotlinx.android.synthetic.main.fragment_place.*


/**
 * @Author: WangQingYi
 * @Time:   2020/6/27
 * @Class:  PlaceFragment
 * @Remark: 搜索城市页面
 */
class PlaceFragment : Fragment() {
    // TODO 方法过时
    private val mViewModel by lazy { ViewModelProviders.of(this).get(PlaceViewModel::class.java) }

    private lateinit var mAdapter: PlaceAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_place, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager
        mAdapter = PlaceAdapter(mViewModel.placeList)
        recyclerView.adapter = mAdapter
        // 监听搜索框的内容变化
        searchPlaceEdit.addTextChangedListener { editable ->
            // 输入框的内容
            val content = editable.toString()
            // 判断是否为空 如果有内容变化就传递给searchPlaces()方法搜索城市数据
            if (content.isNotEmpty()) {
                mViewModel.searchPlaces(content)
            } else {
                // 为null时将Rv隐藏并显示图片
                recyclerView.visibility = View.GONE
                bgImageView.visibility = View.VISIBLE
                mViewModel.placeList.clear()
                mAdapter.notifyDataSetChanged()
            }
        }

        mViewModel.placeLiveData.observe(this,  { result ->
            val places = result.getOrNull()
            if (places != null) {
                recyclerView.visibility = View.VISIBLE
                bgImageView.visibility = View.GONE
                mViewModel.placeList.clear()
                mViewModel.placeList.addAll(places)
            } else {
                Toast.makeText(activity, "未能查询到任何地点", Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
        })
    }
}