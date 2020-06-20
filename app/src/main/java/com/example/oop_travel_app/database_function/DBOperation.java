package com.example.oop_travel_app.database_function;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.oop_travel_app.order_function.Order;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class DBOperation{
    private final Object flag = new Object();
    private DatabaseHelper mDBHelper=null;
    private SQLiteDatabase mDb;


    ArrayList<String> output =new ArrayList<String>();



    /**
     * create the connection with trip.db
     */
    public DBOperation (Context c) {
        mDBHelper = new DatabaseHelper(c);
        try {
            mDBHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }
        try {
            mDb = mDBHelper.getWritableDatabase();
        } catch (Exception mSQLException) {
            throw mSQLException;
        }
    }

    /**
     * According to input instruction to execute certain action
     * @param sql is the action execute at database.
     */
    public void changeData(String sql) {
        try {
            mDb.execSQL(sql);
        } catch (Exception e) {
            System.out.println("changeData ERROR : "+e);
        }
    }


    /**
     * After selection , output the selection result
     * if there is no data then return array with nothing and print error
     * @return member output in String[]
     */
    public String[] getResultSet() {
        String[] resultSet = new String[output.size()];
        for (int i = 0; i < output.size(); i++) {
            resultSet[i] = output.get(i);
        }
        output = new ArrayList<String>();
        if(resultSet.length!=0)return resultSet;
        else {
            System.out.println("NO DATA!");
            System.out.println(02);
            return resultSet;
        }
    }


    /**
     * According to input data to output certain outcome of selection
     * @param sql  is the selection want to execute
     * @param numOfCol  is the number of column you want to get data from
     * @return selecting result in String[],(each String separate different data by " , ")
     */
    public void selectData(String sql, int  numOfCol) {
        try {
            Cursor cs = mDb.rawQuery(sql,null);
            String str="";
            int count=0;
            cs.moveToFirst();
            do{
                str="";
                for(int i=0;i<numOfCol;i++){
                    str+=cs.getString(i);
                    if (i!=numOfCol-1) str+=" , ";
                }
                output.add(str);
                count++;
            }while(cs.moveToNext());
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    /**
     * initialize the order table.
     */
    public void createOrderTable() {
        try {
            String sql = "CREATE TABLE IF NOT EXISTS OrderData"
                    + "(orderID		INT		PRIMARY KEY,"
                    + " userID		TEXT	NOT NULL,"
                    + " tripID      INT    	NOT NULL,"
                    + " numOfAdult  INT    	NOT NULL,"
                    + " numOfChild  INT		NOT NULL,"
                    + " numOfInfant INT		NOT NULL)";
            mDb.execSQL(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * According to input instruction to execute certain action
     * @param sql is the action execute at database.
     */
    public void changeDatabase(String sql) {
        try {
            mDb.execSQL(sql);
        } catch (Exception e) {
            System.out.println("ChangeDatabase ERROR : "+e);
        }
    }




}
