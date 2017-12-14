package com.recycleviewdemo.adapter;

import android.animation.Animator;
import android.content.Context;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import com.recycleviewdemo.R;
import com.recycleviewdemo.animation.AlphaAnimation;
import com.recycleviewdemo.animation.BaseAnimation;
import com.recycleviewdemo.animation.DefaultAnimation;
import com.recycleviewdemo.animation.RightAnimation;
import com.recycleviewdemo.animation.ScaleAnimation;
import com.recycleviewdemo.animation.TranslateAnimation;

import java.util.List;

/**
 * Created by Administrator on 2017/12/14.
 */

public class AnimationIntoAdapter extends BaseAdapter {

    /**
     * item从左边出来的动画
     */
    public static final int LEFT_ANIMATION = 1;
    /**
     * item从右边出来的动画
     */
    public static final int RIGHT_ANIMATION = 2;
    /**
     * item透明的动画
     */
    public static final int ALPHA_ANIMATION = 3;
    /**
     * item缩放的动画
     */
    public static final int SCALE_ANIMATION = 4;


    private Context context;
    private List<String> realDatas;
    private BaseAnimation animation = new DefaultAnimation();
    private int duration = 1000;
    private Interpolator interpolator = new LinearInterpolator();

    public AnimationIntoAdapter(Context context, List<String> realDatas) {
        super(context, realDatas);
        this.context = context;
        this.realDatas = realDatas;
    }

    @Override
    protected int inflaterItemLayout(int viewType) {
        return R.layout.animation_into_item;
    }

    @Override
    protected void bindData(BaseViewHolder holder, int position, Object t) {
        holder.setText(R.id.text1, realDatas.get(position));

    }

    /**
     * 是否重复出现动画，意思就是同一个item如果在滑动的过程中他已经执行过一次动画了，不再执行第二次了。默认是
     * 重复执行
     */
    private boolean mFirstOnly = false;

    /**
     * 目前出现过的最大的itemPosition，会在判断是否重复重复出现动画的时候用到
     */
    private int mLastPosition;

    @Override
    public void onViewAttachedToWindow(BaseViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        if (!mFirstOnly || holder.getLayoutPosition() > mLastPosition) {
            for (Animator anim : animation.getAnimators(holder.itemView)) {
                anim.setDuration(duration).start();
                anim.setInterpolator(interpolator);
            }
            mLastPosition = holder.getLayoutPosition();
        }
    }

    public void setAnimationStyle(int type) {
        switch (type) {
            case LEFT_ANIMATION:
                animation = new TranslateAnimation();
                break;
            case RIGHT_ANIMATION:
                animation = new RightAnimation();
                break;
            case ALPHA_ANIMATION:
                animation = new AlphaAnimation();
                break;
            case SCALE_ANIMATION:
                animation = new ScaleAnimation();
                break;
        }

    }

    @Override
    protected void onItemClickListener(View itemView, int position, Object t) {

    }

    @Override
    protected void onItemLongClickListener(View itemView, int position, Object t) {

    }
}
