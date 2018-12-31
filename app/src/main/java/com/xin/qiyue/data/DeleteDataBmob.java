package com.xin.qiyue.data;

import android.util.Log;
import android.widget.Toast;

import com.xin.qiyue.QiApplication;
import com.xin.qiyue.bean.MyUser;
import com.xin.qiyue.bean.Note;
import com.xin.qiyue.collecter.ErrorCollecter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import cn.bmob.v3.AsyncCustomEndpoints;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobRelation;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.CloudCodeListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by zxj on 2019-1-1.
 */

public class DeleteDataBmob {
    //取消收藏
    public static void deleteLikes(final Note note){
        final MyUser my = BmobUser.getCurrentUser(MyUser.class);
        BmobRelation relation = new BmobRelation();
        relation.remove(note);
        my.setLikes(relation);
        my.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){
                    UpdateDataBmob.delUp(note);
                    Toast.makeText(QiApplication.getContext(),"取消收藏成功",Toast.LENGTH_SHORT).show();
                    QiApplication.setShoucang(QiApplication.getShoucang()-1);
                    Log.i("bmob","取消收藏成功：" + "用户<" + my.getNickname() + ">取消收藏了笔记<" + note.getTitle() + ">");
                }else{
                    Toast.makeText(QiApplication.getContext(),ErrorCollecter.errorCode(e),Toast.LENGTH_SHORT).show();
                    Log.i("bmob","取消收藏失败："+e.getMessage() + e.getErrorCode());
                }
            }
        });
    }

    //删除多对多关系(关注）
    public static void deleteAttention(final String otherId){
        final MyUser my = BmobUser.getCurrentUser(MyUser.class);
        String cloudCodeName = "deleteGuanzhu";
        JSONObject params = new JSONObject();
        try {
            params.put("myId",my.getObjectId());
            params.put("otherId",otherId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        AsyncCustomEndpoints cloudCode = new AsyncCustomEndpoints();
        cloudCode.callEndpoint(cloudCodeName,params, new CloudCodeListener() {
            @Override
            public void done(Object o, BmobException e) {
                if (e==null){
                    Toast.makeText(QiApplication.getContext(),o.toString(),Toast.LENGTH_SHORT).show();
                    QiApplication.setGuanzhu(QiApplication.getGuanzhu()-1);
                    Log.i("bmob","执行云端取消关注方法成功，返回：" + o.toString());
                }else {
                    Toast.makeText(QiApplication.getContext(), ErrorCollecter.errorCode(e),Toast.LENGTH_SHORT).show();
                    Log.i("bmob","执行云端取消关注方法失败：" + e.getMessage() + e.getErrorCode());
                }
            }
        });
    }

    //删除笔记
    public static void deleteNote(final Note note){
        note.delete(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e==null){
                    Toast.makeText(QiApplication.getContext(),"删除笔记成功",Toast.LENGTH_SHORT).show();
                    QiApplication.setFabu(QiApplication.getFabu()-1);
                    Log.i("bmob","删除笔记成功：" + note.getTitle());
                }else {
                    Toast.makeText(QiApplication.getContext(),ErrorCollecter.errorCode(e),Toast.LENGTH_SHORT).show();
                    Log.i("bmob","删除笔记失败：" + e.getErrorCode() + e.getMessage());
                }
            }
        });
    }

    //清空历史搜索
    public static void deleteHistory(){
        MyUser user = BmobUser.getCurrentUser(MyUser.class);
        user.remove("history");
        user.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e==null){
                    Toast.makeText(QiApplication.getContext(),"清空历史成功",Toast.LENGTH_SHORT).show();
                    Log.i("bmob","清空历史成功");
                }else {
                    Toast.makeText(QiApplication.getContext(),ErrorCollecter.errorCode(e),Toast.LENGTH_SHORT).show();
                    Log.i("bmob","清空历史失败：" + e.getMessage() + e.getErrorCode());
                }
            }
        });
    }

    //删除单个搜索记录
    public static void deleteHisOne(String ss){
        MyUser user = BmobUser.getCurrentUser(MyUser.class);
        user.removeAll("history", Arrays.asList(ss));
        user.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e==null){

                }else {

                }
            }
        });
    }
}
