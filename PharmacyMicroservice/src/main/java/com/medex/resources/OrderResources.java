package com.medex.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.medex.communicationmodules.Status;
import com.medex.dependentresources.Ordr;
import com.medex.services.OrderService;



//Request resources which acts as a layer before our Order services

public class OrderResources {
	OrderService orderService = new OrderService();

	public OrderResources() {
	}


	
	
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Ordr> getOrders(@PathParam("Pharmacyid")int pharmacyid) {
		return orderService.getAllOrders(pharmacyid);
	}



	
	
	
}
