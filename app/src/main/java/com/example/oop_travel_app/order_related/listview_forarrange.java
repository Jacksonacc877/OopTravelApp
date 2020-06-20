package com.example.oop_travel_app.order_related;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static androidx.core.content.ContextCompat.getSystemService;
import android.os.Bundle;

import com.example.oop_travel_app.R;

public class listview_forarrange extends BaseAdapter {
        private Context context;
        private ArrayList<Map<String,Object>> list;
        public listview_forarrange(Context context, ArrayList<Map<String,Object>> list){
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
            TextView tripregion;
            TextView tripdate;
            TextView tripamountpeople;
            TextView tripprice;

        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if(convertView==null){
                convertView= LayoutInflater.from(context).inflate(R.layout.arrange_listview_layout, parent,false);
                holder =new ViewHolder();
                holder.tripregion=(TextView)convertView.findViewById(R.id.travelregion_input);
                holder.tripdate=(TextView)convertView.findViewById(R.id.travel_db_date);
                holder.tripamountpeople=(TextView)convertView.findViewById(R.id.travel_memberamount);
                holder.tripprice=(TextView)convertView.findViewById(R.id.travel_price);


                convertView.setTag(holder);
            }else{
                holder=(ViewHolder)convertView.getTag();
            }
            holder.tripregion.setText((String)list.get(position).get("tripregion"));
            holder.tripdate.setText((String)list.get(position).get("tripdate"));
            holder.tripamountpeople.setText((String)list.get(position).get("tripamountpeople"));
            holder.tripprice.setText((String)list.get(position).get("tripprice"));
            return convertView;
        }




}
