package com.recycleviewdemo.adapter;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.recycleviewdemo.R;

import java.util.List;

/**
 * Created by Administrator on 2017/12/12.
 */

public class LikeListAdapter extends BaseAdapter {
    private Context context;
    private List<String> realDatas;

    public LikeListAdapter(Context context, List<String> realDatas) {
        super(context, realDatas);
        this.context = context;
        this.realDatas = realDatas;
    }

    @Override
    protected int inflaterItemLayout(int viewType) {
        return R.layout.like_list_item;
    }

    @Override
    protected void bindData(BaseViewHolder holder, int position, Object t) {
        holder.setText(R.id.text, realDatas.get(position));

    }

    @Override
    protected void onItemClickListener(View itemView, int position, Object t) {
        Toast.makeText(context, "position:" + position + " " + (String) t, Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onItemLongClickListener(View itemView, int position, Object t) {
        Toast.makeText(context, "position:" + position + " 长按了" + (String) t, Toast.LENGTH_SHORT).show();
    }


}
