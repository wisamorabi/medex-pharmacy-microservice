package com.medex.model;

import javax.persistence.*;

//The pharmacy class
//Type = 1
@Entity //A 'serializable' entity
@Table(name = "Pharmacy") //Where this entity will be placed
//Now, the fields that we will annotate will be stored in the hosts table.
public class Pharmacy {
	@Id //Primary key
	@GeneratedValue(strategy=GenerationType.IDENTITY) //"Do this identification for me, generate that specific ID for me
	@Column(name="id") //Which column?
	int id;
	@Column(name="consumerID")
	int consumerID;
	@Column(name="wallet")
	int wallet;
	
	public Pharmacy() {}

	//Non default constructor
	public Pharmacy(int id, int aconsumerID, int awallet) {
		this.id = id;
		this.consumerID = aconsumerID;
		this.wallet = awallet;
	}
	public int getId()
	{
		return id;
	}
	public int getConsumerID()
	{
		return consumerID;
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
	public void setConsumerID(int aconsumerID)
	{
		this.consumerID = aconsumerID;
	}
}