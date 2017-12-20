package com.recycleviewdemo.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.recycleviewdemo.R;
import com.recycleviewdemo.adapter.AnimationIntoAdapter;
import com.recycleviewdemo.view.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/12/13.
 * 动画
 */

public class AnimationIntoActivity extends BaseActivity {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    private List<String> datas = new ArrayList<>();
    private AnimationIntoAdapter animationIntoAdapter;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_animation_into;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initData();
        animationIntoAdapter = new AnimationIntoAdapter(this, datas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new RecycleViewDivider(this, LinearLayout.HORIZONTAL, R.drawable.divider));
        recyclerView.setAdapter(animationIntoAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.aniamtion_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.left_into:
                changeAnimation(AnimationIntoAdapter.LEFT_ANIMATION);
                break;
            case R.id.right_into:
                changeAnimation(AnimationIntoAdapter.RIGHT_ANIMATION);
                break;
            case R.id.alpha_into:
                changeAnimation(AnimationIntoAdapter.ALPHA_ANIMATION);
                break;
            case R.id.scale_into:
                changeAnimation(AnimationIntoAdapter.SCALE_ANIMATION);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initData() {
        for (int i = 0; i < 20; i++) {
            datas.add("第" + i + "条数据");
        }
    }

    private void changeAnimation(int style) {
        datas.clear();
        initData();
        animationIntoAdapter.setAnimationStyle(style);
        recyclerView.setAdapter(animationIntoAdapter);
    }
}
