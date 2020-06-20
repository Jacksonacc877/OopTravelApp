package com.example.oop_travel_app.order_related;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.oop_travel_app.AccountHomepage;
import com.example.oop_travel_app.DevlopHomepage;
import com.example.oop_travel_app.FirestoreHelper;
import com.example.oop_travel_app.MainActivity;
import com.example.oop_travel_app.database_function.DBOperation;
import com.example.oop_travel_app.database_function.DataList;
import com.example.oop_travel_app.order_function.Order;
import com.example.oop_travel_app.order_function.OrderCmdException;
import com.example.oop_travel_app.R;
import com.example.oop_travel_app.order_function.UserOperation;
import com.example.oop_travel_app.search_related.SearchHomepage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ArrangeHomepage extends AppCompatActivity {
    private EditText InputUserName,InputPhoneNumber;
    private ImageButton as,ao,ah,aa,ad;
    private String inputuser,inputphone;
    private ListView listView;
    private ArrayList<Map<String, Object>> list;
    private listview_forarrange layoutlist;
    UserOperation uo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        uo=new UserOperation(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrangehomepage);

        InputUserName = (EditText) findViewById(R.id.revised_inputusername);
        InputPhoneNumber = (EditText) findViewById(R.id.revised_inputorderid);

        Button revised_button = (Button) findViewById(R.id.revised_button);
        revised_button.setOnClickListener(revised_button_listener);

        listView = (ListView) findViewById(R.id.arrange_list);
        list = new ArrayList<Map<String, Object>>();
        layoutlist = new listview_forarrange(ArrangeHomepage.this, list);
        listView.setAdapter(layoutlist);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ArrangeHomepage.this, OrderDetailwithDelete.class);
                Bundle bundle = new Bundle();
                String str=String.valueOf(list.get(position).get("orderid"));
                bundle.putString("orderid",str);
                bundle.putString("info",uo.inquireTheTrip(Integer.valueOf(str)));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        as=(ImageButton)findViewById(R.id.as);
        as.setOnClickListener(as_listener);
        ao=(ImageButton)findViewById(R.id.ao);
        ao.setOnClickListener(ao_listener);
        ah=(ImageButton)findViewById(R.id.ah);
        ah.setOnClickListener(ah_listener);
        aa=(ImageButton)findViewById(R.id.aa);
        aa.setOnClickListener(aa_listener);
        ad=(ImageButton)findViewById(R.id.ad);
        ad.setOnClickListener(ad_listener);
    }

    View.OnClickListener revised_button_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int listsize = list.size();
            for (int i = 0; i < listsize; i++) {
                list.remove(0);
            }

            inputuser = InputUserName.getText().toString();
            inputphone = InputPhoneNumber.getText().toString();
            ArrayList<Order> result=uo.inquireOrders(inputuser);

            if(result.size()==0){
                Toast.makeText(ArrangeHomepage.this,"fail",Toast.LENGTH_LONG).show();
            }else {
                for (int i = 0; i < (result.size()); i++) {
                    HashMap<String, Object> item = new HashMap<String, Object>();
                    int num = result.get(i).getNumOfAdult() + result.get(i).getNumOfChild() + result.get(i).getNumOfInfant();
                    String amount = String.valueOf(num);
                    item.put("tripregion", result.get(i).getTripInfo().get(0));
                    item.put("tripprice", result.get(i).getTripInfo().get(1));
                    item.put("tripdate",  result.get(i).getTripInfo().get(2) + " ~ " +  result.get(i).getTripInfo().get(3));
                    item.put("tripamountpeople", amount);
                    item.put("orderid", result.get(i).getOrderID());
                    list.add(item);
                }

                layoutlist.notifyDataSetChanged();
                listView.setAdapter(layoutlist);
                if (list.size() == 0) {
                    Toast.makeText(ArrangeHomepage.this, "No data !", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(ArrangeHomepage.this,"hay"+" "+"OK!",Toast.LENGTH_SHORT).show();
                }
            }

        }
    };
    View.OnClickListener as_listener =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(ArrangeHomepage.this, SearchHomepage.class);
            startActivity(intent);
        }
    };
    View.OnClickListener ao_listener =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(ArrangeHomepage.this, ArrangeHomepage.class);
            startActivity(intent);
        }
    };
    View.OnClickListener ah_listener =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(ArrangeHomepage.this, MainActivity.class);
            startActivity(intent);
        }
    };
    View.OnClickListener aa_listener =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(ArrangeHomepage.this, AccountHomepage.class);
            startActivity(intent);
        }
    };
    View.OnClickListener ad_listener =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(ArrangeHomepage.this, DevlopHomepage.class);
            startActivity(intent);
        }
    };


}
