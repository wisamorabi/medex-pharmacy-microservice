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
import com.medex.model.Pharmaceutical;
import com.medex.services.PharmaceuticalService;


//Request resources which acts as a layer before our Pharmaceutical services
@Path("/pharmaceuticals")
public class PharmaceuticalResources {
	PharmaceuticalService pharmaceuticalService = new PharmaceuticalService();

	public PharmaceuticalResources() {
	}


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Pharmaceutical> getPharmaceuticals() {
		return pharmaceuticalService.getAllPharmaceuticals();
	}

	@GET
	@Path("{Pharmaceuticalid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Pharmaceutical getPharmaceutical(@PathParam("Pharmaceuticalid") int id) {
		return pharmaceuticalService.getPharmaceutical(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Pharmaceutical addPharmaceutical(Pharmaceutical aPharmaceutical) {
		return pharmaceuticalService.addPharmaceutical(aPharmaceutical);
	}

	@PUT
	@Path("{Pharmaceuticalid}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Pharmaceutical updatePharmaceutical(@PathParam("Pharmaceuticalid") int id, Pharmaceutical Pharmaceutical) {
		Pharmaceutical.setId(id);
		return pharmaceuticalService.updatePharmaceutical(Pharmaceutical);
	}

	@DELETE
	@Path("{Pharmaceuticalid}")
	public Status removePharmaceutical(@PathParam("Pharmaceuticalid") int id) {
		return pharmaceuticalService.removePharmaceutical(id);
	}
	

}
