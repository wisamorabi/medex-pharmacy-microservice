package com.medex.services;

import java.util.List;

import com.medex.database.PatientDB;
import com.medex.model.Patient;

//This is the "backend" of our resources. This is where the logic is executed; the logic is basic since the database handles itself very well.
public class PatientService {
	PatientDB patientdb = new PatientDB(); //(Instead of the pseudo-database)
	public PatientService() {} 
	
	//All what is below is just calling the functions belonging to the patients' database/table.
	
	public List<Patient>getAllPatients()
	{
		return patientdb.getPatients();
	}
	
	public Patient getPatient(int id)
	{
		return patientdb.getPatient(id);
	}
	
	public Patient addPatient(Patient aPatient)
	{
		patientdb.insertPatient(aPatient); return aPatient;
	}
	
	public Patient updatePatient(Patient aPatient)
	{
		patientdb.updatePatient(aPatient); return aPatient;
	}
	
	public void removePatient(int id)
	{
		patientdb.deletePatient(id);
	}
}


