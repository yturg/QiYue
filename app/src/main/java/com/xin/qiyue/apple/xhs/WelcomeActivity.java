package com.xin.qiyue.apple.xhs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.Window;

import com.xin.qiyue.R;
import com.xin.qiyue.bean.MyUser;

import cn.bmob.v3.BmobUser;

/**
 * Created by zxj on 2017/7/21.
 */

public class WelcomeActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.welcome_pic);
        final MyUser userInfo = BmobUser.getCurrentUser(MyUser.class);
        Handler handler = new Handler();
        //账号是否已经登录
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(userInfo != null) {
                    startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                    WelcomeActivity.this.finish();
                }else{
                    startActivity(new Intent(WelcomeActivity.this,LoginOrLogonActivity.class));
                    WelcomeActivity.this.finish();
                }
            }
        },2000);
    }
}
