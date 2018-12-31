package com.xin.qiyue.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.xin.qiyue.R;
import com.xin.qiyue.fragment.mine.MineUserInfoSetting;
import com.xin.qiyue.apple.xhs.note.NoteEditView;
import com.xin.qiyue.base.BaseActivity;
import com.xin.qiyue.bean.MyUser;

import butterknife.BindView;
import cn.bmob.v3.BmobUser;

/**
 * Created by zxj on 2017/7/27.
 */

public class OpenCameraActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.home_close_camera)
    ImageView close;
    @BindView(R.id.home_note)
    ImageView note;
    @BindView(R.id.home_video)
    ImageView video;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setViewListener();
    }

    private void setViewListener() {
        close.setOnClickListener(this);
        note.setOnClickListener(this);
        video.setOnClickListener(this);
    }

    @Override
    public int getContentViewId() {
        return R.layout.home_camera;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.home_close_camera:
                finish();
                this.overridePendingTransition(R.anim.home_camera_close,R.anim.home_camera_back);
                break;
            case R.id.home_note:
            case R.id.home_video:
                MyUser myUser = BmobUser.getCurrentUser(MyUser.class);
                if (myUser.getHead()==null || myUser.getNickname()==null){
                    Toast.makeText(this,"请先设置昵称或头像",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(OpenCameraActivity.this, MineUserInfoSetting.class));
                }else {
                    startActivity(new Intent(this, NoteEditView.class));
                }
                finish();
                break;
        }
    }
}
