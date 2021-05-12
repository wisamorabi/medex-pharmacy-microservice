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

import com.medex.model.Personnel;
import com.medex.services.PersonnelService;


//Request resources which acts as a layer before our Personnel services
@Path("/")
public class PersonnelResources {
	PersonnelService personnelService = new PersonnelService();

	public PersonnelResources() {
	}


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Personnel> getPersonnels() {
		return personnelService.getAllPersonnels();
	}

	@GET
	@Path("{Personnelid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Personnel getPersonnel(@PathParam("Personnelid") int id) {
		return personnelService.getPersonnel(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Personnel addPersonnel(Personnel aPersonnel) {
		return personnelService.addPersonnel(aPersonnel);
	}

	@PUT
	@Path("{Personnelid}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Personnel updatePersonnel(@PathParam("Personnelid") int id, Personnel Personnel) {
		Personnel.setId(id);
		return personnelService.updatePersonnel(Personnel);
	}

	@DELETE
	@Path("{Personnelid}")
	public void removePersonnel(@PathParam("Personnelid") int id, Personnel Personnel) {
		personnelService.removePersonnel(id);
	}
}
