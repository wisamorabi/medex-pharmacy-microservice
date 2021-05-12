package com.medex.communicationmodules;

//Status class which belongs to the communication modules package.
//An object which returns the status of some of the operations (According to the homework specifications) to the user!
//The user would know the status from the TransSuccess boolean.
public class Status
{
	boolean TransSuccess;
	public Status(boolean TransSuccess) {this.TransSuccess = TransSuccess;}
	
	public boolean getTransSuccess() { return TransSuccess; }
	public void setTransSuccess(boolean transSuccess) { TransSuccess = transSuccess; }	
}