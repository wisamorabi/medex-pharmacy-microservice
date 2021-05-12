package com.medex.services;

import java.util.List;

import com.medex.database.PharmacyDB;
import com.medex.model.Pharmacy;

//This is the "backend" of our resources. This is where the logic is executed; the logic is basic since the database handles itself very well.
public class PharmacyService {
	PharmacyDB pharmacydb = new PharmacyDB(); //(Instead of the pseudo-database)
	public PharmacyService() {} 
	
	//All what is below is just calling the functions belonging to the pharmacies' database/table.
	
	public List<Pharmacy>getAllPharmacies()
	{
		return pharmacydb.getPharmacies();
	}
	
	public Pharmacy getPharmacy(int id)
	{
		return pharmacydb.getPharmacy(id);
	}
	
	public Pharmacy addPharmacy(Pharmacy aPharmacy)
	{
		pharmacydb.insertPharmacy(aPharmacy); return aPharmacy;
	}
	
	public Pharmacy updatePharmacy(Pharmacy aPharmacy)
	{
		pharmacydb.updatePharmacy(aPharmacy); return aPharmacy;
	}
	
	public void removePharmacy(int id)
	{
		pharmacydb.deletePharmacy(id);
	}
}


