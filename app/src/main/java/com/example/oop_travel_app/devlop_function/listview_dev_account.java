package com.example.oop_travel_app.devlop_function;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.oop_travel_app.R;

import java.util.ArrayList;
import java.util.Map;

public class listview_dev_account extends BaseAdapter {
    private Context context;
    private ArrayList<Map<String,Object>> list;
    public listview_dev_account(){

    }
    public  listview_dev_account(Context context, ArrayList<Map<String,Object>> list){
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
        TextView setaccount;
        TextView setusername;
        TextView setphone;
        TextView setpassword;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        listview_dev_account.ViewHolder holder;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.listview_dev_account, parent,false);
            holder =new listview_dev_account.ViewHolder();
            holder.setaccount=(TextView)convertView.findViewById(R.id.dev_listact_setaccount);
            holder.setusername=(TextView)convertView.findViewById(R.id.dev_listact_setusername);
            holder.setphone=(TextView)convertView.findViewById(R.id.dev_listact_setphone);
            holder.setpassword=(TextView)convertView.findViewById(R.id.dev_listact_setpassword);
            convertView.setTag(holder);
        }else{
            holder=(listview_dev_account.ViewHolder)convertView.getTag();
        }
        holder.setaccount.setText((String)list.get(position).get("setaccount"));
        holder.setusername.setText((String)list.get(position).get("setusername"));
        holder.setphone.setText((String)list.get(position).get("setphone"));
        holder.setpassword.setText((String)list.get(position).get("setpassword"));
        return convertView;
    }
}
