package com.lilian;

import com.lilian.enums.Category;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.lilian.model.Course;
import com.lilian.repository.CourseRepository;

@SpringBootApplication
public class CrudSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringApplication.class, args);
	}

	@Bean
	CommandLineRunner initDataBase(CourseRepository courseRepository) {
		return args -> {
			courseRepository.deleteAll();

			Course c = new Course();
			c.setName("Angular");
			c.setCategory(Category.FRONT_END);

			courseRepository.save(c);

			Course c2 = new Course();
			c2.setName("Spring");
			c2.setCategory(Category.BACK_END);

			courseRepository.save(c2);
		};
	}
}
