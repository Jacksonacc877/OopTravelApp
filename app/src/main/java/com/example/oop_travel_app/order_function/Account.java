package com.example.oop_travel_app.order_function;

import android.content.Context;

import com.example.oop_travel_app.database_function.DBOperation;

public class Account {
	Context context;
	private int orderID;
	private String userID;
	private String tripID;
	private int numOfAdult;
	private int numOfChild;
	private int numOfInfant;
	private String title;
	private String travelCode;
	private String productKey;
	private int price;
	private String startDate;
	private String endDate;
	private String lowerBound;
	private String upperBound;
	private int bookedTraveler;
	
	public void getAllMems(int orderID, String userID) throws OrderCmdException {
		
		this.userID = userID;
		
		DBOperation OP = new DBOperation (context);
		OP.selectData("select tripID, numOfAdult, numOfChild, numOfInfant from OrderData where orderID = " + String.valueOf(orderID) + " and userID = " + userID, 4);
		
		String[] selectedmems1 = OP.getResultSet();
		
		if (selectedmems1.length > 1) {
			System.out.println("太長了");
		}
		
		String returnStmt;
		
		if(selectedmems1.length == 0) {
			returnStmt = "orderID或userID錯誤";
			throw new OrderCmdException(returnStmt);
		}
		
		else {
			selectedmems1 = selectedmems1[0].split(",");
			
			String tripID = selectedmems1[0];
			int numOfAdult = Integer.valueOf(selectedmems1[1]);
			int numOfChild = Integer.valueOf(selectedmems1[2]);
			int numOfInfant = Integer.valueOf(selectedmems1[3]);
			
			this.tripID = tripID;
			this.numOfAdult = numOfAdult;
			this.numOfChild = numOfChild;
			this.numOfInfant = numOfInfant;
			
			
			
			OP.selectData("select title, travelCode, productKey, price, startDate, endDate, lowerBound, upperBound, bookedTraveler from trip where tripID = " + tripID, 9);
			
			String[] selectedmems2 = OP.getResultSet();
			
			if (selectedmems2.length > 1) {
				System.out.println("太長了");
			}
			
			selectedmems2 = selectedmems2[0].split(",");
			
			String title = selectedmems2[0];
			String travelCode = selectedmems2[1];
			String productKey = selectedmems2[2];
			int price = Integer.valueOf(selectedmems2[3]);
			String startDate = selectedmems2[4];
			String endDate = selectedmems2[5];
			String lowerBound = selectedmems2[6];
			String upperBound = selectedmems2[7];
			int bookedTraveler = Integer.valueOf(selectedmems2[8]);
			
			this.title = title;
			this.travelCode = travelCode;
			this.productKey = productKey;
			this.price = price;
			this.startDate = startDate;
			this.endDate = endDate;
			this.lowerBound = lowerBound;
			this.upperBound = upperBound;
			this.bookedTraveler = bookedTraveler;		
		}
		
	}
	
	public String getTripID(int orderID, String userID) {
		
		try {
			getAllMems(orderID, userID);
		} 
		catch (OrderCmdException e) {
			this.tripID = e.getMessage();
		}
		return this.tripID;
		
	}
	
	public int getNumOfAdult(int orderID, String userID) {
		
		String errMsg = null;
		
		try {
			getAllMems(orderID, userID);
		} 
		catch (OrderCmdException e) {
			errMsg = e.getMessage();
		}
		
		if(errMsg == "orderID或userID錯誤") {
			return 000000;
		}
		
		else {
			return this.numOfAdult;
		}
		
	}
	
	public int getNumOfChild(int orderID, String userID) {
		
		String errMsg = null;
		
		try {
			getAllMems(orderID, userID);
		} 
		catch (OrderCmdException e) {
			errMsg = e.getMessage();
		}
		
		if(errMsg == "orderID或userID錯誤") {
			return 000000;
		}
		
		else {
			return this.numOfChild;
		}
		
	}
	
	public int getNumOfInfant(int orderID, String userID) {
		
		String errMsg = null;
		
		try {
			getAllMems(orderID, userID);
		} 
		catch (OrderCmdException e) {
			errMsg = e.getMessage();
		}
		
		if(errMsg == "orderID或userID錯誤") {
			return 000000;
		}
		
		else {
			return this.numOfInfant;
		}
		
	}

	public String getTitle(int orderID, String userID) {
		
		try {
			getAllMems(orderID, userID);
		} 
		catch (OrderCmdException e) {
			this.title = e.getMessage();
		}
		return this.title;
		
	}
	
	public String getTravelCode(int orderID, String userID) {
		
		try {
			getAllMems(orderID, userID);
		} 
		catch (OrderCmdException e) {
			this.travelCode = e.getMessage();
		}
		return this.travelCode;
		
	}
	
	public String getProductKey(int orderID, String userID) {
		
		try {
			getAllMems(orderID, userID);
		} 
		catch (OrderCmdException e) {
			this.productKey = e.getMessage();
		}
		return this.productKey;
		
	}
	
	public int getPrice(int orderID, String userID) {
		
		String errMsg = null;
		
		try {
			getAllMems(orderID, userID);
		} 
		catch (OrderCmdException e) {
			errMsg = e.getMessage();
		}
		
		if(errMsg == "orderID或userID錯誤") {
			return 000000;
		}
		
		else {
			return this.price;
		}
		
	}
	
	public String getStartDate(int orderID, String userID) {
		
		try {
			getAllMems(orderID, userID);
		} 
		catch (OrderCmdException e) {
			this.startDate = e.getMessage();
		}
		return this.startDate;
		
	}
	
	public String getEndDate(int orderID, String userID) {
		
		try {
			getAllMems(orderID, userID);
		} 
		catch (OrderCmdException e) {
			this.endDate = e.getMessage();
		}
		return this.endDate;
		
	}
	
	public String getLowerBound(int orderID, String userID) {
		
		try {
			getAllMems(orderID, userID);
		} 
		catch (OrderCmdException e) {
			this.lowerBound = e.getMessage();
		}
		return this.lowerBound;
		
	}
	
	public String getUpperBound(int orderID, String userID) {
		
		try {
			getAllMems(orderID, userID);
		} 
		catch (OrderCmdException e) {
			this.upperBound = e.getMessage();
		}
		return this.upperBound;
		
	}
	
	public int getBookedTraveler(int orderID, String userID) {
		
		String errMsg = null;
		
		try {
			getAllMems(orderID, userID);
		} 
		catch (OrderCmdException e) {
			errMsg = e.getMessage();
		}
		
		if(errMsg == "orderID或userID錯誤") {
			return 404;
		}
		
		else {
			return this.bookedTraveler;
		}
		
	}


}
