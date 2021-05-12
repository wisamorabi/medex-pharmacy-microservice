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

import com.medex.model.Doctor;
import com.medex.services.DoctorService;


//Request resources which acts as a layer before our Doctor services
@Path("/")
public class DoctorResources {
	DoctorService doctorService = new DoctorService();

	public DoctorResources() {
	}


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Doctor> getDoctors() {
		return doctorService.getAllDoctors();
	}

	@GET
	@Path("{Doctorid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Doctor getDoctor(@PathParam("Doctorid") int id) {
		return doctorService.getDoctor(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Doctor addDoctor(Doctor aDoctor) {
		return doctorService.addDoctor(aDoctor);
	}

	@PUT
	@Path("{Doctorid}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Doctor updateDoctor(@PathParam("Doctorid") int id, Doctor Doctor) {
		Doctor.setId(id);
		return doctorService.updateDoctor(Doctor);
	}

	@DELETE
	@Path("{Doctorid}")
	public void removeDoctor(@PathParam("Doctorid") int id, Doctor Doctor) {
		doctorService.removeDoctor(id);
	}
}
