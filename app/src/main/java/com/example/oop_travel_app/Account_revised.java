package com.example.oop_travel_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oop_travel_app.order_function.Account;
import com.example.oop_travel_app.order_related.ArrangeHomepage;
import com.example.oop_travel_app.search_related.SearchHomepage;

public class Account_revised extends AppCompatActivity {
    private EditText ar_etname,ar_etphone,ar_etpassword,ar_etrepassword;
    private TextView ar_account_fix;
    private Button ar_bt;
    private String str_userID,str_name,str_phones,str_passwords;
    private ImageButton ards,ardo,ardh,arda,ardd;
    Account acc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_revised);

        acc=new Account("");

        Bundle bundle=this.getIntent().getExtras();
        str_userID=bundle.getString("str_userID");
        str_name =bundle.getString("str_name");
        str_phones=bundle.getString("str_phones");
        str_passwords=bundle.getString("str_passwordss");

        ar_account_fix=(TextView)findViewById(R.id.ar_account_fix);
        ar_account_fix.setText(str_userID);

        ar_etname=(EditText)findViewById(R.id.ar_etname);
        ar_etname.setText(str_name);
        ar_etphone=(EditText)findViewById(R.id.ar_etphone);
        ar_etphone.setText(str_phones);
        ar_etpassword=(EditText)findViewById(R.id.ar_etpassword);
        ar_etpassword.setText(str_passwords);
        ar_etrepassword=(EditText)findViewById(R.id.ar_etrepassword);
        ar_etrepassword.setText(str_passwords);

        ar_bt=(Button)findViewById(R.id.ar_bt);
        ar_bt.setOnClickListener(ar_bt_listener);

        ards=(ImageButton)findViewById(R.id.ards);
        ards.setOnClickListener(ards_listener);
        ardo=(ImageButton)findViewById(R.id.ardo);
        ardo.setOnClickListener(ardo_listener);
        ardh=(ImageButton)findViewById(R.id.ardh);
        ardh.setOnClickListener(ardh_listener);
        arda=(ImageButton)findViewById(R.id.arda);
        arda.setOnClickListener(arda_listener);
        ardd=(ImageButton)findViewById(R.id.ardd);
        ardd.setOnClickListener(ardd_listener);

    }

    View.OnClickListener ar_bt_listener =new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            System.out.println(0);
            String et_password=ar_etpassword.getText().toString();
            System.out.println(et_password);
            String et_repassword=ar_etrepassword.getText().toString();
            System.out.println(et_repassword);
            str_phones=ar_etphone.getText().toString();
            if(et_password.equals(et_repassword)){
                System.out.println("Chech 4"+str_name);
                acc.revise(str_userID,et_password,et_repassword,str_phones);
                Check_login.pphone=str_phones;

                Toast.makeText(Account_revised.this,"Revised success~ ", Toast.LENGTH_LONG).show();
                Intent intent =new Intent(Account_revised.this,AccountHomepage.class);
                startActivity(intent);
            }else{
                Toast.makeText(Account_revised.this,"Please check password again! ", Toast.LENGTH_LONG).show();
            }
        }
    };
    View.OnClickListener ards_listener =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(Account_revised.this, SearchHomepage.class);
            startActivity(intent);
        }
    };
    View.OnClickListener ardo_listener =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(Account_revised.this, ArrangeHomepage.class);
            startActivity(intent);
        }
    };
    View.OnClickListener ardh_listener =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(Account_revised.this, MainActivity.class);
            startActivity(intent);
        }
    };
    View.OnClickListener arda_listener =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(Account_revised.this, AccountHomepage.class);
            startActivity(intent);
        }
    };
    View.OnClickListener ardd_listener =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(Account_revised.this, DevlopHomepage.class);
            startActivity(intent);
        }
    };



}
