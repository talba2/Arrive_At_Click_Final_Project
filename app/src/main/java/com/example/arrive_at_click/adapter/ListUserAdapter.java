package com.example.arrive_at_click.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.view.LayoutInflater;

import com.example.arrive_at_click.model.Users;

import java.util.ArrayList;

public class ListUserAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Users> mUsersList;
    private static LayoutInflater inflater=null;

    public ListUserAdapter(Context mContext, ArrayList<Users> mUsersList) {
        this.mContext = mContext;
        this.mUsersList = mUsersList;
    }

    @Override
    public int getCount() {
        return mUsersList.size();
    }

    @Override
    public Users getItem(int position) {
        return mUsersList.get(position);
    }

    @Override
    public long getItemId(int position) { return Integer.parseInt(mUsersList.get(position).getIdUser()); }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

         View v=null;
         return v;
    }
}
