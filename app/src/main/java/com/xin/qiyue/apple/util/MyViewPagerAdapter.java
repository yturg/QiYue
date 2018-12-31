package com.xin.qiyue.apple.util;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.xin.qiyue.apple.xhs.shopping.ProductScanActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zxj on 2017/7/27.
 */

public class MyViewPagerAdapter extends PagerAdapter implements View.OnClickListener {
    List<ImageView> list = new ArrayList<>();
    public MyViewPagerAdapter(List<ImageView> list){
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(list.get(position));
        list.get(position).setOnClickListener(this);
        return list.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
       container.removeView(list.get(position));
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        //传递一个序列化对象
        //intent.putExtra()
        intent.setClass(view.getContext(), ProductScanActivity.class);
        view.getContext().startActivity(intent);
    }
}
