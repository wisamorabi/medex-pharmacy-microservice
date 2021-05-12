package com.medex.database;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.medex.model.Personnel;

//This class is specifically for the personnels database operations
public class PersonnelDB {
	public void insertPersonnel(Personnel personnel)
	{
		Transaction transaction = null; //You have to make a transaction object
		try (Session session = HibernateUtil.getSessionFactory().openSession()) //And now we make a session using the HibernateUtil object
		{
			// start a transaction using the session
			transaction = session.beginTransaction();
			
			session.save(personnel); //Save transaction allows us to store the personnel object into the database (This is like insert with the fields, etc, etc)
								   //But hibernate knows what to do using the annotation on the personnel class
			
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
	
	//This is the update, which personnel we want to delete
	public void updatePersonnel(Personnel personnel)
	{
		Transaction transaction = null; //You have to make a transaction object
		try (Session session = HibernateUtil.getSessionFactory().openSession()) //And now we make a session using the HibernateUtil object
		{
			// start a transaction using the session
			transaction = session.beginTransaction();
			
			session.saveOrUpdate(personnel); //Save transaction allows us to store the personnel object into the database (This is like insert with the fields, etc, etc)
										   //But hibernate knows what to do using the annotation on the personnel class
			
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
	public void deletePersonnel(int id)
	{
		Transaction transaction = null; //You have to make a transaction object
		Personnel personnel = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) //And now we make a session using the HibernateUtil object
		{
			// start a transaction using the session
			transaction = session.beginTransaction();
			
			personnel = session.get(Personnel.class, id); //We have to get the specific personnel using the ID from the database, so we can delete it
			
			session.delete(personnel); //We delete that personnel
			
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

	//Retrieve all personnels from the database and store them in a list
	public List<Personnel> getPersonnels()
	{
		Transaction transaction = null;
		List<Personnel> personnels = null;
		
		try (Session session = HibernateUtil.getSessionFactory().openSession())
		{
			transaction = session.beginTransaction();
			personnels = session.createQuery("from Personnel", Personnel.class).list(); //This is a hibernate query (Get all personnels from the personnels database)
																		 //Each returned row is a personnel object inserted into the list of personnels --> personnels
			transaction.commit();
		}
		return personnels;
	}
	
	public Personnel getPersonnel(int id)
	{
		Transaction transaction = null;
		Personnel personnel = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession())
		{
			//start a transaction
			transaction = session.beginTransaction();
			
			// get one object
			String hql = " FROM Personnel H WHERE H.id = :id"; //From the personnel table
			Query query = session.createQuery(hql);
			query.setParameter("id", id); //The parameter ":id" is set to the id we passed.
			List results = query.getResultList(); //The results are given to us in a list.
												  //Since the id is unique, we will get a list of one item
			
			//If the result is not null, we get a single personnel object
			if (results != null && !results.isEmpty())
			{
				personnel = (Personnel) results.get(0); //So, we retrieve said personnel from the first index in the list
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
		return personnel; //Return the personnel object retrieved
	}
	
}
