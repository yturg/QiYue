package com.xin.qiyue.fragment.home.child;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.xin.qiyue.QiApplication;
import com.xin.qiyue.R;
import com.xin.qiyue.apple.util.MyRecyclerViewAdapter;
import com.xin.qiyue.apple.xhs.note.NoteScanActivity;
import com.xin.qiyue.bean.Note;
import com.xin.qiyue.bean.Style;
import com.xin.qiyue.collecter.ErrorCollecter;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

import static com.xin.qiyue.data.SelectDataBmob.getStyleId;

/**
 * Created by zxj on 2017/7/26.
 */

public class HomeFragment_11 extends Fragment implements MyRecyclerViewAdapter.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {
    View view;
    RecyclerView recyclerView;
    MyRecyclerViewAdapter adapter;
    SwipeRefreshLayout swiperefreshlayout;
    List<Note> data = new ArrayList<>();
    SpacesItemDecoration space;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        getNoteByStyle("彩妆",true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view==null){
            view = inflater.inflate(R.layout.home_viewp_itemv11,container,false);
            initView(view);
        }else {
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            if(viewGroup != null){
                viewGroup.removeView(view);
            }
        }
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void initView(View view) {
        recyclerView = view.findViewById(R.id.homeFragment11);
        swiperefreshlayout = view.findViewById(R.id.swiperefreshlayout11);
        swiperefreshlayout.setColorSchemeColors(getResources().getColor(R.color.xhsColor));
        swiperefreshlayout.setOnRefreshListener(this);
    }
    private void initPagerView() {
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        if(space==null){
            space = new SpacesItemDecoration(20);
            recyclerView.addItemDecoration(space);
        }
        adapter = new MyRecyclerViewAdapter(data);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(getActivity(), NoteScanActivity.class);
        intent.putExtra("userdata", data.get(position));
        startActivity(intent);
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getNoteByStyle("彩妆",false);
                initPagerView();
                swiperefreshlayout.setRefreshing(false);
            }
        },2000);
    }

    //设置item外边距
    public class SpacesItemDecoration extends RecyclerView.ItemDecoration{
        int space = 0;
        public SpacesItemDecoration(int space){
            this.space = space;
        }
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state){
            outRect.left=space;
            outRect.right=space;
            outRect.bottom=space*2;
            if(parent.getChildAdapterPosition(view)==0||parent.getChildAdapterPosition(view)==1){
                outRect.top=space;
            }
        }
    }
    public void getNoteByStyle(String styleName,boolean isCache){
        BmobQuery<Note> query = new BmobQuery<Note>();
        Style style = new Style();
        style.setObjectId(getStyleId(styleName));
        query.addWhereRelatedTo("note",new BmobPointer(style));
        query.order("-createdAt");
        query.include("author");
        if(isCache){
            query.setCachePolicy(BmobQuery.CachePolicy.CACHE_ELSE_NETWORK);    // 第一次进入的话，则设置策略为CACHE_ELSE_NETWORK
        }else{
            query.setCachePolicy(BmobQuery.CachePolicy.NETWORK_ELSE_CACHE);    // 下拉刷新的话，则设置策略为NETWORK_ELSE_CACHE
        }
        query.findObjects(new FindListener<Note>() {
            @Override
            public void done(final List<Note> notelist, BmobException e) {
                if(e==null){
                    data = notelist;
                    initPagerView();
                }else{
                    Toast.makeText(QiApplication.getContext(), ErrorCollecter.errorCode(e), Toast.LENGTH_SHORT).show();
                    Log.i("bmob",e + "查询笔记失败");
                }
            }
        });
    }
}

