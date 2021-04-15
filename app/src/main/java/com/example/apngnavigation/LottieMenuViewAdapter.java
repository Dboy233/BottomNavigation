package com.example.apngnavigation;

import android.content.Context;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.apngnavigation.widget.BaseMenuViewAdapter;

import org.jetbrains.annotations.NotNull;

/**
 * @author DBoy 2021/2/1
 * <br/>    文件描述 : Lottie菜单适配器
 */
public class LottieMenuViewAdapter extends CommMenuViewAdapter {
    @NotNull
    @Override
    public ImageView loadIconView(@NotNull Context context) {
        return new LottieAnimationView(context);
    }
}
