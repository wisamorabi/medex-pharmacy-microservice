package com.medex.model;

import javax.persistence.*;

//The pharmacy class
@Entity //A 'serializable' entity
@Table(name = "Pharmacy") //Where this entity will be placed
//Now, the fields that we will annotate will be stored in the hosts table.
public class Pharmacy {
	@Id //Primary key
	@GeneratedValue(strategy=GenerationType.IDENTITY) //"Do this identification for me, generate that specific ID for me
	@Column(name="id") //Which column?
	int id;
	@Column(name="name")
	String name;
	@Column(name="wallet")
	int wallet;
	@Column(name="username")
	String username;
	@Column(name="password")
	String password;
	
	
	public Pharmacy() {}

	//Non default constructor
	public Pharmacy(int id, String aname, int awallet, String username, String password) {
		this.id = id;
		this.name = aname;
		this.wallet = awallet;
		this.username = username;
		this.password = password;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}