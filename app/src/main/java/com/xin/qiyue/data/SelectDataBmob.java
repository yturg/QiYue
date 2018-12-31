package com.xin.qiyue.data;

import android.util.Log;
import android.widget.Toast;

import com.xin.qiyue.QiApplication;
import com.xin.qiyue.bean.Comment;
import com.xin.qiyue.bean.Hot;
import com.xin.qiyue.bean.MyUser;
import com.xin.qiyue.bean.Note;
import com.xin.qiyue.bean.Style;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by zxj on 2019-1-1.
 */

public class SelectDataBmob {

    //在Style表中查找styleName对应notes
    public static void getNoteByStyle(String styleName){
        BmobQuery<Note> query = new BmobQuery<Note>();
        Style style = new Style();
        style.setObjectId(getStyleId(styleName));
        query.addWhereRelatedTo("note",new BmobPointer(style));
        query.findObjects(new FindListener<Note>() {
            @Override
            public void done(List<Note> list, BmobException e) {
                if(e==null){

                }else{
                    Log.i("bmob",e + "");
                }
            }
        });
    }

    //获取本人的笔记
    public static void getMineNote(){
        MyUser myUser = BmobUser.getCurrentUser(MyUser.class);
        BmobQuery<Note> query = new BmobQuery<Note>();
        query.addWhereEqualTo("author",myUser);
        query.findObjects(new FindListener<Note>() {
            @Override
            public void done(List<Note> list, BmobException e) {
                if(e==null){
                    Log.i("bmob","成功");
                }else{
                    Log.i("bmob","获取本人笔记失败："+e.getMessage() + e.getErrorCode());
                }
            }
        });
    }

    //关注列表
    public void selectAttentions(){
        MyUser myUser = BmobUser.getCurrentUser(MyUser.class);
        BmobQuery<MyUser> query = new BmobQuery<MyUser>();
        query.addWhereRelatedTo("attention",new BmobPointer(myUser));
        query.findObjects(new FindListener<MyUser>() {
            @Override
            public void done(List<MyUser> list, BmobException e) {
                if (e==null){

                }else {
                    Log.i("bmob","获取关注列表失败："+e.getMessage() + e.getErrorCode());
                }
            }
        });
    }

    //粉丝列表
    public void selectFans(){
        MyUser myUser = BmobUser.getCurrentUser(MyUser.class);
        BmobQuery<MyUser> query = new BmobQuery<MyUser>();
        query.addWhereRelatedTo("fans",new BmobPointer(myUser));
        query.findObjects(new FindListener<MyUser>() {
            @Override
            public void done(List<MyUser> list, BmobException e) {
                if (e==null){

                }else {
                    Log.i("bmob","获取粉丝列表失败："+e.getMessage() + e.getErrorCode());
                }
            }
        });
    }

    //我的收藏
    public void selectLikes(){
        MyUser myUser = BmobUser.getCurrentUser(MyUser.class);
        BmobQuery<Note> query = new BmobQuery<Note>();
        query.addWhereRelatedTo("likes",new BmobPointer(myUser));
        query.findObjects(new FindListener<Note>() {
            @Override
            public void done(List<Note> list, BmobException e) {
                if (e==null){

                }else {
                    Log.i("bmob","获取收藏列表失败："+e.getMessage() + e.getErrorCode());
                }
            }
        });
    }

    //模糊查询(笔记)
    public void selectMore(final String ss){
        BmobQuery<Note> query = new BmobQuery<Note>();
        query.include("author");
        query.findObjects(new FindListener<Note>() {
            @Override
            public void done(List<Note> list, BmobException e) {
                AddDataBmob.addHistory(ss);
                AddDataBmob.addHot(ss);
                if (e==null){
                    List<Note> newList = new ArrayList<>();
                    for (Note note:list){
                        if (note.getTitle().contains(ss)){
                            newList.add(note);
                        }
                    }
                    Log.i("bmob","结果个数：" + newList.size());
                    if (newList.size()==0){
//                        Toast.makeText(QiApplication.getContext(),"结果不存在",Toast.LENGTH_SHORT).show();
                    }else {
                        //代码块
                    }
                }else {
                    Log.i("bmob","模糊查询失败" + e.getErrorCode() + e.getMessage());
                }
            }
        });
    }

