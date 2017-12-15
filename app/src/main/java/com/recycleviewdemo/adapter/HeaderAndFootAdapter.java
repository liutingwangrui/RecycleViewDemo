package com.recycleviewdemo.adapter;

import android.content.Context;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.recycleviewdemo.R;

import java.util.List;

/**
 * Created by Administrator on 2017/12/15.
 */

public class HeaderAndFootAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

//    private RecyclerView.Adapter contentAdapter;

    private static final int HEADER = 10000;
    private static final int FOOTER = 20000;
    private SparseArrayCompat<View> headerViews = new SparseArrayCompat<>();//头部
    private SparseArrayCompat<View> footerViews = new SparseArrayCompat<>();//尾部

    //合并的情况
    private Context context;
    private List<String> datas;

    public HeaderAndFootAdapter(Context context, List<String> datas) {
        this.context = context;
        this.datas = datas;
    }

//分开的情况
//    public HeaderAndFootAdapter(RecyclerView.Adapter contentAdapter) {
//        this.contentAdapter = contentAdapter;
//    }


    /**
     * 是否为头布局
     *
     * @param position
     * @return
     */
    protected boolean isHeaderView(int position) {
        return position < getHeaderCount();

    }

    /**
     * 是否为尾布局
     *
     * @param position
     * @return
     */
    protected boolean isFooterView(int position) {
        return position >= getHeaderCount() + getContentCount();
    }

    /**
     * 获取头部的数量
     *
     * @return
     */
    private int getHeaderCount() {
        return headerViews.size();
    }

    /**
     * 获取尾部的数量
     *
     * @return
     */
    private int getFooterCount() {
        return footerViews.size();
    }

    /**
     * 获取item的数量
     *
     * @return
     */

    private int getContentCount() {
        //分开的情况
        // return contentAdapter.getItemCount();
        //合并的情况
        return datas.size();
    }

    /**
     * 添加头部布局
     *
     * @param view
     */
    public void addHeaderView(View view) {
        headerViews.put(headerViews.size() + HEADER, view);
    }

    /**
     * 添加尾部布局
     *
     * @return
     */
    public void addFooterView(View view) {
        footerViews.append(footerViews.size() + FOOTER, view);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (headerViews.get(viewType) != null) {
            RecyclerView.ViewHolder holder = new HeaderViewHolder(headerViews.get(viewType));
            return holder;

        }
        if (footerViews.get(viewType) != null) {
            RecyclerView.ViewHolder holder = new FooterViewHolder(footerViews.get(viewType));
            return holder;
        }
//        return contentAdapter.onCreateViewHolder(parent, viewType);分开
        //合并
        return new HeaderAndFootAdapter.ContentViewHolder(View.inflate(context, R.layout.group_item, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (isHeaderView(position)) {
            return;
        }

        if (isFooterView(position)) {
            return;
        }
//        contentAdapter.onBindViewHolder(holder, position - getHeaderCount());//分开
        ((ContentViewHolder) holder).textView.setText(datas.get(position-getHeaderCount()));//合并
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeaderView(position)) {
            return headerViews.keyAt(position);
        } else if (isFooterView(position)) {
            return footerViews.keyAt(position - getHeaderCount() - getContentCount());
        } else {
//            return contentAdapter.getItemViewType(position - getHeaderCount());分开
            return 0;
        }
    }

    /**
     * 为了动态的为不同的position设置布局
     *
     * @param recyclerView
     */
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
//        contentAdapter.onAttachedToRecyclerView(recyclerView);分开
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) manager;
            GridLayoutManager.SpanSizeLookup spanSizeLookup = gridLayoutManager.getSpanSizeLookup();
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {//getSpanSize方法，返回值就表示当前item占多少列
                    int viewType = getItemViewType(position);
                    if ((headerViews.get(viewType) != null) || (footerViews.get(viewType) != null)) {
                        return ((GridLayoutManager) gridLayoutManager).getSpanCount();
                    }
                    return 1;
                }
            });
        }

    }

    /**
     * 即滑动离开了当前窗口界面就会被调用
     *
     * @param holder
     */
    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
//        contentAdapter.onViewAttachedToWindow(holder);分开
        int position = holder.getLayoutPosition();
        if (isHeaderView(position) || isFooterView(position)) {
            ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();
            if (params != null && params instanceof StaggeredGridLayoutManager.LayoutParams) {
                StaggeredGridLayoutManager.LayoutParams layoutParams = (StaggeredGridLayoutManager.LayoutParams) params;
                layoutParams.setFullSpan(true);
            }
        }
    }

    @Override
    public int getItemCount() {
        return getHeaderCount() + getContentCount() + getFooterCount();
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {

        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }

    class FooterViewHolder extends RecyclerView.ViewHolder {

        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }

    class ContentViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ContentViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text3);
        }
    }
}
