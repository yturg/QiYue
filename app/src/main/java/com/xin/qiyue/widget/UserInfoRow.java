package com.xin.qiyue.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xin.qiyue.R;

/**
 * Created by zxj on 2019-1-1.
 */

public class UserInfoRow extends RelativeLayout {
    TextView item,name;
    public UserInfoRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs,R.styleable.UserInfoRow);
        String item_title = array.getString(R.styleable.UserInfoRow_setItemTitle);
        String name_text = array.getString(R.styleable.UserInfoRow_setNameText);
        String hint_text = array.getString(R.styleable.UserInfoRow_setHintText);

        LayoutInflater.from(context).inflate(R.layout.mine_userinfosettingrow,this);
        item = (TextView) findViewById(R.id.item_title);
        name = (TextView) findViewById(R.id.name_text);
        item.setText(item_title);
        name.setText(name_text);
        name.setHint(hint_text);
    }

    public TextView getName() {
        return name;
    }

    public void setName(TextView name) {
        this.name = name;
    }
}
