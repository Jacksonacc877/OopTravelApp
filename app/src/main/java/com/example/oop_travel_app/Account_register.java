package com.example.oop_travel_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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

public class Account_register extends AppCompatActivity {
    private EditText account,name,phoness,password,repassword;
    private Button regis_bt;
    private ImageButton ars,aro,arh,ara,ard;
    private Account acc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_register);
        acc=new Account("");

        account=(EditText)findViewById(R.id.regis_et_account);
        name=(EditText)findViewById(R.id.regis_et_name);
        phoness=(EditText)findViewById(R.id.regis_etphone);
        password=(EditText)findViewById(R.id.regis_et_password);
        repassword=(EditText)findViewById(R.id.regis_et_repassword);

        regis_bt=(Button)findViewById(R.id.regis_bt);
        regis_bt.setOnClickListener(regis_bt_listener);

        ars=(ImageButton)findViewById(R.id.ards);
        ars.setOnClickListener(ars_listener);
        aro=(ImageButton)findViewById(R.id.ardo);
        aro.setOnClickListener(aro_listener);
        arh=(ImageButton)findViewById(R.id.ardh);
        arh.setOnClickListener(arh_listener);
        ara=(ImageButton)findViewById(R.id.arda);
        ara.setOnClickListener(ara_listener);
        ard=(ImageButton)findViewById(R.id.ardd);
        ard.setOnClickListener(ard_listener);

    }

    View.OnClickListener regis_bt_listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String str_account=account.getText().toString();
            String str_name=name.getText().toString();
            String str_phoness=phoness.getText().toString();
            String str_password=password.getText().toString();
            String str_repassword=repassword.getText().toString();
            if(str_password.equals(str_repassword)){
                boolean checkregis;
                Account acc=new Account("");
                checkregis=acc.register(str_account,str_name,str_password,str_phoness);
                if(checkregis){
                    Check_login.alreadylogin=0;
                    AlertDialog.Builder builder = new AlertDialog.Builder(Account_register.this);
                    builder.setTitle("帳號註冊成功");
                    builder.setMessage("請重新登入");
                    builder.setPositiveButton("OK!", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent =new Intent(Account_register.this,AccountHomepage.class);
                            startActivity(intent);
                        }
                    });
                    builder.show();

                }else{
                    Toast.makeText(Account_register.this,"帳號重複，請重新輸入帳號",Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(Account_register.this,"請重新確認密碼",Toast.LENGTH_LONG).show();
            }

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
