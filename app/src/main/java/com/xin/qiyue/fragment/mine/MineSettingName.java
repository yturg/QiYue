package com.xin.qiyue.fragment.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.xin.qiyue.widget.InfoSettingTitle;
import com.xin.qiyue.R;
import com.xin.qiyue.base.BaseActivity;
import com.xin.qiyue.data.UpdateDataBmob;

import butterknife.BindView;

/**
 * Created by zxj on 2019-1-1.
 */

public class MineSettingName extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.reset_name)
    InfoSettingTitle resetname;
    @BindView(R.id.my_new_name)
    EditText myNewName;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setViewLisener();
    }

    @Override
    public int getContentViewId() {
        return R.layout.mine_setting_name;
    }

    private void setViewLisener() {
        resetname.setImgListener(this);
        resetname.setDoneListener(this);
        Intent intent = getIntent();
        String getname = intent.getStringExtra("currentname");
        //Toast.makeText(this,getname,Toast.LENGTH_SHORT).show();
        myNewName.setText(getname);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.my_setting_back:
                setResult(0,new Intent(this,MineUserInfoSetting.class));
                finish();
                break;
            case R.id.my_setting_done:
                String string = myNewName.getText().toString();
                if(string.equals("")){
                    Toast.makeText(this,"名字不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                UpdateDataBmob.UpdataNickname(string);
                Intent intent1 = new Intent(MineSettingName.this,MineUserInfoSetting.class);
                intent1.putExtra("name",string);
                setResult(1,intent1);
                finish();
                break;
        }
    }










}
