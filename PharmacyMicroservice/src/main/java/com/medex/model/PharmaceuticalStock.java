package com.medex.model;

import javax.persistence.*;

//The pharmaceutical class
@Entity //A 'serializable' entity
@Table(name = "PharmaceuticalStock") //Where this entity will be placed
//Now, the fields that we will annotate will be stored in the hosts table.
public class PharmaceuticalStock {
	@Id //Primary key
	@GeneratedValue(strategy=GenerationType.IDENTITY) //"Do this identification for me, generate that specific ID for me
	@Column(name="id") //Which column?
	int id;
	@Column(name="medicineID")
	int medicineID;
	@Column(name="name")
	String name;
	@Column(name="pharmacyID")
	int pharmacyID;
	@Column(name="pharmacyName")
	String pharmacyName;
	@Column(name="count")
	int count;
	@Column(name="medicinePrice")
	int medicinePrice;
	

	
	public PharmaceuticalStock() {}

	//Non default constructor
	public PharmaceuticalStock(int id, int amedicineID, int apharmacyID, int amedicinePrice, int acount, String name, String pharmacyName) {
		this.id = id;
		this.name = name;
		this.medicineID = amedicineID;
		this.pharmacyID = apharmacyID;
		this.count = acount;
		this.medicinePrice = amedicinePrice;
		this.pharmacyName = pharmacyName;
}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMedicineID() {
		return medicineID;
	}

	public void setMedicineID(int medicineID) {
		this.medicineID = medicineID;
	}

	public int getPharmacyID() {
		return pharmacyID;
	}

	public void setPharmacyID(int pharmacyID) {
		this.pharmacyID = pharmacyID;
	}


	public int getMedicinePrice() {
		return medicinePrice;
	}

	public void setMedicinePrice(int medicinePrice) {
		this.medicinePrice = medicinePrice;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getPharmacyName() {
		return pharmacyName;
	}

	public void setPharmacyName(String pharmacyName) {
		this.pharmacyName = pharmacyName;
	}
	
}