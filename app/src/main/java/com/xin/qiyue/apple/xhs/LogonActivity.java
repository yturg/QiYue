package com.xin.qiyue.apple.xhs;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.xin.qiyue.R;
import com.xin.qiyue.bean.MyUser;
import com.xin.qiyue.base.BaseActivity;
import com.xin.qiyue.collecter.ErrorCollecter;
import com.xin.qiyue.data.UpdateDataBmob;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by zxj on 2017/7/22.
 */

public class LogonActivity extends BaseActivity {
    @BindView(R.id.logon_user_name) TextView userName;
    @BindView(R.id.logon_user_pass) TextView userPass;
    @BindView(R.id.logon_user_pass_aga) TextView userPassAga;
    @BindView(R.id.logon_user_email) TextView userEmail;
    @BindView(R.id.logon_login) TextView logon_login;
    @BindView(R.id.bt_logon) Button logon;

    String name,pass,passAga,email;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    public int getContentViewId() {
        return R.layout.logon;
    }

    private void initView() {

    }
    //注册
    @OnClick(R.id.bt_logon)
    public void logonOnClick() {
        name = userName.getText().toString().trim();
        pass = userPass.getText().toString().trim();
        passAga = userPassAga.getText().toString().trim();
        email = userEmail.getText().toString().trim();
        if (!passAga.equals(pass)){
            userPassAga.setError("密码不一致");
            return;
        }else if (!name.matches("[a-zA-Z0-9_]{1,12}")){
            userName.setError("用户名只能由字母数字下划线组成，长度不能超过12位");
            return;
        }else if (!pass.matches("[a-zA-Z0-9]{1,16}")){
            userPass.setError("密码含有非法字符，或长度超过16位");
            return;
        }else if (!email.matches("[a-zA-Z_0-9]+@(([a-zA-z0-9]-*)+\\.){1,3}[a-zA-z\\-]+")){
            userEmail.setError("邮箱格式错误");
            return;
        }
        MyUser user = new MyUser();
        BmobFile bmobFile = new BmobFile("ft021l_sm.png","","http://bmob-cdn-13046.b0.upaiyun.com/2017/08/09/6d5a446c40af5dc88025972632129a03.png");
        user.setUsername(name);
        user.setPassword(pass);
        user.setNickname("用户" + System.currentTimeMillis());
        user.setHead(bmobFile);
        if (email != null){
            user.setEmail(email);
        }
        user.signUp(new SaveListener<MyUser>() {
            @Override
            public void done(MyUser myUser, BmobException e) {
                if (e==null){
                    UpdateDataBmob.UpdataIDNew(myUser.getObjectId());
                    startActivity(new Intent(LogonActivity.this,LoginActivity.class));
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(), ErrorCollecter.errorCode(e),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @OnClick(R.id.logon_login)
    public void loginOnClick(){
        finish();
    }
}
