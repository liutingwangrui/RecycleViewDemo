package com.recycleviewdemo.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.recycleviewdemo.R;
import com.recycleviewdemo.adapter.DeleteItemAdapter;
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
 * Created by Administrator on 2017/12/13.
 * 侧拉删除
 */

public class DeleteItemActivity extends BaseActivity {
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    private List<String> datas = new ArrayList<>();
    private LikeListAdapter likeListAdapter;
    private LoadMoreWrapper loadMoreWrapper;
    private int pager = 1;
    private DeleteItemAdapter deleteItemAdapter;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_delete_item;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initData();
        //设置刷新控件颜色
        swipeRefreshLayout.setColorSchemeColors(Color.parseColor("#4DB6AC"));
        deleteItemAdapter = new DeleteItemAdapter(this, datas);
        loadMoreWrapper = new LoadMoreWrapper(deleteItemAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new RecycleViewDivider(this, LinearLayout.HORIZONTAL, R.drawable.divider));
        recyclerView.setAdapter(loadMoreWrapper);

        //侧拉删除
        deleteItemAdapter.setOnDelListener(new DeleteItemAdapter.onSwipeListener() {
            @Override
            public void onDel(int pos) {
                Toast.makeText(DeleteItemActivity.this, pos + "c", Toast.LENGTH_SHORT).show();
                datas.remove(pos);
                loadMoreWrapper.notifyItemRemoved(pos);//推荐用这个
                if (pos != (datas.size())) { // 如果移除的是最后一个，忽略 注意：这里的mDataAdapter.getDataList()不需要-1，因为上面已经-1了
                    loadMoreWrapper.notifyItemRangeChanged(pos, datas.size() - pos);
                }
            }
        });

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
                loadMoreWrapper.notifyDataSetChanged();
                swipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing()) {
                            swipeRefreshLayout.setRefreshing(false);
                            Toast.makeText(DeleteItemActivity.this, "刷新成功", Toast.LENGTH_SHORT).show();
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
                                    Toast.makeText(DeleteItemActivity.this, "加载完成", Toast.LENGTH_SHORT).show();
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
}
