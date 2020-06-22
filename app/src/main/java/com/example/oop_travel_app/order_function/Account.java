package com.example.oop_travel_app.order_function;

import android.content.Context;

import com.example.oop_travel_app.FirestoreHelper;
import com.example.oop_travel_app.database_function.DBOperation;

import java.util.ArrayList;

public class Account {

	public String getUserID() {
		return userID;
	}
	private String userID;

	public String getUserName() {
		return userName;
	}
	private String userName;

	public String getPassword() {
		return password;
	}
	private String password;

	public String getUserPhone() {
		return userPhone;
	}
	private String userPhone;
	private FirestoreHelper fsh ;
	public Account(){

	}
	public Account(String s){
		fsh = new FirestoreHelper();
		fsh.userInit();
	}

	public Account(String userID,String userName,String userPhone,String password){
		this.userID=userID;
		this.userName=userName;
		this.userPhone=userPhone;
		this.password=password;
	}



	public boolean register(String ID, String userName, String password, String userPhone){
		ArrayList<Account> userIDs = fsh.getUserIDs();
		boolean same=false;
		for(Account a:userIDs){
			if((a.getUserID()).equals(ID))same=true;
		}
		if (same)return false;
		else {
			fsh.modifyAccount(ID, userName, password, userPhone);
			return true;
		}
	}
	public boolean login(String ID, String password) {
		ArrayList<Account> userIDs = fsh.getUserIDs();


		for(Account a:userIDs){
			System.out.println(a.getUserID()+"/00/"+ID);
			System.out.println(a.getPassword()+"/00/"+password);
			if (a.getUserID().equals(ID)&&a.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}

	public ArrayList<String> outputData(ArrayList<String> data){
		return data;
	}
	public boolean revise(String userID,String name, String password, String checkedpassword, String userPhone){
		System.out.println("check userID "+userID);
		ArrayList<Account> userIDs = fsh.getUserIDs();
		Account order=new Account();
		for(Account a:userIDs){
			if (userID.equals(a.getUserID())){
				order=a;
				break;
			}
		}
		if (checkedpassword.equals(password)){
			fsh.modifyAccount(order.getUserID(),name, password,userPhone);
			return true;
		}else{
			return false;
		}

	}

}
