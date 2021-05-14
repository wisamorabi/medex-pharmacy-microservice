package com.medex.database;
import org.hibernate.cfg.Configuration;
import java.util.Properties;
//import javax.ws.rs.core.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import com.medex.dependentresources.Ordr;
import com.medex.model.Pharmaceutical;
import com.medex.model.PharmaceuticalStock;
import com.medex.model.Pharmacy;


//This class is used to connect to our SQL database residing on AWS.
public class HibernateUtil {
	private static SessionFactory pharmacysessionFactory;
	private static SessionFactory shoppingsessionFactory;
	
	
	public static SessionFactory getShoppingSessionFactory()
	{
		if (shoppingsessionFactory == null)
		{
			//Create multiple
			try {
				Configuration configuration = new Configuration(); //You have to make your configuration object			
				
				//You need to specify you database environment using your properties settings
				Properties settings = new Properties();
				
				settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver"); //We will the driver/connector for mySQL
				settings.put(Environment.URL, "jdbc:mysql://cmp404projectloginmicroservice.cbnlmhsizyc4.us-east-1.rds.amazonaws.com/ShoppingMicroserviceSchema"); //The environment URL (Our AWS database)
				settings.put(Environment.USER, "wisam"); //Our username
				settings.put(Environment.PASS, "73138ProjectDBPassword"); //Our password
				settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect"); //We will use hibernate mySQL dialact (So it translates)
				
				configuration.setProperties(settings); //Applying the settings to the configuration object

				configuration.addAnnotatedClass(Ordr.class); //The patient class is the one that has the annotation, this is what we consider when saving to the database.
				//If we have multiple classes then we do configuration.addAnnotatedClass(X.class) again.



				
				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
				//Just biuilding the whole thing.
				
				shoppingsessionFactory = configuration.buildSessionFactory(serviceRegistry); //This function will return a session factory now.
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return shoppingsessionFactory; //We use this for insertion, updating, deletion, everything.
	}
	
	public static SessionFactory getPharmacySessionFactory()
	{
		if (pharmacysessionFactory == null)
		{
			//Create multiple
			try {
				Configuration configuration = new Configuration(); //You have to make your configuration object			
				
				//You need to specify you database environment using your properties settings
				Properties settings = new Properties();
				
				settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver"); //We will the driver/connector for mySQL
				settings.put(Environment.URL, "jdbc:mysql://cmp404projectloginmicroservice.cbnlmhsizyc4.us-east-1.rds.amazonaws.com/PharmacyMicroserviceSchema"); //The environment URL (Our AWS database)
				settings.put(Environment.USER, "wisam"); //Our username
				settings.put(Environment.PASS, "73138ProjectDBPassword"); //Our password
				settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect"); //We will use hibernate mySQL dialact (So it translates)
				
				configuration.setProperties(settings); //Applying the settings to the configuration object

				configuration.addAnnotatedClass(Pharmaceutical.class); //The patient class is the one that has the annotation, this is what we consider when saving to the database.
				//If we have multiple classes then we do configuration.addAnnotatedClass(X.class) again.
				configuration.addAnnotatedClass(PharmaceuticalStock.class); //The patient class is the one that has the annotation, this is what we consider when saving to the database.
				//If we have multiple classes then we do configuration.addAnnotatedClass(X.class) again.
				configuration.addAnnotatedClass(Pharmacy.class); //The patient class is the one that has the annotation, this is what we consider when saving to the database.
				//If we have multiple classes then we do configuration.addAnnotatedClass(X.class) again.


				
				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
				//Just biuilding the whole thing.
				
				pharmacysessionFactory = configuration.buildSessionFactory(serviceRegistry); //This function will return a session factory now.
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return pharmacysessionFactory; //We use this for insertion, updating, deletion, everything.
	}
	
	
}
