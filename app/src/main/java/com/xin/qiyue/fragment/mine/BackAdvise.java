package com.xin.qiyue.fragment.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.xin.qiyue.R;
import com.xin.qiyue.widget.InfoSettingTitle;
import com.xin.qiyue.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by zxj on 2017/8/8.
 */

public class BackAdvise extends BaseActivity {
    @BindView(R.id.backadvisetool)
    InfoSettingTitle toolbar;
    @Override
    public int getContentViewId() {
        return R.layout.backadvise;
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
