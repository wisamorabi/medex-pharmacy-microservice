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

import com.medex.communicationmodules.PharmacyInfo;
import com.medex.communicationmodules.Status;
import com.medex.model.Pharmacy;
import com.medex.services.PharmacyService;


//Request resources which acts as a layer before our Pharmacy services
@Path("/pharmacies")
public class PharmacyResources {
	PharmacyService pharmacyService = new PharmacyService();

	public PharmacyResources() {
	}


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<PharmacyInfo> getPharmacies() {
		return pharmacyService.getAllPharmacies();
	}

	@GET
	@Path("{Pharmacyid}")
	@Produces(MediaType.APPLICATION_JSON)
	public PharmacyInfo getPharmacy(@PathParam("Pharmacyid") int id) {
		return pharmacyService.getPharmacy(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public PharmacyInfo addPharmacy(Pharmacy aPharmacy) {
		return pharmacyService.addPharmacy(aPharmacy);
	}

	@PUT
	@Path("{Pharmacyid}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public PharmacyInfo updatePharmacy(@PathParam("Pharmacyid") int id, Pharmacy Pharmacy) {
		Pharmacy.setId(id);
		return pharmacyService.updatePharmacy(Pharmacy);
	}

	@DELETE
	@Path("{Pharmacyid}")
	public Status removePharmacy(@PathParam("Pharmacyid") int id) {
		return pharmacyService.removePharmacy(id);
	}
	
	@Path("{Pharmacyid}/pharmaceuticalsStock")
	public PharmaceuticalsStockResources getPharmaceuticalsStock()
	{
		return new PharmaceuticalsStockResources();
	}
	
	@Path("{Pharmacyid}/orders")
	public OrderResources getOrders()
	{
		return new OrderResources();
	}
	
	@GET
	@Path("/login/{username}/{password}")
	public Pharmacy getPatientLogin(@PathParam("username") String username, @PathParam("password") String password)
	{
		return pharmacyService.getPharmacyLogin(username, password);
	}
	
}
