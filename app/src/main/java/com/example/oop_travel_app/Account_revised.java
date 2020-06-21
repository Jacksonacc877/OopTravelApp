package com.example.oop_travel_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Account_revised extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_revised);
        Bundle bundle=this.getIntent().getExtras();


    }
}
