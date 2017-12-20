package com.recycleviewdemo.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.recycleviewdemo.R;
import com.recycleviewdemo.adapter.NianXingAdapter;
import com.recycleviewdemo.bean.Car;
import com.recycleviewdemo.bean.CarsList;
import com.recycleviewdemo.bean.ClassBean;
import com.recycleviewdemo.view.NormalDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/12/18.
 * 粘性
 */

public class NianXingActivity extends BaseActivity {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    private ClassBean classBean;
    private List<ClassBean> datas = new ArrayList<>();

    @Override
    protected int getLayoutID() {
        return R.layout.activity_nianxing;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
//        initData();
        final List<Car> carList = CarsList.getCars();
        NianXingAdapter nianXingAdapter = new NianXingAdapter(this);
        nianXingAdapter.addDatas(carList);

        final NormalDecoration normalDecoration = new NormalDecoration() {
            @Override
            public String getHeaderName(int pos) {
                return carList.get(pos).getHeaderName();
            }
        };
        normalDecoration.setOnHeaderClickListener(new NormalDecoration.OnHeaderClickListener() {
            @Override
            public void headerClick(int pos) {
                Toast.makeText(NianXingActivity.this, carList.get(pos).getHeaderName(), Toast.LENGTH_SHORT).show();
            }
        });

        normalDecoration.setOnDecorationHeadDraw(new NormalDecoration.OnDecorationHeadDraw() {
            @Override
            public View getHeaderView(final int pos) {
                final View headView = LayoutInflater.from(NianXingActivity.this).inflate(R.layout.decoration_car_head_view, null);
                final TextView ivAvatar = (TextView) headView.findViewById(R.id.header_iv_avatar);
                normalDecoration.setTextView(carList.get(pos).getHeaderName(),ivAvatar);
//                Log.e("QDX", "view inflate " + pos + "头部" + headView.hashCode());
                return headView;
            }
        });


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(normalDecoration);
        recyclerView.setAdapter(nianXingAdapter);


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
