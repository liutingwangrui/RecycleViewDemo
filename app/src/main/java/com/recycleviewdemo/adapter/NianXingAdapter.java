package com.recycleviewdemo.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.recycleviewdemo.R;
import com.recycleviewdemo.bean.Car;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/20.
 */
public class NianXingAdapter extends RecyclerView.Adapter<NianXingAdapter.ContentHolder> {

    private List<Car> mList;
    private Activity mActivity;

    public NianXingAdapter(Activity activity) {
        mList = new ArrayList<>();
        mActivity = activity;
    }

    public void addDatas(List<Car> data) {
        this.mList.clear();
        this.mList.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public ContentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fenlan_normal, null);
        return new ContentHolder(view1);

    }


    @Override
    public void onBindViewHolder(ContentHolder holder, int position) {

        holder.content.setText(mList.get(position).getName());


    }


    @Override
    public int getItemCount() {
        return mList.size();
    }


    class ContentHolder extends RecyclerView.ViewHolder {

        TextView content;

        ContentHolder(final View itemView) {
            super(itemView);
            content = (TextView) itemView.findViewById(R.id.item_fen_lan_normal_tv);
            content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(), content.getText(), Toast.LENGTH_SHORT).show();
                }
            });
        }


    }

}
