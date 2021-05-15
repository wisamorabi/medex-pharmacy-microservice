package com.medex.services;

import java.util.ArrayList;
import java.util.List;

import com.medex.communicationmodules.PharmacyInfo;
import com.medex.communicationmodules.Status;
import com.medex.database.PharmacyDB;

import com.medex.model.PharmaceuticalStock;
import com.medex.model.Pharmacy;

//This is the "backend" of our resources. This is where the logic is executed; the logic is basic since the database handles itself very well.
public class PharmacyService {
	PharmacyDB pharmacydb = new PharmacyDB(); //(Instead of the pseudo-database)
	PharmaceuticalStockService pharmaceuticalStockService = new PharmaceuticalStockService();
	public PharmacyService() {} 
	
	//All what is below is just calling the functions belonging to the pharmacies' database/table.
	
	public List<PharmacyInfo>getAllPharmacies()
	{
		List<Pharmacy> pharmacyList = pharmacydb.getPharmacies(); //Get all hosts.
		List<PharmacyInfo> pharmacyinfoList = new ArrayList<PharmacyInfo>(); //Make a list that contains HostInfo instances
		if (pharmacyList.isEmpty() == true) return null;	
		//If the list of hosts is not empty, "cast" every host instance into a HostInfo instance
		for (Pharmacy p : pharmacyList) {
			pharmacyinfoList.add(PharmacyToPharmacyInfo(p));
			}		
		return pharmacyinfoList;
	}
	
	public PharmacyInfo getPharmacy(int pharmacyid)
	{
		Pharmacy pharmacy = pharmacydb.getPharmacy(pharmacyid); //Get all hosts.
		if (pharmacy == null) return null;
		return PharmacyToPharmacyInfo(pharmacy);
	}
	
	public PharmacyInfo addPharmacy(Pharmacy aPharmacy)
	{
		pharmacydb.insertPharmacy(aPharmacy); return PharmacyToPharmacyInfo(aPharmacy);
	}
	
	public PharmacyInfo updatePharmacy(Pharmacy aPharmacy)
	{
		if (pharmacydb.getPharmacy(aPharmacy.getId()) == null) return null; 
		pharmacydb.updatePharmacy(aPharmacy); 
		return PharmacyToPharmacyInfo(aPharmacy);
	}
	
	public Status removePharmacy(int id)
	{
		if (pharmacydb.getPharmacy(id) == null) return new Status(false);
		pharmacydb.deletePharmacy(id);
		return new Status(true);
	}
	
	
	private PharmacyInfo PharmacyToPharmacyInfo(Pharmacy aPharmacy)
	{
		PharmacyInfo pharmacyInfo = new PharmacyInfo(aPharmacy);
		List<PharmaceuticalStock> lst = pharmaceuticalStockService.getPharmaceuticalStocks(aPharmacy.getId()); //For every host, get the list of VMs it holds and attach that to the hashmap of VMs that each instance of HostInfo has.
		if (lst.isEmpty() == false) { pharmacyInfo.listToMap(lst); }
		
		return pharmacyInfo;
	}
	
	
	
	public Pharmacy getPharmacyLogin(String username, String password)
	{
		return pharmacydb.getPharmacyLogin(username, password); //Get all hosts.

	}
}


