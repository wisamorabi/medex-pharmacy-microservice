package com.medex.services;

import java.util.ArrayList;//
import java.util.List;


import com.medex.communicationmodules.Status;
import com.medex.database.OrderDB;
import com.medex.dependentresources.Ordr;


//This is the "backend" of our resources. This is where the logic is executed; the logic is basic since the database handles itself very well.
public class OrderService {
	OrderDB orderdb = new OrderDB(); //(Instead of the pseudo-database)

	public OrderService() {} 
	
	//All what is below is just calling the functions belonging to the pharmacies' database/table.
	
	public List<Ordr>getAllOrders(int pharmacyid)
	{
		return orderdb.getOrders(pharmacyid); //Get all hosts.

	}
	
	public Ordr getOrder(int pharmacyid)
	{
		return orderdb.getOrder(pharmacyid);
	}

}


