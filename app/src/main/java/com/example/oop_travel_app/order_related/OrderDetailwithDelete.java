package com.example.oop_travel_app.order_related;

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
import com.example.oop_travel_app.DevlopHomepage;
import com.example.oop_travel_app.MainActivity;
import com.example.oop_travel_app.R;
import com.example.oop_travel_app.order_function.UserOperation;
import com.example.oop_travel_app.search_related.SearchHomepage;


public class OrderDetailwithDelete extends AppCompatActivity {
    private int orderid;
    private String userid,info;
    private ImageButton os,oo,oh,oa,od;
    UserOperation Uo2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Uo2=new UserOperation(OrderDetailwithDelete.this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderdetail);
        Bundle bundle=this.getIntent().getExtras();
        orderid=Integer.valueOf(bundle.getString("orderid"));
        info=bundle.getString("info");
        String[] str=bundle.getString("info").split(",");


        Button gorevised =(Button)findViewById(R.id.gorevised_button);
        gorevised.setOnClickListener(gorevisedlistener);
        Button godelete =(Button)findViewById(R.id.godelete_button);
        godelete.setOnClickListener(godeletelistener);

        TextView tripname=(TextView)findViewById(R.id.tripname);
        tripname.setText(str[7]);
        TextView userid=(TextView)findViewById(R.id.userid);
        userid.setText(str[2]);
        TextView tripdate=(TextView)findViewById(R.id.tripdate);
        tripdate.setText(str[10]);
        TextView passengerinfo=(TextView)findViewById(R.id.passengerinfo);
        passengerinfo.setText(str[11]);
        TextView price=(TextView)findViewById(R.id.price);
        price.setText(str[8]);

        os=(ImageButton)findViewById(R.id.os);
        os.setOnClickListener(os_listener);
        oo=(ImageButton)findViewById(R.id.oo);
        oo.setOnClickListener(oo_listener);
        oh=(ImageButton)findViewById(R.id.oh);
        oh.setOnClickListener(oh_listener);
        oa=(ImageButton)findViewById(R.id.oa);
        oa.setOnClickListener(oa_listener);
        od=(ImageButton)findViewById(R.id.od);
        od.setOnClickListener(od_listener);
    }

    View.OnClickListener gorevisedlistener =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent =new Intent(OrderDetailwithDelete.this, Revised.class);
            Bundle bundle=new Bundle();
            String orderidtostring=String.valueOf(orderid);
            bundle.putString("orderid",orderidtostring);
            bundle.putString("info",info);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    };
    View.OnClickListener godeletelistener =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AlertDialog.Builder builder =new AlertDialog.Builder(OrderDetailwithDelete.this);
            builder.setTitle("????????????");
            builder.setMessage("???????????????????????????"+"\n"+"??????!???????????????????????????!");
            builder.setPositiveButton("??????", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    Boolean d=Uo2.deleteTheTrip(orderid);
                    Toast.makeText(OrderDetailwithDelete.this,"????????????,???????????????????????????",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(OrderDetailwithDelete.this,ArrangeHomepage.class);
                    startActivity(intent);
                }
            });
            builder.setNegativeButton("??????",null);
            builder.show();
        }
    };
    View.OnClickListener os_listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent =new Intent(OrderDetailwithDelete.this, SearchHomepage.class);
            startActivity(intent);
        }
    };
    View.OnClickListener oo_listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent =new Intent(OrderDetailwithDelete.this, ArrangeHomepage.class);
            startActivity(intent);
        }
    };
    View.OnClickListener oh_listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent =new Intent(OrderDetailwithDelete.this, MainActivity.class);
            startActivity(intent);
        }
    };
    View.OnClickListener oa_listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent =new Intent(OrderDetailwithDelete.this, AccountHomepage.class);
            startActivity(intent);
        }
    };
    View.OnClickListener od_listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent =new Intent(OrderDetailwithDelete.this, DevlopHomepage.class);
            startActivity(intent);
        }
    };

}
