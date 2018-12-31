package com.xin.qiyue.data;


import android.util.Log;
import android.widget.Toast;

import com.xin.qiyue.QiApplication;
import com.xin.qiyue.bean.MyUser;
import com.xin.qiyue.bean.Note;
import com.xin.qiyue.collecter.ErrorCollecter;


import java.util.List;
import java.util.Map;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;

/**
 * Created by zxj on 2019-1-1.
 */

public class UpdateDataBmob {

    //更新头像
    public static void UpdataHead(final BmobFile bmobFile){
        final MyUser user = BmobUser.getCurrentUser(MyUser.class);
        bmobFile.uploadblock(new UploadFileListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){
                    MyUser myUser = new MyUser();
                    myUser.setValue("head",bmobFile);
                    myUser.update(user.getObjectId(), new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if(e==null){
                                Toast.makeText(QiApplication.getContext(),"头像更新成功", Toast.LENGTH_SHORT).show();
                                Log.i("bmob","头像更新成功");
                            }else{
                                Toast.makeText(QiApplication.getContext(), ErrorCollecter.errorCode(e), Toast.LENGTH_SHORT).show();
                                Log.i("bmob","头像更新失败："+e.getMessage()+","+e.getErrorCode());
                            }
                        }
                    });
                }else{
                    Toast.makeText(QiApplication.getContext(), ErrorCollecter.errorCode(e), Toast.LENGTH_SHORT).show();
                    Log.i("bmob","头像图片上传失败：" + e.getMessage() + e.getErrorCode());
                }
            }
        });
    }

    //更改昵称
    public static void UpdataNickname(String name){
        final MyUser myUser = BmobUser.getCurrentUser(MyUser.class);
        MyUser user = new MyUser();
        user.setValue("nickname",name);
        user.update(myUser.getObjectId(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){
                    Toast.makeText(QiApplication.getContext(), "昵称更新成功", Toast.LENGTH_SHORT).show();
                    Log.i("bmob","昵称更新成功");
                }else{
                    Toast.makeText(QiApplication.getContext(), ErrorCollecter.errorCode(e), Toast.LENGTH_SHORT).show();
                    Log.i("bmob","昵称更新失败："+e.getMessage()+","+e.getErrorCode());
                }
            }
        });
    }

    //初始化ID
    public static void UpdataIDNew(String id){
        final MyUser myUser = BmobUser.getCurrentUser(MyUser.class);
        MyUser user = new MyUser();
        user.setValue("CopyId",id);
        user.setValue("Change",false);
        user.update(myUser.getObjectId(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){
                    Toast.makeText(QiApplication.getContext(), "ID初始化成功", Toast.LENGTH_SHORT).show();
                    Log.i("bmob","ID初始化成功");
                }else{
                    Toast.makeText(QiApplication.getContext(), ErrorCollecter.errorCode(e), Toast.LENGTH_SHORT).show();
                    Log.i("bmob","ID初始化失败："+e.getMessage()+","+e.getErrorCode());
                }
            }
        });
    }

    //更改ID
    public static void UpdataID(String id){
        final MyUser myUser = BmobUser.getCurrentUser(MyUser.class);
        MyUser user = new MyUser();
        user.setValue("CopyId",id);
        user.setValue("Change",true);
        user.update(myUser.getObjectId(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){
                    Toast.makeText(QiApplication.getContext(), "ID更新成功", Toast.LENGTH_SHORT).show();
                    Log.i("bmob","ID更新成功");
                }else{
                    Toast.makeText(QiApplication.getContext(), ErrorCollecter.errorCode(e), Toast.LENGTH_SHORT).show();
                    Log.i("bmob","ID更新失败："+e.getMessage()+","+e.getErrorCode());
                }
            }
        });
    }

    //更改性别
    public static void UpdataSex(boolean sex){
        final MyUser myUser = BmobUser.getCurrentUser(MyUser.class);
        MyUser user = new MyUser();
        user.setValue("sex",sex);
        user.update(myUser.getObjectId(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){
                    Toast.makeText(QiApplication.getContext(), "性别更新成功", Toast.LENGTH_SHORT).show();
                    Log.i("bmob","性别更新成功");
                }else{
                    Toast.makeText(QiApplication.getContext(), ErrorCollecter.errorCode(e), Toast.LENGTH_SHORT).show();
                    Log.i("bmob","性别更新失败："+e.getMessage()+","+e.getErrorCode());
                }
            }
        });
    }

    //更改生日
    public static void UpdataBirthday(String birth){
        final MyUser myUser = BmobUser.getCurrentUser(MyUser.class);
        MyUser user = new MyUser();
        user.setValue("birthday",birth);
        user.update(myUser.getObjectId(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){
                    Toast.makeText(QiApplication.getContext(), "生日更新成功", Toast.LENGTH_SHORT).show();
                    Log.i("bmob","生日更新成功");
                }else{
                    Toast.makeText(QiApplication.getContext(), ErrorCollecter.errorCode(e), Toast.LENGTH_SHORT).show();
                    Log.i("bmob","生日更新失败："+e.getMessage()+","+e.getErrorCode());
                }
            }
        });
    }

    //更改常住地
    public static void UpdataArea(String area){
        final MyUser myUser = BmobUser.getCurrentUser(MyUser.class);
        MyUser user = new MyUser();
        user.setAddress(area);
        user.update(myUser.getObjectId(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){
                    Toast.makeText(QiApplication.getContext(), "常住地更新成功", Toast.LENGTH_SHORT).show();
                    Log.i("bmob","常住地更新成功");
                }else{
                    Toast.makeText(QiApplication.getContext(), ErrorCollecter.errorCode(e), Toast.LENGTH_SHORT).show();
                    Log.i("bmob","常住地更新失败："+e.getMessage()+","+e.getErrorCode());
                }
            }
        });
    }

    //更改个性签名
    public static void UpdataSignature(String signature){
        final MyUser myUser = BmobUser.getCurrentUser(MyUser.class);
        MyUser user = new MyUser();
        user.setValue("signature",signature);
        user.update(myUser.getObjectId(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){
                    Toast.makeText(QiApplication.getContext(), "签名更新成功", Toast.LENGTH_SHORT).show();
                    Log.i("bmob","签名更新成功");
                }else{
                    Toast.makeText(QiApplication.getContext(), ErrorCollecter.errorCode(e), Toast.LENGTH_SHORT).show();
                    Log.i("bmob","签名更新失败："+e.getMessage()+","+e.getErrorCode());
                }
            }
        });
    }

    //更改肤质
    public static void UpdataSkin(List<Map<Integer,String>> skin){
        final MyUser myUser = BmobUser.getCurrentUser(MyUser.class);
        MyUser user = new MyUser();
        user.setValue("skin",skin);
        user.update(myUser.getObjectId(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){
                    Toast.makeText(QiApplication.getContext(), "肤质更新成功", Toast.LENGTH_SHORT).show();
                    Log.i("bmob","肤质更新成功");
                }else{
                    Toast.makeText(QiApplication.getContext(), ErrorCollecter.errorCode(e), Toast.LENGTH_SHORT).show();
                    Log.i("bmob","肤质更新失败："+e.getMessage()+","+e.getErrorCode());
                }
            }
        });
    }

    //更改母婴
    public static void UpdataPregnant(String pregnant){
        final MyUser myUser = BmobUser.getCurrentUser(MyUser.class);
        MyUser user = new MyUser();
        user.setValue("pregnant",pregnant);
        user.update(myUser.getObjectId(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){
                    Toast.makeText(QiApplication.getContext(), "母婴更新成功", Toast.LENGTH_SHORT).show();
                    Log.i("bmob","母婴更新成功");
                }else{
                    Toast.makeText(QiApplication.getContext(), ErrorCollecter.errorCode(e), Toast.LENGTH_SHORT).show();
                    Log.i("bmob","母婴更新失败："+e.getMessage()+","+e.getErrorCode());
                }
            }
        });
    }

    //点赞
    public static void clickUp(final Note note){
        note.increment("up",1);
        note.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e==null){
                    Log.i("bmob","笔记<" + note.getTitle() + ">被收藏次数+1，总次数：" + note.getUp());
                }else {
                    Toast.makeText(QiApplication.getContext(), ErrorCollecter.errorCode(e), Toast.LENGTH_SHORT).show();
                    Log.i("bmob","笔记<" + note.getTitle() + ">被收藏次数增加失败，总次数：" + note.getUp());
                }
            }
        });
    }

    //取消点赞
    public static void delUp(final Note note){
        note.increment("up",-1);
        note.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e==null){
                    Log.i("bmob","笔记<" + note.getTitle() + ">被收藏次数-1，总次数：" + note.getUp());
                }else {
                    Toast.makeText(QiApplication.getContext(), ErrorCollecter.errorCode(e), Toast.LENGTH_SHORT).show();
                    Log.i("bmob","笔记<" + note.getTitle() + ">被收藏次数减少失败，总次数：" + note.getUp());
                }
            }
        });
    }

    //密码修改
    public static void updatePwd(String old,String pwd){
        MyUser user = BmobUser.getCurrentUser(MyUser.class);
        user.updateCurrentUserPassword(old, pwd, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e==null){
                    Toast.makeText(QiApplication.getContext(), "密码修改成功", Toast.LENGTH_SHORT).show();
                    Log.i("bmob","密码修改成功");
                }else {
                    Toast.makeText(QiApplication.getContext(), ErrorCollecter.errorCode(e), Toast.LENGTH_SHORT).show();
                    Log.i("bmob","密码修改失败：" + e.getMessage() + e.getErrorCode());
                }
            }
        });
    }
}
