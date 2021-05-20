package com.medex.services;

import java.util.List;

import com.medex.communicationmodules.Status;
import com.medex.database.PharmaceuticalDB;
import com.medex.model.Pharmaceutical;

//This is the "backend" of our resources. This is where the logic is executed; the logic is basic since the database handles itself very well.
public class PharmaceuticalService {
	PharmaceuticalDB pharmaceuticaldb = new PharmaceuticalDB(); //(Instead of the pseudo-database)
	public PharmaceuticalService() {} 
	
	//All what is below is just calling the functions belonging to the pharmacies' database/table.
	
	public List<Pharmaceutical>getAllPharmaceuticals()
	{
		return pharmaceuticaldb.getPharmaceuticals();
	}
	
	public Pharmaceutical getPharmaceutical(int id)
	{
		return pharmaceuticaldb.getPharmaceutical(id);
	}
	
	public Pharmaceutical addPharmaceutical(Pharmaceutical aPharmaceutical)
	{
		pharmaceuticaldb.insertPharmaceutical(aPharmaceutical); return aPharmaceutical;
	}
	
	public Pharmaceutical updatePharmaceutical(Pharmaceutical aPharmaceutical)
	{
		if (pharmaceuticaldb.getPharmaceutical(aPharmaceutical.getId()) == null) return null;
		pharmaceuticaldb.updatePharmaceutical(aPharmaceutical);
		return aPharmaceutical;
	}
	
	public Status removePharmaceutical(int id)
	{
		if (pharmaceuticaldb.getPharmaceutical(id) == null) return new Status(false);
		pharmaceuticaldb.deletePharmaceutical(id);
		return new Status(true);
	}
}


