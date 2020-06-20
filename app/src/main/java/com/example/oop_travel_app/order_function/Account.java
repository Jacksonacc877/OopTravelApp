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
//		System.out.println(fsh.getUserIDs());
//		Account order=new Account();

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
	public boolean revise(String userName, String password, String checkedpassword, String userPhone){
		ArrayList<Account> userIDs = fsh.getUserIDs();
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
			fsh.modifyAccount(order.getUserID(), order.userName, order.password, order.userPhone);
		}
		return true;
	}

}
