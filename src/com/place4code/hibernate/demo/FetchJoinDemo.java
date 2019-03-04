package com.place4code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.place4code.hibernate.demo.entity.Course;
import com.place4code.hibernate.demo.entity.Instructor;
import com.place4code.hibernate.demo.entity.InstructorDetail;

public class FetchJoinDemo {

	public static void main(String[] args) {

		//create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml") //not required if name is hibernate.cfg.xml
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();
		
		//create a session
		Session session = factory.getCurrentSession();		

		try {
			//start transaction
			session.beginTransaction();

			//option 2 query with HQL
			Query<Instructor> query = 
					session .createQuery("SELECT i FROM Instructor i JOIN FETCH i.courses WHERE i.id=:instructorId"
					,Instructor.class);
			
			query.setParameter("instructorId", 1); //1 = id for example
			
			Instructor temp = query.getSingleResult();
			
			
			//commit transaction
			session.getTransaction().commit();
			
			session.close(); //for e.g. lazy loading
			System.out.println(temp.getCourses());
			
			System.out.println("Done!");
		} finally {
			session.close();
			factory.close();
		}

	}

}
