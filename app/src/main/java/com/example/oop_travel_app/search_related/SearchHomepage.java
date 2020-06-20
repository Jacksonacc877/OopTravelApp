package com.example.oop_travel_app.search_related;

import androidx.appcompat.app.AppCompatActivity;
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

import android.widget.Spinner;
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
    ArrayAdapter<String> hintadapter2;
    private listview_forsearch tld;
    private String region_input;
    private AutoCompleteTextView mutextview;
    private Spinner regionlist;
    private EditText search_region;
    private int checkoncreate=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchhomepage);
        search_button = (Button) findViewById(R.id.search_button);
        search_button.setOnClickListener(search_button_listener);


        search_region=(EditText)findViewById(R.id.search_region);

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

        DataList dlt=new DataList(this);
        String[] hintregion=dlt.listCountry();
        ArrayAdapter<String> hintadapter1=new ArrayAdapter<String>(this,android.R.layout.select_dialog_item,hintregion);
        hintadapter2 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,hintregion);
        mutextview=(AutoCompleteTextView)findViewById(R.id.search_region);
        mutextview.setThreshold(1);
        mutextview.setAdapter(hintadapter1);
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
                String  dateinterval=(String)list.get(position).get("dateinterval");
                String startdate,enddate;
                String[] splitdateinteraval=dateinterval.split("~");
                if(splitdateinteraval.length>1){
                    startdate=splitdateinteraval[0];
                    enddate=splitdateinteraval[1];
                }else{
                    startdate=splitdateinteraval[0];
                    enddate=splitdateinteraval[0];
                }
                bundle_test.putString("RegionNumber_UserIn", region_name);
                bundle_test.putString("StartDate",startdate);
                bundle_test.putString("EndDate",enddate);
                intent.putExtras(bundle_test);
                startActivity(intent);

            }
        });

        regionlist=(Spinner)findViewById(R.id.region_list);
        regionlist.setAdapter(hintadapter2);
        regionlist.setOnItemSelectedListener(regionlist_listener);


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

    AdapterView.OnItemSelectedListener regionlist_listener =new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if(checkoncreate==0 ){
                checkoncreate++;
            }else{
                String str=hintadapter2.getItem(position);
                search_region.setText(str);
            }
        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {

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
            for ( int i=0;i<listsize;i++) {
                list.remove(0);
            }
            region_input = "";
            region_input =mutextview.getText().toString();

            if (region_input.equals("")) {
                Toast.makeText(SearchHomepage.this, "Please input region !", Toast.LENGTH_SHORT);
            } else {
                DataList dl = new DataList(SearchHomepage.this);
                String[] result = dl.searchDestination(region_input);
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