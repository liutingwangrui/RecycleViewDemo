package com.recycleviewdemo.animation;

import android.animation.Animator;
import android.view.View;

/**
 * Created by Administrator on 2017/12/14.
 */

public interface BaseAnimation {

    Animator[] getAnimators(View view);

}
