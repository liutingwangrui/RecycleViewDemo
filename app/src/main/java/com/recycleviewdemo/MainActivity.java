package com.recycleviewdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.recycleviewdemo.activity.AnimationIntoActivity;
import com.recycleviewdemo.activity.BaseActivity;
import com.recycleviewdemo.activity.DeleteItemActivity;
import com.recycleviewdemo.activity.LikeListViewActivity;

public class MainActivity extends BaseActivity {


    @Override
    protected int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    public void baseRecycleView(View view) {
        startActivity(new Intent(this, LikeListViewActivity.class));
    }

    public void deleteRecycleView(View view) {
        startActivity(new Intent(this, DeleteItemActivity.class));
    }

    public void intoRecycleView(View view) {
        startActivity(new Intent(this, AnimationIntoActivity.class));
    }

}
