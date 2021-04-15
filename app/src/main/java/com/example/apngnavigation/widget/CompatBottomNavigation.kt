package com.example.apngnavigation.widget

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.ColorInt
import androidx.appcompat.widget.LinearLayoutCompat

/**
 * @author DBoy 2021/2/1
 * <br/>    文件描述 :
 */
class CompatBottomNavigation(context: Context, attrs: AttributeSet) :
    @JvmOverloads LinearLayoutCompat(context, attrs), MenuAttribute {

    /**
     * 菜单控件适配器
     */
    var globalMenuViewAdapter: BaseMenuViewAdapter = BaseMenuViewAdapter()
        set(value) {
            if (mMenuViews.isNotEmpty()) {
                throw Exception("请在addMenu之前设置 globalMenuViewAdapter")
            }
            field = value
        }

    /**
     * 选中的位置
     */
    var selectorIndex = 0

    /**
     * 菜单Item集合
     */
    val mMenuViews = mutableListOf<CompatMenuView>()

    /**
     * 菜单选中改变监听
     */
    var menuChangeListener: OnMenuChangeListener? = null

    init {
        orientation = HORIZONTAL
    }


    /**
     * 添加菜单项
     */
    fun addMenu(vararg menu: MenuData) {
        //添加MenuView
        for (menuData in menu) {
            mMenuViews.add(addMenuView(menuData))
        }
        //设置点击事件
        mMenuViews.forEachIndexed { index, compatMenuView ->
            compatMenuView.setOnClickListener {
                setCheck(index, true)
            }
        }
        //设置选中
        setCheck(selectorIndex)
    }

    /**
     * 设置选中菜单
     */
    @JvmOverloads
    fun setCheck(index: Int, noticeChange: Boolean = false) {
        mMenuViews.forEachIndexed { i, menuView ->
            if (noticeChange && index == i) {
                menuChangeListener?.onChange(index, menuView)
            }
            menuView.isCheck = index == i
        }
    }

    /**
     * 加入菜单item
     */
    private fun addMenuView(menuData: MenuData): CompatMenuView {
        //如果item的适配器是默认的且 设置了全局的菜单适配器 应用全局
        if (menuData.menuViewAdapter == null) {
            menuData.menuViewAdapter = globalMenuViewAdapter
        }
        val menuView = CompatMenuView(context, menuData)
        menuView.titleView.text = menuData.title
        menuView.layoutParams =
            LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT).apply {
                weight = 1f
            }
        addView(menuView)
        return menuView
    }


    override fun setTextColor(@ColorInt selectorColor: Int, @ColorInt unSelectorColor: Int) {
        emptyMenuException("先添加元素再调用 setTextColor")

        for (menuView in mMenuViews) {
            menuView.setTextColor(selectorColor, unSelectorColor)
        }
    }

    override fun setTextSize(selectorSize: Float, unSelectorSize: Float) {
        emptyMenuException("先添加元素再调用 setTextSize")

        for (menuView in mMenuViews) {
            menuView.setTextSize(selectorSize, unSelectorSize)
        }
    }


    override fun setIconSize(width: Int, height: Int) {
        emptyMenuException("先添加元素再调用 setIconSize")

        for (menuView in mMenuViews) {
            menuView.setIconSize(width, height)
        }
    }

    override fun setSpace(size: Int) {
        emptyMenuException("先添加元素再调用 setSpace")

        for (menuView in mMenuViews) {
            menuView.setSpace(size)
        }
    }

    override fun setTitleVisible(visible: Int) {
        emptyMenuException("先添加元素再调用 setTitleVisible")

        for (menuView in mMenuViews) {
            menuView.setTitleVisible(visible)
        }
    }

    override fun setIconVisible(visible: Int) {
        emptyMenuException("先添加元素再调用 setIconVisible")

        for (menuView in mMenuViews) {
            menuView.setIconVisible(visible)
        }
    }

    private fun emptyMenuException(msg: String) {
        if (mMenuViews.isEmpty()) {
            throw Exception(msg)
        }
    }

    /**
     * 清理菜单项
     */
    fun clearMenu() {
        mMenuViews.clear()
        removeAllViews()
    }


}