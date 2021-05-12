package com.medex.database;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.medex.model.Account;

//This class is specifically for the accounts database operations
public class LoginDB {
	public void insertPatient(Account account)
	{
		Transaction transaction = null; //You have to make a transaction object
		try (Session session = HibernateUtil.getSessionFactory().openSession()) //And now we make a session using the HibernateUtil object
		{
			// start a transaction using the session
			transaction = session.beginTransaction();
			
			session.save(account); //Save transaction allows us to store the account object into the database (This is like insert with the fields, etc, etc)
								   //But hibernate knows what to do using the annotation on the account class
			
			// commit transaction		
			transaction.commit(); //Finalize transaction
		}
		catch (Exception e) //If anything goes wrong
		{
			if (transaction != null)
			{
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	
	//This is the update, which account we want to delete
	public void updatePatient(Account account)
	{
		Transaction transaction = null; //You have to make a transaction object
		try (Session session = HibernateUtil.getSessionFactory().openSession()) //And now we make a session using the HibernateUtil object
		{
			// start a transaction using the session
			transaction = session.beginTransaction();
			
			session.saveOrUpdate(account); //Save transaction allows us to store the account object into the database (This is like insert with the fields, etc, etc)
										   //But hibernate knows what to do using the annotation on the account class
			
			// commit transaction
			
			transaction.commit(); //Finalize transaction
		}
		catch (Exception e) //If anything goes wrong
		{
			if (transaction != null)
			{
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	
	
	//Id of what you want to delete
	public void deletePatient(int id)
	{
		Transaction transaction = null; //You have to make a transaction object
		Account account = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) //And now we make a session using the HibernateUtil object
		{
			// start a transaction using the session
			transaction = session.beginTransaction();
			
			account = session.get(Account.class, id); //We have to get the specific account using the ID from the database, so we can delete it
			
			session.delete(account); //We delete that account
			
			// commit transaction
			transaction.commit(); //Finalize transaction
		}
		catch (Exception e) //If anything goes wrong
		{
			if (transaction != null)
			{
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	//Retrieve all accounts from the database and store them in a list
	public List<Account> getPatients()
	{
		Transaction transaction = null;
		List<Account> accounts = null;
		
		try (Session session = HibernateUtil.getSessionFactory().openSession())
		{
			transaction = session.beginTransaction();
			accounts = session.createQuery("from Patient", Account.class).list(); //This is a hibernate query (Get all accounts from the accounts database)
																		 //Each returned row is a account object inserted into the list of accounts --> accounts
			transaction.commit();
		}
		return accounts;
	}
	
	public Account getPatient(int id)
	{
		Transaction transaction = null;
		Account account = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession())
		{
			//start a transaction
			transaction = session.beginTransaction();
			
			// get one object
			String hql = " FROM Patient H WHERE H.id = :id"; //From the account table
			Query query = session.createQuery(hql);
			query.setParameter("id", id); //The parameter ":id" is set to the id we passed.
			List results = query.getResultList(); //The results are given to us in a list.
												  //Since the id is unique, we will get a list of one item
			
			//If the result is not null, we get a single account object
			if (results != null && !results.isEmpty())
			{
				account = (Account) results.get(0); //So, we retrieve said account from the first index in the list
			}
			//commit transaction
			transaction.commit();
		}
		catch (Exception e)
		{
			if (transaction != null)
			{
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return account; //Return the account object retrieved
	}
	
}
