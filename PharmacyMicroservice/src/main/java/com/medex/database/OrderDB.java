package com.medex.database;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.medex.dependentresources.Ordr;



//This class is specifically for the orders database operations
public class OrderDB {


	//Retrieve all orders from the database and store them in a list
	public List<Ordr> getOrders(int pharmacyid)
	{
		Transaction transaction = null;
		List<Ordr> orders = null;
		
		try (Session session = HibernateUtil.getShoppingSessionFactory().openSession())
		{
			transaction = session.beginTransaction();
			orders = session.createQuery("FROM Ordr", Ordr.class).list(); //This is a hibernate query (Get all orders from the orders database)
			//orders = session.createQuery("FROM Ordr H WHERE H.pharmacyid = :pharmacyid", Ordr.class).setParameter("pharmacyid", pharmacyid).list(); //This is a hibernate query (Get all orders from the orders database)
																		 //Each returned row is a order object inserted into the list of orders --> orders
			transaction.commit();
		}
		return orders;
	}
	
	public Ordr getOrder(int pharmacyid)
	{
		Transaction transaction = null;
		Ordr order = null;
		try (Session session = HibernateUtil.getShoppingSessionFactory().openSession())
		{
			//start a transaction
			transaction = session.beginTransaction();
			// get one object
			String hql = "FROM Ordr H WHERE H.pharmacyID = :pharmacyid"; //From the order table
			
			Query query = session.createQuery(hql);

			query.setParameter("pharmacyid", pharmacyid); //The parameter ":id" is set to the id we passed.
			List results = query.getResultList(); //The results are given to us in a list.
												  //Since the id is unique, we will get a list of one item

			
			//If the result is not null, we get a single order object
			if (results != null && !results.isEmpty())
			{
				order = (Ordr) results.get(0); //So, we retrieve said order from the first index in the list
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
		return order; //Return the order object retrieved
	}
	
}
