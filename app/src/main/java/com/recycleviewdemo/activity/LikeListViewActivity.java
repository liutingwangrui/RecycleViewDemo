package com.recycleviewdemo.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.recycleviewdemo.R;
import com.recycleviewdemo.adapter.LikeListAdapter;
import com.recycleviewdemo.adapter.LoadMoreWrapper;
import com.recycleviewdemo.listener.EndlessRecyclerOnScrollListener;
import com.recycleviewdemo.view.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/12/12.
 * 基本使用
 */

public class LikeListViewActivity extends BaseActivity {


    @BindView(R.id.swipe)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.tv)
    TextView textView;

    private List<String> datas = new ArrayList<>();
    private List<String> tollDatas = new ArrayList<>();
    private LikeListAdapter likeListAdapter;
    private LoadMoreWrapper loadMoreWrapper;
    private boolean isRefresh;
    private boolean isLoadMore;
    private int pager = 1;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_like_listview;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {


        initData();
        datas.clear();
        if (datas.size()>0){
            recyclerView.setVisibility(View.VISIBLE);
            textView.setVisibility(View.GONE);

        }else{
            recyclerView.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);

        }
        //设置刷新控件颜色
        swipeRefreshLayout.setColorSchemeColors(Color.parseColor("#4DB6AC"));
        likeListAdapter = new LikeListAdapter(this, datas);
        loadMoreWrapper = new LoadMoreWrapper(likeListAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new RecycleViewDivider(this, LinearLayout.HORIZONTAL, R.drawable.divider));
        recyclerView.setAdapter(loadMoreWrapper);

        setRefreshAndLoad();


    }

    private void setRefreshAndLoad() {
        //设置下拉刷新
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pager = 1;
                datas.clear();
                initData();
                if (datas.size()>0){
                    recyclerView.setVisibility(View.VISIBLE);
                    textView.setVisibility(View.GONE);

                }else{
                    recyclerView.setVisibility(View.GONE);
                    textView.setVisibility(View.VISIBLE);

                }
                loadMoreWrapper.notifyDataSetChanged();

                swipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing()) {
                            swipeRefreshLayout.setRefreshing(false);
                            Toast.makeText(LikeListViewActivity.this, "刷新成功", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, 1000);

            }
        });

        //设置下拉加载更多监听
        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING);
                //延迟1秒获取数据
                if (pager < 2) {
                    pager++;
                    Log.e("xxxxxxxxxx", pager + "-------------");
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    loadMoreData();
                                    loadMoreWrapper.notifyDataSetChanged();
                                    loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING_COMPLETE);
                                    Toast.makeText(LikeListViewActivity.this, "加载完成", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }, 1000);
                } else {
                    Log.e("xxxxxxxxxx", "11111111111111111");
                    loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING_END);
                }

            }
        });


    }

    /**
     * 加载的数据
     */
    private void loadMoreData() {

        for (int i = 0; i < 2; i++) {
            datas.add("这是加载的数据" + i);
        }

    }

    private void initData() {
        for (int i = 0; i < 20; i++) {
            datas.add("第" + i + "条数据");
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.layout_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                break;
            case R.id.items:
                recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
//                recyclerView.addItemDecoration(new RecycleViewDivider(this, LinearLayout.VERTICAL, R.drawable.divider));
                break;
        }
        datas.clear();
        pager=1;
        initData();
        if (datas.size()>0){
            recyclerView.setVisibility(View.VISIBLE);
            textView.setVisibility(View.GONE);

        }else{
            recyclerView.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);

        }
        recyclerView.setAdapter(loadMoreWrapper);


        return super.onOptionsItemSelected(item);
    }
}
