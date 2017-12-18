package com.recycleviewdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.recycleviewdemo.R;
import com.recycleviewdemo.bean.ClassBean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/15.
 */

public class GroupContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<ClassBean> classBeen;
    private static final int HEADER = 1;
    private static final int CONTENT = 2;

    public GroupContentAdapter(Context context, List<ClassBean> classBeen) {
        this.context = context;
        this.classBeen = classBeen;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //根据返回的viewType在加载不同的布局
        if (viewType == HEADER) {//标题
            View view = LayoutInflater.from(context).inflate(R.layout.item_fen_lan_title, null);
            return new HeaderHolder(view);
        } else if (viewType == CONTENT) {//内容
            View view1 = LayoutInflater.from(context).inflate(R.layout.item_fenlan_normal, null);
            return new ContentHolder(view1);
        } else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //根据不同的位置来设置不同的内容
        int count = -1;
        for (int i = 0; i < classBeen.size(); i++) {
            count += 1;
            if (position == count) {
                HeaderHolder headerHolder = (HeaderHolder) holder;
                headerHolder.name.setText(classBeen.get(i).getClassName());
            } else {
                List<String> classStudents = classBeen.get(i).getClassStudents();
                for (int j = 0; j < classStudents.size(); j++) {
                    count += 1;
                    if (position == count) {
                        final String s = classStudents.get(j);
                        final ContentHolder contentHolder = (ContentHolder) holder;
                        contentHolder.content.setText(s);
                        // 点击事件和长按事件
                        if (mOnItemClickLitener != null) {
                            contentHolder.content.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    mOnItemClickLitener.onItemClick(s);
                                }
                            });
                        }
                        contentHolder.content.setOnLongClickListener(new View.OnLongClickListener() {
                            @Override
                            public boolean onLongClick(View v) {
                                mOnItemClickLitener.onItemLongClick(s);
                                return false;
                            }
                        });

                    }
                }
            }
        }

    }

    /**
     * 区分是标题还是内容
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        int count = -1;
        for (int i = 0; i < classBeen.size(); i++) {
            count++;
            if (position == count) {
                return HEADER;
            }

            //获取内容的集合，然后在进行遍历
            List<String> classStudents = classBeen.get(i).getClassStudents();
            for (int j = 0; j < classStudents.size(); j++) {
                count++;
                if (position == count) {
                    return CONTENT;
                }

            }
        }
        return super.getItemViewType(position);
    }

    /**
     * RecycleView返回的数量
     * @return
     */
    @Override
    public int getItemCount() {
        int childCount = classBeen.size();//标题的数量
        for (int i = 0; i < classBeen.size(); i++) {
            childCount += classBeen.get(i).getClassStudents().size();//内容的数量
        }
        return childCount;
    }

    /**
     * 标题ViewHolder
     */
    private class HeaderHolder extends RecyclerView.ViewHolder {

        TextView name;

        HeaderHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.item_title_tv);
        }
    }


    /**
     * 内容ViewHolder
     */
    private class ContentHolder extends RecyclerView.ViewHolder {

        TextView content;

        ContentHolder(View itemView) {
            super(itemView);
            content = (TextView) itemView.findViewById(R.id.item_fen_lan_normal_tv);
        }
    }


    /**
     * 条目点击事件和长按事件
     */
    public interface OnItemClickLitener {
        void onItemClick(String name);

        void onItemLongClick(String name);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

}
