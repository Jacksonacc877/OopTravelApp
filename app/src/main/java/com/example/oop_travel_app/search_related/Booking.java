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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oop_travel_app.FirestoreHelper;
import com.example.oop_travel_app.order_function.Order;
import com.example.oop_travel_app.order_function.OrderCmdException;
import com.example.oop_travel_app.R;
import com.example.oop_travel_app.SecondFragment;
import com.example.oop_travel_app.order_function.UserOperation;

public class Booking extends AppCompatActivity {
    private Spinner adultspinner;
    private Spinner childspinner;
    private Spinner infantspinner;
    private TextView tv_id;
    private TextView tv_title;
    private TextView tv_price;
    private TextView tv_date;
    private EditText ed_username;
    private EditText ed_phone;
    private String id;
    private String Title;
    private String startdate;
    private String enddate;
    private int price;
    private int numOfadult=0;
    private int numOfchild=0;
    private int numOfinfant=0;
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
        ed_username=(EditText)findViewById(R.id.arrange_inputusername);
        ed_phone=(EditText)findViewById(R.id.arrange_inputphone);


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
        Button backtoHome=(Button)findViewById(R.id.backtoHome);
        backtoHome.setOnClickListener(homelistener);



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
                    String user=ed_username.getText().toString();
                    String phone =ed_phone.getText().toString();
                    int checknum=numOfadult+numOfchild+numOfinfant;
                    if(user.equals("")  ){
                        Toast.makeText(Booking.this,"請輸入姓名",Toast.LENGTH_SHORT).show();
                    }else if(phone.equals("")){
                        Toast.makeText(Booking.this,"請輸入電話",Toast.LENGTH_SHORT).show();
                    }else if(checknum==0){
                        Toast.makeText(Booking.this,"請選擇人數",Toast.LENGTH_SHORT).show();
                    }else{
//                        UserOperation Uo=new UserOperation(Booking.this);
//                        int orderid=Uo.getLastID();
                        System.out.println("test message 001");
                        Boolean od=uo.bookATrip(new Order(Booking.this,-1,user,Integer.valueOf(id),numOfadult,numOfchild,numOfinfant));
                        System.out.println("test message 002");
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
    View.OnClickListener homelistener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent =new Intent(Booking.this, SecondFragment.class);
            startActivity(intent);
        }
    };
}
