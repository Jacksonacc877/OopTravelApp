package com.example.oop_travel_app.search_related;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

import com.example.oop_travel_app.R;

public class listview_forsearch extends BaseAdapter {
    private Context context;
    private ArrayList<Map<String,Object>> list;
    public listview_forsearch(){

    }
    public  listview_forsearch(Context context, ArrayList<Map<String,Object>> list){
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
        TextView triptitle;
        TextView priceinterval;
        TextView dateinterval;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.listview_forsearch, parent,false);
            holder =new ViewHolder();
            holder.triptitle=(TextView)convertView.findViewById(R.id.search_initialresult);
            holder.priceinterval=(TextView)convertView.findViewById(R.id.actual_price);
            holder.dateinterval=(TextView)convertView.findViewById(R.id.actual_date);

            convertView.setTag(holder);
        }else{
            holder=(listview_forsearch.ViewHolder)convertView.getTag();
        }
        holder.triptitle.setText((String)list.get(position).get("triptitle"));
        holder.priceinterval.setText((String)list.get(position).get("priceinterval"));
        holder.dateinterval.setText((String)list.get(position).get("dateinterval"));
        return convertView;
    }
    }


