package com.bawei.apptwo.ui.custom;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.bawei.apptwo.R;

@SuppressLint("AppCompatCustomView")
public class CustomView extends TextView {

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.customView);
        int color = array.getColor(R.styleable.customView_textColor, Color.GREEN);
        setTextColor(color);
        //回收
        array.recycle();
    }

}
