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
		fsh.userInit();
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
		if(upperBound>(booked+numOfAdult+numOfChild+numOfInfant)){
			fsh.newOrder(order,maxID,booked);
			System.out.println("order successful");
			return true ;
		}else{
			System.out.println("Invalid order ");
			return false;
		}
	}

    public boolean deleteTheTrip(int orderID) {
        int numOfRemain=0;
        int tID=0;
		for (Order o:fsh.mOrders) {
			if(o.getOrderID()==orderID){
				tID=o.getTripID();
			}
		}
		fsh.deleteOrder(orderID,tID,numOfRemain);
        return true;
    }
	
	public Boolean updateTheTrip(Order oldOne, int changeNumOfAdult, int changeNumOfChild, int changeNumOfInfant) {
		Order newOne=new Order(context,oldOne.getOrderID(),oldOne.getUserID(),oldOne.getTripID()
				,changeNumOfAdult,changeNumOfChild,changeNumOfInfant);

		return deleteTheTrip(oldOne.getOrderID())&&bookATrip(newOne);
	}


	public String inquireTheTrip(int orderID) {
		Order order=new Order();
		for(Order o:fsh.mOrders){
			if (o.getOrderID()==orderID){
				order=o;
				break;
			}
		}
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
					"0987654321"+","+						//9 phone
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
			if (o.getUserID().contentEquals(userID)){
				result.add(o);
			}
		}
		return result;
	}


//	public String[] inquireOrders(String userID, String userPhone) {
//		Order order1 = new Order(context,0);
//		String[] returnStmt = order1.outputInquireImformations(userID, userPhone);
//		return returnStmt;
//		//[0]為成功與否，[1]為訂單筆數或錯訊息第一行(若有誤則至此為止)，[2][3][4][5][6][7]以後為每筆訂單的orderID、訊息第一行至第五行，依此循環
//	}


//	public String inquireTheTrip(int userphone, String userID) throws OrderCmdException {
//		Order order1 = new Order(context);
//		String returnStmt = order1.outputInquireImformation(userphone, userID);
//		return returnStmt;
//	}

//	public String searchImf(int orderID) {
//		DBOperation OP = new DBOperation (context);
//
//		OP.selectData("select orderDate, userID, userPhone, tripID, numOfAdult, numOfChild, numOfInfant from orderData where orderID = " + orderID, 7);
//
//		String[] selectedmems = OP.getResultSet();
//
//		if (selectedmems.length > 1) {
//			System.out.println("太長了");
//			return "太多東西了";
//		}
//
//		else {
//			return selectedmems[0];
//		}
//	}
//	public int getOrderlstID(){
//		return this.orderlstID;
//	}


//	public String[] inquireTheTrip(int orderID) {
//		Order order1 = new Order(context,0);
//		String[] returnStmt = order1.outputInquireImformation(orderID);
//		return returnStmt;
//		//[0]為成功與否，[1]為訂單編號或錯誤訊息第一行(若有誤則至此為止)，[2]為訊息第一行，[3]為訊息第二行，[4]為訊息第三行，[5]為訊息第四行，[6]為訊息第四行
//	}
//
//	public String[] searchImf(int orderID) {
//		DBOperation  OP = new DBOperation (context);
//
//		OP.selectData("select orderDate, userID, userPhone, tripID, numOfAdult, numOfChild, numOfInfant from orderData where orderID = " + orderID, 7);
//
//		String[] selectedmems = OP.getResultSet();
//
//		String[] returnStmt = new String[2];
//
//		if (selectedmems.length > 1) {
//			//System.out.println("太長了");
//			returnStmt[0] = "fail";
//			returnStmt[1] = "太長了";
//			return returnStmt;
//		}
//
//		else {
//			returnStmt[0] = "fail";
//			returnStmt[1] = selectedmems[0];
//			return returnStmt;
//		}
//
//		selectedmems = selectedmems[0].split(",");
//
//		String orderDate = selectedmems[0];
//		String userID = selectedmems[1];
//		String userPhone = selectedmems[2];
//		String tripID = selectedmems[3];
//		int numOfAdult = Integer.valueOf(selectedmems[4]);
//		int numOfChild = Integer.valueOf(selectedmems[5]);
//		int numOfInfant = Integer.valueOf(selectedmems[6]);

//	}
}
