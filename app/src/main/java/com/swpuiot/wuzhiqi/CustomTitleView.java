package com.swpuiot.wuzhiqi;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by 羊荣毅_L on 2016/12/4.
 */
public class CustomTitleView extends View {
    private String mtitletext;
    private int mtitletextcolor;
    private int mtitletextsize;
    private Rect mBound;
    private Paint mPaint;
    public CustomTitleView(Context context) {
        this(context, null, 0);
    }

    public CustomTitleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomTitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray=context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomTitleView, defStyleAttr, 0);
//        TypedArray typedArray12=context.getTheme().obtainStyledAttributes(attrs,R.styleable.CustomTitleView,defStyleAttr,0);
        int n=typedArray.getIndexCount();
        for (int i=0;i<n;i++){
            int str=typedArray.getIndex(i);
            switch (str){
                case R.styleable.CustomTitleView_titletext:
                    mtitletext=typedArray.getString(str);
                    break;
                case R.styleable.CustomTitleView_titletextcolor:
                    mtitletextcolor=typedArray.getColor(str, Color.CYAN);
                    break;
                case R.styleable.CustomTitleView_titletextsize:
                    mtitletextsize=typedArray.getDimensionPixelSize(str,(int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP, 20, getResources().getDisplayMetrics()));
                    break;

            }
        }
        typedArray.recycle();
        mPaint=new Paint();
        mPaint.setTextSize(mtitletextsize);
        mPaint.setColor(mtitletextcolor);
        mBound=new Rect();
        mPaint.getTextBounds(mtitletext,0,mtitletext.length(),mBound);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height ;
        if (widthMode == MeasureSpec.EXACTLY)
        {
            width = widthSize;
        } else
        {
            mPaint.setTextSize(mtitletextsize);
            mPaint.getTextBounds(mtitletext, 0, mtitletext.length(), mBound);
            float textWidth = mBound.width();
            int desired = (int) (getPaddingLeft() + textWidth + getPaddingRight());
            width = desired;
        }

        if (heightMode == MeasureSpec.EXACTLY)
        {
            height = heightSize;
        } else
        {
            mPaint.setTextSize(mtitletextsize);
            mPaint.getTextBounds(mtitletext, 0, mtitletext.length(), mBound);
            float textHeight = mBound.height();
            int desired = (int) (getPaddingTop() + textHeight + getPaddingBottom());
            height = desired;
        }



        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);

        mPaint.setColor(mtitletextcolor);
        canvas.drawText(mtitletext, getWidth() / 2 - mBound.width() / 2, getHeight() / 2 + mBound.height() / 2, mPaint);
    }

}












