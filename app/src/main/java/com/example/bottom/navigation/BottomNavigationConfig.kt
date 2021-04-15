package com.example.bottom.navigation

import android.content.Context
import android.view.Gravity
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.LinearLayoutCompat

/**
 * 菜单信息
 * [title] 菜单标题
 *
 * [selector] 选择控制器 默认提供[DrawableMenuSelector]
 */

data class MenuData @JvmOverloads constructor(
    val title: String,
    var selector: MenuSelector<*>,
    var menuViewAdapter: BaseMenuViewAdapter? = null
)

/**
 * 单个菜单可设置的属性
 */
interface MenuAttribute {
    /**
     * 设置文本颜色
     */
    fun setTextColor(@ColorInt selectorColor: Int, @ColorInt unSelectorColor: Int)

    /**
     * 设置文本大小
     */
    fun setTextSize(selectorSize: Float, unSelectorSize: Float)

    /**
     * 设置图片大小
     */
    fun setIconSize(width: Int, height: Int)

    /**
     * 设置文本与Icon的间距
     */
    fun setSpace(size: Int)

    /**
     * 设置文本可见性
     */
    fun setTitleVisible(visible: Int)

    /**
     * 设置Icon可见性
     */
    fun setIconVisible(visible: Int)
}

/**
 * 菜单选择器
 */
abstract class MenuSelector<R>(val selectorRes: R, val unSelectorRes: R) {

    /**
     * 在View[CompatMenuView.onAttachedToWindow]方法执行时触发
     */
    open fun attachedToWindow(view: CompatMenuView) {

    }

    /**
     * 选中
     */
    abstract fun selector(view: CompatMenuView)

    /**
     * 未选中
     */
    abstract fun unSelector(view: CompatMenuView)

    /**
     * 在View[CompatMenuView.onDetachedFromWindow]方法执行前触发
     */
    open fun detachedFromWindow(view: CompatMenuView) {

    }
}

/**
 * Drawable资源选择器
 */
class DrawableMenuSelector(@DrawableRes selector: Int, @DrawableRes unSelector: Int) :
    MenuSelector<Int>(selector, unSelector) {

    override fun selector(view: CompatMenuView) {
        view.iconView.setImageResource(selectorRes)
    }

    override fun unSelector(view: CompatMenuView) {
        view.iconView.setImageResource(unSelectorRes)
    }

}

/**
 * 在生成一个菜单的时候，提供菜单View的对象
 * 如果有自定一的View但必须是这两种类型
 */
open class BaseMenuViewAdapter {

    open fun loadIconView(context: Context): ImageView {
        return ImageView(context).apply {
            layoutParams =
                LinearLayoutCompat.LayoutParams(35, 35).apply {
                }
        }
    }

    open fun loadTitleView(context: Context): TextView {
        return TextView(context).apply {
            gravity = Gravity.CENTER
            layoutParams =
                LinearLayoutCompat.LayoutParams(
                    LinearLayoutCompat.LayoutParams.WRAP_CONTENT,
                    LinearLayoutCompat.LayoutParams.WRAP_CONTENT
                )
        }
    }
}

/**
 * 菜单选中回调
 */
open interface OnMenuChangeListener {
    fun onChange(index: Int, menuView: CompatMenuView)
}