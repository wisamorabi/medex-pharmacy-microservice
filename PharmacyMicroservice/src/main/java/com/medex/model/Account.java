package com.medex.model;

import javax.persistence.*;

//The account class

@Entity //A 'serializable' entity
@Table(name = "Account") //Where this entity will be placed
//Now, the fields that we will annotate will be stored in the hosts table.
public class Account {
	@Id //Primary key
	@GeneratedValue(strategy=GenerationType.IDENTITY) //"Do this identification for me, generate that specific ID for me
	@Column(name="id") //Which column?
	int id;
	@Column(name="type")
	int type;

	public Account() {}

	//Non default constructor
	public Account(int id, int atype) {
		this.id = id;
		this.type = atype;
	}
	public int getId()
	{
		return id;
	}
	public int getType()
	{
		return type;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public void setType(int atype)
	{
		this.type = atype;
	}
}
