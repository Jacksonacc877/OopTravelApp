package com.example.oop_travel_app.order_function;



import android.content.Context;

import com.example.oop_travel_app.database_function.DBOperation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order {
	Context context;
	private int orderID;
	private String userID;
	private int tripID;
	private List<String> tripInfo;
	private	int numOfChild;
	private	int numOfInfant;
	private	int numOfAdult;

	public List<String> getTripInfo() {
		return tripInfo;
	}

	public int getOrderID() {
		return orderID;
	}

	public String getUserID() {
		return userID;
	}

	public int getTripID() {
		return tripID;
	}

	public int getNumOfChild() {
		return numOfChild;
	}

	public int getNumOfInfant() {
		return numOfInfant;
	}

	public int getNumOfAdult() {
		return numOfAdult;
	}

	public Order(){	}

	public Order(Context c,int oID,String uID,int tID, int adult,int child,int infant){
		this.context=c;
		orderID=oID;
		userID=uID;
		tripID=tID;
		numOfAdult=adult;
		numOfChild=child;
		numOfInfant=infant;
		DBOperation dbo=new DBOperation(context);
		String sql = "SELECT title,price,startDate,endDate FROM trip WHERE tripID = "+tID+" ;";
		dbo.selectData(sql, 4);
		tripInfo=(Arrays.asList((dbo.getResultSet()[0]).split(" , ")));
	}

//	public String[] outputBookImformation(int orderID, String userID, String userPhone, String tripID, int numOfAdult, int numOfChild, int numOfInfant) {
//		System.out.println("test message 00a");
//		String[] returnStmt = new String[5];
//
//		//***由 tripID 從舊的 db 中 select 出 upperBound、bookedTraveler
//		DBOperation  OP = new DBOperation (context);
//
//		OP.selectData("select upperBound, bookedTraveler from trip where tripID = '" + tripID + "'", 2);
//		System.out.println("test message 00b");
//		String[] selectedmems1 = OP.getResultSet();
//
//		if (selectedmems1.length > 1) {
//			//System.out.println("太長了");
//			returnStmt[0] = "fail";
//			returnStmt[1] = "太長了";
//			return returnStmt;
//		}
//
//		String[] selectedmems11 = selectedmems1[0].split(" , ");
//
//		String upperBound = selectedmems11[0];
//
//		int bookedTraveler = Integer.valueOf(selectedmems11[1]);
//
//		System.out.println("test message 00c");
//		//然後判斷若 numOfAdult + numOfChild + numOfInfant > upperBound - bookedTraveler;
//		//就 return new OrderCmdException("失敗 " + startDate + " " + title + "剩餘數量不足/機位已售罄");
//		if(numOfAdult + numOfChild + numOfInfant > Integer.valueOf(upperBound) - bookedTraveler) {
//			OP.selectData("select startDate, title from trip where tripID = '" + tripID + "'", 2);
//
//			String[] selectedmems2 = OP.getResultSet();
//
//			if (selectedmems2.length > 1) {
//				//System.out.println("太長了");
//				returnStmt[0] = "fail";
//				returnStmt[1] = "太長了";
//				return returnStmt;
//			}
//
//			String[] selectedmems12 = selectedmems2[0].split(" , ");
//
//			String startDate = selectedmems12[0];
//			String title = selectedmems12[1];
//
//			returnStmt[0] = "fail";
//			returnStmt[1] = "失敗  " + startDate + " " + title + "  剩餘數量不足/機位已售罄";
//			return returnStmt;
//		}
//
//		else {
//			System.out.println("test message 00d");
//			//***由 tripID 從舊的 db 中 select 出 startDate, endDate, price
//			OP.selectData("select startDate, endDate, price from trip where tripID = '" + tripID + "'", 3);
//
//			String[] selectedmems2 = OP.getResultSet();
//
//			if (selectedmems2.length > 1) {
//				//System.out.println("太長了");
//				returnStmt[0] = "fail";
//				returnStmt[1] = "太長了";
//				return returnStmt;
//			}
//
//			String[] selectedmems12 = selectedmems2[0].split(" , ");
//
//			String startDate = selectedmems12[0];
//			String endDate = selectedmems12[1];
//			int price = Integer.valueOf(selectedmems12[2]);
//
//			System.out.println("test message 00e");
//
//			bookedTraveler += (numOfAdult + numOfChild + numOfInfant);
//			//upperBound 和 lowerBound 會受剩餘人數影響？？
//
//			//***由 tripID 將新的 bookedTraveler update 進舊的 db
//			OP.changeDatabase("update trip set bookedTraveler = " + String.valueOf(bookedTraveler) + " where tripID = '" + tripID + "'");
//
//			//***將有orderID, userID, userPhone, tripID, numOfAdult, numOfChild, numOfInfant 7個成員的這筆資料insert進新的db
//			OP.changeDatabase("insert into OrderData values (" + String.valueOf(orderID) + ", '" + userID + "', '" + userPhone + "', '" + tripID + "', " + String.valueOf(numOfAdult) + ", " + String.valueOf(numOfChild) + ", " + String.valueOf(numOfInfant) + ")");
//
//			returnStmt[0] = "success";
//			returnStmt[1] = "訂單編號：" + orderID + "  使用者ID：" + userID;
//			returnStmt[2] = startDate + " - " + endDate;
//			returnStmt[3] = "總價：" + price*(numOfAdult + numOfChild + numOfInfant);
//			returnStmt[4] = "入住人數：" + "成人" + numOfAdult + "  孩童" + numOfChild + "  嬰兒" + numOfInfant;
//			System.out.println("test message 00f");
//			return returnStmt;
//		}
//
//	}
//
//public String[] outputDeleteImformation(int orderID) {
//
//	String[] returnStmt = new String[2];
//
//	//***由 orderID從新的 db 中 select 出該筆訂單的 tripID
//	DBOperation  OP = new DBOperation (context);
//	OP.selectData("select tripID from OrderData where orderID = " + String.valueOf(orderID), 1);
//
//	String[] selectedmems1 = OP.getResultSet();
//
//	if (selectedmems1.length > 1) {
//		//System.out.println("太長了");
//		returnStmt[0] = "fail";
//		returnStmt[1] = "太長了";
//		return returnStmt;
//	}
//
//	//若不存在該筆訂單(或者不存在該使用者ID)，也就是取不出tripID，則 return new OrderCmdException("退訂失敗，此訂單編號不存在");;
//	if(selectedmems1.length == 0) {
//		//throw new OrderCmdException(returnStmt);
//		returnStmt[0] = "fail";
//		returnStmt[1] = "退訂失敗，此訂單編號不存在";
//		return returnStmt;
//	}
//
//	else {
//		String tripID = selectedmems1[0];
//
//		//***由 orderID 從新的db中select 出 numOfAdult、numOfChild、numOfInfant
//		OP.selectData("select numOfAdult, numOfChild, numOfInfant from OrderData where orderID = " + String.valueOf(orderID), 3);
//
//		String[] selectedmems2 = OP.getResultSet();
//
//		if (selectedmems2.length > 1) {
//			//System.out.println("太長了");
//			returnStmt[0] = "fail";
//			returnStmt[1] = "太長了";
//			return returnStmt;
//		}
//
//		String[] selectedmems12 = selectedmems2[0].split(" , ");
//
//		int numOfAdult = Integer.valueOf(selectedmems12[0]);
//		int numOfChild = Integer.valueOf(selectedmems12[1]);
//		int numOfInfant = Integer.valueOf(selectedmems12[2]);
//
//		//***再由 tripID 從舊的 db 中 select 出 bookedTraveler
//		OP.selectData("select bookedTraveler from trip where tripID = '" + tripID + "'", 1);
//
//		String[] selectedmems3 = OP.getResultSet();
//
//		if (selectedmems3.length > 1) {
//			//System.out.println("太長了");
//			returnStmt[0] = "fail";
//			returnStmt[1] = "太長了";
//			return returnStmt;
//		}
//
//		int bookedTraveler = Integer.valueOf(selectedmems3[0]);
//
//		bookedTraveler -= (numOfAdult + numOfChild + numOfInfant);
//		//upperBound 和 lowerBound 會受剩餘人數影響？？
//		//System.out.println("退訂成功,已取消您的預訂紀錄");
//
//
//		//***再由 tripID 將新的 bookedTraveler update進舊的 db
//		OP.changeDatabase("update trip set bookedTraveler = " + String.valueOf(bookedTraveler) + " where tripID = '" + tripID + "'");
//
//		//***透過 orderID 將有orderID, userID, tripID, numOfAdult, numOfChild, numOfInfant 6個成員的這筆資料從新的 db 中 delete
//		OP.changeDatabase("delete from OrderData where orderID = " + String.valueOf(orderID));
//
//		returnStmt[0] = "success";
//		returnStmt[1] = "退訂成功,已取消您的預訂紀錄";
//		return returnStmt;
//	}
//}

//
//public String[] outputUpdateImformation(int orderID, int changeNumOfAdult, int changeNumOfChild, int changeNumOfInfant) {
//
//	Order order=new Order(context,0);
//	String[] oldinfo=order.outputInquireImformation(orderID);
//	String userid=oldinfo[2];
//	String userphone=oldinfo[3];
//	String tripid=oldinfo[11];
//	order.outputDeleteImformation(orderID);
//	return order.outputBookImformation(orderID,userid,userphone,tripid,changeNumOfAdult,changeNumOfChild,changeNumOfInfant);
//}
//
//	public String[] outputInquireImformation(int orderID) {
//
//		String[] returnStmt = new String[12];
//
//		//***由 orderID、userID 從新的 db 中 select 出該筆訂單的 tripID
//		DBOperation OP = new DBOperation(context);
//		OP.selectData("select tripID from OrderData where orderID = " + String.valueOf(orderID), 1);
//
//		String[] selectedmems1 = OP.getResultSet();
//
//		if (selectedmems1.length > 1) {
//			//System.out.println("太長了");
//			returnStmt[0] = "fail";
//			returnStmt[1] = "太長了";
//			return returnStmt;
//		}
//		//若orderID或userID有誤，或者說不存在該筆訂單(或者不存在該使用者ID)，也就是取不出tripID，則 return new OrderCmdException("您輸入的身分識別碼/訂單編號有誤,請重新輸入");
//		if(selectedmems1.length == 0) {
//			//throw new OrderCmdException(returnStmt);
//			returnStmt[0] = "fail";
//			returnStmt[1] = "您輸入的身分識別碼/訂單編號有誤,請重新輸入";
//			return returnStmt;
//		}
//
//		else{
//			String tripID = selectedmems1[0];
//			System.out.println("op 1");
//			//***由 orderID 從新的 db 中select 出  userPhone, numOfAdult, numOfChild, numOfInfant
//			OP.selectData("select userPhone, numOfAdult, numOfChild, numOfInfant, userID from OrderData where orderID = " + String.valueOf(orderID), 5);
//			System.out.println("op 2");
//			String[] selectedmems2 = OP.getResultSet();
//			System.out.println("op q");
//			if (selectedmems2.length > 1) {
//				//System.out.println("太長了");
//				returnStmt[0] = "fail";
//				returnStmt[1] = "太長了";
//				return returnStmt;
//			}
//			System.out.println("op df");
//			String[] selectedmems12 = selectedmems2[0].split(" , ");
//
//			String userPhone = selectedmems12[0];
//			int numOfAdult = Integer.valueOf(selectedmems12[1]);
//			int numOfChild = Integer.valueOf(selectedmems12[2]);
//			int numOfInfant = Integer.valueOf(selectedmems12[3]);
//
//			String userid=selectedmems12[4];
//
//
//			System.out.println("op 3");
//			//***再由 tripID 從舊的db中select 出 productKey, startDate, endDate, price
//			OP.selectData("select  tripID,startDate, endDate, price, title from trip where tripID = '" + tripID + "'", 5);
//			System.out.println("op 4");
//			String[] selectedmems3 = OP.getResultSet();
//
//			if (selectedmems3.length > 1) {
//				//System.out.println("太長了");
//				returnStmt[0] = "fail";
//				returnStmt[1] = "太長了";
//				return returnStmt;
//			}
//
//			String[] selectedmems13 = selectedmems3[0].split(" , ");
//
//			String productKey = selectedmems13[0];
//			String startDate = selectedmems13[1];
//			String endDate = selectedmems13[2];
//			int price = Integer.valueOf(selectedmems13[3]);
//			String triptitle=selectedmems13[4];
//			String testing=String.valueOf(price*(numOfAdult + numOfChild + numOfInfant));
//
//			System.out.println("op 5");
//
//			returnStmt[0] = "sucesss";
//			returnStmt[1] = triptitle;
//			returnStmt[2] = userid;
//			returnStmt[3] = userPhone;
//			returnStmt[4] = startDate + " ~ " + endDate;
//			returnStmt[5] = " 成人" + numOfAdult+"位" + "  孩童" + numOfChild+"位" + "  嬰兒" + numOfInfant+"位";
//			returnStmt[6] = testing ;
//			returnStmt[7]=String.valueOf(numOfAdult);
//			returnStmt[8]=String.valueOf(numOfChild);
//			returnStmt[9]=String.valueOf(numOfInfant);
//			returnStmt[10]=String.valueOf(price);
//			returnStmt[11]=tripID;
//
//			System.out.println("op 6");
//			return returnStmt;
//		}
//	}
//
//	public String[] outputInquireImformations(String userID, String userPhone) {
//
//		ArrayList<String> returnStmt = new ArrayList<String>();
//
//		DBOperation  OP = new DBOperation (context);
//		OP.selectData("select orderID from OrderData where userID = '" + userID + "' and userPhone = '" + userPhone + "'", 1);
//
//		String[] selectedmems1 = OP.getResultSet();
//
//		//若userID或userPhone有誤，或者說不存在該筆訂單(或者不存在該使用者ID)，也就是取不出tripID，則 return new OrderCmdException("您輸入的身分識別碼/手機號碼有誤,請重新輸入");
//		if(selectedmems1.length == 0) {
//			//throw new OrderCmdException(returnStmt);
//			returnStmt.add("fail");
//			returnStmt.add(1, "您輸入的身分識別碼/手機號碼有誤,請重新輸入");
//			String returnStmt1[] = (String[]) returnStmt.toArray(new String[0]);
//			return returnStmt1;
//		}
//
//		else{
//			String orderid=selectedmems1[0];
//			returnStmt.add("success");
//			returnStmt.add(String.valueOf(selectedmems1.length));
//
//			for(int a = 0; a < selectedmems1.length; a++) {
//				//String tripID = selectedmems1[0];
//
//				//***由 orderID 從新的 db 中select 出  userPhone, numOfAdult, numOfChild, numOfInfant
//				OP.selectData("select tripID, numOfAdult, numOfChild, numOfInfant from OrderData where orderID = " + selectedmems1[a], 4);
//
//				String[] selectedmems2 = OP.getResultSet();
//
//				if (selectedmems2.length > 1) {
//					//System.out.println("太長了");
//					returnStmt.set(0, "fail");
//					returnStmt.set(1, "太長了");
//					String returnStmt1[] = (String[]) returnStmt.toArray(new String[0]);
//					return returnStmt1;
//				}
//
//				String[] selectedmems12 = selectedmems2[0].split(" , ");
//
//				String tripID = selectedmems12[0];
//				int numOfAdult = Integer.valueOf(selectedmems12[1]);
//				int numOfChild = Integer.valueOf(selectedmems12[2]);
//				int numOfInfant = Integer.valueOf(selectedmems12[3]);
//
//				//***再由 tripID 從舊的db中select 出 productKey, startDate, endDate, price
//				OP.selectData("select tripID, startDate, endDate, price, title from trip where tripID = " + tripID, 4);
//
//				String[] selectedmems3 = OP.getResultSet();
//
//				if (selectedmems3.length > 1) {
//					//System.out.println("太長了");
//					returnStmt.set(0, "fail");
//					returnStmt.set(1, "太長了");
//					String returnStmt1[] = (String[]) returnStmt.toArray(new String[0]);
//					return returnStmt1;
//				}
//
//				String[] selectedmems13 = selectedmems3[0].split(" , ");
//
//				String productKey = selectedmems13[0];
//				String startDate = selectedmems13[1];
//				String endDate = selectedmems13[2];
//				String title=selectedmems13[3];
//				int price = Integer.valueOf(selectedmems13[3]);
//				String priceinterval= String.valueOf(price*(numOfAdult + numOfChild + numOfInfant));
//				String s=userID+","+userPhone+","+tripID+","+numOfAdult+","+numOfChild+","+numOfInfant+","+priceinterval+","+startDate+","+endDate+","+title+","+orderid;
//				returnStmt.add(s);
//			}
//
//			String returnStmt1[] = (String[]) returnStmt.toArray(new String[0]);
//			return returnStmt1;
//		}
//	}


}
