package com.example.oop_travel_app.devlop_function;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

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
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    private static class ViewHolder{
        TextView triptitle;
        TextView priceinterval;
        TextView dateinterval;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
