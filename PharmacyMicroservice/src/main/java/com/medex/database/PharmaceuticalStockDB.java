package com.medex.database;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.medex.model.PharmaceuticalStock;

//This class is specifically for the pharmacies database operations
public class PharmaceuticalStockDB {
	public void insertPharmaceuticalStock(PharmaceuticalStock pharmaceuticalStock)
	{
		Transaction transaction = null; //You have to make a transaction object
		try (Session session = HibernateUtil.getPharmacySessionFactory().openSession()) //And now we make a session using the HibernateUtil object
		{
			// start a transaction using the session
			transaction = session.beginTransaction();
			
			session.save(pharmaceuticalStock); //Save transaction allows us to store the pharmaceuticalStock object into the database (This is like insert with the fields, etc, etc)
								   //But hibernate knows what to do using the annotation on the pharmaceuticalStock class
			
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
	
	//This is the update, which pharmaceuticalStock we want to delete
	public void updatePharmaceuticalStock(PharmaceuticalStock pharmaceuticalStock)
	{
		Transaction transaction = null; //You have to make a transaction object
		try (Session session = HibernateUtil.getPharmacySessionFactory().openSession()) //And now we make a session using the HibernateUtil object
		{
			// start a transaction using the session
			transaction = session.beginTransaction();
			
			session.saveOrUpdate(pharmaceuticalStock); //Save transaction allows us to store the pharmaceuticalStock object into the database (This is like insert with the fields, etc, etc)
										   //But hibernate knows what to do using the annotation on the pharmaceuticalStock class
			
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
	public void deletePharmaceuticalStock(int id)
	{
		Transaction transaction = null; //You have to make a transaction object
		PharmaceuticalStock pharmaceuticalStock = null;
		try (Session session = HibernateUtil.getPharmacySessionFactory().openSession()) //And now we make a session using the HibernateUtil object
		{
			// start a transaction using the session
			transaction = session.beginTransaction();
			
			pharmaceuticalStock = session.get(PharmaceuticalStock.class, id); //We have to get the specific pharmaceuticalStock using the ID from the database, so we can delete it
			
			session.delete(pharmaceuticalStock); //We delete that pharmaceuticalStock
			
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
	public List<PharmaceuticalStock> getPharmaceuticalStocks(int pharmacyid)
	{
		Transaction transaction = null;
		List<PharmaceuticalStock> pharmaceuticalStock = null;
		
		try (Session session = HibernateUtil.getPharmacySessionFactory().openSession())
		{
			transaction = session.beginTransaction();
			pharmaceuticalStock = session.createQuery("from PharmaceuticalStock AS P WHERE P.pharmacyID = :pharmacyid", PharmaceuticalStock.class).setParameter("pharmacyid", pharmacyid).list(); //This is a hibernate query (Get all pharmacies from the pharmacies database)
																		 //Each returned row is a pharmaceuticalStock object inserted into the list of pharmacies --> pharmacies
			transaction.commit();
		}
		return pharmaceuticalStock;
	}
	
	public PharmaceuticalStock getPharmaceuticalStock(int pharmacyid, int pharmaceuticalstockid)
	{
		Transaction transaction = null;
		PharmaceuticalStock pharmaceuticalStock = null;
		try (Session session = HibernateUtil.getPharmacySessionFactory().openSession())
		{
			//start a transaction
			transaction = session.beginTransaction();
			
			// get one object
			String hql = "FROM PharmaceuticalStock P WHERE P.id = :pharmaceuticalstockid AND P.pharmacyID = :pharmacyid"; //From the pharmaceuticalStock table
			Query query = session.createQuery(hql);
			query.setParameter("pharmaceuticalstockid", pharmaceuticalstockid); //The parameter ":id" is set to the id we passed.
			query.setParameter("pharmacyid", pharmacyid); //The parameter ":id" is set to the id we passed.
			List results = query.getResultList(); //The results are given to us in a list.
												  //Since the id is unique, we will get a list of one item
			
			//If the result is not null, we get a single pharmaceuticalStock object
			if (results != null && !results.isEmpty())
			{
				pharmaceuticalStock = (PharmaceuticalStock) results.get(0); //So, we retrieve said pharmaceuticalStock from the first index in the list
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
		return pharmaceuticalStock; //Return the pharmaceuticalStock object retrieved
	}
	
}
