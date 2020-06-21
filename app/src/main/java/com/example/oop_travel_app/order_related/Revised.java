package com.example.oop_travel_app.order_related;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oop_travel_app.AccountHomepage;
import com.example.oop_travel_app.DevlopHomepage;
import com.example.oop_travel_app.MainActivity;
import com.example.oop_travel_app.R;
import com.example.oop_travel_app.order_function.Order;
import com.example.oop_travel_app.order_function.UserOperation;
import com.example.oop_travel_app.search_related.SearchHomepage;

public class Revised extends AppCompatActivity {
    private TextView inrevisd_user,inrevised_phone,adultnum,childnum,infantnum;
    private TextView price_before,price_after;
    private Spinner adultspinner,childspinner,infantspinner;
    private int price,numOfadult=0,numOfchild=0,numOfinfant=0,orderid;
    private ImageButton rs,ro,rh,ra,rd;
    private Order oldOne =new Order();
    private UserOperation Uo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revised);
        Bundle bundle=this.getIntent().getExtras();
        String orderidstring=bundle.getString("orderid");
        orderid=Integer.valueOf(orderidstring);
        String[] str=bundle.getString("info").split(",");
        oldOne =new Order(this,Integer.valueOf(str[1]),str[2],Integer.valueOf(str[3]),
                Integer.valueOf(str[4]),Integer.valueOf(str[5]),Integer.valueOf(str[6]));
        Uo=new UserOperation(Revised.this);


        String username=str[2];
        inrevisd_user=(TextView)findViewById(R.id.inrevised_user);
        inrevisd_user.setText("訂單人 ： "+username);
        String phonenumber=str[9];
        inrevised_phone=(TextView)findViewById(R.id.inrevised_phone);
        inrevised_phone.setText("電話 ： "+phonenumber);
        String getadult=str[4];
        adultnum=(TextView)findViewById(R.id.inrevised_adultnum);
        adultnum.setText("成人人數 ： "+getadult);
        numOfadult=Integer.valueOf(getadult);
        String getchild=str[5];
        childnum=(TextView)findViewById(R.id.inrevised_childnum);
        childnum.setText("小孩人數 ： "+getchild);
        numOfchild=Integer.valueOf(getchild);
        String getinfant=str[6];
        infantnum=(TextView)findViewById(R.id.inrevised_infantnum);
        infantnum.setText("嬰兒人數 ： "+getinfant);
        numOfinfant=Integer.valueOf(getinfant);
        String prices=str[12];
        price_before=(TextView)findViewById(R.id.inrevised_pricebefore);
        price_before.setText("訂單總價 ： "+prices);
        price_after=(TextView)findViewById(R.id.inrevisd_priceafter);
        price_after.setText("0");

        price=Integer.valueOf(str[8]);

        Button inrevised_button=(Button)findViewById(R.id.inrevised_button);
        inrevised_button.setOnClickListener(inrevised_button_listener);


        final String[] number={"0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20"};
        ArrayAdapter<String> numberadapter =new ArrayAdapter<>(Revised.this,android.R.layout.simple_spinner_dropdown_item,number);
        adultspinner=(Spinner)findViewById(R.id.inrevisd_adultspinner);
        adultspinner.setAdapter(numberadapter);
        adultspinner.setSelection(numOfadult);
        adultspinner.setOnItemSelectedListener(adultlistener);
        childspinner=(Spinner)findViewById(R.id.inrevised_childspinner);
        childspinner.setAdapter(numberadapter);
        childspinner.setSelection(numOfchild);
        childspinner.setOnItemSelectedListener(childlistener);
        infantspinner=(Spinner)findViewById(R.id.inrevised_infantspinner);
        infantspinner.setAdapter(numberadapter);
        infantspinner.setSelection(numOfinfant);
        infantspinner.setOnItemSelectedListener(infantlistener);

        rs=(ImageButton)findViewById(R.id.rs);
        rs.setOnClickListener(rs_listener);
        ro=(ImageButton)findViewById(R.id.ro);
        ro.setOnClickListener(ro_listener);
        rh=(ImageButton)findViewById(R.id.rh);
        rh.setOnClickListener(rh_listener);
        ra=(ImageButton)findViewById(R.id.ra);
        ra.setOnClickListener(ra_listener);
        rd=(ImageButton)findViewById(R.id.rd);
        rd.setOnClickListener(rd_listener);

    }
    View.OnClickListener inrevised_button_listener =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            boolean op=Uo.updateTheTrip(oldOne,numOfadult,numOfchild,numOfinfant);
            if (op){
                Toast.makeText(Revised.this,"修改成功",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(Revised.this,ArrangeHomepage.class);
                startActivity(intent);
            }
            else Toast.makeText(Revised.this,Uo.getOperatinonState(),Toast.LENGTH_LONG).show();
        }
    };


    AdapterView.OnItemSelectedListener adultlistener =new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            numOfadult=position;
            int total=(numOfadult+numOfchild+numOfinfant)*price;
            price_after.setText(String.valueOf(total));
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
    AdapterView.OnItemSelectedListener childlistener =new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            numOfchild=position;
            int total=(numOfadult+numOfchild+numOfinfant)*price;
            price_after.setText(String.valueOf(total));
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
    AdapterView.OnItemSelectedListener infantlistener =new AdapterView.OnItemSelectedListener() {
        @Override

        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            numOfinfant=position;
            int total=(numOfadult+numOfchild+numOfinfant)*price;
            price_after.setText(String.valueOf(total));
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    };
    View.OnClickListener rs_listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(Revised.this, SearchHomepage.class);
            startActivity(intent);
        }
    };
    View.OnClickListener ro_listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(Revised.this, ArrangeHomepage.class);
            startActivity(intent);
        }
    };
    View.OnClickListener rh_listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(Revised.this, MainActivity.class);
            startActivity(intent);
        }
    };
    View.OnClickListener ra_listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(Revised.this, AccountHomepage.class);
            startActivity(intent);
        }
    };
    View.OnClickListener rd_listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(Revised.this, DevlopHomepage.class);
            startActivity(intent);
        }
    };

}
