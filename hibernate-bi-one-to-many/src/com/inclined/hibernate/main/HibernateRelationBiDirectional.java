package com.inclined.hibernate.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.inclined.hibernate.table.relations.Course;
import com.inclined.hibernate.table.relations.Instructor;
import com.inclined.hibernate.table.relations.InstructorDetail;

public class HibernateRelationBiDirectional {

	public static void main(String[] args) {
			
		// Session Start
		// Session, SessionFactory implements AutoClosable()
		// try with resources
		try(SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();
				Session session = factory.getCurrentSession();)
		{
			session.beginTransaction();
			
			Instructor instructor = new Instructor("Ashu01", "Tiw10", "ashu01@gmail.com");
			InstructorDetail instructorDetail = new InstructorDetail("inclinedscorpio01","TechSavvy10");
			
			instructor.setInstructorDetail(instructorDetail);
//			session.save(instructor);
			
			Course course = new Course("IT");
			Course course1 = new Course("Chem1");
			
//			session.save(course);
//			session.save(course1);
			
			instructor.addCourse(course);
			instructor.addCourse(course1);
			
			session.persist(instructor);
			
			// commit Transaction
			session.getTransaction().commit();	
		}
		// Always close the session (session.close), factory.close() won't close the session ( if finally is used )
		// , As to prevent leaks. And keep application running ! 
		
	}

}
