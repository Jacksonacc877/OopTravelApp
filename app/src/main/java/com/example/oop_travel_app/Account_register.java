package com.example.oop_travel_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.oop_travel_app.order_related.ArrangeHomepage;
import com.example.oop_travel_app.search_related.SearchHomepage;

import javax.microedition.khronos.egl.EGLDisplay;

public class Account_register extends AppCompatActivity {
    private EditText account,name,phoness,password,repassword;
    private Button regis_bt;
    private ImageButton ars,aro,arh,ara,ard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_register);

        account=(EditText)findViewById(R.id.regis_et_account);
        name=(EditText)findViewById(R.id.regis_et_name);
        phoness=(EditText)findViewById(R.id.regis_etphone);
        password=(EditText)findViewById(R.id.regis_et_password);
        repassword=(EditText)findViewById(R.id.regis_et_repassword);

        regis_bt=(Button)findViewById(R.id.regis_bt);
        regis_bt.setOnClickListener(regis_bt_listener);

        ars=(ImageButton)findViewById(R.id.ars);
        ars.setOnClickListener(ars_listener);
        aro=(ImageButton)findViewById(R.id.aro);
        aro.setOnClickListener(aro_listener);
        arh=(ImageButton)findViewById(R.id.arh);
        arh.setOnClickListener(arh_listener);
        ara=(ImageButton)findViewById(R.id.ara);
        ara.setOnClickListener(ara_listener);
        ard=(ImageButton)findViewById(R.id.ard);
        ard.setOnClickListener(ard_listener);

    }

    View.OnClickListener regis_bt_listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };
    View.OnClickListener ars_listener=new  View.OnClickListener(){
        @Override
        public void onClick(View v) {
            Intent intent =new Intent(Account_register.this, SearchHomepage.class);
            startActivity(intent);
        }
    };
    View.OnClickListener aro_listener=new  View.OnClickListener(){
        @Override
        public void onClick(View v) {
            Intent intent =new Intent(Account_register.this, ArrangeHomepage.class);
            startActivity(intent);
        }
    };
    View.OnClickListener arh_listener=new  View.OnClickListener(){
        @Override
        public void onClick(View v) {
            Intent intent =new Intent(Account_register.this, MainActivity.class);
            startActivity(intent);
        }
    };
    View.OnClickListener ara_listener=new  View.OnClickListener(){
        @Override
        public void onClick(View v) {
            Intent intent =new Intent(Account_register.this, AccountHomepage.class);
            startActivity(intent);
        }
    };
    View.OnClickListener ard_listener=new  View.OnClickListener(){
        @Override
        public void onClick(View v) {
            Intent intent =new Intent(Account_register.this, DevlopHomepage.class);
            startActivity(intent);
        }
    };
}
