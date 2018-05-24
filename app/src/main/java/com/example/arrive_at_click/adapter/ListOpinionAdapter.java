package com.example.arrive_at_click.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.view.LayoutInflater;
import android.widget.EditText;

import com.example.arrive_at_click.R;
import com.example.arrive_at_click.model.Opinion;
import java.util.ArrayList;


public class ListOpinionAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Opinion> mOpinionList;
    private static LayoutInflater inflater=null;

    public ListOpinionAdapter(ArrayList<Opinion> opinions,Context context)
    {
        this.mContext=context;
        this.mOpinionList=opinions;
        inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mOpinionList.size();
    }

    @Override
    public Opinion getItem(int position) {
        return mOpinionList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mOpinionList.get(position).getIdSite();
    }

    public class Holder
    {
        EditText name;
        EditText date;
        EditText score;
        EditText opinion;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Holder holder=new Holder();

        // Get the data item for this position
        Opinion opinion_position = mOpinionList.get(position);

        View rowView = inflater.inflate(R.layout.listview_item, parent, false);

        holder.name= (EditText) rowView.findViewById(R.id.etName);
        holder.date = (EditText) rowView.findViewById(R.id.etDate);
        holder.score = (EditText) rowView.findViewById(R.id.etScore);
        holder.opinion = (EditText) rowView.findViewById(R.id.etExistOpinion);

        holder.name.setText(opinion_position.getName());
        holder.date.setText(opinion_position.getDateOfOpinion());
        holder.score.setText(Integer.toString(opinion_position.getScore()));
        holder.opinion.setText(opinion_position.getTextOpinion());

        return rowView;
    }
}
