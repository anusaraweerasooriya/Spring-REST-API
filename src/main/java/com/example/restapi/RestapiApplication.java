package com.example.restapi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@SpringBootApplication
public class RestapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestapiApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(StudentRepository repository){
		return args -> {
			Address address = new Address(
					"England",
					"London",
					"NE9"
			);
			String email ="anusar112a@gmail.com";
			Student student = new Student(
					 "Anusara",
						"Weerasooriya",
		 				email,
						Gender.FEMALE,
						address,
					List.of("Computer Science"),
					BigDecimal.TEN,
					LocalDateTime.now()
			);

			//usingMongoTemplateAndQuery(repository,mongoTemplate,student);
			repository.findStudentByEmail(email)
					.ifPresentOrElse(s -> {
						System.out.println(student + "already exists");
					}, ()->{
						System.out.println("Added the student" + student);
						repository.insert(student);
					});
		};
	}

//	private void usingMongoTemplateAndQuery(StudentRepository repository, MongoTemplate mongoTemplate, Student student){
//		Query query = new Query();
//		query.addCriteria(Criteria.where("email").is(email));
//
//		List<Student> students = mongoTemplate.find(query, Student.class);
//
//		if(students.size()>1){
//			throw new IllegalStateException("found many students with email" + email);
//		}
//
//		if(students.isEmpty()){
//			System.out.println("Added the student" + student);
//			repository.insert(student);
//		} else {
//			System.out.println(student + "already exists");
//		}
//
//
//	}
}
