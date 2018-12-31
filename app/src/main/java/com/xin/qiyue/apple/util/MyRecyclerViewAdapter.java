package com.xin.qiyue.apple.util;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.xin.qiyue.R;
import com.xin.qiyue.bean.MyUser;
import com.xin.qiyue.bean.Note;
import com.xin.qiyue.data.AddDataBmob;
import com.xin.qiyue.data.DeleteDataBmob;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import me.xiaopan.sketch.SketchImageView;
import me.xiaopan.sketch.process.CircleImageProcessor;
import me.xiaopan.sketch.request.DisplayOptions;

/**
 * Created by zxj on 2017/7/28.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> implements View.OnClickListener {
    List<Note> data;
    View view;

    public MyRecyclerViewAdapter(List<Note> data) {
        this.data = data;
    }

    private OnItemClickListener mOnItemClickListener = null;

    //定义一个interface
    public static interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_grid_item, parent, false);
        view.setOnClickListener(this);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Note note = data.get(position);
        MyUser myUser = data.get(position).getAuthor();
        holder.imgPic.displayImage(note.getImage().get(0).getUrl());
        holder.textTitle.setText(note.getTitle());
        holder.textUserHead.displayImage(myUser.getHead().getUrl());
        holder.textUserName.setText(myUser.getNickname());
        holder.itemView.setTag(position);

        MyUser user = BmobUser.getCurrentUser(MyUser.class);
        BmobQuery<Note> query = new BmobQuery<Note>();
        query.addWhereRelatedTo("likes", new BmobPointer(user));
        query.findObjects(new FindListener<Note>() {
            @Override
            public void done(List<Note> list, BmobException e) {
                if (e == null) {
                    for (Note n : list) {
                        if (n.getObjectId().equals(note.getObjectId())) {
                            holder.checkBox.setChecked(true);
                        }
                    }
                    holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                            if (b) {
                                AddDataBmob.addLikes(note);
                            } else {
                                DeleteDataBmob.deleteLikes(note);
                            }
                        }
                    });
                } else {
                    Log.i("bmob", "获取收藏列表失败：" + e.getMessage() + e.getErrorCode());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onClick(View view) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(view, (int) view.getTag());
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        SketchImageView imgPic;
        TextView textTitle;
        TextView textUserName;
        SketchImageView textUserHead;
        CheckBox checkBox;

        public MyViewHolder(View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.grid_item_checkbox);
            // 获取img设置
            imgPic = itemView.findViewById(R.id.grid_item_pic);
            imgPic.setScaleType(SketchImageView.ScaleType.CENTER_CROP);
            imgPic.setAdjustViewBounds(true);
            textTitle = itemView.findViewById(R.id.grid_item_textTitle);
            textUserName = itemView.findViewById(R.id.grid_item_user);
            textUserHead = itemView.findViewById(R.id.grid_item_head);
            DisplayOptions displayOptions = new DisplayOptions();
            displayOptions.setImageProcessor(CircleImageProcessor.getInstance());
            textUserHead.setOptions(displayOptions);
        }
    }
}
