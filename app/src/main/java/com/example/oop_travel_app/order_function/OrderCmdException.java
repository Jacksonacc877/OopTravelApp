package com.example.oop_travel_app.order_function;

public class OrderCmdException extends Exception {
	// definition of UnknownCmdException constructor
	public OrderCmdException(String errMessage) {
		super(errMessage);		
	}
}
