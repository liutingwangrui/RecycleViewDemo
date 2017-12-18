package com.recycleviewdemo.activity;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.recycleviewdemo.R;
import com.recycleviewdemo.adapter.GroupContentAdapter;
import com.recycleviewdemo.bean.ClassBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/12/14.
 */

public class GroupActivity extends BaseActivity {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    private List<ClassBean> datas = new ArrayList<>();
    private ClassBean classBean;
    private GroupContentAdapter sitesAdapter;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_group;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initData();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        GroupContentAdapter columnsAdapter = new GroupContentAdapter(this, datas);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));

        recyclerView.setAdapter(columnsAdapter);

        columnsAdapter.setOnItemClickLitener(new GroupContentAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(String name) {
                Toast.makeText(GroupActivity.this,name,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(String name) {
                Toast.makeText(GroupActivity.this,name+"长按了",Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void initData() {
        for (int i = 1; i < 4; i++) {
            List<String> students = new ArrayList<>();
            for (int j = 1; j < 10; j++) {
                students.add(i + "班 学生" + j);
            }
            classBean = new ClassBean();
            classBean.setClassName("二年级" + i + "班");
            classBean.setClassStudents(students);
            datas.add(classBean);
            Log.e("zzzzzzzz", classBean.toString() + "----------");
            Log.e("zzzzzzzz", datas.size() + "----------");

        }
    }
}
