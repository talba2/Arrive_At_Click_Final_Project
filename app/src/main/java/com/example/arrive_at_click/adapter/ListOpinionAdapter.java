package com.example.arrive_at_click.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.arrive_at_click.model.Opinion;
import com.example.arrive_at_click.model.Site;

import java.util.List;

public class ListOpinionAdapter extends BaseAdapter {

    private Context mContext;
    private List<Opinion> mOpinionList;

    public ListOpinionAdapter(Context mContext, List<Opinion> mOpinionList) {
        this.mContext = mContext;
        this.mOpinionList = mOpinionList;
    }

    @Override
    public int getCount() {
        return mOpinionList.size();
    }

    @Override
    public Object getItem(int position) {
        return mOpinionList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mOpinionList.get(position).getIdSite();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v=null; //convertView.inflate(mContext, R.layout,null);

        ///need to add something

        return v;
    }
}
