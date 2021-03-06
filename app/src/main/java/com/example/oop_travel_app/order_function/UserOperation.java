package com.example.oop_travel_app.order_function;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.oop_travel_app.FirestoreHelper;
import com.example.oop_travel_app.database_function.DBOperation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class UserOperation {
	Context context;
	DBOperation  dbo ;
	public FirestoreHelper fsh;
	private String operatinonState="";


	public String getOperatinonState() {
		return operatinonState;
	}

	public UserOperation(Context c){
		this.context=c;
		dbo = new DBOperation (c);
		fsh=new FirestoreHelper();
		fsh.orderInit();
	}

	public boolean bookATrip(Order order){
		int maxID=-1;
		int booked=0;

		int tripID=order.getTripID();
		int numOfAdult=order.getNumOfAdult();
		int numOfChild=order.getNumOfChild();
		int numOfInfant=order.getNumOfInfant();
		dbo.selectData("select upperBound from trip where tripID = '" + tripID + "'", 1);
		int upperBound=Integer.valueOf((dbo.getResultSet())[0]);
		System.out.println("upperBound : "+upperBound);

		for (Order o:fsh.mOrders){
			if (o.getTripID()==tripID)booked+=o.getNumOfAdult()+o.getNumOfInfant()+o.getNumOfChild();
			if (o.getOrderID()>maxID) maxID=o.getOrderID();
		}
		if(upperBound>=(booked+numOfAdult+numOfChild+numOfInfant)){
			fsh.newOrder(order,maxID,booked);
			return true ;
		}else{
			operatinonState = "人數超過上限";
			return false;
		}
	}

    public boolean deleteTheTrip(int orderID) {
        int numOfRemain=0,numOfThis=0,tID=0;
		for (Order o:fsh.mOrders) {
			if(o.getOrderID()==orderID)	{
				tID=o.getTripID();
				numOfThis=o.getNumOfAdult()+o.getNumOfInfant()+o.getNumOfChild();
				break;
			}
		}
		for (Order o:fsh.mOrders) {
			if(o.getTripID()==tID)	{
				numOfRemain+=o.getNumOfAdult()+o.getNumOfChild()+o.getNumOfInfant();
			}
		}
		numOfRemain=numOfRemain-numOfThis;
		System.out.println("check numOfRemain="+numOfRemain+"numOfThis="+numOfThis+"tID="+tID);
		fsh.deleteOrder(orderID,tID,numOfRemain);
        return true;
    }
	
	public Boolean updateTheTrip(Order oldOne, int changeNumOfAdult, int changeNumOfChild, int changeNumOfInfant) {
		int booked=0;
		int maxID=-1;
		for (Order o:fsh.mOrders){
			if (o.getTripID()==oldOne.getTripID())booked+=o.getNumOfAdult()+o.getNumOfInfant()+o.getNumOfChild();
			if (o.getOrderID()>maxID) maxID=o.getOrderID();
		}
		booked=booked-(oldOne.getNumOfChild()+oldOne.getNumOfInfant()+oldOne.getNumOfAdult());
		Order newOne=new Order(context,oldOne.getOrderID(),oldOne.getUserID(),oldOne.getTripID()
				,changeNumOfAdult,changeNumOfChild,changeNumOfInfant);
		dbo.selectData("select upperBound from trip where tripID = '" + oldOne.getTripID() + "'", 1);
		int upperBound=Integer.valueOf((dbo.getResultSet())[0]);
		if(upperBound>=(booked+changeNumOfAdult+changeNumOfChild+changeNumOfInfant)){
			deleteTheTrip(oldOne.getOrderID());
			fsh.newOrder(newOne,maxID,booked);
//			bookATrip(newOne);
			return true ;
		}else{
			operatinonState = "修改後的人數超過上限";
			return false;
		}
	}


	public String inquireTheTrip(int orderID) {
		Order order=new Order();
		for(Order o:fsh.mOrders){
			if (o.getOrderID()==orderID){
				order=o;
				break;
			}
		}
		Account acc=new Account();
		for (Account a:fsh.getUserIDs()){
			if ((a.getUserID()).contentEquals(order.getUserID())) acc=a;
		}
		System.out.println("Check usID "+acc.getUserName());

		String output= "";
		if(order.getTripID()>0){
			output="sucesss"+","+
					order.getOrderID()+","+
					order.getUserID()+","+
					order.getTripID()+","+
					order.getNumOfAdult()+","+
					order.getNumOfChild()+","+
					order.getNumOfInfant()+","+
					order.getTripInfo().get(0)+","+			//7 title
					order.getTripInfo().get(1)+","+			//8 price
					","+						//9 phone
					order.getTripInfo().get(2)+" ~ "+order.getTripInfo().get(3)+"," +		//10 date
					" 成人" + order.getNumOfAdult()+"位" + "  孩童" + order.getNumOfChild()+"位" + "  嬰兒" + order.getNumOfInfant()+"位"+","+//11 people
					Integer.valueOf(order.getTripInfo().get(1))*
							( order.getNumOfAdult() +  order.getNumOfChild() + order.getNumOfInfant());		//12 new price
		}
		return output;
	}

	public ArrayList<Order> inquireOrders(String userID) {
		ArrayList<Order> result=new ArrayList();
		for(Order o:fsh.mOrders){
			if ((o.getUserID()).equals(userID)){
				result.add(o);
			}
		}
		return result;
	}


}
