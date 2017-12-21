package com.recycleviewdemo.listener;

import com.recycleviewdemo.bean.ShowItemBean;

/**
 * Created by Administrator on 2017/12/21.
 */

public interface ItemClickListener {
    void onExpandChildren(ShowItemBean showItemBean);

    void onHideChildren(ShowItemBean showItemBean);
}
