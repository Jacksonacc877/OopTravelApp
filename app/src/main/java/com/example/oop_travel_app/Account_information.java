package com.example.oop_travel_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.oop_travel_app.order_function.Account;
import com.example.oop_travel_app.order_related.ArrangeHomepage;
import com.example.oop_travel_app.search_related.SearchHomepage;

public class Account_information extends AppCompatActivity {
    private TextView ainfo_account,ainfo_name,ainfo_phonesss;
    private Button ainfo_bt;
    private ImageButton ais,aio,aih,aia,aid;
    private Account acc;
    private String str_phones,str_name,str_userID,str_passwordss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_information);
        Bundle bundle=this.getIntent().getExtras();
        str_phones=bundle.getString("str_phones");
        str_name=bundle.getString("str_name");
        str_userID=bundle.getString("str_userID");
        str_passwordss=bundle.getString("str_passwordss");


        ainfo_account=(TextView)findViewById(R.id.ainfo_account);
        ainfo_account.setText(str_userID);
        ainfo_name=(TextView)findViewById(R.id.ainfo_name);
        ainfo_name.setText(str_name);
        ainfo_phonesss=(TextView)findViewById(R.id.ainfo_phonesss);
        ainfo_phonesss.setText(str_phones);
        ainfo_bt=(Button)findViewById(R.id.ainfo_bt);
        ainfo_bt.setOnClickListener(ainfo_bt_listener);

        ais=(ImageButton)findViewById(R.id.ais);
        ais.setOnClickListener(ais_listener);
        aio=(ImageButton)findViewById(R.id.aio);
        aio.setOnClickListener(aio_listener);
        aih=(ImageButton)findViewById(R.id.aih);
        aih.setOnClickListener(aih_listener);
        aia=(ImageButton)findViewById(R.id.aia);
        aia.setOnClickListener(aia_listener);
        aid=(ImageButton)findViewById(R.id.aid);
        aid.setOnClickListener(aid_listener);
    }

    View.OnClickListener ainfo_bt_listener =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(Account_information.this,Account_revised.class);
            Bundle bundle=new Bundle();
            bundle.putString("str_passwordss",str_passwordss);
            bundle.putString("str_phones",str_phones);
            bundle.putString("str_name",str_name);
            bundle.putString("str_userID",str_userID);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    };
    View.OnClickListener ais_listener =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent =new Intent(Account_information.this, SearchHomepage.class);
            startActivity(intent);
        }
    };
    View.OnClickListener aio_listener =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent =new Intent(Account_information.this, ArrangeHomepage.class);
            startActivity(intent);
        }
    };
    View.OnClickListener aih_listener =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent =new Intent(Account_information.this, MainActivity.class);
            startActivity(intent);
        }
    };
    View.OnClickListener aia_listener =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent =new Intent(Account_information.this, AccountHomepage.class);
            startActivity(intent);
        }
    };
    View.OnClickListener aid_listener =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent =new Intent(Account_information.this, DevlopHomepage.class);
            startActivity(intent);
        }
    };

}
