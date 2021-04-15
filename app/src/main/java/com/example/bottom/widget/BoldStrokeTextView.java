package com.example.bottom.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

/**
 * 文字加粗一点 默认不会太粗 也不会太细
 * Bold
 */
public class BoldStrokeTextView extends AppCompatTextView {

    private float boldSize = 1f;


    public BoldStrokeTextView(@NonNull Context context) {
        super(context);
        init(context, null);
    }


    public BoldStrokeTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }


    public BoldStrokeTextView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }


    private void init(Context context, AttributeSet attrs) {

    }


    @Override
    protected void onDraw(Canvas canvas) {
        //获取当前控件的画笔
        TextPaint paint = getPaint();
        //设置画笔的描边宽度值
        paint.setStrokeWidth(boldSize);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        super.onDraw(canvas);
    }


    public BoldStrokeTextView setBoldSize(float boldSize) {
        this.boldSize = boldSize;
        invalidate();
        return this;
    }
}