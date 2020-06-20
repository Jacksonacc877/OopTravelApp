package com.example.oop_travel_app.database_function;


import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class DataList {
	Context context;
	DBOperation dbo;
	private String[] result;
	private int[] travelCode;
	int resultSize=0;


	public DataList(Context c){
		context=c;
		dbo=new DBOperation(context);
	}


	public int getResultSize() {
		return resultSize;
	}

	public String[] getResult() {
		return result;
	}

	public int[] getTravelCode() {
		int[] err={-1};
		if (travelCode.length!=0) return travelCode;
		else return err;
	}

	/**
	 * Search the input place from the column Country ,  travel_code_name ,
	 * @param place you want to go
	 */
	public void selectTravelCode(String place) {
		String sql = "SELECT  travelCode FROM travel_code \n"
				+"WHERE	travelCodeName LIKE '%"+place+"%' "
				+"or country LIKE '%"+place+"%';" ;
		dbo.selectData(sql,1);
		result=dbo.getResultSet();
		resultSize=result.length;
		travelCode =new int[resultSize];
		for (int i=0;i<resultSize;i++) {
			travelCode[i]=Integer.valueOf(result[i]);
		}
	}

	/**
	 * Search the input place to find all match trip title 
	 * @param place you want to go
	 * @return match trip title (without duplicated)
	 */
	public String[] searchDestination(String place) {
		selectTravelCode(place);
		for (int i=0;i<travelCode.length;i++) {
			String sql = "SELECT DISTINCT title FROM trip WHERE travelCode = "+travelCode[i]+";";
			dbo.selectData(sql,1);
		}
		result=dbo.getResultSet();
		resultSize=result.length;
		String[] titleData=new String[resultSize];
		int count=0;
		for (String title:result) {
			titleData[count]=title +" , "+ getPriceInterval(title) +" , "+ getDateInterval(title);
			count++;
		}
		return titleData;
	}

	/**
	 * Search the input place to find all match trip title
	 * @param place you want to go
	 * @param date is the earliest date you want the trip to start
	 * @return match trip title (without duplicated)
	 */
	public String[] searchDestination(String place,String date) {
		selectTravelCode(place);
		for (int i=0;i<travelCode.length;i++) {
			String sql = "SELECT DISTINCT title FROM trip WHERE travelCode = "+travelCode[i]+" \r\n"
					+ "and date(startDate) >= date('"+date+"');";
			dbo.selectData(sql,1);
		}
		result=dbo.getResultSet();
		resultSize=result.length;
		String[] titleData=new String[resultSize];
		int count=0;
		for (String title:result) {
			titleData[count]=title +" , "+ getPriceInterval(title) +" , "+ getDateInterval(title);
			count++;
		}
		return titleData;
	}

	/**
	 * List all the data with certain title
	 * @param title ,the trip which you want to know more
	 * @return match trip detail data
	 */
	public String[] listTitleData(String title) {
		String sql = "SELECT DISTINCT tripID,price,startDate,endDate FROM trip "
				+ "WHERE title Like '" + title + "';";
		dbo.selectData(sql, 4);
		result=dbo.getResultSet();
		resultSize=result.length;
		return result;
	}
	/**
	 * List all the data with certain title
	 * @param title ,the trip which you want to know more
	 * @return match trip detail data
	 */
	public String[] listTitleData(String title,Boolean orderByPrice) {
		String sql = "SELECT DISTINCT tripID,price,startDate,endDate FROM trip "
				+ "WHERE title Like '" + title + "'";
		if (orderByPrice) sql+="ORDER BY price ;";
		else sql+=";";
		dbo.selectData(sql, 4);
		result=dbo.getResultSet();
		resultSize=result.length;
		return result;
	}
	/**
	 * List all the data with certain title and certain start_date
	 * @param title ,the trip which you want to know more
	 * @param startDate ,the date you expect the trip start after
	 * @param endDate ,the date you expect the trip start after
	 * @param orderByPrice ,if it is true ,list the result order by price,
	 *                     or order by start date (default)
	 * @return match trip detail data
	 */
	public String[] listTitleData(String title,String startDate,String endDate,Boolean orderByPrice) {
		String sql = "SELECT DISTINCT tripID,price,startDate,endDate FROM trip "
				+ "WHERE title Like '" + title + "' "
				+ "and date(startDate) >= date('"+ startDate +"') "
				+ "and date(endDate) <= date('"+ endDate +"') ";
		if (orderByPrice) sql+=" ORDER BY price ;";
		else sql+=";";
		dbo.selectData(sql, 4);
		result=dbo.getResultSet();
		resultSize=result.length;
		return result;
	}


	/**
	 * List all the data with certain title and certain start_date
	 * @param title ,the trip which you want to know more
	 * @param startDate ,the date you expect the trip start after
	 * @param endDate ,the date you expect the trip start after
	 * @return match trip detail data
	 */
	public String[] listTitleData(String title,String startDate,String endDate) {
		String sql = "SELECT DISTINCT tripID,price,startDate,endDate FROM trip "
				+ "WHERE title Like '" + title + "' "
				+ "and date(startDate) >= date('"+ startDate + "') "
				+ "and date(endDate) <= date('"+ endDate + "');";
		dbo.selectData(sql, 4);
		result=dbo.getResultSet();
		resultSize=result.length;
		return result;
	}

	/**
	 * input trip id then return all information
	 * @param id of the trip want to search
	 * @return all data in String Array
	 */
	public String[] getTripData(int id){
		String sql = "SELECT tripID,title,price,startDate,endDate,lowerBound,upperBound,bookedTraveler FROM trip WHERE tripID = "+id+" ;";
		dbo.selectData(sql, 8);
		result=(dbo.getResultSet());
		for (String s:result){
			result=s.split(" , ");
		}
		String[] output=new String[7];
		for (int i =0;i<5;i++ ){
			output[i]=result[i];
		}
		if (Integer.valueOf(result[7])>=Integer.valueOf(result[5])) output[5]= "已可出團";
		else output[5]= "還差 "+ String.valueOf(Integer.valueOf(result[5])-Integer.valueOf(result[7])) +" 人可出團" ;
		if (Integer.valueOf(result[7])==Integer.valueOf(result[5])) output[6]= "已額滿";
		else output[6]="尚未額滿，還可以報 "+String.valueOf(Integer.valueOf(result[6])-Integer.valueOf(result[7]))+" 人";
		result=new String[7];
		result=output;
		resultSize=result.length;
		return result;
	}
	/**
	 * List all the regions
	 * @return  all the regions in String array
	 */
	public String[] listCountry() {
		ArrayList<String> place=new ArrayList<String>();
		String sql = "SELECT DISTINCT travelCodeName FROM travel_code;" ;
		dbo.selectData(sql, 1);
		String[] travelCodeName =dbo.getResultSet();
		for (String tcn:travelCodeName){
			String[] region =tcn.split("．");
			for (String r:region){
				place.add(r);
			}
		}
		sql = "SELECT DISTINCT country FROM travel_code;" ;
		dbo.selectData(sql, 1);
		String[] country=dbo.getResultSet();
		for (String c:country){
			String[] region =c.split("．");
			for (String r:region){
				if (!place.contains(r))place.add(r);
			}
		}
		result=place.toArray(new String[place.size()]);
		resultSize=result.length;
		return result;
	}



	private String getPriceInterval(String title){
		String sql = "SELECT price FROM trip "
				+ "WHERE title Like '" + title + "';";
		dbo.selectData(sql, 1);
		result=dbo.getResultSet();
		int count =0;
		int max=0,min=0;
		for (String s:result){
			if (count==0) max=min=Integer.valueOf(s);
			else{
				if (Integer.valueOf(s)>max) max=Integer.valueOf(s);
				if (Integer.valueOf(s)<min) min=Integer.valueOf(s);
			}
			count++;
		}
		String interval;
		if (min==max)interval=String.valueOf(min);
		else interval= min +"~"+ max;
		return interval;
	}

	private String getDateInterval(String title){
		String sql = "SELECT startDate,endDate FROM trip "
				+ "WHERE title Like '" + title + "';";
		dbo.selectData(sql, 2);
		result=dbo.getResultSet();
		int count =0;
		Date earliest=new Date();
		Date latest=new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try{
			for (String s:result){
				String[] date=s.split(",");
				if (count==0) {
					earliest=sdf.parse(date[0]);
					latest=earliest=sdf.parse(date[1]);
				}
				else{
					if (earliest.after(sdf.parse(date[0]))) earliest=sdf.parse(date[0]);
					if (latest.before(sdf.parse(date[1]))) latest=sdf.parse(date[1]);
				}
				count++;
			}
		}catch (Exception e){
			System.out.println("getDateInterval ERROR : "+e);
		}
		String interval;
		if (earliest.equals(latest)) interval=sdf.format(earliest);
		else interval= sdf.format(earliest) +"~"+ sdf.format(latest);
		return interval;
	}

}
//	/**
//	 * List all the Continent
//	 * @return  all the Continent
//	 */
//	public String[] listContinent() {
//		String sql = "SELECT DISTINCT Continent FROM travel_code;" ;
//		dbo.selectData(sql, 1);
//		resultSize=result.length;
//		result=dbo.getResultSet();
//		return result;
//	}
//
//
//	/**
//	 * List all the country in certain Continent
//	 * @param continent is the continent you want to know more
//	 * @return those country in certain continent
//	 */
//	public String[] listContinentCountry(String continent) {
//		String sql = "SELECT DISTINCT Country FROM travel_code "
//				+ "WHERE Continent LIKE '%"+continent+"%' ;";
//		dbo.selectData(sql, 1);
//		result=dbo.getResultSet();
//		resultSize=result.length;
//		return result;
//	}