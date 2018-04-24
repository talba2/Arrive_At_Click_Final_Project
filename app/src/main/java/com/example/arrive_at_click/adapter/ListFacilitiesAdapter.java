package com.example.arrive_at_click.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.Context;

import java.util.List;
import com.example.arrive_at_click.model.Facilities;

public class ListFacilitiesAdapter extends BaseAdapter {

    private Context mContext;
    private List<Facilities> mFacilitiesList;

    public ListFacilitiesAdapter(Context mContext, List<Facilities> mFacilitiesList) {
        this.mContext = mContext;
        this.mFacilitiesList = mFacilitiesList;
    }

    @Override
    public int getCount() {
        return mFacilitiesList.size();
    }

    @Override
    public Object getItem(int position) {
        return mFacilitiesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mFacilitiesList.get(position).getIdSite();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
