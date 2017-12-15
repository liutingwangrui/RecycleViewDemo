package com.recycleviewdemo.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.recycleviewdemo.R;
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

    @Override
    protected int getLayoutID() {
        return R.layout.activity_group;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initData();
//        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
//        GroupContentAdapter groupContentAdapter = new GroupContentAdapter(this, datas);
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
//        recyclerView.setAdapter(groupContentAdapter);

    }

    private void initData() {
        for (int i = 0; i < 3; i++) {
            List<String> students = new ArrayList<>();
            for (int j = 0; j < 20; j++) {
                students.add(i + "班 学生" + j);
            }
            ClassBean classBean = new ClassBean();
            classBean.setClassName("二年级" + i + "班");
            classBean.setClassStudents(students);
            datas.add(classBean);
        }
    }
}
