package com.example.oop_travel_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.oop_travel_app.order_related.ArrangeHomepage;
import com.example.oop_travel_app.search_related.SearchHomepage;

public class DevlopHomepage extends AppCompatActivity {
    private ImageButton dhs,dho,dhh,dha,dhd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devlop_homepage);

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
