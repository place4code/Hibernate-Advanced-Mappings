package com.place4code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.place4code.hibernate.demo.entity.Instructor;
import com.place4code.hibernate.demo.entity.InstructorDetail;

public class CreateDemo {

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
			//create the objects
			Instructor instructor = 
					new Instructor("Mark", "Wege", "foo@email.com");
			
			InstructorDetail instructorDetail = 
					new InstructorDetail("twitter channel", "foorbal");
			
			//associate the objects together
			instructor.setInstructorDetail(instructorDetail);
			
			//start transaction
			session.beginTransaction();
			
			//save the object in database
			//this will save the details object because of CascadeType.ALL
			System.out.println("Saving... " + instructor);
			session.save(instructor);

			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
		} finally {
			factory.close();
		}

	}

}
