package com.medex.model;

import javax.persistence.*;

//The Doctor class
//Type = 2
@Entity //A 'serializable' entity
@Table(name = "Doctor") //Where this entity will be placed
//Now, the fields that we will annotate will be stored in the hosts table.
public class Doctor {
	@Id //Primary key
	@GeneratedValue(strategy=GenerationType.IDENTITY) //"Do this identification for me, generate that specific ID for me
	@Column(name="id") //Which column?
	int id;
	@Column(name="consumerID")
	int consumerID;


	public Doctor() {}

	//Non default constructor
	public Doctor(int id, int aconsumerID) {
		this.id = id;
		this.consumerID = aconsumerID;
	}
	public int getId()
	{
		return id;
	}
	public int getConsumerID()
	{
		return consumerID;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public void setConsumerID(int aconsumerID)
	{
		this.consumerID = aconsumerID;
	}
}
