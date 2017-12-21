package com.recycleviewdemo.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.recycleviewdemo.R;
import com.recycleviewdemo.adapter.ShowItemAdapter;
import com.recycleviewdemo.bean.ShowItemBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/12/20.
 */

public class ShowItemActivity extends BaseActivity {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    private List<ShowItemBean> list;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_showitem;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initData();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.addItemDecoration(new RecycleViewDivider(this,LinearLayout.HORIZONTAL));
        ShowItemAdapter showItemAdapter=new ShowItemAdapter(this,list);
        recyclerView.setAdapter(showItemAdapter);
        showItemAdapter.setOnScrollListener(new ShowItemAdapter.OnScrollListener() {
            @Override
            public void scrollTo(int pos) {
                recyclerView.scrollToPosition(pos);
            }
        });

    }

    private void initData() {
        list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            ShowItemBean showItemBean = new ShowItemBean();
            showItemBean.setID(i + "");
            showItemBean.setType(0);
            showItemBean.setParentContent("父内容" + i);
            showItemBean.setChildContent("字内容" + i);
            list.add(showItemBean);
        }




    }
}
