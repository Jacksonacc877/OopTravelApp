package com.example.oop_travel_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oop_travel_app.order_function.Account;

public class Account_revised extends AppCompatActivity {
    private EditText ar_etname,ar_etphone,ar_etpassword,ar_etrepassword;
    private TextView ar_account_fix;
    private Button ar_bt;
    private String str_userID,str_name,str_phones;
    Account acc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_revised);

        acc=new Account("");

        Bundle bundle=this.getIntent().getExtras();
        str_userID=bundle.getString("str_userID");
        str_name =bundle.getString("str_name");
        str_phones=bundle.getString("str_phones");

        ar_account_fix=(TextView)findViewById(R.id.ar_account_fix);
        ar_account_fix.setText(str_userID);

        ar_etname=(EditText)findViewById(R.id.ar_etname);
        ar_etname.setText(str_name);
        ar_etphone=(EditText)findViewById(R.id.ar_etphone);
        ar_etphone.setText(str_phones);
        ar_etpassword=(EditText)findViewById(R.id.ar_etpassword);
        ar_etrepassword=(EditText)findViewById(R.id.ar_etrepassword);

        ar_bt=(Button)findViewById(R.id.ar_bt);
        ar_bt.setOnClickListener(ar_bt_listener);

    }

    View.OnClickListener ar_bt_listener =new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            System.out.println(0);
            String et_password=ar_etpassword.getText().toString();
            System.out.println(et_password);
            String et_repassword=ar_etrepassword.getText().toString();
            System.out.println(et_repassword);
            str_phones=ar_etphone.getText().toString();
            if(et_password.equals(et_repassword)){
                acc.revise(str_name,et_password,et_repassword,str_phones);
                Toast.makeText(Account_revised.this,"Revised success~ ", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(Account_revised.this,"Please check password again! ", Toast.LENGTH_LONG).show();
            }
        }
    };



}
