package com.xin.qiyue.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.xin.qiyue.R;

/**
 * Created by zxj on 2017/8/3.
 */

public class NoteDivideLine extends LinearLayout {
    View view;
    public NoteDivideLine(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.note_pinglun_line,this);
        view = findViewById(R.id.note_pinglunline);
    }
}
