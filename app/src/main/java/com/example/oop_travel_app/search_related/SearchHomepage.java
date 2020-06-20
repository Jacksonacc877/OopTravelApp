package com.example.oop_travel_app.search_related;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
//import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.oop_travel_app.AccountHomepage;
import com.example.oop_travel_app.DevlopHomepage;
import com.example.oop_travel_app.MainActivity;
import com.example.oop_travel_app.database_function.DataList;
import com.example.oop_travel_app.R;
import com.example.oop_travel_app.order_related.ArrangeHomepage;

public class SearchHomepage extends AppCompatActivity {
    private ListView listView;
    private Button search_button;
    private ImageButton ss,so,sh,sa,sd;
    private ArrayList<Map<String,Object>> list;
    private listview_forsearch tld;
    private String region_input,startDate_input;
    private AutoCompleteTextView mutextview;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchhomepage);
        search_button = (Button) findViewById(R.id.search_button);
        search_button.setOnClickListener(search_button_listener);

        ss=(ImageButton)findViewById(R.id.ss);
        ss.setOnClickListener(ss_listener);
        so=(ImageButton)findViewById(R.id.so);
        so.setOnClickListener(so_listener);
        sh=(ImageButton)findViewById(R.id.sh);
        sh.setOnClickListener(sh_listener);
        sa=(ImageButton)findViewById(R.id.sa);
        sa.setOnClickListener(sa_listener);
        sd=(ImageButton)findViewById(R.id.sd);
        sd.setOnClickListener(sd_listener);

        System.out.println("1");

        DataList dlt=new DataList(this);
        String[] hintregion=dlt.listCountry();
        ArrayAdapter<String> hintadapter=new ArrayAdapter<String>(this,android.R.layout.select_dialog_item,hintregion);
        mutextview=(AutoCompleteTextView)findViewById(R.id.search_region);
        mutextview.setThreshold(1);
        mutextview.setAdapter(hintadapter);
        mutextview.setOnClickListener(mutlistener);


        listView = (ListView) findViewById(R.id.listview_search_result);
        list = new ArrayList<Map<String,Object>>();
        tld = new listview_forsearch(this, list);
        listView.setAdapter(tld);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Adapter adapter = adapterView.getAdapter();
                ListView listView = (ListView) adapterView;
                Intent intent = new Intent(SearchHomepage.this, Search_Result_SameTitle.class);
                Bundle bundle_test = new Bundle();
                String region_name = (String) list.get(position).get("triptitle");
                bundle_test.putString("RegionNumber_UserIn", region_name);
                intent.putExtras(bundle_test);
                startActivity(intent);

            }
        });


    }
    View.OnClickListener ss_listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(SearchHomepage.this,SearchHomepage.class);
            startActivity(intent);
        }
    };
    View.OnClickListener so_listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(SearchHomepage.this, ArrangeHomepage.class);
            startActivity(intent);
        }
    };
    View.OnClickListener sh_listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(SearchHomepage.this, MainActivity.class);
            startActivity(intent);
        }
    };
    View.OnClickListener sa_listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(SearchHomepage.this, AccountHomepage.class);
            startActivity(intent);
        }
    };
    View.OnClickListener sd_listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(SearchHomepage.this, DevlopHomepage.class);
            startActivity(intent);
        }
    };


    View.OnClickListener mutlistener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mutextview.showDropDown();
        }
    };

    View.OnClickListener search_button_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int listsize = list.size();
            for (int i = 0; i < listsize; i++) {
                list.remove(0);
            }
            region_input = "";
            startDate_input = "";
            region_input =mutextview.getText().toString();
            EditText et2 = (EditText) findViewById(R.id.search_startDate);
            startDate_input = et2.getText().toString();

            if (region_input.equals("")) {
                Toast.makeText(SearchHomepage.this, "Please input region !", Toast.LENGTH_SHORT);
            } else if (startDate_input.equals("")) {
                DataList dl = new DataList(SearchHomepage.this);
                String[] result = dl.searchDestination(region_input);
                System.out.println("5");
                //int i = 0; i < result.length; i++
                for (String s:result) {
                    HashMap<String,Object> item = new HashMap<String,Object>();
                    String[] str=s.split(" , ");
                    //result[i].split
                    item.put("triptitle",str[0]);
                    item.put("priceinterval",str[1]);
                    item.put("dateinterval",str[2]);
                    list.add(item);
                }
                System.out.println("6");
                tld.notifyDataSetChanged();
                listView.setAdapter(tld);
                if (list.size() == 0) {
                    Toast.makeText(SearchHomepage.this, "No data !", Toast.LENGTH_SHORT).show();
                }
            } else {
                DataList dl = new DataList(SearchHomepage.this);
                String[] result = dl.searchDestination(region_input,startDate_input);
                for (String s:result) {
                    HashMap<String,Object> item = new HashMap<String,Object>();
                    String[] str=s.split(" , ");
                    item.put("triptitle",str[0]);
                    item.put("priceinterval",str[1]);
                    item.put("dateinterval",str[2]);
                    list.add(item);
                }
                tld.notifyDataSetChanged();
                listView.setAdapter(tld);
                if (list.size() == 0) {
                    Toast.makeText(SearchHomepage.this, "No data !", Toast.LENGTH_SHORT).show();
                }
            }
        };


    };



}