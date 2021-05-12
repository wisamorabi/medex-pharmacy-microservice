package com.medex.services;

import java.util.List;

import com.medex.database.DoctorDB;
import com.medex.model.Doctor;

//This is the "backend" of our resources. This is where the logic is executed; the logic is basic since the database handles itself very well.
public class DoctorService {
	DoctorDB doctordb = new DoctorDB(); //(Instead of the pseudo-database)
	public DoctorService() {} 
	
	//All what is below is just calling the functions belonging to the doctors' database/table.
	
	public List<Doctor>getAllDoctors()
	{
		return doctordb.getDoctors();
	}
	
	public Doctor getDoctor(int id)
	{
		return doctordb.getDoctor(id);
	}
	
	public Doctor addDoctor(Doctor aDoctor)
	{
		doctordb.insertDoctor(aDoctor); return aDoctor;
	}
	
	public Doctor updateDoctor(Doctor aDoctor)
	{
		doctordb.updateDoctor(aDoctor); return aDoctor;
	}
	
	public void removeDoctor(int id)
	{
		doctordb.deleteDoctor(id);
	}
}


