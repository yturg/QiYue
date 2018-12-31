package com.xin.qiyue.apple.xhs.note;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;

import com.xin.qiyue.R;
import com.xin.qiyue.widget.CommentModule;
import com.xin.qiyue.widget.InfoSettingTitle;
import com.xin.qiyue.widget.NoteDivideLine;
import com.xin.qiyue.base.BaseActivity;
import com.xin.qiyue.bean.Comment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.xiaopan.sketch.process.CircleImageProcessor;
import me.xiaopan.sketch.request.DisplayOptions;

/**
 * Created by zxj on 2017/8/3.
 */

public class NoteAllPinglunActivity extends BaseActivity {
    @BindView(R.id.allpingluntoolbar)
    InfoSettingTitle toolbar;
    @BindView(R.id.allpinglunparent)
    LinearLayout parent;
    DisplayOptions displayOptions;
    List<Comment> list = new ArrayList<>();
    @Override
    public int getContentViewId() {
        return R.layout.note_showallpinglun;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        displayOptions = new DisplayOptions();
        displayOptions.setImageProcessor(CircleImageProcessor.getInstance());
        initData();
        toolbar.setImgListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initData() {
        Intent intent = getIntent();
        list = (List<Comment>) intent.getSerializableExtra("allpinglun");
        toolbar.getTextView().setText(list.size()+"条评论");
        for(int i = 0;i < list.size() ;i++){
            Comment comment = list.get(i);
            String url = comment.getUser().getHead().getUrl();
            String nickname = comment.getUser().getNickname();
            String createdAt = comment.getCreatedAt();
            String content = comment.getContent();
            CommentModule module = new CommentModule(this,null);
            module.getHeadPic().setOptions(displayOptions);
            module.getHeadPic().displayImage(url);
            module.getUserContent().setText(content);
            module.getUserName().setText(nickname);
            module.getPushDate().setText(createdAt);
            NoteDivideLine line = new NoteDivideLine(this);
            parent.addView(module);
            parent.addView(line);
        }
    }
}
