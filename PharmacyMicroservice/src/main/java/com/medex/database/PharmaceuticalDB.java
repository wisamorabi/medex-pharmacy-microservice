package com.medex.database;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.medex.model.Pharmaceutical;

//This class is specifically for the pharmacies database operations
public class PharmaceuticalDB {
	public void insertPharmaceutical(Pharmaceutical pharmaceutical)
	{
		Transaction transaction = null; //You have to make a transaction object
		try (Session session = HibernateUtil.getPharmacySessionFactory().openSession()) //And now we make a session using the HibernateUtil object
		{
			// start a transaction using the session
			transaction = session.beginTransaction();
			
			session.save(pharmaceutical); //Save transaction allows us to store the pharmaceutical object into the database (This is like insert with the fields, etc, etc)
								   //But hibernate knows what to do using the annotation on the pharmaceutical class
			
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
	
	//This is the update, which pharmaceutical we want to delete
	public void updatePharmaceutical(Pharmaceutical pharmaceutical)
	{
		Transaction transaction = null; //You have to make a transaction object
		try (Session session = HibernateUtil.getPharmacySessionFactory().openSession()) //And now we make a session using the HibernateUtil object
		{
			// start a transaction using the session
			transaction = session.beginTransaction();
			
			session.saveOrUpdate(pharmaceutical); //Save transaction allows us to store the pharmaceutical object into the database (This is like insert with the fields, etc, etc)
										   //But hibernate knows what to do using the annotation on the pharmaceutical class
			
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
	public void deletePharmaceutical(int id)
	{
		Transaction transaction = null; //You have to make a transaction object
		Pharmaceutical pharmaceutical = null;
		try (Session session = HibernateUtil.getPharmacySessionFactory().openSession()) //And now we make a session using the HibernateUtil object
		{
			// start a transaction using the session
			transaction = session.beginTransaction();
			
			pharmaceutical = session.get(Pharmaceutical.class, id); //We have to get the specific pharmaceutical using the ID from the database, so we can delete it
			
			session.delete(pharmaceutical); //We delete that pharmaceutical
			
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

	//Retrieve all pharmacies from the database and store them in a list
	public List<Pharmaceutical> getPharmaceuticals()
	{
		Transaction transaction = null;
		List<Pharmaceutical> pharmacies = null;
		
		try (Session session = HibernateUtil.getPharmacySessionFactory().openSession())
		{
			transaction = session.beginTransaction();
			pharmacies = session.createQuery("from Pharmaceutical", Pharmaceutical.class).list(); //This is a hibernate query (Get all pharmacies from the pharmacies database)
																		 //Each returned row is a pharmaceutical object inserted into the list of pharmacies --> pharmacies
			transaction.commit();
		}
		return pharmacies;
	}
	
	public Pharmaceutical getPharmaceutical(int id)
	{
		Transaction transaction = null;
		Pharmaceutical pharmaceutical = null;
		try (Session session = HibernateUtil.getPharmacySessionFactory().openSession())
		{
			//start a transaction
			transaction = session.beginTransaction();
			
			// get one object
			String hql = " FROM Pharmaceutical H WHERE H.id = :id"; //From the pharmaceutical table
			Query query = session.createQuery(hql);
			query.setParameter("id", id); //The parameter ":id" is set to the id we passed.
			List results = query.getResultList(); //The results are given to us in a list.
												  //Since the id is unique, we will get a list of one item
			
			//If the result is not null, we get a single pharmaceutical object
			if (results != null && !results.isEmpty())
			{
				pharmaceutical = (Pharmaceutical) results.get(0); //So, we retrieve said pharmaceutical from the first index in the list
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
		return pharmaceutical; //Return the pharmaceutical object retrieved
	}
	
}
