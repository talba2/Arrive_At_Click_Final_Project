package com.example.arrive_at_click.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.view.LayoutInflater;
import android.widget.ListView;
import android.widget.TextView;

import com.example.arrive_at_click.R;
import com.example.arrive_at_click.model.Opinion;

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
        if( convertView == null ){
            //We must create a View:
            LayoutInflater lInflater = (LayoutInflater)mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = lInflater.inflate(R.layout.activity_information, parent, false);
        }

        TextView name=(TextView)convertView.findViewById(R.id.tvNamePerson);
        TextView date=(TextView)convertView.findViewById(R.id.tvDate);
        TextView score=(TextView)convertView.findViewById(R.id.tvScore);
        TextView opinion=(TextView)convertView.findViewById(R.id.tvPersonOpinion);

        Opinion opinion_position=mOpinionList.get(position);

        name.setText(opinion_position.getName());
        date.setText((String.valueOf(opinion_position.getDateOfOpinion())));
        score.setText(Integer.toString(opinion_position.getScore()));
        opinion.setText(opinion_position.getTextOpinion());

        return convertView;
    }
}
