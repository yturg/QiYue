package com.xin.qiyue.bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobRelation;

/**
 * Created by zxj on 2019-1-1.
 */

public class Style extends BmobObject {
    private String name;
    private BmobRelation note;

    public Style() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BmobRelation getNote() {
        return note;
    }

    public void setNote(BmobRelation note) {
        this.note = note;
    }
}
