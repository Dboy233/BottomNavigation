package com.example.bottom.comm

import android.content.Context
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import com.example.bottom.navigation.BaseMenuViewAdapter
import com.example.bottom.widget.BoldStrokeTextView

/**
 * @author DBoy 2021/2/1
 * <br></br>    文件描述 : 通用菜单视图适配器 通用的加粗titleView
 */
open class CommMenuViewAdapter : BaseMenuViewAdapter() {

    override fun loadTitleView(context: Context): TextView {
        return BoldStrokeTextView(context).apply {
            setBoldSize(0.5f)
            layoutParams = LinearLayoutCompat.LayoutParams(-2, -2)
        }
    }

}