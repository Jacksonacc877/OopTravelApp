package com.example.oop_travel_app.search_related;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.oop_travel_app.database_function.DataList;
import com.example.oop_travel_app.R;

public class Trip_DetailwithBooking extends AppCompatActivity {
    private Button goArrange;
    protected String id;
    protected String title;
    protected String price;
    protected String startday;
    protected String endday;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tripdetailwithbooking);
        Bundle bundle_test=this.getIntent().getExtras();
        String s=bundle_test.getString("ID");
        s=s.replaceAll("\\s","");
        int region_number=Integer.parseInt(s);
        DataList dl=new DataList(Trip_DetailwithBooking.this);
        String[]  info= dl.getTripData(region_number);
        String Id=info[0];
        id=Id;
        String TripName=info[1];
        title=TripName;
        String Price=info[2];
        String[] rePrice =Price.split("//s");
        price=rePrice[0];
        startday=info[3];
        endday=info[4];
        String EnableGo=info[5];
        String Place=info[6];

        TextView ID = (TextView)findViewById(R.id.search_result_id);
        ID.setText("ID : "+Id);
        TextView tripname = (TextView)findViewById(R.id.search_result_tripname);
        tripname.setText("行程名稱 : "+TripName);
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
}
