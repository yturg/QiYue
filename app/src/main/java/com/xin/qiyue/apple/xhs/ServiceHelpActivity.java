package com.xin.qiyue.apple.xhs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.xin.qiyue.R;
import com.xin.qiyue.base.BaseActivity;
import com.xin.qiyue.widget.InfoSettingTitle;

import butterknife.BindView;

/**
 * Created by zxj on 2017/8/3.
 */

public class ServiceHelpActivity extends BaseActivity {
    @BindView(R.id.helptoolbar)
    InfoSettingTitle toolbar;
    @Override
    public int getContentViewId() {
        return R.layout.serviceshelp;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolbar.setImgListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
