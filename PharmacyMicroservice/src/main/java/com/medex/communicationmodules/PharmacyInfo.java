package com.medex.communicationmodules;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.*;

import com.medex.model.PharmaceuticalStock;
import com.medex.model.Pharmacy;

//The pharmacy class.
@Entity //A 'serializable' entity
//Now, the fields that we will annotate will be stored in the hosts table.
public class PharmacyInfo {
	int id;
	String name;
	int wallet;
	Map<Integer, PharmaceuticalStock> pharmacyStock = new HashMap<>(); 
	
	public PharmacyInfo() {}

	//Non default constructor
	public PharmacyInfo(int id, String aname, int awallet) {
		this.id = id;
		this.name = aname;
		this.wallet = awallet;
	}
	public PharmacyInfo(Pharmacy p) {
		this.id = p.getId();
		this.name = p.getName();
		this.wallet = p.getWallet();
	}
	public int getId()
	{
		return id;
	}
	public String getName()
	{
		return name;
	}
	public int getWallet()
	{
		return wallet;
	}
	public void setWallet(int awallet) {
		this.wallet = awallet;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public void listToMap(List<PharmaceuticalStock> lst)
	{
		for (PharmaceuticalStock i : lst) 
		{
			pharmacyStock.put(i.getId(),i);
		}
	}
	public Map<Integer, PharmaceuticalStock> getPharmaceuticalStock()
	{
		return pharmacyStock;
	}
}