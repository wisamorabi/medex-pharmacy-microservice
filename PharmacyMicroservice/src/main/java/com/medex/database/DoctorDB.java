package com.medex.database;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.medex.model.Doctor;

//This class is specifically for the doctors database operations
public class DoctorDB {
	public void insertDoctor(Doctor doctor)
	{
		Transaction transaction = null; //You have to make a transaction object
		try (Session session = HibernateUtil.getSessionFactory().openSession()) //And now we make a session using the HibernateUtil object
		{
			// start a transaction using the session
			transaction = session.beginTransaction();
			
			session.save(doctor); //Save transaction allows us to store the doctor object into the database (This is like insert with the fields, etc, etc)
								   //But hibernate knows what to do using the annotation on the doctor class
			
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
	
	//This is the update, which doctor we want to delete
	public void updateDoctor(Doctor doctor)
	{
		Transaction transaction = null; //You have to make a transaction object
		try (Session session = HibernateUtil.getSessionFactory().openSession()) //And now we make a session using the HibernateUtil object
		{
			// start a transaction using the session
			transaction = session.beginTransaction();
			
			session.saveOrUpdate(doctor); //Save transaction allows us to store the doctor object into the database (This is like insert with the fields, etc, etc)
										   //But hibernate knows what to do using the annotation on the doctor class
			
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
	public void deleteDoctor(int id)
	{
		Transaction transaction = null; //You have to make a transaction object
		Doctor doctor = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) //And now we make a session using the HibernateUtil object
		{
			// start a transaction using the session
			transaction = session.beginTransaction();
			
			doctor = session.get(Doctor.class, id); //We have to get the specific doctor using the ID from the database, so we can delete it
			
			session.delete(doctor); //We delete that doctor
			
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

	//Retrieve all doctors from the database and store them in a list
	public List<Doctor> getDoctors()
	{
		Transaction transaction = null;
		List<Doctor> doctors = null;
		
		try (Session session = HibernateUtil.getSessionFactory().openSession())
		{
			transaction = session.beginTransaction();
			doctors = session.createQuery("from Doctor", Doctor.class).list(); //This is a hibernate query (Get all doctors from the doctors database)
																		 //Each returned row is a doctor object inserted into the list of doctors --> doctors
			transaction.commit();
		}
		return doctors;
	}
	
	public Doctor getDoctor(int id)
	{
		Transaction transaction = null;
		Doctor doctor = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession())
		{
			//start a transaction
			transaction = session.beginTransaction();
			
			// get one object
			String hql = " FROM Doctor H WHERE H.id = :id"; //From the doctor table
			Query query = session.createQuery(hql);
			query.setParameter("id", id); //The parameter ":id" is set to the id we passed.
			List results = query.getResultList(); //The results are given to us in a list.
												  //Since the id is unique, we will get a list of one item
			
			//If the result is not null, we get a single doctor object
			if (results != null && !results.isEmpty())
			{
				doctor = (Doctor) results.get(0); //So, we retrieve said doctor from the first index in the list
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
		return doctor; //Return the doctor object retrieved
	}
	
}
