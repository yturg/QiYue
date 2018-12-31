package com.xin.qiyue.fragment.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.xin.qiyue.widget.InfoSettingTitle;
import com.xin.qiyue.R;
import com.xin.qiyue.base.BaseActivity;
import com.xin.qiyue.bean.MyUser;

import butterknife.BindView;
import cn.bmob.v3.BmobUser;

/**
 * Created by zxj on 2019-1-1.
 */

public class MineSettingID extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.reset_id)
    InfoSettingTitle resetid;
    @BindView(R.id.id_reset)
    EditText idReset;
    @BindView(R.id.id_tips)
    TextView idTips;

    MyUser myUser = BmobUser.getCurrentUser(MyUser.class);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setViewLisener();

        Intent intent = getIntent();
        String getId = intent.getStringExtra("currentid");
        idReset.setText(getId);

    }

    @Override
    public int getContentViewId() {
        return R.layout.mine_setting_id;
    }

    private void setViewLisener() {
        resetid.setImgListener(this);
        resetid.setDoneListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.my_setting_back:
                setResult(0,new Intent(this,MineUserInfoSetting.class));
                finish();
                break;
            case R.id.my_setting_done:
                String string = idReset.getText().toString();
                if (!myUser.getCopyId().equals(string) && myUser.getChange()){
                    Toast.makeText(this,"ID只能修改一次",Toast.LENGTH_SHORT).show();
                    return;
                }else if(string.equals("")){
                    Toast.makeText(this,"名字不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent1 = new Intent(MineSettingID.this,MineUserInfoSetting.class);
                intent1.putExtra("id",string);
                setResult(2,intent1);
                finish();
                break;
        }
    }
}
