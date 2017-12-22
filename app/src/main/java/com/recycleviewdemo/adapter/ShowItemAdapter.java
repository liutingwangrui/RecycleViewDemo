package com.recycleviewdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.recycleviewdemo.R;
import com.recycleviewdemo.bean.ShowItemBean;
import com.recycleviewdemo.listener.ItemClickListener;

import java.util.List;

/**
 * Created by Administrator on 2017/12/21.
 */

public class ShowItemAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private Context context;
    private List<ShowItemBean> list;
    private OnScrollListener mOnScrollListener;
    private int preClickPosition = -1;

    public ShowItemAdapter(Context context, List<ShowItemBean> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType) {
            case ShowItemBean.PARENT_ITEM:
                view = LayoutInflater.from(context).inflate(R.layout.item_fen_lan_title, parent, false);
                return new ParentHolder(view);
            case ShowItemBean.CHILD_ITEM:
                view = LayoutInflater.from(context).inflate(R.layout.item_fenlan_normal, parent, false);
                return new ChildrenHolder(view);
            default:
                view = LayoutInflater.from(context).inflate(R.layout.item_fen_lan_title, parent, false);
                return new ParentHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case ShowItemBean.PARENT_ITEM:
                ParentHolder parentHolder = (ParentHolder) holder;
                parentHolder.bindView(list.get(position), position, itemClickListener);
                break;
            case ShowItemBean.CHILD_ITEM:
                ChildrenHolder childrenHolder = (ChildrenHolder) holder;
                childrenHolder.bindView(list.get(position), position);
                break;
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    @Override
    public int getItemViewType(int position) {
        return list.get(position).getType();
    }

    private ItemClickListener itemClickListener = new ItemClickListener() {
        @Override
        public void onExpandChildren(ShowItemBean showItemBean) {
            //确定当前点击的item的位置
            int currentPosition = getCurrentPosition(showItemBean.getID());
            preClickPosition = currentPosition;
            //获取子数据的对象
            ShowItemBean childrenData = getChildrenData(showItemBean);
            if (childrenData == null) {
                return;
            }

            add(childrenData, currentPosition + 1);
            //如果点击的item为最后一个
            if (currentPosition == list.size() - 2 && mOnScrollListener != null) {
                //向下滚动，使子布局可以完全展示
                mOnScrollListener.scrollTo(currentPosition + 1);
            }


        }

        @Override
        public void onHideChildren(ShowItemBean showItemBean) {
            int currentPosition = getCurrentPosition(showItemBean.getID());
            ShowItemBean childrenData = getChildrenData(showItemBean);
            if (childrenData == null) {
                return;
            }
//            if (currentPosition != preClickPosition && preClickPosition > 0) {
//                remove(preClickPosition + 1);
//
//                if (mOnScrollListener != null) {
//                    mOnScrollListener.scrollTo(preClickPosition);
//                }
//
//            } else {
//                preClickPosition = -1;
                remove(currentPosition + 1);
                if (mOnScrollListener != null) {
                    mOnScrollListener.scrollTo(currentPosition);
//                }
            }



        }
    };

    /**
     * 滚动监听
     */
    public interface OnScrollListener {
        void scrollTo(int pos);
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.mOnScrollListener = onScrollListener;
    }

    /**
     * 增加数据
     *
     * @param showItemBean
     * @param pos
     */
    private void add(ShowItemBean showItemBean, int pos) {
        list.add(pos, showItemBean);
        notifyItemInserted(pos);
    }

    private void remove(int pos) {
        list.remove(pos);
        notifyItemRemoved(pos);
    }


    /**
     * 获取子数据的对象
     *
     * @param showItemBean
     * @return
     */
    private ShowItemBean getChildrenData(ShowItemBean showItemBean) {
        ShowItemBean childrenItem = new ShowItemBean();
        childrenItem.setType(1);
        childrenItem.setParentContent(showItemBean.getParentContent());
        childrenItem.setChildContent(showItemBean.getChildContent());
        return childrenItem;

    }

    /**
     * 确定当前item的位置并返回
     *
     * @param id
     * @return
     */
    private int getCurrentPosition(String id) {
        for (int i = 0; i < list.size(); i++) {
            if (id.equals(list.get(i).getID())) {
                return i;
            }
        }

        return -1;

    }

    /**
     * 标题ViewHolder
     */
    private class ParentHolder extends BaseViewHolder {

        TextView parentContent;
        private View itemView;
        private View fenGeview;//分割线

        ParentHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;

        }

        public void bindView(final ShowItemBean showItemBean, final int position, final ItemClickListener itemClickListener) {
            parentContent = (TextView) itemView.findViewById(R.id.item_title_tv);
            fenGeview = (View) itemView.findViewById(R.id.view);
            parentContent.setText(showItemBean.getParentContent());

            parentContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickListener != null) {
                        if (showItemBean.isExpand()) {
                            itemClickListener.onHideChildren(showItemBean);
                            showItemBean.setExpand(false);
                        } else {
                            itemClickListener.onExpandChildren(showItemBean);
                            showItemBean.setExpand(true);
                        }
                    }
                }
            });
        }
    }


    /**
     * 内容ViewHolder
     */
    private class ChildrenHolder extends BaseViewHolder {

        TextView childremContent;

        private View itemView;

        ChildrenHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
        }

        public void bindView(final ShowItemBean showItemBean, final int position) {
            childremContent = (TextView) itemView.findViewById(R.id.item_fen_lan_normal_tv);
            childremContent.setText(showItemBean.getChildContent());


        }

    }


}
