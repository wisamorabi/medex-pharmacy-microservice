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

import com.medex.services.LoginService;



@Path("/Login") 
public class LoginResources
{
	LoginService loginService = new LoginService();

	//This will be for logging in.
	
//	@GET
//	@Produces(MediaType.APPLICATION_JSON) 
//	public List<User> getPatients()
//	{
//		return PatientService.getAllPatients();
//	}
//	
//	@GET
//	@Path("{Patientid}") 
//	@Produces(MediaType.APPLICATION_JSON) 
//	public User getPatient(@PathParam("Patientid")int id) 
//	{
//		return PatientService.getPatient(id);
//	}
//	
//
//	@POST 
//	@Consumes(MediaType.APPLICATION_JSON) 
//	@Produces(MediaType.APPLICATION_JSON)
//	public User addPatient(User aPatient)
//	{
//		return PatientService.addPatient(aPatient);
//	}
//
//	
//	@PUT
//	@Path("{Patientid}")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public User updatePatient(@PathParam("Patientid")int id, User Patient)
//	{
//		Patient.setId(id);
//		return PatientService.updatePatient(Patient);
//	}
//	
//	@DELETE
//	@Path("{Patientid}")
//	public void updateremovePatient(@PathParam("Patientid")int id, User Patient)
//	{
//		PatientService.removePatient(id);
//	}
	
	//Required to acccess the subdirectory/subresource which is the VMs which belong to the selected host!
	@Path("/personnel")
	public PersonnelResources getPersonnel()
	{
		return new PersonnelResources();	
	}
	@Path("/pharmacy")
	public PharmacyResources getPharmacies()
	{
		return new PharmacyResources();	
	}
	@Path("/doctors")
	public DoctorResources getDoctors()
	{
		return new DoctorResources();	
	}
	@Path("/patients")
	public PatientResources getPatients()
	{
		return new PatientResources();	
	}
}

