package com.recycleviewdemo.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.recycleviewdemo.R;
import com.recycleviewdemo.view.SwipeMenuView;

import java.util.List;

/**
 * Created by Administrator on 2017/12/13.
 */

public class DeleteItemAdapter extends BaseAdapter {
    private Context context;
    private List<String> realDatas;

    public DeleteItemAdapter(Context context, List<String> realDatas) {
        super(context, realDatas);
        this.context = context;
        this.realDatas = realDatas;
    }

    @Override
    protected int inflaterItemLayout(int viewType) {
        return R.layout.delete_item;
    }

    @Override
    protected void bindData(final BaseViewHolder holder, int position, Object t) {
        holder.setText(R.id.text, realDatas.get(position));
        final TextView delete = holder.getView(R.id.delete);
        SwipeMenuView menuView = (SwipeMenuView) holder.getView(R.id.swipemenu);
        menuView.setIos(false).setLeftSwipe(true);//设置为true，表示可以侧拉删除；设置为false，表示不可以侧拉

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnSwipeListener!=null) {
                    //如果删除时，不使用mAdapter.notifyItemRemoved(pos)，则删除没有动画效果，
                    //且如果想让侧滑菜单同时关闭，需要同时调用 ((CstSwipeDelMenu) holder.itemView).quickClose();
                    //((CstSwipeDelMenu) holder.itemView).quickClose();
                    mOnSwipeListener.onDel(holder.getLayoutPosition());
                }
            }
        });

    }

    @Override
    protected void onItemClickListener(View itemView, int position, Object t) {
        Toast.makeText(context, "position:" + position + " " + (String) t, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onItemLongClickListener(View itemView, int position, Object t) {
        Toast.makeText(context, "position:" + position + " 长按了" + (String) t, Toast.LENGTH_SHORT).show();
    }



    /**
     * 删除
     */
    public interface onSwipeListener {
        void onDel(int pos);

    }

    private onSwipeListener mOnSwipeListener;

    public void setOnDelListener(onSwipeListener mOnDelListener) {
        this.mOnSwipeListener = mOnDelListener;
    }

}
