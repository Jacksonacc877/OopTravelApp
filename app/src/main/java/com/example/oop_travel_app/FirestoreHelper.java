package com.example.oop_travel_app;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.oop_travel_app.database_function.DBOperation;

import com.example.oop_travel_app.order_function.Account;
import com.example.oop_travel_app.order_function.Order;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class FirestoreHelper {
    public ArrayList<Order> mOrders=new ArrayList();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ArrayList<Account> userIDs=new ArrayList();

    public FirestoreHelper(){  }

    public ArrayList<Account> getUserIDs() {
        return userIDs;
    }
    public void orderInit() {
        DocumentReference orderIDs = db.collection("orderIDs").document("example");
        Map<String, Object> info = new HashMap<>();
        info.put("userID", "Hello World");
        info.put("tripID", -1);
        info.put("tripInfo", Arrays.asList(new String[]{"a", "b", "c","d"}));
        info.put("numOfChild", -1);
        info.put("numOfAdult", -1);
        info.put("numOfInfant", -1);
        orderIDs.set(info);
        DocumentReference tripIDs =db.collection("tripIDs").document("-1");
        info = new HashMap<>();
        info.put("bookedTraveler",-3);
        tripIDs.set(info);
    }


    public void userInit() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference userIDs = db.collection("userIDs").document("example");
        Map<String, Object> info = new HashMap<>();
        info.put("name","輸入姓名");
        info.put("password","password");
        info.put("phone","0987654321");
        info.put("orders",Arrays.asList(-1, -2));
        userIDs.set(info);
    }

    public void newOrder(Order o,int maxID,int booked,List orders){
        DocumentReference orderIDs = db.collection("orderIDs").document(String.valueOf(maxID+1));
        Map<String, Object> info = new HashMap<>();
        info.put("orderID",maxID+1);
        info.put("userID",o.getUserID());
        info.put("tripID", o.getTripID());
        info.put("tripInfo", o.getTripInfo());
        info.put("numOfAdult", o.getNumOfAdult());
        info.put("numOfChild", o.getNumOfChild());
        info.put("numOfInfant", o.getNumOfInfant());
        orderIDs.set(info);
        syncTrip(o,booked);
        syncUser(o,orders);
    }

    public void syncTrip(Order o,int booked){
        DocumentReference tripIDs = db.collection("tripIDs").document(String.valueOf(o.getTripID()));
        Map<String, Object>info = new HashMap<>();
        info.put("bookedTraveler",booked+o.getNumOfChild()+o.getNumOfAdult()+o.getNumOfInfant());
        tripIDs.set(info);
    }

    public void syncUser(Order o,List orders){
        DocumentReference userIDs = db.collection("userIDs").document(String.valueOf(o.getUserID()));
        Map<String, Object>info = new HashMap<>();
        info.put("bookedTraveler",orders.add(o.getOrderID()));
        userIDs.set(info);
    }

    public void deleteOrder(Integer oID){
        db.collection("orderIDs").document(oID.toString())
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully deleted!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error deleting document", e);
                    }
                });
    }

    public Order readOrder(int orderID) {
        Order order=new Order();
        for (Order o:mOrders){
            if (o.getOrderID()==orderID) {
                order=o;
                break;
            }
        }
        return order;
    }

    public void initialize(){
        db.collection("orderIDs").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>(){
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        mOrders.add(document.toObject(Order.class));
                        Log.d("System.out", document.getId() + " => " + document.getData());
                    }
                } else {
                    Log.d("System.out", "Error getting documents: ", task.getException());
                }
                System.out.println("Loading finish !");
            }
        });
    }
    public void initialize2(){
        db.collection("userIDs").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>(){
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        userIDs.add(document.toObject(Account.class));
                        Log.d("System.out", document.getId() + " => " + document.getData());
                    }
                } else {
                    Log.d("System.out", "Error getting documents: ", task.getException());
                }
                System.out.println("Loading finish !");
            }
        });
    }
    /**
     * create a new account or modify the imfomation of account
     * @param userID
     * @param name
     * @param password
     * @param phone
     */
    public void modifyAccount(String userID,String name,String password,String phone){
        DocumentReference userIDs = db.collection("userIDs").document(userID);
        Map<String, Object> info = new HashMap<>();
        info.put("name",name);
        info.put("password",password);
        info.put("phone",phone);
        userIDs.set(info);
    }




}


