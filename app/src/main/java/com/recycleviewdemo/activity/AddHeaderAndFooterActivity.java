package com.recycleviewdemo.activity;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.recycleviewdemo.R;
import com.recycleviewdemo.adapter.HeaderAndFootAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/12/15.
 */

public class AddHeaderAndFooterActivity extends BaseActivity {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    private List<String> datas = new ArrayList<>();

    @Override
    protected int getLayoutID() {
        return R.layout.activity_addheaderandfooter;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initData();
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
//        GroupContentAdapter groupContentAdapter = new GroupContentAdapter(this, datas);
        //初始化头部和尾部
//        HeaderAndFootAdapter headerAndFootAdapter = new HeaderAndFootAdapter(groupContentAdapter);
        HeaderAndFootAdapter headerAndFootAdapter = new HeaderAndFootAdapter(this, datas);


        TextView textView1 = new TextView(this);
        textView1.setText("当前是头部布局11111");
        textView1.setPadding(10, 10, 10, 10);
        textView1.setGravity(Gravity.CENTER);

        TextView textView2 = new TextView(this);
        textView2.setText("当前是头部布局22222");
        textView2.setPadding(10, 10, 10, 10);
        textView2.setGravity(Gravity.CENTER);

        TextView textView3 = new TextView(this);
        textView3.setText("当前是尾部布局11111");
        textView3.setPadding(10, 10, 10, 10);
        textView3.setGravity(Gravity.CENTER);


        headerAndFootAdapter.addHeaderView(textView1);
        headerAndFootAdapter.addHeaderView(textView2);
        headerAndFootAdapter.addFooterView(textView3);

        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerView.setAdapter(headerAndFootAdapter);

    }

    private void initData() {
        for (int i = 0; i < 20; i++) {
            datas.add("当前的内容是：" + i);
        }
    }
}
