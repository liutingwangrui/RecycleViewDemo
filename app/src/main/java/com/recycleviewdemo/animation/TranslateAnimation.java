package com.recycleviewdemo.animation;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

/**
 * Created by Administrator on 2017/12/14.
 */

public class TranslateAnimation implements BaseAnimation {
    @Override
    public Animator[] getAnimators(View view) {
        return new Animator[]{
                ObjectAnimator.ofFloat(view, "translationX",-view.getRootView().getWidth(),0)
        };
    }
}
