package com.example.oop_travel_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import javax.microedition.khronos.egl.EGLDisplay;

public class Account_register extends AppCompatActivity {
    private EditText account,name,phoness,password,repassword;
    private Button regis_bt;

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

    }

    View.OnClickListener regis_bt_listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };
}
