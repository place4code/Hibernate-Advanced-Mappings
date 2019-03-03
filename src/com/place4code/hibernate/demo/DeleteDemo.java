package com.place4code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.place4code.hibernate.demo.entity.Instructor;
import com.place4code.hibernate.demo.entity.InstructorDetail;
import com.sun.org.apache.bcel.internal.generic.Instruction;

public class DeleteDemo {

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
			
			//get object by primary key
			int id = 1;
			Instructor temp = session.get(Instructor.class, id); //null = not found
			System.out.println("Object: " + temp);
			
			//delete object
			if (temp != null) {
				System.out.println("Deleting object...");
				//will delete also associated object, because of CascateType.ALL
				session.delete(temp);
			}

			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
		} finally {
			factory.close();
		}

	}

}
