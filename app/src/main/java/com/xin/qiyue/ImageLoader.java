package com.xin.qiyue;

import android.content.Context;
import android.widget.ImageView;

import java.io.Serializable;

/**
 * @author zxj.
 * @date 2016/8/5.
 */
public interface ImageLoader extends Serializable {
    void displayImage(Context context, String path, ImageView imageView);
}