package com.place4code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.place4code.hibernate.demo.entity.Instructor;
import com.place4code.hibernate.demo.entity.InstructorDetail;

public class GetInstructorDetailDemo {

	public static void main(String[] args) {

		//create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml") //not required if name is hibernate.cfg.xml
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();
		
		//create a session
		Session session = factory.getCurrentSession();		

		try {
			
			//start transaction
			session.beginTransaction();

			InstructorDetail temp = session.get(InstructorDetail.class, 2); //id = 2 
			System.out.println("temp: " + temp);
			System.out.println("the associated object: " + temp.getInstructor());
			
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			session.close(); //handle connection leak issue
			factory.close();
		}

	}

}
