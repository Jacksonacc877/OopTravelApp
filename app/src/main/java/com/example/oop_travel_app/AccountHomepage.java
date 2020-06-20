package com.example.oop_travel_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AccountHomepage extends AppCompatActivity {
    private EditText accounts;
    private EditText passwords;
    private Button login, regis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_homepage);

        accounts=(EditText)findViewById(R.id.account_et1);
        passwords=(EditText)findViewById(R.id.account_et2);
        login=(Button)findViewById(R.id.account_bt1);
        login.setOnClickListener(login_listener);
        regis=(Button)findViewById(R.id.account_bt2);
        regis.setOnClickListener(regis_listener);

    }

    View.OnClickListener login_listener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(AccountHomepage.this,Account_information.class);
            startActivity(intent);
        }
    };
    View.OnClickListener regis_listener =new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            Intent intent=new Intent(AccountHomepage.this,Account_register.class);
            startActivity(intent);
        }
    };
}
