package com.example.oop_travel_app.search_related;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oop_travel_app.AccountHomepage;
import com.example.oop_travel_app.Check_login;
import com.example.oop_travel_app.DevlopHomepage;
import com.example.oop_travel_app.FirestoreHelper;
import com.example.oop_travel_app.MainActivity;
import com.example.oop_travel_app.database_function.DataList;
import com.example.oop_travel_app.R;
import com.example.oop_travel_app.order_related.ArrangeHomepage;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Trip_DetailwithBooking extends AppCompatActivity {
    private Button goArrange;
    private ImageButton tds,tdo,tdh,tda,tdd;
    protected String id, title,price,startday,endday;

    private boolean okdate=true;

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
        System.out.println("test"+info[0]+"test"+info[1]+"test"+info[2]);
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
        tripname.setText(title);
        tripname.setSelected(true);
        TextView price = (TextView)findViewById(R.id.search_result_price);
        price.setText("?????? : "+Price);
        TextView startdate = (TextView)findViewById(R.id.search_result_startdate);
        startdate.setText("???????????? : "+startday);
        TextView enddate = (TextView)findViewById(R.id.search_result_enddate);
        enddate.setText("???????????? : "+endday);
        TextView enablego = (TextView)findViewById(R.id.search_result_enablego);
        enablego.setText("???????????? : "+EnableGo);
        TextView placess = (TextView)findViewById(R.id.search_result_place);
        placess.setText("??????????????? : "+Place);
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
            if(Check_login.alreadylogin==0){
                AlertDialog.Builder builder =new AlertDialog.Builder(Trip_DetailwithBooking.this);
                builder.setTitle("???????????????");
                builder.setMessage("????????????????????????");
                builder.setPositiveButton("??????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=new Intent(Trip_DetailwithBooking.this,AccountHomepage.class);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("??????",null);
                builder.show();

            }else{
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                Date curdate=new Date(System.currentTimeMillis());
                String currentdate=sdf.format(curdate);

                String[] splitcurdate=currentdate.split("-");
                int curyear=Integer.valueOf(splitcurdate[0]);
                int curmonth=Integer.valueOf(splitcurdate[1]);
                int curday=Integer.valueOf(splitcurdate[2]);
                String[] splitstartdate=startday.split("-");
                int staryear=Integer.valueOf(splitstartdate[0]);
                int starmonth=Integer.valueOf(splitstartdate[1]);
                int starday=Integer.valueOf(splitstartdate[2]);
                if(curyear>staryear){
                    okdate=false;
                }
                if(curyear==staryear&&curmonth>starmonth){
                    okdate=false;
                }
                if(curyear==staryear&&curmonth==starmonth&&curday>starday){
                    okdate=false;
                }
                if(okdate){
                    Intent intent = new Intent(Trip_DetailwithBooking.this, Booking.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("ID",id );
                    bundle.putString("Title",title);
                    bundle.putString("Price",price);
                    bundle.putString("StartDate",startday);
                    bundle.putString("EndDate",endday);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }else{
                    Toast.makeText(Trip_DetailwithBooking.this,"Exceed start date!",Toast.LENGTH_LONG).show();
                }
            }


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
