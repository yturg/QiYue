package com.xin.qiyue.apple.xhs.note;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.xin.qiyue.R;
import com.xin.qiyue.base.BaseActivity;

import butterknife.BindView;
import me.xiaopan.sketch.SketchImageView;
import me.xiaopan.sketch.viewfun.zoom.ImageZoomer;

/**
 * Created by zxj on 2017/8/1.
 */

public class NoteEditShowBigPicActivity extends BaseActivity {
    @BindView(R.id.show_big_pic)
    SketchImageView imageView;

    @Override
    public int getContentViewId() {
        return R.layout.note_edit_showbigpic;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        imageView.setZoomEnabled(true);
        ImageZoomer imageZoomer = new ImageZoomer(imageView);
        imageZoomer.zoom(3f, true);
        imageView.displayImage(getIntent().getStringExtra("showbigpic"));
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.showbigpic_out, R.anim.showbigpic_out_big);
            }
        });
    }
}
