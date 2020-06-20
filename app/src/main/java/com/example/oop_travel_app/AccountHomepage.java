package com.example.oop_travel_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.oop_travel_app.order_function.Account;
import com.example.oop_travel_app.order_related.ArrangeHomepage;
import com.example.oop_travel_app.search_related.SearchHomepage;

public class AccountHomepage extends AppCompatActivity {
    private EditText accounts,passwords;
    private Button login, regis;
    private ImageButton ahs,aho,ahh,aha,ahd;
    private Account account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_homepage);
        account=new Account("");

        accounts=(EditText)findViewById(R.id.account_et1);
        passwords=(EditText)findViewById(R.id.account_et2);
        login=(Button)findViewById(R.id.account_bt1);
        login.setOnClickListener(login_listener);
        regis=(Button)findViewById(R.id.account_bt2);
        regis.setOnClickListener(regis_listener);

        ahs=(ImageButton)findViewById(R.id.ahs);
        ahs.setOnClickListener(ahs_listener);
        aho=(ImageButton)findViewById(R.id.aho);
        aho.setOnClickListener(aho_listener);
        ahh=(ImageButton)findViewById(R.id.ahh);
        ahh.setOnClickListener(ahh_listener);
        aha=(ImageButton)findViewById(R.id.aha);
        aha.setOnClickListener(aha_listener);
        ahd=(ImageButton)findViewById(R.id.ahd);
        ahd.setOnClickListener(ahd_listener);

    }

    View.OnClickListener login_listener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            boolean checkaccount;
            String str_account,str_password;
            str_account=accounts.getText().toString();
            str_password=passwords.getText().toString();
            checkaccount=account.login(str_account,str_password);
            if(checkaccount){
                Intent intent=new Intent(AccountHomepage.this,Account_information.class);
                startActivity(intent);
            }else{
                Toast.makeText(AccountHomepage.this,"wrong account or wrong passwoed!",Toast.LENGTH_LONG).show();
            }

        }
    };
    View.OnClickListener regis_listener =new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            Intent intent=new Intent(AccountHomepage.this,Account_register.class);
            startActivity(intent);
        }
    };
    View.OnClickListener ahs_listener =new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            Intent intent=new Intent(AccountHomepage.this, SearchHomepage.class);
            startActivity(intent);
        }
    };
    View.OnClickListener aho_listener =new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            Intent intent=new Intent(AccountHomepage.this, ArrangeHomepage.class);
            startActivity(intent);
        }
    };
    View.OnClickListener ahh_listener =new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            Intent intent=new Intent(AccountHomepage.this,MainActivity.class);
            startActivity(intent);
        }
    };
    View.OnClickListener aha_listener =new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            Intent intent=new Intent(AccountHomepage.this,AccountHomepage.class);
            startActivity(intent);
        }
    };
    View.OnClickListener ahd_listener =new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            Intent intent=new Intent(AccountHomepage.this,DevlopHomepage.class);
            startActivity(intent);
        }
    };
}
