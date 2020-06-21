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
import com.example.oop_travel_app.order_related.ArrangeHomepage;
import com.example.oop_travel_app.search_related.SearchHomepage;

import java.util.ArrayList;
import java.util.Map;

public class DevlopHomepage extends AppCompatActivity {
    private ImageButton dhs,dho,dhh,dha,dhd;
    private RadioGroup radiogroup;
    private RadioButton ctr_trip,ctr_order,ctr_account;
    private ListView listView;
    private ArrayList<Map<String,Object>> list;
    private Button dev_bt;
    private int devcheckin=0;
    private EditText dev_etaccount,dev_etpassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devlop_homepage);

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
                        listView.setAdapter(ldt);
                        break;
                    case R.id.dev_controlorder :
                        listview_dev_order ldo=new listview_dev_order(DevlopHomepage.this,list);
                        listView.setAdapter(ldo);
                        break;
                    case R.id.dev_controlaccount :
                        listview_dev_account lda=new listview_dev_account(DevlopHomepage.this,list);
                        listView.setAdapter(lda);
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
