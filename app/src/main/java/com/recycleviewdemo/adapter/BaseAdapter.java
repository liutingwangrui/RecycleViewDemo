package com.recycleviewdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/12.
 */

public abstract class BaseAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private Context context;
    private List<? extends Object> realDatas = new ArrayList<>();
    private OnItemClickListener mClickListener;
    private OnItemLongClickListener mCLickLongListener;

    public BaseAdapter(Context context, List<? extends Object> realDatas) {

        this.context = context;
        this.realDatas = realDatas;
    }


    /**
     * 绑定布局界面
     */
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(inflaterItemLayout(viewType), parent, false);
        return new BaseViewHolder(itemView);
    }

    /**
     * 往控件中填充数据
     */
    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        bindData(holder, position, realDatas.get(position));
        //在这里设置Item的点击事件
        //在这里设置Item的点击事件
        if (mClickListener == null) {
            mClickListener = new OnItemClickListener<Object>() {
                @Override
                public void onItemClick(View itemView, int position, Object t) {
                    //让子类去实现
                    onItemClickListener(itemView, position, t);

                }
            };
        }
        holder.itemView.setOnClickListener(new TimmyItemClickListener(position));

        if (mCLickLongListener == null) {
            mCLickLongListener = new OnItemLongClickListener<Object>() {
                @Override
                public void onItemLongClick(View itemView, int position, Object t) {
                    onItemLongClickListener(itemView, position, t);
                }
            };
        }
        holder.itemView.setOnLongClickListener(new TimmyItemLongClickListener(position));


    }

    //获取Item数量
    @Override
    public int getItemCount() {
        return realDatas.size();
    }


    private class TimmyItemClickListener implements View.OnClickListener {
        private int mPosition;

        public TimmyItemClickListener(int position) {
            this.mPosition = position;
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null && view != null) {
                mClickListener.onItemClick(view, mPosition, realDatas.get(mPosition));
            }
        }
    }

    private class TimmyItemLongClickListener implements View.OnLongClickListener {
        private int mPosition;

        public TimmyItemLongClickListener(int position) {
            this.mPosition = position;
        }

        @Override
        public boolean onLongClick(View view) {
            if (mCLickLongListener != null && view != null) {
                mCLickLongListener.onItemLongClick(view, mPosition, realDatas.get(mPosition));
                return true;
            }
            return false;
        }
    }

    /**
     * Item的点击事件接口
     */
    public interface OnItemClickListener<T> {
        void onItemClick(View itemView, int position, T t);
    }

    public interface OnItemLongClickListener<T> {
        void onItemLongClick(View itemView, int position, T t);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mClickListener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        this.mCLickLongListener = listener;
    }

    /**
     * 交给子类自己去填充Item布局
     */
    protected abstract int inflaterItemLayout(int viewType);

    protected abstract void bindData(BaseViewHolder holder, int position, Object t);

    protected abstract void onItemClickListener(View itemView, int position, Object t);

    protected abstract void onItemLongClickListener(View itemView, int position, Object t);
}
