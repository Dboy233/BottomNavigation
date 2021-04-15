package com.example.apngnavigation

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.apngnavigation.databinding.ActivityMainBinding
import com.example.apngnavigation.widget.*

class MainActivity : AppCompatActivity() {

    val viewBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        initNav()
        viewBinding.choice2.setOnClickListener {
            viewBinding.navigationView.setCheck(1)
        }
        viewBinding.choice3.setOnClickListener {
            viewBinding.navigationView.setCheck(2, true)
        }
    }

    private fun initNav() {

        viewBinding.navigationView.menuChangeListener = object : OnMenuChangeListener {
            override fun onChange(index: Int, menuView: CompatMenuView) {
                Log.d("DJC", "onChange: index = $index")
            }
        }

        viewBinding.navigationView.globalMenuViewAdapter = CommMenuViewAdapter()

        viewBinding.navigationView.addMenu(
            MenuData(
                "首页", DrawableMenuSelector(
                    R.drawable.icon_main_nav_home_primary,
                    R.drawable.icon_main_nav_home_gray
                )
            ),
            MenuData(
                "资讯", LottieRawMenuSelector(R.raw.lottie_news), LottieMenuViewAdapter()
            ),
            MenuData(
                "我的", DrawableMenuSelector(
                    R.drawable.icon_main_nav_mine_primary,
                    R.drawable.icon_main_nav_mine_gray
                )
            )
        )

        viewBinding.navigationView.setIconSize(dp2px(25f), dp2px(25f))
        viewBinding.navigationView.setTextSize(13f, 13f)
        viewBinding.navigationView.setSpace(dp2px(2f))
    }

    fun dp2px(dpValue: Float): Int {
        val scale = Resources.getSystem().displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }
}