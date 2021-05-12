package com.medex.model;

import javax.persistence.*;

//The pharmaceutical class
@Entity //A 'serializable' entity
@Table(name = "Pharmaceutical") //Where this entity will be placed
//Now, the fields that we will annotate will be stored in the hosts table.
public class Pharmaceutical {
	@Id //Primary key
	@GeneratedValue(strategy=GenerationType.IDENTITY) //"Do this identification for me, generate that specific ID for me
	@Column(name="id") //Which column?
	int id;
	@Column(name="name")
	String name;
	@Column(name="description")
	String description;
	@Column(name="prescription")
	boolean prescription;

	
	public Pharmaceutical() {}

	//Non default constructor
	public Pharmaceutical(int id, String aname, String adescription, boolean prescription) {
		this.id = id;
		this.name = aname;
		this.description = adescription;
		this.prescription = prescription;

	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean getPrescription() {
		return prescription;
	}

	public void setPrescription(boolean prescription) {
		this.prescription = prescription;
	}

	
}