package com.example.oop_travel_app.search_related;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.oop_travel_app.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static androidx.core.content.ContextCompat.getSystemService;

public class listview_forsametitle extends BaseAdapter {
    private Context context;
    private ArrayList<Map<String,Object>> list;

    //private LayoutInflater testLayoutInflater;
    public listview_forsametitle(Context context, ArrayList<Map<String,Object>> list){
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
        TextView ID;
        TextView Price;
        TextView StartDate;
        TextView EndDate;
        ImageView image;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
           convertView= LayoutInflater.from(context).inflate(R.layout.listview_layout, parent,false);
           holder =new ViewHolder();
           holder.ID=(TextView)convertView.findViewById(R.id.listview_listId);
           holder.Price=(TextView)convertView.findViewById(R.id.listview_listPrice);
           holder.StartDate=(TextView)convertView.findViewById(R.id.listview_listStartDate);
           holder.EndDate=(TextView)convertView.findViewById(R.id.listview_listEndDate);
           holder.image=(ImageView)convertView.findViewById(R.id.listview_listImage);

           convertView.setTag(holder);
        }else{
            holder=(ViewHolder)convertView.getTag();
        }
        holder.ID.setText((String)list.get(position).get("ID"));
        holder.Price.setText((String)list.get(position).get("Price"));
        holder.StartDate.setText((String)list.get(position).get("StartDate"));
        holder.EndDate.setText((String)list.get(position).get("EndDate"));
        return convertView;
    }

}
