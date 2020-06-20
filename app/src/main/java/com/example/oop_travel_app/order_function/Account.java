package com.example.oop_travel_app.order_function;

import android.content.Context;

import com.example.oop_travel_app.FirestoreHelper;
import com.example.oop_travel_app.database_function.DBOperation;

import java.util.ArrayList;

public class Account {


	public String getID() {
		return ID;
	}
	private String ID;

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

	Account(){}

	FirestoreHelper fbs = new FirestoreHelper();
	public boolean register(String ID, String userName, String password, String userPhone){
		ArrayList<Account> userIDs = fbs.getUserIDs();
		Account order=new Account();
		for(Account a:userIDs){
			if((a.getID()).equals(ID)){
				order = a;
				fbs.modifyAccount(order.ID, order.userName, order.password, order.userPhone);
				return true;
			}
		}
		return false;
	}
	public boolean login(String ID, String password) {
		ArrayList<Account> userIDs = fbs.getUserIDs();
		Account order=new Account();
		for(Account a:userIDs){
			if (a.equals(ID)&&a.equals(password)) {
				return true;
			}
		}
		return false;
	}
	public ArrayList<String> outputData(ArrayList<String> data){
		return data;
	}
	public boolean revise(String userName, String password, String checkedpassword, String userPhone){
		ArrayList<Account> userIDs = fbs.getUserIDs();
		Account order=new Account();
		for(Account a:userIDs){
			if(!password.equals(null)&&!checkedpassword.equals(null)&&password!=checkedpassword) {
				return false;
			}
			else if(userName.equals(null)) {
				userName = a.getUserName();
			}
			else if(password.equals(null)) {
				password = a.getPassword();
			}
			else if(userPhone.equals(null)) {
				userPhone = a.getUserPhone();
			}
			fbs.modifyAccount(order.ID, order.userName, order.password, order.userPhone);
		}
		return true;
	}

}
