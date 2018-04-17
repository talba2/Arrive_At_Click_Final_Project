package com.example.arrive_at_click.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.support.v7.appcompat.R;

import com.example.arrive_at_click.model.Site;
import java.util.List;

public class ListSiteAdapter extends BaseAdapter {
    private Context mContext;
    private List<Site> mSiteList;

    public ListSiteAdapter(Context mContext, List<Site> mSiteList) {
        this.mContext = mContext;
        this.mSiteList = mSiteList;
    }

    @Override
    public int getCount() {
        return mSiteList.size();
    }

    @Override
    public Object getItem(int position) {
        return mSiteList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mSiteList.get(position).getIdSite();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v=null; //convertView.inflate(mContext, R.layout,null);

        ///need to add something

        return v;
    }
}
