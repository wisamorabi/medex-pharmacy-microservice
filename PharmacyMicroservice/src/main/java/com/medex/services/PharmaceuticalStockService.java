package com.medex.services;

import java.util.List;

import com.medex.communicationmodules.Status;
import com.medex.database.PharmaceuticalDB;
import com.medex.database.PharmaceuticalStockDB;
import com.medex.database.PharmacyDB;
import com.medex.model.PharmaceuticalStock;



public class PharmaceuticalStockService {

	PharmaceuticalStockDB pharmaceuticalStockDB = new PharmaceuticalStockDB(); // (Instead of the pseudo-database)
	PharmacyDB pharmacyDB = new PharmacyDB();
	PharmaceuticalDB pharmaceuticalDB = new PharmaceuticalDB();

	public PharmaceuticalStockService() {
	}

	// pharmacys/{id}/pharmaceuticalStocks web service Requirement #1: Retrive te PharmaceuticalStocks allocated in a pharmacy.
	public List<PharmaceuticalStock> getPharmaceuticalStocks(int pharmacyID) {
		return pharmaceuticalStockDB.getPharmaceuticalStocks(pharmacyID);
	}

	
	
	// Requirement 9: Create a REST API the provides the ability to delete a PharmaceuticalStock that
	// is allocated to a pharmacy.
	// pharmacys/{id}/pharmaceuticalStocks web service Requirement #4: Delete a PharmaceuticalStock from the pharmacy.
////////////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////////////
	
	
	// pharmacys/{id}/pharmaceuticalStocks web service requirement #2: Retrieve a single PharmaceuticalStock details that
	// is allocated to a pharmacy.
	public PharmaceuticalStock getPharmaceuticalStock(int pharmacyID, int pharmaceuticalStockid) {
		return pharmaceuticalStockDB.getPharmaceuticalStock(pharmacyID, pharmaceuticalStockid);
	}

	
	
	
	/*
	 * Requirement 10: Create the REST API that provides the ability to allocate a
	 * PharmaceuticalStock to a pharmacy. The PharmaceuticalStock will allocate a created PharmaceuticalStock pharmaceuticalStock that is not allocated
	 * to a pharmacy. If the pharmacy does not contain enough resources, the service will
	 * not do the allocation and returns status false. If the allocation is
	 * successful, it shall return status true. Make sure to allocate the resources
	 * from the pharmacy capability to the PharmaceuticalStocks allocated to the pharmacy.
	 */
	 //pharmacys/{id}/pharmaceuticalStocks web service requirement #3: Allocate a create PharmaceuticalStock pharmaceuticalStock to a pharmacy if the capabilities required by the PharmaceuticalStock is available at the pharmacy. Once it is allocated, remove the PharmaceuticalStock from the pharmaceuticalStock list by calling the correct service function.
////////////////////////////////////////////////////////////////////////////////////////

	public PharmaceuticalStock addPharmaceuticalStock(int pharmacyid, PharmaceuticalStock pharmaceuticalStock) {
		pharmaceuticalStock.setPharmacyID(pharmacyid);
		if (pharmacyDB.getPharmacy(pharmacyid) == null) return null;
		if (pharmaceuticalDB.getPharmaceutical(pharmaceuticalStock.getMedicineID()) == null) return null;
		List<PharmaceuticalStock> lst = getPharmaceuticalStocks(pharmacyid);
		for (PharmaceuticalStock ps : lst)
		{
			if (ps.getMedicineID() == pharmaceuticalStock.getMedicineID())
			{
				return null;
			}
		}
		pharmaceuticalStockDB.insertPharmaceuticalStock(pharmaceuticalStock);
		return pharmaceuticalStock;
	}
////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	// pharmacys/{id}/pharmaceuticalStocks web service requirement #5: Update a PharmaceuticalStock allocated in the pharmacy
////////////////////////////////////////////////////////////////////////////////////////

	public PharmaceuticalStock updatePharmaceuticalStock(int pharmacyid, int pharmaceuticalStockid, PharmaceuticalStock pharmaceuticalStock)
	{

		pharmaceuticalStock.setId(pharmaceuticalStockid);
		if (pharmacyDB.getPharmacy(pharmacyid) == null) return null;

		if (pharmaceuticalDB.getPharmaceutical(pharmaceuticalStock.getMedicineID()) == null) return null;

		if (pharmaceuticalStockDB.getPharmaceuticalStock(pharmacyid, pharmaceuticalStock.getId()) == null) return null;
		pharmaceuticalStockDB.updatePharmaceuticalStock(pharmaceuticalStock);
		return pharmaceuticalStock;
	}
	
	
	public Status removePharmaceuticalStock(int pharmacyid, int pharmaceuticalStockid)
	{
		if (pharmaceuticalStockDB.getPharmaceuticalStock(pharmacyid, pharmaceuticalStockid) == null) return new Status(false);
		pharmaceuticalStockDB.deletePharmaceuticalStock(pharmaceuticalStockid);
		return new Status(true);
	}
////////////////////////////////////////////////////////////////////////////////////////
}