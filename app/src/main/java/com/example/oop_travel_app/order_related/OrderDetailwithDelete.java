package com.example.oop_travel_app.order_related;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oop_travel_app.R;
import com.example.oop_travel_app.order_function.UserOperation;


public class OrderDetailwithDelete extends AppCompatActivity {
    private int orderid;
    private String userid;
    private String info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //uo=new UserOperation(this);
        ArrangeHomepage ah=new ArrangeHomepage();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderdetail);
        System.out.println("test message 0");
        Bundle bundle=this.getIntent().getExtras();
        System.out.println("test message 1");
        orderid=Integer.valueOf(bundle.getString("orderid"));
        System.out.println("asd"+orderid+"dfsdf");
        System.out.println("test message 3");
        info=bundle.getString("info");
        String[] str=bundle.getString("info").split(",");
        System.out.println("test message 4");


        Button gorevised =(Button)findViewById(R.id.gorevised_button);
        gorevised.setOnClickListener(gorevisedlistener);
        Button godelete =(Button)findViewById(R.id.godelete_button);
        godelete.setOnClickListener(godeletelistener);
        System.out.println("test message 5");

        TextView tripname=(TextView)findViewById(R.id.tripname);
        tripname.setText(str[7]);
        TextView userid=(TextView)findViewById(R.id.userid);
        userid.setText(str[2]);
        TextView userphone=(TextView)findViewById(R.id.userphone);
        userphone.setText(str[8]);
        TextView tripdate=(TextView)findViewById(R.id.tripdate);
        tripdate.setText(str[10]);
        TextView passengerinfo=(TextView)findViewById(R.id.passengerinfo);
        passengerinfo.setText(str[11]);
        TextView price=(TextView)findViewById(R.id.price);
        price.setText(str[8]);
        System.out.println("test message 6");
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
            builder.setTitle("行程刪除");
            builder.setMessage("你確定要刪除行程？"+"\n"+"注意!行程刪除將無法復原!");
            builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    UserOperation Uo2=new UserOperation(OrderDetailwithDelete.this);
                    Boolean d=Uo2.deleteTheTrip(orderid);
                    Toast.makeText(OrderDetailwithDelete.this,"退訂成功,已取消您的預訂紀錄",Toast.LENGTH_LONG).show();
                }
            });
            builder.setNegativeButton("取消",null);
            builder.show();
        }
    };
}