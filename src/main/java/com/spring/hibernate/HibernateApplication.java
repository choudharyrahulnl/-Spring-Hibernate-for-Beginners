package com.spring.hibernate;

import com.spring.hibernate.entities.Course;
import com.spring.hibernate.entities.Instructor;
import com.spring.hibernate.entities.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
@Slf4j
public class HibernateApplication implements CommandLineRunner {

	@PersistenceContext
	private EntityManager entityManager;

	public static void main(String[] args) {
		SpringApplication.run(HibernateApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {

		/**
		 *  ONE-TO-ONE RELATIONSHIP
		 *
		 *  ONE-TO-ONE UNIDIRECTIONAL
		 *  When we load Instructor we would like to get associated Instructor Detail
		 *
		 *   @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
		 *   @JoinColumn(name = "instructor_detail_id")
		 *   private InstructorDetail instructorDetail;
		 */

		/**
		 *  CREATE
		 */

//		InstructorDetail instructorDetail = InstructorDetail.builder()
//				.youtubeChannel("luv 2 code")
//				.hobby("learning new technologies")
//				.build();
//
//		// associate the objects
//		Instructor instructor = Instructor.builder()
//				.firstName("Chad")
//				.lastName("Darby")
//				.email("chad.darby@gmail.com")
//				.instructorDetail(instructorDetail)
//				.build();
//
//		// save instructor, this will also save instructor details because of CascadeType.ALL
//		log.info("Saving Instructor ...");
//		entityManager.persist(instructor);
//		log.info("Instructor saved to database...");


		/**
		 *  READ UniDirectional
		 *
		 * 	One to One has default type Eager, so it will fetch Instructor and it's associated Instructor Details
		 * 	If Instructor id is passed then Instructor Left Join Instructor Detail where both id match and instructor id = given id
		 *
		 *  One to One change fetch type from eager to lazy
		 *  If Instructor id is passed then it will only fetch Instructor
		 *
		 */

//		log.info("Fetch Instructor ...");
//		Instructor instructor = entityManager.find(Instructor.class, 1);
//		log.info(instructor.toString());
//		log.info(instructor.getInstructorDetail().toString());
//		log.info("Instructor fetched from database...");

		/**
		 *  UPDATE
		 *
		 *  update instructor, this will also update instructor details because of CascadeType.ALL
		 */
//		log.info("Update Instructor ...");
//		Instructor instructor = entityManager.find(Instructor.class, 1);
//		instructor.setEmail("chad.darby@luv2code.com");
//		instructor.getInstructorDetail().setYoutubeChannel("Luv2Code");
//		log.info("Instructor update in database...");

		/**
		 *  DELETE
		 *
		 *  delete instructor, this will also delete instructor details because of CascadeType.ALL
		 */

//		log.info("Delete Instructor ...");
//		Instructor instructor = entityManager.find(Instructor.class, 1);
//		if(instructor != null) {
//			entityManager.remove(instructor);
//		}
//		log.info("Instructor deleted from database...");



		/**
		 *  ONE TO ONE BIDIRECTIONAL
		 *  When we load Instructor we would like to get associated Instructor Detail
		 *  When we load Instructor Detail we would like to get associated Instructor
		 *
		 *   @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
		 *   @JoinColumn(name = "instructor_detail_id")
		 *   private InstructorDetail instructorDetail;
		 *
		 *   No need to update the SQL
		 *   Just need to update the Java Code for Instructor Detail for bi-directional mapping
		 *
		 *
		 */

		/**
		 *  CREATE
		 *
		 *  First we need to store the instructor details so that we can get the id
		 *  which we will use as a foreign key in instructor
		 */

//		InstructorDetail instructorDetail = InstructorDetail.builder()
//				.youtubeChannel("luv 2 code")
//				.hobby("learning new technologies")
//				.build();
//
//		// associate the objects
//		Instructor instructor = Instructor.builder()
//				.firstName("Chad")
//				.lastName("Darby")
//				.email("chad.darby@gmail.com")
//				.instructorDetail(instructorDetail)
//				.build();
//
//		// save instructor, this will also save instructor details because of CascadeType.ALL
//		log.info("Saving Instructor ...");
//		entityManager.persist(instructor);
//		log.info("Instructor saved to database...");


		/**
		 *  READ BiDirectional
		 *
		 * 	One to One has default type Eager, so it will fetch Instructor and it's associated Instructor Details and vice versa
		 * 	Note: In BiDirectional we need to add fetch = FetchType.EAGER on both side to achieve above behavior
		 * 	If Instructor id is passed then Instructor Left Join Instructor Detail where both id match and instructor id = given id
		 * 	If Instructor Detail id is passed then Instructor Detail Left Join Instructor where both id match and instructor detail id = given id
		 *
		 *  One to One change fetch type from eager to lazy
		 *  Note: In BiDirectional we need to add fetch = FetchType.LAZY on both side to achieve above behavior
		 *  If Instructor id is passed then it will only fetch Instructor
		 *  If InstructorDetail id is passed then it will query InstructorDetail & then it will query Instructor
		 *  Total 2 query are fired
		 *  Assuming this is because of mappedBy = "instructorDetail" present in InstructorDetail
		 *
		 *  If Instructor id is passed, and we do instructor.getInstructorDetail().getHobby();
		 *  then first it will query instructor, then it will query instructor detail and finally again it will query instructor
		 *  Total 3 query are fired
		 *
		 */

//		log.info("Fetch Instructor ...");
//		Instructor instructor = entityManager.find(Instructor.class, 1);
//		log.info(instructor.toString());
//		log.info(instructor.getInstructorDetail().getHobby());
//		log.info("Instructor fetched from database...");

//		log.info("Fetch Instructor Detail...");
//		InstructorDetail instructorDetail = entityManager.find(InstructorDetail.class, 1);
//		//log.info(instructorDetail.toString());
//		log.info("Instructor Detail fetched from database...");

		/**
		 *  DELETE
		 *
		 *  delete instructor detail, this will also delete instructor because of CascadeType.ALL
		 *
		 *  If we want to delete only Instructor Detail then change cascade type and remove CascadeType.REMOVE
		 *  Also we need to set instructorDetail.getInstructor().setInstructorDetail(null) to break bidirectional link
		 *  Otherwise we will get EntityNotFoundException
		 */

//		log.info("Delete Instructor Detail ...");
//		InstructorDetail instructorDetail = entityManager.find(InstructorDetail.class, 1);
//		if(instructorDetail != null) {
//			entityManager.remove(instructorDetail);
//		}
//		log.info("Instructor Detail deleted from database...");


		/**
		 *  ONE-TO-MANY RELATIONSHIP
		 *
		 *  ONE-TO-MANY BIDIRECTIONAL RELATIONSHIP
		 * 	Many Courses mapped to One Instructor
		 * 	One Instructor mapped to Many Courses
		 *
		 */

//		InstructorDetail instructorDetail = InstructorDetail.builder()
//				.youtubeChannel("luv 2 code")
//				.hobby("learning new technologies")
//				.build();
//
//		// associate the objects
//		Instructor instructor = Instructor.builder()
//				.firstName("Chad")
//				.lastName("Darby")
//				.email("chad.darby@gmail.com")
//				.instructorDetail(instructorDetail)
//				.build();
//
//		// save instructor & instructor detail
//		entityManager.persist(instructor);


		/**
		 * Fetch Instructor and Set some Courses
		 */
//		Instructor instructorEntity = entityManager.find(Instructor.class, 1);
//
//		Course angular = Course.builder()
//				.title("Angular")
//				.build();
//		Course spring = Course.builder()
//				.title("Spring")
//				.build();
//		Course hibernate = Course.builder()
//				.title("Hibernate")
//				.build();
//
//
//		// Dirty Check will add courses to the instructor in DB
//		instructorEntity.add(angular);
//		instructorEntity.add(spring);
//		instructorEntity.add(hibernate);
//
//		// save courses to the DB
//		entityManager.persist(angular);
//		entityManager.persist(spring);
//		entityManager.persist(hibernate);


		/**
		 * ONE-TO-MANY By Default LAZY
		 * Fetching Instructor will only fetch instructor, it will not fetch associated Courses
		 * If we fetch instructor.getCourses() then also it will not fetch associated Courses
		 *
		 * If we fetch instructor.getCourses().size() then it will fetch associated Courses
		 * If we fetch instructor.getCourses().toString() then it will fetch associated Courses
		 *
		 * ONE-TO-MANY fetch type change to FetchType.EAGER
		 * Fetching Instructor will fetch Instructor and it's associated Courses
		 */
		Instructor instructor = entityManager.find(Instructor.class, 1);
		log.info(instructor.toString());
		log.info(instructor.getCourses().toString());

	}
}
