package com.example.oop_travel_app.devlop_function;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.oop_travel_app.R;
import com.example.oop_travel_app.search_related.listview_forsearch;

import java.util.ArrayList;
import java.util.Map;

public class listview_dev_trip extends BaseAdapter {
    private Context context;
    private ArrayList<Map<String,Object>> list;
    public listview_dev_trip(){

    }
    public  listview_dev_trip(Context context, ArrayList<Map<String,Object>> list){
        this.context=context;
        this.list=list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        Object obj=list.get(position);
        return list.indexOf(obj);
    }
    private static class ViewHolder{
        TextView tripid;
        TextView bookedtraveler;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        listview_dev_trip.ViewHolder holder;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.listview_dev_trip, parent,false);
            holder =new listview_dev_trip.ViewHolder();
            holder.tripid=(TextView)convertView.findViewById(R.id.dev_list_settripid);
            holder.bookedtraveler=(TextView)convertView.findViewById(R.id.dev_list_setbooknum);
            convertView.setTag(holder);
        }else{
            holder=(listview_dev_trip.ViewHolder)convertView.getTag();
        }
        holder.tripid.setText(String.valueOf(list.get(position).get("tripid")));
        holder.bookedtraveler.setText(String.valueOf(list.get(position).get("bookedtraveler")));
        return convertView;
    }
}
