package com.example.oop_travel_app.order_related;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.oop_travel_app.FirestoreHelper;
import com.example.oop_travel_app.database_function.DBOperation;
import com.example.oop_travel_app.database_function.DataList;
import com.example.oop_travel_app.order_function.Order;
import com.example.oop_travel_app.order_function.OrderCmdException;
import com.example.oop_travel_app.R;
import com.example.oop_travel_app.order_function.UserOperation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ArrangeHomepage extends AppCompatActivity {
    private EditText InputUserName;
    private EditText InputPhoneNumber;
    private String inputuser;
    private String inputphone;
    private ListView listView;
    private ArrayList<Map<String, Object>> list;
    private listview_forarrange layoutlist;

    UserOperation uo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        fsh.initialize();
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
                System.out.println("test 001");
                String str=String.valueOf(list.get(position).get("orderid"));
                System.out.println("test 002");
                bundle.putString("orderid",str);
                bundle.putString("info",uo.inquireTheTrip(Integer.valueOf(str)));
                System.out.println("test 003");
                intent.putExtras(bundle);
                System.out.println("test 004");
                startActivity(intent);
            }
        });
    }

    View.OnClickListener revised_button_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            System.out.println(fsh.mOrders.size());
            int listsize = list.size();
            for (int i = 0; i < listsize; i++) {
                list.remove(0);
            }
//            UserOperation Uo = new UserOperation(ArrangeHomepage.this);

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

//            String[] result=uo.inquireOrders(inputuser,inputphone);
//            if(result[0].equals("fail")){
//                Toast.makeText(ArrangeHomepage.this,result[1],Toast.LENGTH_LONG).show();
//            }else{
//                for (int i = 2; i < (result.length); i++) {
//                    HashMap<String,Object> item = new HashMap<String,Object>();
//                    System.out.println(result[i]);
//                    String[] str =result[i].split(",");
//                    int num=Integer.valueOf(str[3])+Integer.valueOf(str[4])+Integer.valueOf(str[5]);
//                    String amount=String.valueOf(num);
//                    item.put("tripregion","title test");
//                    item.put("tripprice",str[6]);
//                    item.put("tripdate",str[7]+" ~ "+str[8]);
//                    item.put("tripamountpeople",amount);
//                    item.put("orderid",str[10]);
//                    list.add(item);
//                }
                layoutlist.notifyDataSetChanged();
                listView.setAdapter(layoutlist);
                if (list.size() == 0) {
                    Toast.makeText(ArrangeHomepage.this, "No data !", Toast.LENGTH_SHORT).show();
                }else{
//                    Toast.makeText(ArrangeHomepage.this,result[0]+" "+result[1],Toast.LENGTH_SHORT).show();
                    Toast.makeText(ArrangeHomepage.this,"hay"+" "+"OK!",Toast.LENGTH_SHORT).show();
                }
                System.out.println("test message 8");
            }

        }
    };
}