    //模糊查询(用户)
    public void selectUser(final String ss){
        BmobQuery<MyUser> query = new BmobQuery<MyUser>();
        query.findObjects(new FindListener<MyUser>() {
            @Override
            public void done(List<MyUser> list, BmobException e) {
                AddDataBmob.addHistory(ss);
                AddDataBmob.addHot(ss);
                if (e==null){
                    List<MyUser> newList = new ArrayList<>();
                    for (MyUser user:list){
                        if (user.getNickname().contains(ss)){
                            newList.add(user);
                        }
                    }
                    Log.i("bmob","结果个数：" + newList.size());
                    if (newList.size()==0){
//                        Toast.makeText(QiApplication.getContext(),"结果不存在",Toast.LENGTH_SHORT).show();
                    }else {
                        //代码块
                    }
                }else {
                    Log.i("bmob","模糊查询失败" + e.getErrorCode() + e.getMessage());
                }
            }
        });
    }

    //获取前16热门搜索
    public void selectHot(){
        BmobQuery<Hot> query = new BmobQuery<Hot>();
        query.order("-number");
        query.setLimit(16);
        query.findObjects(new FindListener<Hot>() {
            @Override
            public void done(List<Hot> list, BmobException e) {
                if (e==null){

                }else {
                    Log.i("bmob","热门搜索查询失败" + e.getErrorCode() + e.getMessage());
                }
            }
        });
    }

    //查询评论-第一页
    public void selectComment(Note note){
        BmobQuery<Comment> query = new BmobQuery<Comment>();
        query.addWhereEqualTo("note",note);
        query.order("-createdAt");
        query.include("user");
        query.setLimit(3);
        query.findObjects(new FindListener<Comment>() {
            @Override
            public void done(List<Comment> list, BmobException e) {
                if (e==null){
                    Toast.makeText(QiApplication.getContext(),"查询到" + list.size() + "条评论",Toast.LENGTH_SHORT).show();
                }else {
                    Log.i("bmob","查询评论失败" + e.getErrorCode() + e.getMessage());
                }
            }
        });
    }

    //查询自己关注的人的笔记列表
    public void selectGuanzhu(){
        MyUser user = BmobUser.getCurrentUser(MyUser.class);
        BmobQuery<MyUser> queryUser = new BmobQuery<MyUser>();
        queryUser.addWhereRelatedTo("attention",new BmobPointer(user));
        BmobQuery<Note> queryNote = new BmobQuery<Note>();
        queryNote.addWhereMatchesQuery("author","MyUser",queryUser);
        queryNote.findObjects(new FindListener<Note>() {
            @Override
            public void done(List<Note> list, BmobException e) {
                if (e==null){

                }else {

                }
            }
        });
    }

    //该用户是否被关注
    public void isGuanzhu(MyUser other){
        MyUser user = BmobUser.getCurrentUser(MyUser.class);
        BmobQuery<MyUser> query1 = new BmobQuery<MyUser>();
        BmobQuery<MyUser> query2 = new BmobQuery<MyUser>();
        query1.addWhereRelatedTo("attention",new BmobPointer(user));

    }

    public static String getStyleId(String name){
        switch (name){
            case "武汉": return "Nbze4449";
            case "母婴": return "HEXG999Q";
            case "彩妆": return "Rbun1116";
            case "旅行": return "rAuZFFFs";
            case "运动": return "sdQ1777d";
            case "美食": return "hBaF999C";
            case "时尚": return "byZHwww1";
            case "居家": return "cSvsVVVu";
            case "护肤": return "jeM5sssz";
            case "男人": return "N7cyXXXz";
            default: return null;
        }
    }
}
