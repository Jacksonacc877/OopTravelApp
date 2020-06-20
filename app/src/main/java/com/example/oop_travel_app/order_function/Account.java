package com.example.oop_travel_app.order_function;

import android.content.Context;

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

	FireStoreHelper fbs = new FireStoreHelper();
	public boolean register(String ID, String userName, String password, String userPhone){
		ArrayList<String> userIDs = fbs.getUserIDs();
		for(String str:userIDs){
			if(str.contentEquals(ID)){

				return true;
			}
			else{
				return false;
			}
		}
	}
	public boolean login(String ID, String password) {
		ArrayList<String> data = fbs.getData();
		for (String str : data) {
			if (str.contentEquals(account)) {


				if (str.contentEquals(password)) {

					return true;
				}
				else {
					return false;
				}
			}
		}
	}
	public ArrayList<String> outputData(ArrayList<String> data){
		return data;
	}
	public String revise(String userName, String password, String checkedpassword, String userPhone){
		if(!userName.equals(null))

		return null;
	}

}
