package com.example.oop_travel_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Account_information extends AppCompatActivity {
    private TextView ainfo_account,ainfo_name,ainfo_phonesss;
    private Button ainfo_bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_information);


        ainfo_account=(TextView)findViewById(R.id.ainfo_account);
        ainfo_name=(TextView)findViewById(R.id.ainfo_name);
        ainfo_phonesss=(TextView)findViewById(R.id.ainfo_phonesss);

        ainfo_bt=(Button)findViewById(R.id.ainfo_bt);
        ainfo_bt.setOnClickListener(ainfo_bt_listener);

    }

    View.OnClickListener ainfo_bt_listener =new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

}
