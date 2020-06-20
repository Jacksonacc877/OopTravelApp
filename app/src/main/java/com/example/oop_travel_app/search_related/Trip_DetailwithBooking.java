package com.example.oop_travel_app.search_related;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.oop_travel_app.AccountHomepage;
import com.example.oop_travel_app.DevlopHomepage;
import com.example.oop_travel_app.FirestoreHelper;
import com.example.oop_travel_app.MainActivity;
import com.example.oop_travel_app.database_function.DataList;
import com.example.oop_travel_app.R;
import com.example.oop_travel_app.order_related.ArrangeHomepage;

public class Trip_DetailwithBooking extends AppCompatActivity {
    private Button goArrange;
    private ImageButton tds,tdo,tdh,tda,tdd;
    protected String id, title,price,startday,endday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tripdetailwithbooking);

        Bundle bundle_test=this.getIntent().getExtras();
        String s=bundle_test.getString("ID");
        int bookedTraveler =bundle_test.getInt("bookTraveler");;
        s=s.replaceAll("\\s","");
        int region_number=Integer.parseInt(s);
        DataList dl=new DataList(Trip_DetailwithBooking.this);
        String[]  info= dl.getTripData(region_number,bookedTraveler);
        id=info[0];
        title=info[1];
        String Price=info[2];
        String[] rePrice =Price.split("//s");
        price=rePrice[0];
        startday=info[3];
        endday=info[4];
        String EnableGo=info[5];
        String Place=info[6];

        TextView ID = (TextView)findViewById(R.id.search_result_id);
        ID.setText("ID : "+id);
        TextView tripname = (TextView)findViewById(R.id.search_result_tripname);
        tripname.setText("行程名稱 : "+title);
        TextView price = (TextView)findViewById(R.id.search_result_price);
        price.setText("價錢 : "+Price);
        TextView startdate = (TextView)findViewById(R.id.search_result_startdate);
        startdate.setText("開始日期 : "+startday);
        TextView enddate = (TextView)findViewById(R.id.search_result_enddate);
        enddate.setText("結束日期 : "+endday);
        TextView enablego = (TextView)findViewById(R.id.search_result_enablego);
        enablego.setText("出團人數 : "+EnableGo);
        TextView placess = (TextView)findViewById(R.id.search_result_place);
        placess.setText("旅遊團空位 : "+Place);
        goArrange =(Button)findViewById(R.id.gobooking);
        goArrange.setOnClickListener(goArrangeListener);

        tds=(ImageButton)findViewById(R.id.tds);
        tds.setOnClickListener(tds_listener);
        tdo=(ImageButton)findViewById(R.id.tdo);
        tdo.setOnClickListener(tdo_listener);
        tdh=(ImageButton)findViewById(R.id.tdh);
        tdh.setOnClickListener(tdh_listener);
        tda=(ImageButton)findViewById(R.id.tda);
        tda.setOnClickListener(tda_listener);
        tdd=(ImageButton)findViewById(R.id.tdd);
        tdd.setOnClickListener(tdd_listener);
    }

    View.OnClickListener goArrangeListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Trip_DetailwithBooking.this, Booking.class);
            Bundle bundle = new Bundle();
            bundle.putString("ID",id );
            bundle.putString("Title",title);
            bundle.putString("Price",price);
            bundle.putString("StartDate",startday);
            bundle.putString("EndDate",endday);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    };
    View.OnClickListener tds_listener =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent =new Intent(Trip_DetailwithBooking.this,SearchHomepage.class);
            startActivity(intent);
        }
    };
    View.OnClickListener tdo_listener =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent =new Intent(Trip_DetailwithBooking.this, ArrangeHomepage.class);
            startActivity(intent);
        }
    };
    View.OnClickListener tdh_listener =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent =new Intent(Trip_DetailwithBooking.this, MainActivity.class);
            startActivity(intent);
        }
    };
    View.OnClickListener tda_listener =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent =new Intent(Trip_DetailwithBooking.this, AccountHomepage.class);
            startActivity(intent);
        }
    };
    View.OnClickListener tdd_listener =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent =new Intent(Trip_DetailwithBooking.this, DevlopHomepage.class);
            startActivity(intent);
        }
    };

}
