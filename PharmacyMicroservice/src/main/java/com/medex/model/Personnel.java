package com.medex.model;

import javax.persistence.*;

//The personnel class
//Type = 1
@Entity //A 'serializable' entity
@Table(name = "Personnel") //Where this entity will be placed
//Now, the fields that we will annotate will be stored in the hosts table.
public class Personnel {
	@Id //Primary key
	@GeneratedValue(strategy=GenerationType.IDENTITY) //"Do this identification for me, generate that specific ID for me
	@Column(name="id") //Which column?
	int id;
	@Column(name="consumerID")
	int consumerID;

	public Personnel() {}

	//Non default constructor
	public Personnel(int id, int aConsumerID) {
		this.id = id;
		this.consumerID = aConsumerID;
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
