package com.example.oop_travel_app.devlop_function;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.oop_travel_app.R;

import java.util.ArrayList;
import java.util.Map;

public class listview_dev_order extends BaseAdapter {
    private Context context;
    private ArrayList<Map<String,Object>> list;
    public listview_dev_order(RadioGroup.OnCheckedChangeListener onCheckedChangeListener, ArrayList<Map<String, Object>> list){

    }
    public  listview_dev_order(Context context, ArrayList<Map<String,Object>> list){
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
        TextView orderid;
        TextView userid;
        TextView tripid;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        listview_dev_order.ViewHolder holder;
        if(convertView==null){
        convertView= LayoutInflater.from(context).inflate(R.layout.listview_dev_order, parent,false);
        holder =new listview_dev_order.ViewHolder();

        holder.orderid=(TextView)convertView.findViewById(R.id.dev_listorder_setorderid);
        holder.userid=(TextView)convertView.findViewById(R.id.dev_listorder_setusername);
        holder.tripid=(TextView)convertView.findViewById(R.id.dev_listorder_settripid);
        convertView.setTag(holder);
    }else{
        holder=(listview_dev_order.ViewHolder)convertView.getTag();
    }
        holder.orderid.setText(String.valueOf(list.get(position).get("orderid")));
        holder.userid.setText(String.valueOf(list.get(position).get("userid")));
        holder.tripid.setText(String.valueOf(list.get(position).get("tripid")));

        return convertView;
    }
}
