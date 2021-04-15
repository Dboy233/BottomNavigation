package com.example.apngnavigation.widget

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.Gravity
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.appcompat.widget.LinearLayoutCompat

/**
 * @author DBoy 2021/2/1
 * <br/>    文件描述 :
 */
class CompatMenuView(
    private val mContext: Context,
    private val menuData: MenuData
) : LinearLayoutCompat(mContext), MenuAttribute {


    /**
     * 标题View
     */
    val titleView: TextView by lazy {
        menuData.menuViewAdapter!!.loadTitleView(mContext)
    }

    /**
     * 图片View
     */
    val iconView: ImageView by lazy {
        menuData.menuViewAdapter!!.loadIconView(mContext)
    }

    /**
     * 选中时文本颜色
     */
    @ColorInt
    var mSelectorColor: Int = Color.BLACK

    /**
     * 未选中时文本颜色
     */
    @ColorInt
    var mUnSelectorColor: Int = Color.GRAY

    /**
     * 选中时文本大小
     */
    var mSelectorSize = 12f

    /**
     * 未选中时文本大小
     */
    var mUnSelectorSize = 12f

    /**
     * 初始化检查
     */
    private var isInitCheck = false

    /**
     * 选中状态
     */
    var isCheck: Boolean = false
        set(value) {
            if (field == value && isInitCheck) return
            isInitCheck = true
            field = value
            changeCheck(value)
        }


    init {
        orientation = VERTICAL
        gravity = Gravity.CENTER
        initViews()
        Log.d(
            "Dboy",
            "${javaClass.simpleName} 选择器${menuData.selector.javaClass.simpleName} 适配器 ${menuData.menuViewAdapter!!.javaClass.simpleName}"
        )
    }

    /**
     * 改变选择状态通过选择器
     */
    private fun changeCheck(isCheck: Boolean) {
        if (isCheck) {
            titleView.textSize = mSelectorSize
            titleView.setTextColor(mSelectorColor)
            menuData.selector.selector(this)
        } else {
            titleView.textSize = mUnSelectorSize
            titleView.setTextColor(mUnSelectorColor)
            menuData.selector.unSelector(this)
        }
    }

    private fun initViews() {
        addView(iconView)
        titleView.text = menuData.title
        addView(titleView)
    }


    override fun setTextColor(@ColorInt selectorColor: Int, @ColorInt unSelectorColor: Int) {
        mSelectorColor = selectorColor
        mUnSelectorColor = unSelectorColor
        refresh()
    }


    override fun setTextSize(selectorSize: Float, unSelectorSize: Float) {
        mSelectorSize = selectorSize
        mUnSelectorSize = unSelectorSize
        refresh()
    }


    override fun setIconSize(width: Int, height: Int) {
        iconView.layoutParams = iconView.layoutParams.apply {
            this.width = width
            this.height = height
        }
    }


    override fun setSpace(size: Int) {
        showDividers = SHOW_DIVIDER_MIDDLE
        dividerDrawable = object : ColorDrawable() {
            override fun getIntrinsicHeight(): Int = size
        }
    }

    override fun setTitleVisible(visible: Int) {
        titleView.visibility = visible
    }

    override fun setIconVisible(visible: Int) {
        iconView.visibility = visible
    }

    /**
     * 控件属性改变时 刷新控件
     */
    private fun refresh() {
        changeCheck(isCheck)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        menuData.selector.attachedToWindow(this)
    }

    override fun onDetachedFromWindow() {
        menuData.selector.detachedFromWindow(this)
        super.onDetachedFromWindow()
    }
}
