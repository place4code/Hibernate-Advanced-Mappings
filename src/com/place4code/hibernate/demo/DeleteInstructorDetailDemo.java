package com.place4code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.place4code.hibernate.demo.entity.Instructor;
import com.place4code.hibernate.demo.entity.InstructorDetail;

public class DeleteInstructorDetailDemo {

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

			InstructorDetail temp = session.get(InstructorDetail.class, 4); //id = 4 
			System.out.println("temp: " + temp);
			System.out.println("the associated object: " + temp.getInstructor());
			
			//when i will delete only InstructorDetail from database
			//remove associated object reference, break bi link
			temp.getInstructor().setInstructorDetail(null);
			
			System.out.println("deleting...");
			session.delete(temp);
			
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
