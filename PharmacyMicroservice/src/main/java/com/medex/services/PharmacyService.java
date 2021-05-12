package com.medex.services;

import java.util.ArrayList;
import java.util.List;

import com.medex.communicationmodules.PharmacyInfo;
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
		if (pharmacyList.isEmpty() == false) return null;	
		//If the list of hosts is not empty, "cast" every host instance into a HostInfo instance
		int i = 0; //Could have just used a for loop rather than a foreach loop with a counter really..
		for (Pharmacy p : pharmacyList) { //For each host, "cast" it into a HostInfo instance and add it it to the hostinfoList
			pharmacyinfoList.add(new PharmacyInfo(p));
			List<PharmaceuticalStock> lst = pharmaceuticalStockService.getPharmaceuticalStocks(p.getId()); //For every host, get the list of VMs it holds and attach that to the hashmap of VMs that each instance of HostInfo has.
			if (lst.isEmpty() == false) { //If not empty (There are VMs attached)
				pharmacyinfoList.get(i++).listToMap(lst); //Copy the list of VMs.
				}
			}		
		return pharmacyinfoList;
	}
	
	public PharmacyInfo getPharmacy(int id)
	{
		Pharmacy pharmacy = pharmacydb.getPharmacy(id); //Get all hosts.
		if (pharmacy == null) return null;
		PharmacyInfo pharmacyInfo = new PharmacyInfo(pharmacy);
		List<PharmaceuticalStock> lst = pharmaceuticalStockService.getPharmaceuticalStocks(pharmacyInfo.getId()); //For every host, get the list of VMs it holds and attach that to the hashmap of VMs that each instance of HostInfo has.
		if (lst.isEmpty() == false) { pharmacyInfo.listToMap(lst); 	}	
		return pharmacyInfo;
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


