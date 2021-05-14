package com.medex.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.medex.communicationmodules.Status;
import com.medex.database.PharmaceuticalDB;
import com.medex.model.PharmaceuticalStock;
import com.medex.services.PharmaceuticalStockService;



//Request resources which acts as a layer before our PharmaceuticalStock services
@Path("/")
public class PharmaceuticalsStockResources
{
	PharmaceuticalStockService pharmaceuticalsStockService = new PharmaceuticalStockService();
	
	public void pharmaceuticalsStockresources() {}


	// hosts/{id}/pharmaceuticalStocks web service Requirement #1: Retrive the PharmaceuticalStocks allocated in a host.
	//Returns a JSON object with all PharmaceuticalStocks allocated in host {id}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<PharmaceuticalStock> getPharmaceuticalStocks(@PathParam("Pharmacyid")int pharmacyid)
	{
		return pharmaceuticalsStockService.getPharmaceuticalStocks(pharmacyid);
	}


	//hosts/{id}/pharmaceuticalStocks web service requirement #2: Retrieve a single PharmaceuticalStock details that is allocated to a host.
	//Returns a JSON object with the PharmaceuticalStock details or null if the PharmaceuticalStock doesn’t exist.
	@GET
	@Path("{PharmaceuticalStockid}")
	@Produces(MediaType.APPLICATION_JSON)
	public PharmaceuticalStock getSinglePharmaceuticalStock(@PathParam("Pharmacyid") int pharmacyid, @PathParam("PharmaceuticalStockid") int pharmaceuticalStockid)
	{
		return pharmaceuticalsStockService.getPharmaceuticalStock(pharmacyid, pharmaceuticalStockid);
	}
	
	
	/*
	Requirement 10:
	Create the REST API that provides the ability to allocate a PharmaceuticalStock to a host.
	The PharmaceuticalStock will allocate a created PharmaceuticalStock request that is not allocated to a host. If
	the host does not contain enough resources, the service will not do the
	allocation and returns status false. If the allocation is successful, it shall
	return status true. Make sure to allocate the resources from the host
	capability to the PharmaceuticalStocks allocated to the host. 
	 */
	//hosts/{id}/pharmaceuticalStocks web service requirement #3: Allocate a create PharmaceuticalStock request to a host if the capabilities required by the PharmaceuticalStock is available at the host. Once it is allocated, remove the PharmaceuticalStock from the request list by calling the created REST API.
	//Returns a JSON object with status true if allocation is successful or false if the allocation is not successful. A host must contain adequate capabilities for the PharmaceuticalStock requirements.
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public PharmaceuticalStock allocateHostPharmaceuticalStock(@PathParam("Pharmacyid")int pharmacyid, PharmaceuticalStock pharmaceuticalstock)
	{
		return pharmaceuticalsStockService.addPharmaceuticalStock(pharmacyid, pharmaceuticalstock);
	}
	
	//No point.
	//Requirement 9: Create a REST API the provides the ability to delete a PharmaceuticalStock that is allocated to a host (Note: This place it back into the pool!)
	//hosts/{id}/pharmaceuticalStocks web service Requirement #4: Delete a PharmaceuticalStock from the host.
	//Return a JSON object with status true if delete is successful or false if the delete is not successful.
//	@DELETE
//	@Path("{pharmaceuticalStockid}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Status removeHostPharmaceuticalStock(@PathParam("pharmacyid")int pharmacyid, @PathParam("pharmaceuticalstockid") int pharmaceuticalStockid)
//	{
//		return pharmaceuticalsStockService.(pharmacyid, pharmaceuticalStockid);
//	}
	
	
	//hosts/{id}/pharmaceuticalStocks web service requirement #5: Update a PharmaceuticalStock allocated in the host
	//return a JSON object with status true if update is successful or false if the update is not successful. The host must contain adequate resources.
	@PUT
	@Path("{PharmaceuticalStockid}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public PharmaceuticalStock updateHostPharmaceuticalStock(@PathParam("Pharmacyid")int pharmacyid, @PathParam("PharmaceuticalStockid") int pharmaceuticalStockid, PharmaceuticalStock pharmaceuticalStock)
	{
		return pharmaceuticalsStockService.updatePharmaceuticalStock(pharmacyid, pharmaceuticalStockid, pharmaceuticalStock);
	}
}
