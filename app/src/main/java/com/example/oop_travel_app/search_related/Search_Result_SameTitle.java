package com.example.oop_travel_app.search_related;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oop_travel_app.AccountHomepage;
import com.example.oop_travel_app.DevlopHomepage;
import com.example.oop_travel_app.FirestoreHelper;
import com.example.oop_travel_app.MainActivity;
import com.example.oop_travel_app.database_function.DataList;
import com.example.oop_travel_app.R;
import com.example.oop_travel_app.order_related.ArrangeHomepage;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Search_Result_SameTitle extends AppCompatActivity {
    private String region_User_input,start_dates,end_dates;
    private EditText Start_date,End_date;
    private Button searchButton,strdateButton,enddateButton;
    private ImageButton srs,sro,srh,sra,srd;
    private ListView listView;
    private ArrayList<Map<String,Object>> list;
    private listview_forsametitle tld;
    private Spinner priceOrdate;
    private Boolean permutation=true;  //T = by price ; F = by date; default = T
    private FirestoreHelper fsh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search__resultsametitle);
        Bundle bundle_test=this.getIntent().getExtras();
        region_User_input = bundle_test.getString("RegionNumber_UserIn");

        TextView SRListdate = (TextView)findViewById(R.id.trip_region_Userin);
        SRListdate.setText(region_User_input);

        String startdate=bundle_test.getString("StartDate");
        Start_date =(EditText)findViewById(R.id.start_date);
        Start_date.setText(startdate);
        String enddate=bundle_test.getString("EndDate");
        End_date =(EditText)findViewById(R.id.end_date);
        End_date.setText(enddate);

        searchButton = (Button)findViewById(R.id.gosearch_date);
        searchButton.setOnClickListener(searchButtonListener);
        strdateButton =(Button)findViewById(R.id.startDateButton);
        strdateButton.setOnClickListener(strdateListener);
        enddateButton = (Button)findViewById(R.id.endDateButton);
        enddateButton.setOnClickListener(enddateListener);

        srs=(ImageButton)findViewById(R.id.srs);
        srs.setOnClickListener(srs_listener);
        sro=(ImageButton)findViewById(R.id.sro);
        sro.setOnClickListener(sro_listener);
        srh=(ImageButton)findViewById(R.id.srh);
        srh.setOnClickListener(srh_listener);
        sra=(ImageButton)findViewById(R.id.sra);
        sra.setOnClickListener(sra_listener);
        srd=(ImageButton)findViewById(R.id.srd);
        srd.setOnClickListener(srd_listener);

        fsh=new FirestoreHelper();
        fsh.tripInit();

        final String[] choice={"依價錢排列","依日期排列"};
        ArrayAdapter<String> permuteadpater =new ArrayAdapter<>(Search_Result_SameTitle.this,android.R.layout.simple_spinner_dropdown_item,choice);
        priceOrdate=(Spinner)findViewById(R.id.priceordate);
        priceOrdate.setAdapter(permuteadpater);
        priceOrdate.setOnItemSelectedListener(choicelistener);


        listView = (ListView) findViewById(R.id.date_list);
        list=new ArrayList<Map<String,Object>>();
        tld = new listview_forsametitle(this,list);
        listView.setAdapter(tld);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Adapter adapter = parent.getAdapter();
                ListView listView = (ListView) parent;
                Intent intent = new Intent(Search_Result_SameTitle.this, Trip_DetailwithBooking.class);
                Bundle bundle_test = new Bundle();
                String ID =(String) list.get(position).get("ID");
                bundle_test.putString("ID", ID);
                int bookTraveler=0;
                if (!(fsh.getTripIDs().get(ID)==null)) bookTraveler=(int) fsh.getTripIDs().get(ID);
                bundle_test.putInt("bookTraveler", bookTraveler);
                intent.putExtras(bundle_test);
                startActivity(intent);
            }
        });
    }
    AdapterView.OnItemSelectedListener choicelistener =new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if(position==0){
                permutation=true;
            }else{
                permutation=false;
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
    View.OnClickListener strdateListener =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Calendar cal1 =Calendar.getInstance();
            int year=cal1.get(Calendar.YEAR);
            int month =cal1.get(Calendar.MONTH);
            int day = cal1.get(Calendar.DAY_OF_MONTH);
            new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    month++;
                    String dateTime= String.valueOf(year);
                    if(month<10 ){
                        dateTime+="-0"+String.valueOf(month);
                    }else{
                        dateTime+="-"+String.valueOf(month);
                    }
                    if(dayOfMonth<10 ){
                        dateTime+="-0"+String.valueOf(dayOfMonth);
                    }else{
                        dateTime+="-"+String.valueOf(dayOfMonth);
                    }
                    Start_date.setText(dateTime);
                }
            },year,month,day).show();
        }
    };
    View.OnClickListener enddateListener =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Calendar cal2 =Calendar.getInstance();
            int year=cal2.get(Calendar.YEAR);
            int month =cal2.get(Calendar.MONTH);
            int day = cal2.get(Calendar.DAY_OF_MONTH);
            new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    month++;
                    String dateTime= String.valueOf(year);
                    if(month<10 ){
                        dateTime+="-0"+String.valueOf(month);
                    }else{
                        dateTime+="-"+String.valueOf(month);
                    }
                    if(dayOfMonth<10 ){
                        dateTime+="-0"+String.valueOf(dayOfMonth);
                    }else{
                        dateTime+="-"+String.valueOf(dayOfMonth);
                    }
                    End_date.setText(dateTime);
                }
            },year,month,day).show();
        }
    };
    View.OnClickListener searchButtonListener =new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            start_dates=Start_date.getText().toString();
            end_dates= End_date.getText().toString();
            if(start_dates.equals("")&&end_dates.equals("")){
                Toast.makeText(Search_Result_SameTitle.this,"Please input StartDate and EndDate !",Toast.LENGTH_SHORT).show();
            }else if(start_dates.equals("")){
                Toast.makeText(Search_Result_SameTitle.this,"Please input StartDate !",Toast.LENGTH_SHORT).show();
            }else if(end_dates.equals("")){
                Toast.makeText(Search_Result_SameTitle.this,"Please input EndDate !",Toast.LENGTH_SHORT).show();
            }else{
                DataList dl =new DataList(Search_Result_SameTitle.this);
                String[] resultTest =dl.listTitleData(region_User_input,start_dates,end_dates,permutation);
                for(int i=0;i<resultTest.length;i++){
                    HashMap<String,Object> item = new HashMap<String,Object>();
                    String[] getinfo =resultTest[i].split(",");
                    item.put("ID",getinfo[0]);
                    item.put("Price",getinfo[1]);
                    item.put("StartDate",getinfo[2]);
                    item.put("EndDate",getinfo[3]);
                    list.add(item);
                }
                tld.notifyDataSetChanged();
                listView.setAdapter(tld);
                if(list.size()==0){
                    Toast.makeText(Search_Result_SameTitle.this,"No data !",Toast.LENGTH_SHORT).show();
                }
                }
            }
        };
    View.OnClickListener srs_listener =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent =new Intent(Search_Result_SameTitle.this,SearchHomepage.class);
            startActivity(intent);
        }
    };
    View.OnClickListener sro_listener =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent =new Intent(Search_Result_SameTitle.this, ArrangeHomepage.class);
            startActivity(intent);
        }
    };
    View.OnClickListener srh_listener =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent =new Intent(Search_Result_SameTitle.this, MainActivity.class);
            startActivity(intent);
        }
    };
    View.OnClickListener sra_listener =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent =new Intent(Search_Result_SameTitle.this, AccountHomepage.class);
            startActivity(intent);
        }
    };
    View.OnClickListener srd_listener =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent =new Intent(Search_Result_SameTitle.this, DevlopHomepage.class);
            startActivity(intent);
        }
    };

    }

