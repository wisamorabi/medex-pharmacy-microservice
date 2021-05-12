package com.medex.database;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.medex.model.Patient;

//This class is specifically for the patients database operations
public class PatientDB {
	public void insertPatient(Patient patient)
	{
		Transaction transaction = null; //You have to make a transaction object
		try (Session session = HibernateUtil.getSessionFactory().openSession()) //And now we make a session using the HibernateUtil object
		{
			// start a transaction using the session
			transaction = session.beginTransaction();
			
			session.save(patient); //Save transaction allows us to store the patient object into the database (This is like insert with the fields, etc, etc)
								   //But hibernate knows what to do using the annotation on the patient class
			
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
	
	//This is the update, which patient we want to delete
	public void updatePatient(Patient patient)
	{
		Transaction transaction = null; //You have to make a transaction object
		try (Session session = HibernateUtil.getSessionFactory().openSession()) //And now we make a session using the HibernateUtil object
		{
			// start a transaction using the session
			transaction = session.beginTransaction();
			
			session.saveOrUpdate(patient); //Save transaction allows us to store the patient object into the database (This is like insert with the fields, etc, etc)
										   //But hibernate knows what to do using the annotation on the patient class
			
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
		Patient patient = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) //And now we make a session using the HibernateUtil object
		{
			// start a transaction using the session
			transaction = session.beginTransaction();
			
			patient = session.get(Patient.class, id); //We have to get the specific patient using the ID from the database, so we can delete it
			
			session.delete(patient); //We delete that patient
			
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

	//Retrieve all patients from the database and store them in a list
	public List<Patient> getPatients()
	{
		Transaction transaction = null;
		List<Patient> patients = null;
		
		try (Session session = HibernateUtil.getSessionFactory().openSession())
		{
			transaction = session.beginTransaction();
			patients = session.createQuery("from Patient", Patient.class).list(); //This is a hibernate query (Get all patients from the patients database)
																		 //Each returned row is a patient object inserted into the list of patients --> patients
			transaction.commit();
		}
		return patients;
	}
	
	public Patient getPatient(int id)
	{
		Transaction transaction = null;
		Patient patient = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession())
		{
			//start a transaction
			transaction = session.beginTransaction();
			
			// get one object
			String hql = " FROM Patient H WHERE H.id = :id"; //From the patient table
			Query query = session.createQuery(hql);
			query.setParameter("id", id); //The parameter ":id" is set to the id we passed.
			List results = query.getResultList(); //The results are given to us in a list.
												  //Since the id is unique, we will get a list of one item
			
			//If the result is not null, we get a single patient object
			if (results != null && !results.isEmpty())
			{
				patient = (Patient) results.get(0); //So, we retrieve said patient from the first index in the list
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
		return patient; //Return the patient object retrieved
	}
	
}
