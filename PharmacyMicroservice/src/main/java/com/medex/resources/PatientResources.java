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

import com.medex.model.Patient;
import com.medex.services.PatientService;


//Request resources which acts as a layer before our Patient services
@Path("/")
public class PatientResources {
	PatientService patientService = new PatientService();

	public PatientResources() {
	}


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Patient> getPatients() {
		return patientService.getAllPatients();
	}

	@GET
	@Path("{Patientid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Patient getPatient(@PathParam("Patientid") int id) {
		return patientService.getPatient(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Patient addPatient(Patient aPatient) {
		return patientService.addPatient(aPatient);
	}

	@PUT
	@Path("{Patientid}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Patient updatePatient(@PathParam("Patientid") int id, Patient Patient) {
		Patient.setId(id);
		return patientService.updatePatient(Patient);
	}

	@DELETE
	@Path("{Patientid}")
	public void removePatient(@PathParam("Patientid") int id, Patient Patient) {
		patientService.removePatient(id);
	}
}
