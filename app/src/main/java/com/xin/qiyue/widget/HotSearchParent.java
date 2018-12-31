package com.xin.qiyue.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.xin.qiyue.R;

/**
 * Created by zxj on 2017/8/4.
 */

public class HotSearchParent extends LinearLayout {
    public HotSearchParent(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.hotsearchparent,this);
    }
}
