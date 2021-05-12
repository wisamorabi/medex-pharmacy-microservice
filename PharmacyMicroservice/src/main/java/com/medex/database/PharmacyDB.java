package com.medex.database;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.medex.model.Pharmacy;

//This class is specifically for the pharmacies database operations
public class PharmacyDB {
	public void insertPharmacy(Pharmacy pharmacy)
	{
		Transaction transaction = null; //You have to make a transaction object
		try (Session session = HibernateUtil.getSessionFactory().openSession()) //And now we make a session using the HibernateUtil object
		{
			// start a transaction using the session
			transaction = session.beginTransaction();
			
			session.save(pharmacy); //Save transaction allows us to store the pharmacy object into the database (This is like insert with the fields, etc, etc)
								   //But hibernate knows what to do using the annotation on the pharmacy class
			
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
	
	//This is the update, which pharmacy we want to delete
	public void updatePharmacy(Pharmacy pharmacy)
	{
		Transaction transaction = null; //You have to make a transaction object
		try (Session session = HibernateUtil.getSessionFactory().openSession()) //And now we make a session using the HibernateUtil object
		{
			// start a transaction using the session
			transaction = session.beginTransaction();
			
			session.saveOrUpdate(pharmacy); //Save transaction allows us to store the pharmacy object into the database (This is like insert with the fields, etc, etc)
										   //But hibernate knows what to do using the annotation on the pharmacy class
			
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
	public void deletePharmacy(int id)
	{
		Transaction transaction = null; //You have to make a transaction object
		Pharmacy pharmacy = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) //And now we make a session using the HibernateUtil object
		{
			// start a transaction using the session
			transaction = session.beginTransaction();
			
			pharmacy = session.get(Pharmacy.class, id); //We have to get the specific pharmacy using the ID from the database, so we can delete it
			
			session.delete(pharmacy); //We delete that pharmacy
			
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
	public List<Pharmacy> getPharmacies()
	{
		Transaction transaction = null;
		List<Pharmacy> pharmacies = null;
		
		try (Session session = HibernateUtil.getSessionFactory().openSession())
		{
			transaction = session.beginTransaction();
			pharmacies = session.createQuery("from Pharmacy", Pharmacy.class).list(); //This is a hibernate query (Get all pharmacies from the pharmacies database)
																		 //Each returned row is a pharmacy object inserted into the list of pharmacies --> pharmacies
			transaction.commit();
		}
		return pharmacies;
	}
	
	public Pharmacy getPharmacy(int id)
	{
		Transaction transaction = null;
		Pharmacy pharmacy = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession())
		{
			//start a transaction
			transaction = session.beginTransaction();
			
			// get one object
			String hql = " FROM Pharmacy H WHERE H.id = :id"; //From the pharmacy table
			Query query = session.createQuery(hql);
			query.setParameter("id", id); //The parameter ":id" is set to the id we passed.
			List results = query.getResultList(); //The results are given to us in a list.
												  //Since the id is unique, we will get a list of one item
			
			//If the result is not null, we get a single pharmacy object
			if (results != null && !results.isEmpty())
			{
				pharmacy = (Pharmacy) results.get(0); //So, we retrieve said pharmacy from the first index in the list
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
		return pharmacy; //Return the pharmacy object retrieved
	}
	
}
