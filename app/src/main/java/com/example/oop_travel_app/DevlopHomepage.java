package com.example.oop_travel_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.oop_travel_app.devlop_function.listview_dev_account;
import com.example.oop_travel_app.devlop_function.listview_dev_order;
import com.example.oop_travel_app.devlop_function.listview_dev_trip;
import com.example.oop_travel_app.order_function.Account;
import com.example.oop_travel_app.order_function.Order;
import com.example.oop_travel_app.order_related.ArrangeHomepage;
import com.example.oop_travel_app.search_related.SearchHomepage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class DevlopHomepage extends AppCompatActivity {
    private ImageButton dhs,dho,dhh,dha,dhd;
    private RadioGroup radiogroup;
    private RadioButton ctr_trip,ctr_order,ctr_account;
    private ListView listView;
    private ArrayList<Map<String,Object>> list;
    private Button dev_bt;
    private int devcheckin=0;
    private EditText dev_etaccount,dev_etpassword;
    FirestoreHelper fh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devlop_homepage);
        fh=new FirestoreHelper();
        fh.userInit();
        fh.orderInit();
        fh.tripInit();

        listView=(ListView)findViewById(R.id.dev_listview);
        list=new ArrayList<Map<String,Object>>();

        dev_etaccount=(EditText)findViewById(R.id.dev_etaccount);
        dev_etpassword=(EditText)findViewById(R.id.dev_etpassword);


        ctr_trip=(RadioButton)findViewById(R.id.dev_controltrip);
        ctr_order=(RadioButton)findViewById(R.id.dev_controlorder);
        ctr_account=(RadioButton)findViewById(R.id.dev_controlaccount);
        radiogroup=(RadioGroup)findViewById(R.id.radioregion);
        radiogroup.setOnCheckedChangeListener(radiochange_lsitener);
        dev_bt=(Button)findViewById(R.id.dev_login);
        dev_bt.setOnClickListener(dev_bt_listener);


        dhs=(ImageButton)findViewById(R.id.dhs);
        dhs.setOnClickListener(dhs_listener);
        dho=(ImageButton)findViewById(R.id.dho);
        dho.setOnClickListener(dho_listener);
        dhh=(ImageButton)findViewById(R.id.dhh);
        dhh.setOnClickListener(dhh_listener);
        dha=(ImageButton)findViewById(R.id.dha);
        dha.setOnClickListener(dha_listener);
        dhd=(ImageButton)findViewById(R.id.dhd);
        dhd.setOnClickListener(dhd_listener);

    }
    View.OnClickListener dev_bt_listener =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String str_account=dev_etaccount.getText().toString();
            String str_password=dev_etpassword.getText().toString();
            if(str_account.equals("0") && str_password.equals("1")){
                devcheckin=1;
                Toast.makeText(DevlopHomepage.this,"登入成功",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(DevlopHomepage.this,"登入失敗，請重新確認帳號、密碼",Toast.LENGTH_LONG).show();
            }
        }
    };
    RadioGroup.OnCheckedChangeListener radiochange_lsitener=new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if(devcheckin==0){
                AlertDialog.Builder builder=new AlertDialog.Builder(DevlopHomepage.this);
                builder.setTitle("開發者模式");
                builder.setMessage("請先登入開發者以獲得資訊");
                builder.setPositiveButton("確定",null);
                builder.show();
            }else{
                int listsize = list.size();
                for ( int i=0;i<listsize;i++) {
                    list.remove(0);
                }
                switch (checkedId){
                    case R.id.dev_controltrip :
                        listview_dev_trip ldt=new listview_dev_trip(DevlopHomepage.this,list);
                        Set<String> set = fh.getTripIDs().keySet();
                        Iterator<String> it = set.iterator();
                        while (it.hasNext()) {
                            String key = it.next();
                        }
                        for (Map.Entry<String, Object> entry : fh.getTripIDs().entrySet()) {
                            HashMap<String,Object> item = new HashMap<String,Object>();
                            item.put("tripid",entry.getKey());
                            System.out.println("check can get value of tripid"+entry.getKey());
                            item.put("bookedtraveler",entry.getValue());
                            list.add(item);
                        }
                        ldt.notifyDataSetChanged();
                        listView.setAdapter(ldt);
                        if (list.size() == 0) {
                            Toast.makeText(DevlopHomepage.this, "No data !", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.dev_controlorder :
                        listview_dev_order ldo=new listview_dev_order(DevlopHomepage.this,list);
                        System.out.println("check order"+fh.mOrders.size());
                        for(Order o : fh.mOrders){
                            HashMap<String,Object> item = new HashMap<String,Object>();
                            item.put("orderid",o.getOrderID());
                            System.out.println("check can get value of orderid"+o.getOrderID());
                            item.put("userid",o.getUserID());
                            System.out.println("check can get value of userid"+o.getUserID());
                            item.put("tripid",o.getTripID());
                            list.add(item);
                        }

                        ldo.notifyDataSetChanged();
                        listView.setAdapter(ldo);
                        if (list.size() == 0) {
                            Toast.makeText(DevlopHomepage.this, "No data !", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.dev_controlaccount :
                        listview_dev_account lda=new listview_dev_account(DevlopHomepage.this,list);
                        for(Account o : fh.getUserIDs()){
                            HashMap<String,Object> item = new HashMap<String,Object>();
                            item.put("setaccount",o.getUserID());
                            item.put("setusername",o.getUserName());
                            item.put("setphone",o.getUserPhone());
                            item.put("setpassword",o.getPassword());
                            list.add(item);
                        }
                        lda.notifyDataSetChanged();
                        listView.setAdapter(lda);
                        if (list.size() == 0) {
                            Toast.makeText(DevlopHomepage.this, "No data !", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
            }

        }
    };
    View.OnClickListener dhs_listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent =new Intent(DevlopHomepage.this, SearchHomepage.class);
            startActivity(intent);
        }
    };
    View.OnClickListener dho_listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent =new Intent(DevlopHomepage.this, ArrangeHomepage.class);
            startActivity(intent);
        }
    };
    View.OnClickListener dhh_listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent =new Intent(DevlopHomepage.this, MainActivity.class);
            startActivity(intent);
        }
    };
    View.OnClickListener dha_listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent =new Intent(DevlopHomepage.this, AccountHomepage.class);
            startActivity(intent);
        }
    };
    View.OnClickListener dhd_listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent =new Intent(DevlopHomepage.this, DevlopHomepage.class);
            startActivity(intent);
        }
    };

}
