package com.place4code.hibernate.demo;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.place4code.hibernate.demo.entity.Course;
import com.place4code.hibernate.demo.entity.Instructor;
import com.place4code.hibernate.demo.entity.InstructorDetail;
import com.place4code.hibernate.demo.entity.Review;
import com.place4code.hibernate.demo.entity.Student;

public class DeleteLukiDemo {

	public static void main(String[] args) {

		//create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml") //not required if name is hibernate.cfg.xml
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		//create a session
		Session session = factory.getCurrentSession();		

		try {
			//start transaction
			session.beginTransaction();
			
			session.delete(session.get(Student.class, 1)); //1 for example to delete
			
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
		} finally {
			session.close();
			factory.close();
		}

	}

}
