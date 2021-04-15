package com.example.bottom.comm

import androidx.annotation.RawRes
import com.airbnb.lottie.LottieAnimationView
import com.example.bottom.navigation.CompatMenuView
import com.example.bottom.navigation.MenuSelector

/**
 * @author DBoy 2021/2/1
 * <br/>    文件描述 : lottie动画菜单选择控制器
 */
class LottieRawMenuSelector(@RawRes lottieRawId: Int) :
    MenuSelector<Int>(lottieRawId, lottieRawId) {

    override fun attachedToWindow(view: CompatMenuView) {
        if (view.iconView is LottieAnimationView) {
            (view.iconView as LottieAnimationView).apply {
                setAnimation(selectorRes)
            }
        }
    }

    override fun selector(view: CompatMenuView) {
        if (view.iconView is LottieAnimationView) {
            val lottieAnimationView = view.iconView as LottieAnimationView
            if (lottieAnimationView.progress == 1f) return
            lottieAnimationView
                .apply {
                    cancelAnimation()
                    speed = 2f
                    playAnimation()
                }
        }
    }

    override fun unSelector(view: CompatMenuView) {
        if (view.iconView is LottieAnimationView) {
            val lottieAnimationView = view.iconView as LottieAnimationView
            if (lottieAnimationView.progress == 0f) return
            lottieAnimationView.apply {
                cancelAnimation()
                speed = -2f
                playAnimation()
            }
        }
    }

}