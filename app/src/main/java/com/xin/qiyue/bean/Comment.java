package com.xin.qiyue.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by zxj on 2019-1-1.
 */

public class Comment extends BmobObject {
    private String content;
    private MyUser user;
    private Note note;

    public Comment() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MyUser getUser() {
        return user;
    }

    public void setUser(MyUser user) {
        this.user = user;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }
}
