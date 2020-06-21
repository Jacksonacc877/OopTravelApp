package com.example.oop_travel_app.search_related;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oop_travel_app.AccountHomepage;
import com.example.oop_travel_app.Check_login;
import com.example.oop_travel_app.DevlopHomepage;
import com.example.oop_travel_app.FirestoreHelper;
import com.example.oop_travel_app.MainActivity;
import com.example.oop_travel_app.order_function.Order;
import com.example.oop_travel_app.order_function.OrderCmdException;
import com.example.oop_travel_app.R;
import com.example.oop_travel_app.SecondFragment;
import com.example.oop_travel_app.order_function.UserOperation;
import com.example.oop_travel_app.order_related.ArrangeHomepage;

public class Booking extends AppCompatActivity {
    private Spinner adultspinner,childspinner,infantspinner;
    private TextView tv_id,tv_title,tv_price,tv_date,login_name;
    private String id,Title,startdate,enddate,username;
    private int price,numOfadult=0,numOfchild=0,numOfinfant=0;
    private ImageButton bs,bo,bh,ba,bd;
    private UserOperation uo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        Bundle bundle=this.getIntent().getExtras();
        uo=new UserOperation(Booking.this);

        tv_id=(TextView)findViewById(R.id.arrange_inputid);
        id=bundle.getString("ID");
        tv_id.setText(id);
        tv_title=(TextView)findViewById(R.id.arrange_inputtripname);
        Title=bundle.getString("Title");
        tv_title.setText(Title);
        price=Integer.parseInt(bundle.getString("Price").toString());
        tv_date=(TextView)findViewById(R.id.arrange_date);
        startdate=bundle.getString("StartDate");
        enddate=bundle.getString("EndDate");
        tv_date.setText("行程日期 ： "+startdate+"~"+enddate);

        tv_price=(TextView)findViewById(R.id.arrange_amount);

        login_name=(TextView)findViewById(R.id.login_name);
        login_name.setText(Check_login.usernam);


        final String[] number={"0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20"};
        ArrayAdapter<String> numberadapter =new ArrayAdapter<>(Booking.this,android.R.layout.simple_spinner_dropdown_item,number);
        adultspinner=(Spinner)findViewById(R.id.spinner_adult);
        adultspinner.setAdapter(numberadapter);
        adultspinner.setOnItemSelectedListener(adultlistener);
        childspinner=(Spinner)findViewById(R.id.spinner_child);
        childspinner.setAdapter(numberadapter);
        childspinner.setOnItemSelectedListener(childlistener);
        infantspinner=(Spinner)findViewById(R.id.spinner_infant);
        infantspinner.setAdapter(numberadapter);
        infantspinner.setOnItemSelectedListener(infantlistener);


        Button arrange_button=(Button) findViewById(R.id.arrange_button);
        arrange_button.setOnClickListener(arrange_button_listener);

        bs=(ImageButton)findViewById(R.id.bs);
        bs.setOnClickListener(bs_listener);
        bo=(ImageButton)findViewById(R.id.bo);
        bo.setOnClickListener(bo_listener);
        bh=(ImageButton)findViewById(R.id.bh);
        bh.setOnClickListener(bh_listener);
        ba=(ImageButton)findViewById(R.id.ba);
        ba.setOnClickListener(ba_listener);
        bd=(ImageButton)findViewById(R.id.bd);
        bd.setOnClickListener(bd_listener);


    }

    AdapterView.OnItemSelectedListener adultlistener=new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               numOfadult=position;
               int total=(numOfadult+numOfchild+numOfinfant)*price;
               tv_price.setText(String.valueOf(total));
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
            tv_price.setText(String.valueOf(total));
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
            tv_price.setText(String.valueOf(total));
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    };
    View.OnClickListener arrange_button_listener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            AlertDialog.Builder builder = new AlertDialog.Builder(Booking.this);
            builder.setTitle("行程預定");
            builder.setMessage("你確定要預訂這行程？");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    int checknum=numOfadult+numOfchild+numOfinfant;
                    if(checknum==0){
                        Toast.makeText(Booking.this,"請選擇人數",Toast.LENGTH_SHORT).show();
                    }else{

                        Boolean od=uo.bookATrip(new Order(Booking.this,-1,username,Integer.valueOf(id),numOfadult,numOfchild,numOfinfant));

                        if(!od){
                            Toast.makeText(Booking.this,"預定失敗 !",Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(Booking.this,"預定成功 !",Toast.LENGTH_LONG).show();
                        }
                    }
                }
            });
            builder.setNegativeButton("cancel",null);
            builder.show();
        }
    };
  View.OnClickListener bs_listener=new View.OnClickListener() {
      @Override
      public void onClick(View v) {
          Intent intent=new Intent(Booking.this,SearchHomepage.class);
          startActivity(intent);
      }
  };
    View.OnClickListener bo_listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(Booking.this, ArrangeHomepage.class);
            startActivity(intent);
        }
    };
    View.OnClickListener bh_listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(Booking.this, MainActivity.class);
            startActivity(intent);
        }
    };
    View.OnClickListener ba_listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(Booking.this, AccountHomepage.class);
            startActivity(intent);
        }
    };
    View.OnClickListener bd_listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(Booking.this, DevlopHomepage.class);
            startActivity(intent);
        }
    };
}
